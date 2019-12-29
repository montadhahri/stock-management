package org.montadhr.stockManagement.dao;

import java.util.List;

import org.montadhr.stockManagement.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {

	@Query("select c from Operation c where c.article.articleId=:x  order by c.date")
	public List<Operation> getAllByArticle(@Param("x") Long articleId);

}
