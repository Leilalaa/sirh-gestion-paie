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

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	RemunerationEmployeRepository rer;
	@Autowired
	BulletinSalaireRepository bsr;
	@Autowired
	PeriodeRepository pr;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer") 
	public ModelAndView creerBulletin() {
		
		List<Periode> periodes = pr.findAll();
		List<RemunerationEmploye> remun = rer.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodes);
		mv.addObject("remunerationEmployes", remun);
		mv.addObject("bulletinSalaire", new BulletinSalaire());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer") 
	public String submit(@ModelAttribute("bulletinSalaire")BulletinSalaire bulletinSalaire) {
		
		bsr.save(bulletinSalaire);
        return "redirect:/index.html";
    }
		
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister") 
	public ModelAndView listerBulletin() {
		
		List<BulletinSalaire> bulletins = bsr.findAll();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("bulletinSalaire", bulletins);

		return mv;
	}

}
