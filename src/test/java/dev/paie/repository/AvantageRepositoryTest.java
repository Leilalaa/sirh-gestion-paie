package dev.paie.repository;

import javax.sql.DataSource;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private AvantageRepository avantageRepository;

	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		
		
	// sauvegarder un nouvel avantage
		Avantage avantage = new Avantage();
		
		avantage.setCode("ABC");
		avantage.setMontant(new BigDecimal("12.36"));
		avantage.setNom("Test");
		
		avantageRepository.save(avantage);
		
	// vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		assertTrue(avantageRepository.findOne(1).getCode().equals("ABC"));
						
	// modifier un avantage
		avantage.setCode("DEF");
		avantageRepository.save(avantage);
		
	
	// vérifier que les modifications sont bien prises en compte via la méthode findOne
		assertTrue(avantageRepository.findOne(1).getCode().equals("DEF"));
	}

}
