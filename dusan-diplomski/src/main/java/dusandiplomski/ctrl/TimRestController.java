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

import dusandiplomski.jpa.Tim;
import dusandiplomski.reps.TimRepository;
import io.swagger.annotations.ApiOperation;

@RestController
public class TimRestController {

	@Autowired
	private TimRepository timRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("tim")
	public Collection<Tim> getTim() {
		return timRepository.findAll();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@GetMapping("tim/{id}")
	public Tim getPorudzbina(@PathVariable("id") Integer id) {
		return timRepository.getOne(id);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PostMapping("tim")
	public ResponseEntity<HttpStatus> addOne(@RequestBody Tim tim){
		timRepository.save(tim);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@PutMapping("tim/{id}")
	public ResponseEntity<HttpStatus> update(@RequestBody Tim tim, @PathVariable("id") Integer id){
		if(timRepository.existsById(id)) {
			tim.setId(id);
			timRepository.save(tim);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Artikl from database.")
	@DeleteMapping("tim/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		
		if (id == -100 && !timRepository.existsById(-100)) {
			
			jdbcTemplate.execute("INSERT INTO tim (\"id\", \"naziv\", \"broj_zaposlenih\", \"vlasnik\", \"proizvodjac\") "
					+ "VALUES ('-100', 'Ferarri1', '58', 'Maranello', '3')");
		}
		
		if(timRepository.existsById(id)) {
			timRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		
	}
}
