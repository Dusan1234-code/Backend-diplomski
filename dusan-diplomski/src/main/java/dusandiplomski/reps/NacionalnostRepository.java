package dusandiplomski.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import dusandiplomski.jpa.Nacionalnost;

public interface NacionalnostRepository extends JpaRepository <Nacionalnost,Integer> {

	Collection<Nacionalnost> findByNazivContainingIgnoreCase(String naziv);
}
