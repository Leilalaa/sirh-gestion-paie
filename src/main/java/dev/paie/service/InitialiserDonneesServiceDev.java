package dev.paie.service;

import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;


@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private List<Entreprise> entreprises;
	@Autowired
	private List<Grade> grades;
	@Autowired
	private List<Cotisation> cotisations;
	@Autowired
	private List<ProfilRemuneration> profils;
	@Autowired
	private List<Utilisateur> utilisateurs;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	

	
	@Override
	@Transactional
	public void initialiser(){
		
		for (Utilisateur u: utilisateurs){
			
			u.setMotDePasse(this.passwordEncoder.encode(u.getMotDePasse()));
			
		}
		entreprises.stream().forEach(e -> em.persist(e));
		grades.stream().forEach(g -> em.persist(g));
		cotisations.stream().forEach(c -> em.persist(c));
		profils.stream().forEach(pr -> em.persist(pr));
		utilisateurs.stream().forEach(u -> em.persist(u));
		
		IntStream.rangeClosed(1, 12).forEach(i -> {
			Periode p = new Periode();
			p.setDateDebut(Year.now().atMonth(i).atDay(1));
			p.setDateFin(Year.now().atMonth(i).atEndOfMonth());
			em.persist(p);
		});

	}

}
