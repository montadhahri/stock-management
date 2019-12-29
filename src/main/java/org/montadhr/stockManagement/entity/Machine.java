package org.montadhr.stockManagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MACHINE")
public class Machine implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MACHINE_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="machine")
	private List<Equipement> equipements;

	
	
	
	public List<Equipement> getEquipements() {
		if(equipements == null){
			equipements = new ArrayList<>();
		}
		return equipements;
	}

	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

	@Override
	public String toString() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
