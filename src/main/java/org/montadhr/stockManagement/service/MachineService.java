package org.montadhr.stockManagement.service;

import java.util.List;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.entity.Machine;
import org.montadhr.stockManagement.entity.Piece;
import org.montadhr.stockManagement.entity.SousEquipemet;

public interface MachineService {

	public void saveMachine(Machine machine);

	public List<Machine> getAllMachines();

	public void saveEquipement(Equipement equipement);

	public void saveSousEquipement(SousEquipemet souEquipement);

	public Machine getMachineByEquipement(Equipement equipement);

	public Equipement getEquipementBySousEquipement(SousEquipemet sousEquipemnt);

	public List<Equipement> getEquipementByMachine(Machine machine);

	public List<SousEquipemet> getSousEquipementByEquipement(Equipement equipemnt);

	public Machine getMachineById(Long id);

	public Equipement getEquipementById(Long id);

	public SousEquipemet getSousEquipementById(Long id);

	public List<Piece> getPieceBySousEquipement(SousEquipemet sousEquipement);
	
	public void savePiece(Piece piece);
	
	public Article saveArticle(Article article);
	
	public void deletePiece(Piece piece);
	
	public Article getArticleByName(String name);

}
