package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.entity.ProduitConsommable;
import org.montadhr.stockManagement.service.ProduitConsommableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class ProduitsConsommableController implements Initializable {

	@Autowired
	private ProduitConsommableService produitConsommableService;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent parent;

	@FXML
	private Parent nodeScene;

	@FXML
	private Button returnbutton;

	@FXML
	private TextField nameProduit;

	@FXML
	private ListView<Object> produitListView;

	@FXML
	protected void handleSubmitReturnAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Modules.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) returnbutton.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}

	@FXML
	protected void handleAddProduitButton(ActionEvent event) {
		if (!nameProduit.getText().isEmpty()) {
			ProduitConsommable produit = new ProduitConsommable();
			produit.setNameProduit(nameProduit.getText());
			produitConsommableService.addProduit(produit);
			produitListView.getItems().add(produit);
			nameProduit.setText("");
		}
	}

	@FXML
	protected void setOnMouseClicked(MouseEvent event) {
		ProduitConsommable produit = (ProduitConsommable) produitListView.getSelectionModel().getSelectedItem();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Previsionnelle.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			parent = fxmlLoader.load();
			Stage stage = (Stage) nameProduit.getScene().getWindow();
			stage.close();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(parent));
			PrevisionnelleController previsionnelleController = fxmlLoader.<PrevisionnelleController> getController();
			previsionnelleController.setProduit(produit);
			stage1.show();
		} catch (Exception e) {
			System.out.println("Exception on FXMLLoader.load()");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<ProduitConsommable> produits = produitConsommableService.getAll();
		ObservableList<Object> ob = FXCollections.observableArrayList(produits);
		produitListView.setItems(ob);
	}

}
