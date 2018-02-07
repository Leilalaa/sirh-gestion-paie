package dev.paie.web.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	RemunerationEmployeRepository rer;
	@Autowired
	EntrepriseRepository er;
	@Autowired
	GradeRepository gr;
	@Autowired
	ProfilRemunerationRepository prr;

	
	@RequestMapping(method = RequestMethod.GET, path = "/creer") 
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {
		
		List<Entreprise> entreprises = er.findAll();
		List<Grade> grades = gr.findAll();
		List<ProfilRemuneration> profils = prr.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		mv.addObject("entreprise", entreprises);
		mv.addObject("grade", grades);
		mv.addObject("profil", profils);
		mv.addObject("employe", new RemunerationEmploye());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer") 
	@Secured("ROLE_ADMINISTRATEUR")
	public String submit(@ModelAttribute("employe")RemunerationEmploye employe) {
		
		LocalDateTime localDateTime = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / hh:mm");
        String format = localDateTime.format(formatter);
		employe.setDateCreation(format);
		rer.save(employe);
        return "redirect:/mvc/employes/lister.html";
    }
		
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister") 
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerEmploye() {
		
		List<RemunerationEmploye> remun = rer.findAll();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("remunerationemploye", remun);

		return mv;
	}


}
