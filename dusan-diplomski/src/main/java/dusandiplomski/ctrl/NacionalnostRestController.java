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

import dusandiplomski.jpa.Nacionalnost;
import dusandiplomski.reps.NacionalnostRepository;
import io.swagger.annotations.ApiOperation;

@RestController
public class NacionalnostRestController {
	
	
	@Autowired
	NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("nacionalnost") 
	public Collection<Nacionalnost> getAll(){
		return nacionalnostRepository.findAll();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("nacionalnost/{id}")
	public Nacionalnost getOne(@PathVariable("id") Integer id) {
		return nacionalnostRepository.getOne(id);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("nacionalnost/naziv/{naziv}")
	public Collection<Nacionalnost> getByNaziv(@PathVariable("naziv") String naziv){
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PostMapping("nacionalnost")
	public ResponseEntity<HttpStatus> addNacionalnost(@RequestBody Nacionalnost nacionalnost) {
		nacionalnostRepository.save(nacionalnost);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PutMapping("nacionalnost/{id}")
	public ResponseEntity<HttpStatus> updateNacionalnost(@RequestBody Nacionalnost nacionalnost, 
			@PathVariable("id")Integer id){
		if (nacionalnostRepository.existsById(id)) {
			nacionalnost.setId(id);
			nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@DeleteMapping("nacionalnost/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		
		if(id==-100) {
			jdbcTemplate.execute("INSERT INTO nacionalnost (\"id\", \"naziv\", \"skracenica\") VALUES (-100, 'Test Naziv', 'Test Skracenica')");
		}
		
		if (nacionalnostRepository.existsById(id)) {
			nacionalnostRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		
	}


}
