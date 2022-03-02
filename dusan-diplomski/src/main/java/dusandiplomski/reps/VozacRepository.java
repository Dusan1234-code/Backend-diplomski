package dusandiplomski.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dusandiplomski.jpa.Tim;
import dusandiplomski.jpa.Vozac;

public interface VozacRepository  extends JpaRepository <Vozac, Integer>{
	

	Collection <Vozac> findByImeContainingIgnoreCase(String ime);
	Collection<Vozac> findByTim(Tim t);
	
	@Query(value = "select coalesce(max(broj_vozaca)+1, 1) from vozac where tim = ?1", nativeQuery = true)
	Integer nextRBr(Integer timId);
}
