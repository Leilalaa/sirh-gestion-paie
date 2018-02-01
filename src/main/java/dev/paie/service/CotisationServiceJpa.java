package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;


@Service
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void sauvegarder(Cotisation nouvelleCotisation) {

		em.persist(nouvelleCotisation);

	}

	@Override
	@Transactional
	public void mettreAJour(Cotisation cotisation) {
		
		Cotisation c = em.find(Cotisation.class, cotisation.getId());

		c.setLibelle(cotisation.getLibelle());
		c.setTauxPatronal(cotisation.getTauxPatronal());
		c.setTauxSalarial(cotisation.getTauxSalarial());
		c.setCode(cotisation.getCode());

	}

	@Override
	@Transactional
	public List<Cotisation> lister() {

		List<Cotisation> cotisations = new ArrayList();

		TypedQuery<Cotisation> query = em.createQuery("from Cotisation", Cotisation.class);

		for (Cotisation c : query.getResultList()) {

			cotisations.add(c);

		}

		return cotisations;
	}

}
