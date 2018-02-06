package dev.paie.web.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String submit(@ModelAttribute("employe")RemunerationEmploye employe) {
		
		rer.save(employe);
        return "redirect:/index.html";
    }
		
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister") 
	public ModelAndView listerEmploye() {
		
		List<RemunerationEmploye> remun = rer.findAll();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("remunerationemploye", remun);

		return mv;
	}


}
