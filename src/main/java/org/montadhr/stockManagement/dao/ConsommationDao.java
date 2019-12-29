package org.montadhr.stockManagement.dao;

import org.montadhr.stockManagement.entity.Consommation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsommationDao extends JpaRepository<Consommation, Long> {

}
