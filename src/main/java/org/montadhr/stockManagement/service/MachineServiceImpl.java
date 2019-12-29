package org.montadhr.stockManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.montadhr.stockManagement.dao.ArticleDao;
import org.montadhr.stockManagement.dao.EquipementDao;
import org.montadhr.stockManagement.dao.MachineDao;
import org.montadhr.stockManagement.dao.PieceDao;
import org.montadhr.stockManagement.dao.SouEquipementDao;
import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.entity.Machine;
import org.montadhr.stockManagement.entity.Piece;
import org.montadhr.stockManagement.entity.SousEquipemet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineDao machineDao;

	@Autowired
	private EquipementDao equipementDao;

	@Autowired
	private SouEquipementDao souEquipementDao;

	@Autowired
	private PieceDao pieceDao;

	@Autowired
	private ArticleDao articleDao;

	@Override
	@Transactional
	public void saveMachine(Machine machine) {
		machineDao.save(machine);
	}

	@Override
	public List<Machine> getAllMachines() {
		return machineDao.findAll();
	}

	@Override
	@Transactional
	public void saveEquipement(Equipement equipement) {
		equipementDao.save(equipement);
	}

	@Override
	@Transactional
	public void saveSousEquipement(SousEquipemet souEquipement) {
		souEquipementDao.save(souEquipement);
	}

	@Override
	public List<Equipement> getEquipementByMachine(Machine machine) {
		return equipementDao.getEquipementByMachine(machine.getId());
	}

	@Override
	public List<SousEquipemet> getSousEquipementByEquipement(Equipement equipemnt) {
		return souEquipementDao.getSousEquipementByEquipement(equipemnt.getId());
	}

	@Override
	public Machine getMachineById(Long id) {
		return machineDao.getOne(id);
	}

	@Override
	public Equipement getEquipementById(Long id) {
		return equipementDao.getOne(id);
	}

	@Override
	public SousEquipemet getSousEquipementById(Long id) {
		return souEquipementDao.getOne(id);
	}

	@Override
	public Machine getMachineByEquipement(Equipement equipement) {
		return equipementDao.getMachineByEquipement(equipement.getId());
	}

	@Override
	public Equipement getEquipementBySousEquipement(SousEquipemet sousEquipemnt) {
		return souEquipementDao.getEquipementBySousEquipement(sousEquipemnt.getId());
	}

	@Override
	public List<Piece> getPieceBySousEquipement(SousEquipemet sousEquipement) {
		return pieceDao.getPieceBySousEquipement(sousEquipement.getId());
	}

	@Override
	@Transactional
	public void savePiece(Piece piece) {
		pieceDao.save(piece);
	}
	
	@Override
	@Transactional
	public Article saveArticle(Article article) {
		return articleDao.save(article);
	}

	@Override
	public Article getArticleByName(String name) {
		Article article = articleDao.getArticleByName(name);
		return article;
	}

	@Override
	@Transactional
	public void deletePiece(Piece piece) {
		pieceDao.delete(piece);
		
	}
	
	

}
