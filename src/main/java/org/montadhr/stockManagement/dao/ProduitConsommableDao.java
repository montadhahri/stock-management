package org.montadhr.stockManagement.dao;

import org.montadhr.stockManagement.entity.ProduitConsommable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitConsommableDao extends JpaRepository<ProduitConsommable, Long> {

}
