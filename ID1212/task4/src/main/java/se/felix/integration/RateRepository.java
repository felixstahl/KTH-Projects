package se.felix.integration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface RateRepository extends JpaRepository<RateDTO, Integer> {

	//@Query(value="SELECT rate FROM converter", nativeQuery=true)
	//List<String> getRate();
	
}