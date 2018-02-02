package dev.paie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;


public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	
	private RemunerationEmploye remun;
	@Autowired
	private Entreprise etp;
	@Autowired
	private Grade grade;
	@Autowired
	private ProfilRemuneration profil;
	@Autowired
	private List<Cotisation> coti;
	

	
	@Override
	public void initialiser(){
		
		remun.setMatricule("TEST");
		
		remun.setEntreprise(etp);
		
		remun.setProfilRemuneration(profil);
		
		remun.setGrade(grade);
		
		
		
	}

}
