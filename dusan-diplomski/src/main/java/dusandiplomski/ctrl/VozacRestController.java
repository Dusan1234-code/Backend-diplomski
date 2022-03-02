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
import dusandiplomski.jpa.Vozac;
import dusandiplomski.reps.TimRepository;
import dusandiplomski.reps.VozacRepository;
import io.swagger.annotations.ApiOperation;

@RestController
public class VozacRestController {

	
	@Autowired
	private VozacRepository vozacRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TimRepository timRepository;
	
	
	@CrossOrigin
	@ApiOperation(value = "Returns collection of all Vozac from database.")
	@GetMapping("vozac")
	public Collection<Vozac> getAll() {
		return vozacRepository.findAll();
	}
	
	@CrossOrigin
	@ApiOperation(value = "Returns Vozac with id that was forwarded as path variable.")
	@GetMapping("vozac/{id}")
	public Vozac getOne(@PathVariable("id") Integer id) {
		return vozacRepository.getOne(id);
	}
	
	@ApiOperation(value = "Returns collection of Vozaci for Porudzbina with id that was forwarded as path variable.")
	@GetMapping("vozacitimova/{id}")
	public Collection<Vozac> getAllForTim(@PathVariable("id") Integer id){
		Tim t = timRepository.getOne(id);
		return vozacRepository.findByTim(t);
		
	}

	
	@CrossOrigin
	@ApiOperation(value = "Adds instance of Vozac to database.")
	@PostMapping("vozac")
	public ResponseEntity<HttpStatus> addOne(@RequestBody Vozac vozac){
		vozacRepository.save(vozac);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Updates Vozac that has id that was forwarded as path variable with values forwarded in Request Body.")
	@PutMapping("vozac/{id}")
	public ResponseEntity<HttpStatus> updateOne(@RequestBody Vozac vozac, @PathVariable("id") Integer id){
		if (!vozacRepository.existsById(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		vozac.setId(id);
		vozacRepository.save(vozac);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Deletes Vozac with id that was forwarded as path variable.")
	@DeleteMapping("vozac/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		if (id == -100 && !vozacRepository.existsById(-100)) {
            
            jdbcTemplate.execute("INSERT INTO vozac (\"id\", \"ime\", \"prezime\", \"datum_rodjenja\", \"broj_vozaca\", \"nacionalnost\", \"tim\") "
                    + "VALUES ('-100', 'Nico', 'Rosberg', to_date('03.03.1987.', 'dd.mm.yyyy.'), '84', '1', '1')");
        }
		
		if(vozacRepository.existsById(id)) {
            vozacRepository.deleteById(id); 
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
         
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
