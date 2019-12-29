package org.montadhr.stockManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.montadhr.stockManagement.dao.PrevisionnelleDao;
import org.montadhr.stockManagement.dao.ProduitConsommableDao;
import org.montadhr.stockManagement.dao.SalarieDao;
import org.montadhr.stockManagement.entity.Previsionnelle;
import org.montadhr.stockManagement.entity.ProduitConsommable;
import org.montadhr.stockManagement.entity.Salarie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduitConsommableServiceImpl implements ProduitConsommableService {

	@Autowired
	private ProduitConsommableDao produitDao;
	
	@Autowired
	private PrevisionnelleDao previsionnelleDao;
	
	@Autowired
	private SalarieDao salarieDao;

	@Override
	@Transactional
	public void addProduit(ProduitConsommable produit) {
		produitDao.save(produit);
	}

	@Override
	public List<ProduitConsommable> getAll() {
		return produitDao.findAll();
	}



	@Override
	@Transactional
	public Previsionnelle addPrevionnelle(Previsionnelle previonnelle) {
		return previsionnelleDao.save(previonnelle);
	}

	@Override
	public List<Previsionnelle> getAllPrevisionnelleByProduit(ProduitConsommable produit) {
		return previsionnelleDao.getPrevisionnelleByProduit(produit.getId());
	}

	@Override
	public int getNumberPrevisionnelle(ProduitConsommable produit) {
		return previsionnelleDao.getPrevisionnelleByProduit(produit.getId()).size();
	}

	@Override
	public Previsionnelle getPrevisionnelleByNumber(int number,ProduitConsommable produit) {
		return previsionnelleDao.getPrevisionnelleByNumber(number,produit.getId());
	}

	@Override
	public Double getEalm(ProduitConsommable produit) {
		List<Previsionnelle> list = getAllPrevisionnelleByProduit(produit);
		Double ealm = 0d;
		for (Previsionnelle previsionnelle : list) {
			if(previsionnelle.getEt() != null){
				ealm += previsionnelle.getEt();	
			}
		}
		return ealm / 12;
	}

	@Override
	public Double getPrevision(ProduitConsommable produit) {
		return getFinalPrevisionnelle(produit).getPt() + getEalm(produit);
	}

	@Override
	public Previsionnelle getFinalPrevisionnelle(ProduitConsommable produit) {
		List<Previsionnelle> list = getAllPrevisionnelleByProduit(produit);
		Previsionnelle previsionnelleFinal = new Previsionnelle();
		previsionnelleFinal.setProduit(produit);
		previsionnelleFinal.setNumber(list.size() + 1);
		previsionnelleFinal.setPt(0.23 * list.get(list.size() - 1).getDt() + 0.77 * list.get(list.size() - 1).getPt());
		return previsionnelleFinal;
	}

	@Override
	@Transactional
	public void addSalarie(Salarie salarie) {
		this.salarieDao.save(salarie);
		
	}

	@Override
	@Transactional
	public void deleteSalarie(Salarie salarie) {
		this.salarieDao.delete(salarie);
	}

	@Override
	public List<Salarie> getAllSalarie() {
		return this.salarieDao.getAllSalaries();
	}
	
	

}
