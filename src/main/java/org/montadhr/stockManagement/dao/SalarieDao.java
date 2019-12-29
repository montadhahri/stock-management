package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarieDao extends JpaRepository<Salarie, Long> {

	@Query("select s from Salarie s")
	public List<Salarie> getAllSalaries();

}
