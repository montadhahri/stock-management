package org.montadhr.stockManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EQUIPEMENT")
public class Equipement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Machine machine;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="equipement")
	private List<SousEquipemet> sousEquipements; 
	
	
	

	@Override
	public String toString() {
		return name ;
	}

	public List<SousEquipemet> getSousEquipements() {
		if(sousEquipements == null){
			sousEquipements = new ArrayList<SousEquipemet>();
		}
		return sousEquipements;
	}

	public void setSousEquipements(List<SousEquipemet> sousEquipements) {
		this.sousEquipements = sousEquipements;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
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
