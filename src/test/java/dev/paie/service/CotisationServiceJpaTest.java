package dev.paie.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { ServicesConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CotisationServiceJpaTest {
	
	@Autowired 
	private CotisationService cotisationService;
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// sauvegarder une nouvelle cotisation
		Cotisation cot = new Cotisation();
		
		cot.setCode("TST");
		cot.setLibelle("Test");
		cot.setTauxPatronal(new BigDecimal("2.36"));
		cot.setTauxSalarial(new BigDecimal("1.72"));
		cotisationService.sauvegarder(cot);
		
	// vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		List<Cotisation> listeCoti = new ArrayList();
		listeCoti = cotisationService.lister();
		assert listeCoti.stream()
						.filter(c -> c.getCode().equals("TST"))
						.findAny()
						.isPresent();

		
	// modifier une cotisation
		cot.setCode("HAH");
		cotisationService.mettreAJour(cot);
		
		listeCoti.clear();
		listeCoti.addAll(cotisationService.lister());
	// vérifier que les modifications sont bien prises en compte via la méthode lister
	
		assert listeCoti.stream()
						.filter(c -> c.getCode().equals("HAH"))
						.findAny()
						.isPresent();
	}

}
