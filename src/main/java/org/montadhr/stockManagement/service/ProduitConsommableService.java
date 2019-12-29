package org.montadhr.stockManagement.service;

import java.util.List;

import org.montadhr.stockManagement.entity.Previsionnelle;
import org.montadhr.stockManagement.entity.ProduitConsommable;
import org.montadhr.stockManagement.entity.Salarie;

public interface ProduitConsommableService {
	
	
	public void addProduit(ProduitConsommable produit);
	public List<ProduitConsommable> getAll();
	public Previsionnelle addPrevionnelle(Previsionnelle previonnelle);
	public List<Previsionnelle> getAllPrevisionnelleByProduit(ProduitConsommable produit);
	public int getNumberPrevisionnelle(ProduitConsommable produit);
	public Previsionnelle getPrevisionnelleByNumber(int number,ProduitConsommable produit);
	public Double getEalm(ProduitConsommable produit);
	public Double getPrevision(ProduitConsommable produit);
	public Previsionnelle getFinalPrevisionnelle(ProduitConsommable produit);
	
	
	public void addSalarie(Salarie salarie);
	public void deleteSalarie(Salarie salarie);
	public List<Salarie> getAllSalarie();

}
