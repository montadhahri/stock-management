package org.montadhr.stockManagement.controllers;

import org.montadhr.stockManagement.TypeScreenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class ErpController {
	@Autowired
	private TypeScreenFactory typeScreenFactory;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;

	@FXML
	private Button gestionstock;
	
	@FXML
	private Button ressourcehumaine;

	@FXML
	protected void handleSubmitGestionStock(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Modules.fxml"));
			typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.MACHINE);
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) gestionstock.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}

	@FXML
	protected void handleSubmitRH(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Rh.fxml"));
			typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.MACHINE);
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) ressourcehumaine.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}

	}

	@FXML
	protected void handleSubmitGestionVente(ActionEvent event) {

	}

}
