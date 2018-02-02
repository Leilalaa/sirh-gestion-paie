package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.paie.entite.Avantage;

@Service
public interface AvantageRepository extends JpaRepository<Avantage, Integer> {
	

	
}