package dusandiplomski.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the tim database table.
 * 
 */
@Entity
@NamedQuery(name="Tim.findAll", query="SELECT t FROM Tim t")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIM_ID_GENERATOR", sequenceName="TIM_SEQ",allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIM_ID_GENERATOR")
	private Integer id;

	@Column(name="broj_zaposlenih")
	private Integer brojZaposlenih;

	private String naziv;

	private String vlasnik;

	//bi-directional many-to-one association to Proizvodjac
	@ManyToOne
	@JoinColumn(name="proizvodjac")
	private Proizvodjac proizvodjac;

	//bi-directional many-to-one association to Vozac
	@OneToMany(mappedBy="tim")
	@JsonIgnore
	private List<Vozac> vozacs;

	public Tim() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojZaposlenih() {
		return this.brojZaposlenih;
	}

	public void setBrojZaposlenih(Integer brojZaposlenih) {
		this.brojZaposlenih = brojZaposlenih;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getVlasnik() {
		return this.vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<Vozac> getVozacs() {
		return this.vozacs;
	}

	public void setVozacs(List<Vozac> vozacs) {
		this.vozacs = vozacs;
	}

	public Vozac addVozac(Vozac vozac) {
		getVozacs().add(vozac);
		vozac.setTim(this);

		return vozac;
	}

	public Vozac removeVozac(Vozac vozac) {
		getVozacs().remove(vozac);
		vozac.setTim(null);

		return vozac;
	}

}