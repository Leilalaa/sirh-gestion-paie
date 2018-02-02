package dev.paie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;

//TODO compléter la configuration
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private DataSource dataSource;

	@Before
	public void setup(){
		 new JdbcTemplate(dataSource).update("TRUNCATE TABLE grade");
	}
	
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