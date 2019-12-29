package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Long> {

	@Query("select c from Commande c order by c.date")
	public List<Commande> getAllByDate();

	@Query("select c from Commande c where c.article.articleId=:x")
	public List<Commande> getAllByArticle(@Param("x") Long articleId);

}
