package org.montadhr.stockManagement.controllers;

import java.util.Date;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Consommation;
import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.service.CommandeService;
import org.montadhr.stockManagement.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class ChangeArticleController {
	

	@Autowired
	private CommandeService commandeService;

	private Article article;
	private int nbarticle;
	private Equipement equipement;
	
	
	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setNbarticle(int nbarticle) {
		this.nbarticle = nbarticle;
	}

	@Autowired
	private MachineService machineService;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;

	@FXML
	private TextField nmbrePiece;

	@FXML
	private Text errorText;

	@FXML
	protected void handleSubmitSaveAction(ActionEvent event) {
		try {
			int nbPiece = Integer.parseInt(nmbrePiece.getText());
			if (nbPiece > nbarticle || nbPiece <=0) {
				errorText.setText("erreur donnes saisie");
			} else if(nbPiece > article.getStock()){
				errorText.setText("stock insufisant");
			}else{
				article.setStock(article.getStock() - nbPiece);
				machineService.saveArticle(article);
				Consommation consommation = new Consommation();
				consommation.setArticle(article);
				consommation.setQuantite(nbPiece);
				consommation.setDate(new Date());
				consommation.setEquipement(equipement.getName());
				commandeService.saveConsommation(consommation);
				Stage stage = (Stage) nmbrePiece.getScene().getWindow();
				stage.close();
			}
		} catch (NumberFormatException e) {
			errorText.setText("erreur donnes saisie ");
		}
	}
}
