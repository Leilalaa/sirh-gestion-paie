package dev.paie.web.controller;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

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
	@Autowired
	CalculerRemunerationServiceSimple crss;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer") 
	@Secured("ROLE_ADMINISTRATEUR")
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
	@Secured("ROLE_ADMINISTRATEUR")
	public String submit(@ModelAttribute("bulletinSalaire")BulletinSalaire bulletinSalaire) {
		
		LocalDateTime localDateTime = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / hh:mm");
        String format = localDateTime.format(formatter);
		bulletinSalaire.setDateCreation(format);
		bsr.save(bulletinSalaire);
        return "redirect:/mvc/bulletins/lister.html";
    }
		
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/lister") 
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerBulletin() {
		
		List<BulletinSalaire> bulletins = bsr.findAll();
		Map<BulletinSalaire, ResultatCalculRemuneration> bulletinCalcul = new HashMap<>();
		
		for(BulletinSalaire b: bulletins){
			
			ResultatCalculRemuneration resultat = crss.calculer(b);
			bulletinCalcul.put(b, resultat);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("bulletinCalcul", bulletinCalcul);
		mv.addObject("bulletinSalaire", bulletins);

		return mv;
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/voir") 
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView voirBulletin(@RequestParam("index") int i) {
		
		ModelAndView mv = new ModelAndView();
		BulletinSalaire bulletin = bsr.findOne(i+1);
		ResultatCalculRemuneration resultat = crss.calculer(bulletin);
		
		mv.setViewName("bulletins/voirBulletin");
		mv.addObject("bulletinSalaire", bulletin);
		mv.addObject("resultat", resultat);


		return mv;
	}

}
