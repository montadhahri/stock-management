package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Commande;
import org.montadhr.stockManagement.entity.Operation;
import org.montadhr.stockManagement.service.CommandeService;
import org.montadhr.stockManagement.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class GestionstockController implements Initializable {
	
	private Article selectedArticles;
	

	public void setSelectedArticles(Article selectedArticles) {
		
		
		choiseStockArticle.setText(selectedArticles.getName());
		stockArticle.setText(String.valueOf(selectedArticles.getStock()));
		stocksecarticle.setText(String.valueOf(selectedArticles.getStockMin()));
		selectedArticle = selectedArticles;
		
		//tableview
		List<Operation> commandes = commandeService.getOperationByArticle(selectedArticles);
		ObservableList<Operation> commandesData = FXCollections.observableArrayList(commandes);
		operationTableView.getItems().removeAll(operationTableView.getItems());
		operationTableView.getItems().addAll(commandesData);
		dateColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("date"));
		entreColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("qteEntre"));
		fourniseurColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("fournisseur"));
		sortieColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("qteSortie"));
		equipementColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("equipement"));
		
		
		this.selectedArticles = selectedArticles;
	}

	private Article selectedArticle;

	@Autowired
	private CommandeService commandeService;

	@Autowired
	private MachineService machineService;
	
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;
	
	@FXML
	private Button returnbutton;
	
	@FXML
	private MenuButton choiseStockArticle;
	
	@FXML
	private TextField stockArticle;
	
	@FXML
	private TextField stocksecarticle;
	
	
	@FXML
	private TableView<Operation> operationTableView;
	@FXML
	private TableColumn<Operation, String> dateColumnView;
	@FXML
	private TableColumn<Operation, String> entreColumnView;
	@FXML
	private TableColumn<Operation, String> fourniseurColumn;
	@FXML
	private TableColumn<Operation, String> sortieColumnView;
	@FXML
	private TableColumn<Operation, String> equipementColumn;

	
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
	protected void handleSaveButtonAction(ActionEvent event) {
		try {
			int securite = Integer.parseInt(stocksecarticle.getText());
			selectedArticle.setStockMin(securite);
			machineService.saveArticle(selectedArticle);
		} catch (NumberFormatException e) {
		}	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Article> articles = commandeService.getAllArticle();
		List<MenuItem> menuArticle = new ArrayList<>();
		for (Article article : articles) {
			MenuItem menuItem = new MenuItem(article.getName());
			menuArticle.add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					choiseStockArticle.setText(article.getName());
					stockArticle.setText(String.valueOf(article.getStock()));
					stocksecarticle.setText(String.valueOf(article.getStockMin()));
					selectedArticle = article;
					
					//tableview
					List<Operation> commandes = commandeService.getOperationByArticle(article);
					ObservableList<Operation> commandesData = FXCollections.observableArrayList(commandes);
					operationTableView.getItems().removeAll(operationTableView.getItems());
					operationTableView.getItems().addAll(commandesData);
					dateColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("date"));
					entreColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("qteEntre"));
					fourniseurColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("fournisseur"));
					sortieColumnView.setCellValueFactory(new PropertyValueFactory<Operation, String>("qteSortie"));
					equipementColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("equipement"));
				}
			});
		}
		choiseStockArticle.getItems().addAll(menuArticle);
	}
}
