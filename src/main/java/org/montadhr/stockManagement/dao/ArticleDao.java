package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {
	
	@Query("select a from Article a where a.name=:x")
	public Article getArticleByName(@Param("x")String name);
	
	@Query("select a from Article a where a.stock <= a.stockMin")
	public List<Article> getArticleBySecuriteStock();

}
