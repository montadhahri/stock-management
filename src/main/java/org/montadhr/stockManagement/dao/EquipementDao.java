package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementDao extends JpaRepository<Equipement, Long> {

	@Query("select e from Equipement e where e.machine.id =:x")
	public List<Equipement> getEquipementByMachine(@Param("x")Long machineId);
	
	@Query("select e.machine from Equipement e where e.id=:x")
	public Machine getMachineByEquipement(@Param("x")Long EquipementId);
}
