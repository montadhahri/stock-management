package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.entity.SousEquipemet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SouEquipementDao extends JpaRepository<SousEquipemet, Long> {

	@Query("select s from SousEquipemet s where s.equipement.id =:x")
	public List<SousEquipemet> getSousEquipementByEquipement(@Param("x") Long equipementId);

	@Query("select s.equipement from SousEquipemet s where s.id=:x")
	public Equipement getEquipementBySousEquipement(@Param("x") Long sousEqId);
}
