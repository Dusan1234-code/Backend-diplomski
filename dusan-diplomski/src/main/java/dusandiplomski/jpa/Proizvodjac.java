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
 * The persistent class for the proizvodjac database table.
 * 
 */
@Entity
@NamedQuery(name="Proizvodjac.findAll", query="SELECT p FROM Proizvodjac p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proizvodjac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVODJAC_ID_GENERATOR", sequenceName="PROIZVODJAC_SEQ",allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVODJAC_ID_GENERATOR")
	private Integer id;

	private String mesto;

	private String naziv;

	private Integer osnovan;

	//bi-directional many-to-one association to Tim
	@OneToMany(mappedBy="proizvodjac")
	@JsonIgnore
	private List<Tim> tims;

	public Proizvodjac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMesto() {
		return this.mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getOsnovan() {
		return this.osnovan;
	}

	public void setOsnovan(Integer osnovan) {
		this.osnovan = osnovan;
	}

	public List<Tim> getTims() {
		return this.tims;
	}

	public void setTims(List<Tim> tims) {
		this.tims = tims;
	}

	public Tim addTim(Tim tim) {
		getTims().add(tim);
		tim.setProizvodjac(this);

		return tim;
	}

	public Tim removeTim(Tim tim) {
		getTims().remove(tim);
		tim.setProizvodjac(null);

		return tim;
	}

}