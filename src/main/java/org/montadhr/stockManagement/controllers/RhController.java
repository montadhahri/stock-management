package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.entity.Salarie;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class RhController implements Initializable {

	@Autowired
	private ProduitConsommableService produitConsommableService;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;

	@FXML
	private Button returnbutton;

	@FXML
	private TextField nomField;

	@FXML
	private TextField prenomField;

	@FXML
	private TextField salaireField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField telephoneField;

	@FXML
	private TextField titreField;

	@FXML
	private TableView<Salarie> employesTableView;
	@FXML
	private TableColumn<Salarie, String> idColumn;
	@FXML
	private TableColumn<Salarie, String> nomColumn;
	@FXML
	private TableColumn<Salarie, String> prenomColumn;
	@FXML
	private TableColumn<Salarie, String> salaireColumn;
	@FXML
	private TableColumn<Salarie, String> emailColumn;
	@FXML
	private TableColumn<Salarie, String> telColumn;
	@FXML
	private TableColumn<Salarie, String> titreColumn;

	@FXML
	protected void handleSubmitReturnAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Erp.fxml"));
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
	protected void handleSubmitAddCollaborateur(ActionEvent event) {
		try {
			int salaire = Integer.parseInt(salaireField.getText());
			int tel = Integer.parseInt(telephoneField.getText());
			Salarie salarie = new Salarie(nomField.getText(), prenomField.getText(), salaire, emailField.getText(), tel,
					titreField.getText());
			produitConsommableService.addSalarie(salarie);
			employesTableView.getItems().add(salarie);
			nomField.setText("");
			prenomField.setText("");
			salaireField.setText("");
			emailField.setText("");
			telephoneField.setText("");
			titreField.setText("");
		} catch (NumberFormatException e) {
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initialiser TableViw
		List<Salarie> salaries = produitConsommableService.getAllSalarie();
		System.out.println(salaries);
		ObservableList<Salarie> salariesData = FXCollections.observableArrayList(salaries);
		employesTableView.getItems().addAll(salariesData);
		idColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("id"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("nom"));
		prenomColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("prenom"));
		salaireColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("salaire"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("email"));
		telColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("tel"));
		titreColumn.setCellValueFactory(new PropertyValueFactory<Salarie, String>("titre"));
	}

}
