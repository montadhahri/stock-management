package org.montadhr.stockManagement.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "CM")
public class Commande extends Operation {

	@Transient
	private String articleName;

	@Column(name = "UNIT_PRICE")
	private Double prixUnit;

	@Column(name = "BANDE_LIVRAISON")
	private Integer bandeLivraison;

	@Column(name = "FOURNISSEUR")
	private String fournisseur;

	@Transient
	private Double prixTotal;

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fourniseur) {
		this.fournisseur = fourniseur;
	}

	public Integer getBandeLivraison() {
		return bandeLivraison;
	}

	public void setBandeLivraison(Integer bandeLivraison) {
		this.bandeLivraison = bandeLivraison;
	}

	public Double getPrixUnit() {
		if (prixUnit == null) {
			prixUnit = super.getArticle().getUnitPrice();
		}
		return prixUnit;
	}

	public void setPrixUnit(Double prixUnit) {
		this.prixUnit = prixUnit;
	}

	public Double getPrixTotal() {
		prixTotal = super.getQuantite() * super.getArticle().getUnitPrice();
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

}
