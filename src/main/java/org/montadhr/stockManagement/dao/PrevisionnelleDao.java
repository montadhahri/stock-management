package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Previsionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisionnelleDao extends JpaRepository<Previsionnelle, Long> {

	@Query("select p from Previsionnelle p where p.produit.id =:x order by p.number")
	public List<Previsionnelle> getPrevisionnelleByProduit(@Param("x") Long produitId);
	
	@Query("select p from Previsionnelle p where p.number =:x and p.produit.id =:y")
	public Previsionnelle getPrevisionnelleByNumber(@Param("x") int number,@Param("y") Long produitId);

}
