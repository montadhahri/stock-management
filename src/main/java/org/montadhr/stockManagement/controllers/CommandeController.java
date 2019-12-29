package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Commande;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class CommandeController implements Initializable {

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
	private TextField quantite;

	@FXML
	private TextField unitPriceField;

	@FXML
	private TextField bandeLivraisonField;

	@FXML
	private TextField fourniseurField;

	@FXML
	private MenuButton choiseArticle;

	@FXML
	private Button returnbutton;

	@FXML
	private Text errorText;

	private String selectedItem;

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	private Article selectedArticle;

	public void setSelectedArticle(Article selectedArticle) {
		this.selectedItem = selectedArticle.getName();
		choiseArticle.setText(selectedItem);
		if (selectedArticle.getUnitPrice() != null) {
			unitPriceField.setText(String.valueOf(selectedArticle.getUnitPrice()));
		} else {
			unitPriceField.setText(String.valueOf(0.0));
		}
		this.selectedArticle = selectedArticle;
	}

	@FXML
	private TableView<Commande> commandeTableView;
	@FXML
	private TableColumn<Commande, String> dateColumn;
	@FXML
	private TableColumn<Commande, String> articleColumn;
	@FXML
	private TableColumn<Commande, String> quantiteColumn;
	@FXML
	private TableColumn<Commande, String> prixUnitColumn;
	@FXML
	private TableColumn<Commande, String> prixTotalColumn;
	@FXML
	private TableColumn<Commande, String> bandeLivraisonColumn;
	@FXML
	private TableColumn<Commande, String> fourniseurColumn;

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
	protected void handleSubmitPassCmdeAction(ActionEvent event) {
		try {
			errorText.setText("");
			Article article = machineService.getArticleByName(selectedItem);
			if (article.getUnitPrice() == null && unitPriceField.getText().isEmpty()) {
				errorText.setText("l'article \'" + article.getName() + "\' n admet pas prix unitaire");
			} else {
				int quantiteComande = Integer.parseInt(quantite.getText());
				int bandeLivraison = Integer.parseInt(bandeLivraisonField.getText());
				double unitPrice = Double.parseDouble(unitPriceField.getText());
				article.setStock(article.getStock() + quantiteComande);
				article.setUnitPrice(unitPrice);
				article = machineService.saveArticle(article);
				Commande commande = new Commande();
				commande.setPrixUnit(unitPrice);
				commande.setQuantite(quantiteComande);
				commande.setDate(new Date());
				commande.setArticle(article);
				commande.setBandeLivraison(bandeLivraison);
				commande.setFournisseur(fourniseurField.getText());
				commande = commandeService.saveCommande(commande);
				commandeTableView.getItems().add(commande);
				quantite.setText("");
				unitPriceField.setText("");
				bandeLivraisonField.setText("");
				fourniseurField.setText("");
				choiseArticle.setText("Name Article");
			}
		} catch (NumberFormatException e) {
			errorText.setText("erreur donnes saisie ");
		}
	}

	@FXML
	protected void handleSubmitInitialiserAction(ActionEvent event) {
		quantite.setText("");
		unitPriceField.setText("");
		errorText.setText("");
		selectedItem = "";
		bandeLivraisonField.setText("");
		fourniseurField.setText("");
		choiseArticle.setText("Name Article");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initialiser MenuButton
		List<Article> articles = commandeService.getAllArticle();
		List<MenuItem> menuArticle = new ArrayList<>();
		for (Article article : articles) {
			MenuItem menuItem = new MenuItem(article.getName());
			menuArticle.add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					selectedItem = article.getName();
					choiseArticle.setText(selectedItem);
					if (article.getUnitPrice() != null) {
						unitPriceField.setText(String.valueOf(article.getUnitPrice()));
					} else {
						unitPriceField.setText(String.valueOf(0.0));
					}
				}
			});
		}
		choiseArticle.getItems().addAll(menuArticle);
		// initialiser TableViw
		List<Commande> commandes = commandeService.getAllCommande();
		ObservableList<Commande> commandesData = FXCollections.observableArrayList(commandes);
		commandeTableView.getItems().addAll(commandesData);
		dateColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
		articleColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("articleName"));
		quantiteColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("quantite"));
		prixUnitColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("prixUnit"));
		prixTotalColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("prixTotal"));
		bandeLivraisonColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("bandeLivraison"));
		fourniseurColumn.setCellValueFactory(new PropertyValueFactory<Commande, String>("fournisseur"));

	}
}
