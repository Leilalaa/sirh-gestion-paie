package dev.paie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;


//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;
	
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
	//sauvegarder un nouveau grade
		Grade g = new Grade();
		
		g.setCode("123");
		g.setNbHeuresBase(new BigDecimal("26.36"));
		g.setTauxBase(new BigDecimal("26.36"));
		
		gradeService.sauvegarder(g);
		
	// vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		
		List<Grade> listeGrade = new ArrayList();
		listeGrade = gradeService.lister();
		assert listeGrade.stream()
						.filter(c -> c.getCode().equals("123"))
						.findAny()
						.isPresent();
		
	// modifier un grade
		g.setCode("456");
		g.setId(1);
		gradeService.mettreAJour(g);
		
		listeGrade.clear();
		listeGrade.addAll(gradeService.lister());
		
	// vérifier que les modifications sont bien prises en compte via la méthode lister
		assert listeGrade.stream()
		.filter(c -> c.getCode().equals("456"))
		.findAny()
		.isPresent();
	}
}