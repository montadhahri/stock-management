package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.entity.Previsionnelle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class PrevisionnelleController implements Initializable {

	private ProduitConsommable produit;

	public void setProduit(ProduitConsommable produit) {
		this.produit = produit;
		titleText.setText(this.produit.getNameProduit());
		List<Previsionnelle> previsionnelles = produitConsommableService.getAllPrevisionnelleByProduit(produit);
		ObservableList<Previsionnelle> previsionnellesData = FXCollections.observableArrayList(previsionnelles);
		previsionnelleTableView.getItems().addAll(previsionnellesData);
		idPrevisionnelleColumn.setCellValueFactory(new PropertyValueFactory<Previsionnelle, String>("number"));
		dtPrevisionnelleColumn.setCellValueFactory(new PropertyValueFactory<Previsionnelle, String>("dt"));
		ptPrevisionnelleColumn.setCellValueFactory(new PropertyValueFactory<Previsionnelle, String>("pt"));
		etPrevisionnelleColumn.setCellValueFactory(new PropertyValueFactory<Previsionnelle, String>("et"));
		if(produitConsommableService.getNumberPrevisionnelle(produit) >=2){
			Previsionnelle previsionnelleFinal = produitConsommableService.getFinalPrevisionnelle(produit);
			previsionnelleTableView.getItems().add(previsionnelleFinal);
			ealmField.setText(String.valueOf(produitConsommableService.getEalm(produit)));
			previsionField.setText(String.valueOf(produitConsommableService.getPrevision(produit)));
		}
	}

	@FXML
	private TableView<Previsionnelle> previsionnelleTableView;
	@FXML
	private TableColumn<Previsionnelle, String> idPrevisionnelleColumn;
	@FXML
	private TableColumn<Previsionnelle, String> dtPrevisionnelleColumn;
	@FXML
	private TableColumn<Previsionnelle, String> ptPrevisionnelleColumn;
	@FXML
	private TableColumn<Previsionnelle, String> etPrevisionnelleColumn;

	@Autowired
	private ProduitConsommableService produitConsommableService;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent parent;

	@FXML
	private Button returnbutton;

	@FXML
	private Parent nodeScene;

	@FXML
	private TextField previsionnelleField;
	
	@FXML
	private TextField ealmField;
	
	@FXML
	private TextField previsionField;

	@FXML
	private Text titleText;

	@FXML
	protected void handleSubmitReturnAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProduitsConsommable.fxml"));
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
	protected void handleSubmitAddPrevisionnelle(ActionEvent event) {
		try {
			if (!previsionnelleField.getText().isEmpty()) {
				if(produitConsommableService.getNumberPrevisionnelle(produit) >= 2){
					previsionnelleTableView.getItems().remove(previsionnelleTableView.getItems().size()-1);
				}
				Previsionnelle previsionnelle = new Previsionnelle();
				previsionnelle.setProduit(this.produit);
				previsionnelle.setDt(Double.parseDouble(previsionnelleField.getText()));
				if (produitConsommableService.getNumberPrevisionnelle(produit) == 1) {
					previsionnelle.setPt(produitConsommableService
							.getPrevisionnelleByNumber(produitConsommableService.getNumberPrevisionnelle(produit),produit)
							.getDt());
				} else if (produitConsommableService.getNumberPrevisionnelle(produit) > 1) {
					List<Previsionnelle> previsionnelle0 = produitConsommableService
							.getAllPrevisionnelleByProduit(produit);
					previsionnelle.setPt(0.23 * previsionnelle0.get(previsionnelle0.size() - 1).getDt()
							+ 0.77 * previsionnelle0.get(previsionnelle0.size() - 1).getPt());
				}
				if (produitConsommableService.getNumberPrevisionnelle(produit) >= 1) {
					previsionnelle.setEt(previsionnelle.getDt() - previsionnelle.getPt());
				}
				previsionnelle.setNumber(produitConsommableService.getNumberPrevisionnelle(produit) + 1);
				previsionnelle = produitConsommableService.addPrevionnelle(previsionnelle);
				previsionnelleTableView.getItems().add(previsionnelle);
				previsionnelleField.setText("");
				
				
				Previsionnelle previsionnelleFinal = produitConsommableService.getFinalPrevisionnelle(produit);
				previsionnelleTableView.getItems().add(previsionnelleFinal);
				ealmField.setText(String.valueOf(produitConsommableService.getEalm(produit)));
				previsionField.setText(String.valueOf(produitConsommableService.getPrevision(produit)));
			}
		} catch (NumberFormatException e) {
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
