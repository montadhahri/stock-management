package org.montadhr.stockManagement.service;

import java.util.List;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Commande;
import org.montadhr.stockManagement.entity.Consommation;
import org.montadhr.stockManagement.entity.Operation;

public interface CommandeService {
	
	public List<Commande> getAllCommande();
	
	public List<Commande> getCommandesByArticle(Article article);
	
	public List<Article> getAllArticle();
	
	public Commande saveCommande(Commande commande);
	
	public void saveConsommation(Consommation consommation);
	
	public List<Operation> getOperationByArticle(Article article);
	
	public List<Article> getArticleByStockMin();

}
