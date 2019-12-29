package org.montadhr.stockManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PREVISIONNELLE")
public class Previsionnelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMBER_PREVISIONNELLE")
	private int number;

	@Column(name = "DT")
	private Double dt;

	@Column(name = "PT")
	private Double pt;

	@Column(name = "ET")
	private Double et;

	@ManyToOne(fetch = FetchType.LAZY)
	private ProduitConsommable produit;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDt() {
		return dt;
	}

	public void setDt(Double dt) {
		this.dt = dt;
	}

	public Double getPt() {
		return pt;
	}

	public void setPt(Double pt) {
		this.pt = pt;
	}

	public Double getEt() {
		return et;
	}

	public void setEt(Double et) {
		this.et = et;
	}

	public ProduitConsommable getProduit() {
		return produit;
	}

	public void setProduit(ProduitConsommable produit) {
		this.produit = produit;
	}

}
