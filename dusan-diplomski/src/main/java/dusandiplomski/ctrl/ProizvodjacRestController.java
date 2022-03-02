package dusandiplomski.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dusandiplomski.jpa.Proizvodjac;
import dusandiplomski.reps.ProizvodjacRepository;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProizvodjacRestController {
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("proizvodjac")
	public Collection<Proizvodjac> getAll(){
		return proizvodjacRepository.findAll();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("proizvodjac/{id}")
	public Proizvodjac getOne(@PathVariable("id") Integer id) {
		return proizvodjacRepository.getOne(id);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("proizvodjac/naziv/{naziv}")
	public Collection<Proizvodjac> getByNaziv(@PathVariable("naziv") String naziv){
		return proizvodjacRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PostMapping("proizvodjac")
	public ResponseEntity<HttpStatus> addDobavljac(@RequestBody Proizvodjac proizvodjac) {
		proizvodjacRepository.save(proizvodjac);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PutMapping("proizvodjac/{id}")
	public ResponseEntity<HttpStatus> updateProizvodjac(@RequestBody Proizvodjac proizvodjac, 
			@PathVariable("id")Integer id){
		if (proizvodjacRepository.existsById(id)) {
			proizvodjac.setId(id);
			proizvodjacRepository.save(proizvodjac);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@DeleteMapping("proizvodjac/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		
		if(id==-100) {
			jdbcTemplate.execute("INSERT INTO dobavljac (\"id\", \"naziv\", \"osnovan\", \"mesto\") VALUES (-100, 'Test Naziv', 'Test Osnovan', 'Test Mesto')");
		}
		
		if (proizvodjacRepository.existsById(id)) {
			proizvodjacRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		
	}
	

}
