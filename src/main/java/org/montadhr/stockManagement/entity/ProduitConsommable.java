package org.montadhr.stockManagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PRODUIT_CONSOMMABLE")
public class ProduitConsommable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMPIECE")
	private String nameProduit;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit")
	private List<Previsionnelle> previsionnelle; 
	
	@Transient
	private Double ealm;
	
	@Transient
	private Double prevision;
	
	

	public Double getEalm() {
		return ealm;
	}

	public void setEalm(Double ealm) {
		this.ealm = ealm;
	}

	public Double getPrevision() {
		return prevision;
	}

	public void setPrevision(Double prevision) {
		this.prevision = prevision;
	}

	public List<Previsionnelle> getPrevisionnelle() {
		return previsionnelle;
	}

	public void setPrevisionnelle(List<Previsionnelle> previsionnelle) {
		this.previsionnelle = previsionnelle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameProduit() {
		return nameProduit;
	}

	public void setNameProduit(String nameProduit) {
		this.nameProduit = nameProduit;
	}

	@Override
	public String toString() {
		return  nameProduit;
	}
	
	

}
