package dusandiplomski.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the nacionalnost database table.
 * 
 */
@Entity
@NamedQuery(name="Nacionalnost.findAll", query="SELECT n FROM Nacionalnost n")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Nacionalnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NACIONALNOST_ID_GENERATOR", sequenceName="NACIONALNOST_SEQ",allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NACIONALNOST_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String skracenica;

	//bi-directional many-to-one association to Vozac
	@OneToMany(mappedBy="nacionalnost")
	@JsonIgnore 
	private List<Vozac> vozacs;

	public Nacionalnost() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSkracenica() {
		return this.skracenica;
	}

	public void setSkracenica(String skracenica) {
		this.skracenica = skracenica;
	}

	public List<Vozac> getVozacs() {
		return this.vozacs;
	}

	public void setVozacs(List<Vozac> vozacs) {
		this.vozacs = vozacs;
	}

	public Vozac addVozac(Vozac vozac) {
		getVozacs().add(vozac);
		vozac.setNacionalnost(this);

		return vozac;
	}

	public Vozac removeVozac(Vozac vozac) {
		getVozacs().remove(vozac);
		vozac.setNacionalnost(null);

		return vozac;
	}

}