package dev.paie.service;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {
	
	@Autowired
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		ResultatCalculRemuneration r = new ResultatCalculRemuneration();
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		BigDecimal salaireBase = (grade.getNbHeuresBase()).multiply(grade.getTauxBase());
		
		

		
		r.setSalaireBrut(salaireBase+paieUtils.formaterBigDecimal(bulletin.getPrimeExceptionnelle()));
		r.setTotalRetenueSalarial(calculRetSal(r,bulletin).toString());
		r.setTotalCotisationsPatronales(calculCotPat(r,bulletin).toString());
		String netImpo = (new BigDecimal(r.getSalaireBrut()).subtract(new BigDecimal(r.getTotalRetenueSalarial()))).toString();
		r.setNetImposable(netImpo);
		r.setNetAPayer((new BigDecimal(r.getNetImposable())).subtract(imposable(r,bulletin)).toString());
		
		return r;
		
			
	}
	
	
	// Calcul de la retenue salariale selon la formule fournie dans le tp
	public BigDecimal calculRetSal(ResultatCalculRemuneration rem, BulletinSalaire bulletin){

		
		return bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
	            .filter(c -> c.getTauxSalarial()!=null)
	            .map(c -> c.getTauxSalarial().multiply(new BigDecimal(rem.getSalaireBrut())))
	            .reduce((a,b) -> a.add(b)).get();
		
	}
	
	// Calcul des cotisations patronales selon la formule fournie dans le tp
	public BigDecimal calculCotPat(ResultatCalculRemuneration rem, BulletinSalaire bulletin){
		
		
		return bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
	            .filter(c -> c.getTauxPatronal()!=null)
	            .map(c -> c.getTauxPatronal().multiply(new BigDecimal(rem.getSalaireBrut())))
	            .reduce((a,b) -> a.add(b)).get();
		
	}
	
	// Fonction faisant la somme du taux salarial des cotisations imposables multiplié par le salaire brut pour pouvoir calculer par la suite le net a payer
	public BigDecimal imposable(ResultatCalculRemuneration rem, BulletinSalaire bulletin){
		
		
		return bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables().stream()
	            .filter(c -> c.getTauxSalarial()!=null)
	            .map(c -> c.getTauxSalarial().multiply(new BigDecimal(rem.getSalaireBrut())))
	            .reduce((a,b) -> a.add(b)).get();
		
	}

}
