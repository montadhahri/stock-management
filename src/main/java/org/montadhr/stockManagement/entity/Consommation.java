package org.montadhr.stockManagement.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CO")
public class Consommation extends Operation {
	@Column(name="Equipement")
	private String equipement;
	
	

	public String getEquipement() {
		return equipement;
	}



	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}



	public Consommation() {
		super();
	}
	
	

}
