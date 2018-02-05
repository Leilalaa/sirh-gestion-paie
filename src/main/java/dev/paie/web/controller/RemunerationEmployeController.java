package dev.paie.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer") 
	public ModelAndView creerEmploye() {
		
		List<Entreprise> entreprises = em.createQuery("from Entreprise", Entreprise.class).getResultList();
		List<Grade> grades = em.createQuery("from Grade", Grade.class).getResultList();
		List<ProfilRemuneration> profils = em.createQuery("from ProfilRemuneration", ProfilRemuneration.class).getResultList();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		mv.addObject("entreprise", entreprises);
		mv.addObject("grade", grades);
		mv.addObject("profil", profils);
		return mv;
	}
}
