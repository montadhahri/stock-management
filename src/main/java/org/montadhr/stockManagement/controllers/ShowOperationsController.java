package org.montadhr.stockManagement.controllers;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class ShowOperationsController {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;
	
	@FXML
	private Text titleText;

	@Autowired
	private CommandeService commandeService;
	
	private Article article;
	
	public void setArticle(Article article) {
		this.article = article;
	}
	
	@FXML
	protected void handleSubmitCommandeAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Commandes.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) titleText.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			CommandeController mainController = fxmlLoader
					.<CommandeController> getController();
			mainController.setSelectedArticle(article);
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}
	
	@FXML
	protected void handleSubmitStockAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Gestionstock.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) titleText.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			GestionstockController mainController = fxmlLoader
					.<GestionstockController> getController();
			mainController.setSelectedArticles(article);
			stage1.show();
			
		} catch (Exception e) {
		}
	}
}
