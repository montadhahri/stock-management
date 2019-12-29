package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceDao extends JpaRepository<Piece, Long> {
	
	@Query("select p from Piece p where p.sousEquipement.id=:x")
	public List<Piece> getPieceBySousEquipement(@Param("x")Long sousEqId);

}
