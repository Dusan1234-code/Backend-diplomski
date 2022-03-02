package dusandiplomski.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the vozac database table.
 * 
 */
@Entity
@NamedQuery(name="Vozac.findAll", query="SELECT v FROM Vozac v")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vozac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VOZAC_ID_GENERATOR", sequenceName="VOZAC_SEQ",allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOZAC_ID_GENERATOR")
	private Integer id;

	@Column(name="broj_vozaca")
	private Integer brojVozaca;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_rodjenja")
	private Date datumRodjenja;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Nacionalnost
	@ManyToOne
	@JoinColumn(name="nacionalnost")
	private Nacionalnost nacionalnost;

	//bi-directional many-to-one association to Tim
	@ManyToOne
	@JoinColumn(name="tim")
	private Tim tim;

	public Vozac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojVozaca() {
		return this.brojVozaca;
	}

	public void setBrojVozaca(Integer brojVozaca) {
		this.brojVozaca = brojVozaca;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Nacionalnost getNacionalnost() {
		return this.nacionalnost;
	}

	public void setNacionalnost(Nacionalnost nacionalnost) {
		this.nacionalnost = nacionalnost;
	}

	public Tim getTim() {
		return this.tim;
	}

	public void setTim(Tim tim) {
		this.tim = tim;
	}

}