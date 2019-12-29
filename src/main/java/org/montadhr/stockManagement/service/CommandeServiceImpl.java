package org.montadhr.stockManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.montadhr.stockManagement.dao.ArticleDao;
import org.montadhr.stockManagement.dao.CommandeDao;
import org.montadhr.stockManagement.dao.ConsommationDao;
import org.montadhr.stockManagement.dao.OperationDao;
import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Commande;
import org.montadhr.stockManagement.entity.Consommation;
import org.montadhr.stockManagement.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceImpl implements CommandeService {
	
	@Autowired
	private CommandeDao commandeDao;
	
	@Autowired
	private ConsommationDao consommationDao;
	
	@Autowired
	private OperationDao operationDao;
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public List<Commande> getAllCommande() {
		return commandeDao.getAllByDate();
	}

	@Override
	public List<Article> getAllArticle() {
		return articleDao.findAll();
	}

	@Override
	@Transactional
	public Commande saveCommande(Commande commande) {
		return commandeDao.save(commande);
	}

	@Override
	public List<Commande> getCommandesByArticle(Article article) {
		return commandeDao.getAllByArticle(article.getArticleId());
	}

	@Override
	@Transactional
	public void saveConsommation(Consommation consommation) {
		consommationDao.save(consommation);
	}

	@Override
	public List<Operation> getOperationByArticle(Article article) {
		return operationDao.getAllByArticle(article.getArticleId());
	}

	@Override
	public List<Article> getArticleByStockMin() {
		return articleDao.getArticleBySecuriteStock();
	}
	
	

}
