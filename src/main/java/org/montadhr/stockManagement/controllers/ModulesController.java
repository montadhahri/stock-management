package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.TypeScreenFactory;
import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Piece;
import org.montadhr.stockManagement.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

@Component
public class ModulesController implements Initializable {

	@Autowired
	private TypeScreenFactory typeScreenFactory;
	
	@Autowired
	private CommandeService commandeService;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private BorderPane root;

	@FXML
	private Parent nodeScene;

	@FXML
	private Button atelier;
	
	
	// Table View cell
	@FXML
	private TableView<Article> articleSecuriteTable;
	@FXML
	private TableColumn<Article, String> pieceColumn;
	@FXML
	private TableColumn<Article, String> qteColumn;
	@FXML
	private TableColumn<Article, Article> stockColumn;
	@FXML
	private TableColumn<Article, Article> livraisonColumn;
	
	
	
	
	
	@FXML
	protected void setOnMouseClicked(MouseEvent event) {
		
	}

	@FXML
	protected void handleSubmitAtelierAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
			typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.MACHINE);
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) atelier.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}
	
	@FXML
	protected void handleSubmitCommandeAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Commandes.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) atelier.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
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
			Stage stage = (Stage) atelier.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}
	
	@FXML
	protected void handleSubmitPrevisionnelleAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProduitsConsommable.fxml"));
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			nodeScene = fxmlLoader.load();
			Stage stage = (Stage) atelier.getScene().getWindow();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
			stage.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Article> articles = commandeService.getArticleByStockMin();
		ObservableList<Article> ob = FXCollections.observableArrayList(articles);
		
		
		articleSecuriteTable.getItems().addAll(ob);
		pieceColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
		qteColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("stock"));
		
		//show operations  

		stockColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Article>(cellData.getValue()));

		stockColumn.setCellFactory(new Callback<TableColumn<Article, Article>, TableCell<Article, Article>>() {
					@Override
					public TableCell<Article, Article> call(TableColumn<Article, Article> colAsistencia) {
						return new TableCell<Article, Article>() {
							@Override
							protected void updateItem(Article item, boolean empty) {
								super.updateItem(item, empty);

								if (empty) {
									setGraphic(null);
								} else {
									final Button AZOUZ = new Button("stock");
									AZOUZ.setOnAction((ActionEvent event) -> {
										try {
											FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Gestionstock.fxml"));
											fxmlLoader.setControllerFactory(applicationContext::getBean);
											nodeScene = fxmlLoader.load();
											Stage stage = (Stage) atelier.getScene().getWindow();
											stage.close();
											Stage stage1 = new Stage();
											stage1.setScene(new Scene(nodeScene));
											GestionstockController mainController = fxmlLoader
													.<GestionstockController> getController();
											mainController.setSelectedArticles(item);
											stage1.show();
											
										} catch (Exception e) {
										}
									});
									setGraphic(AZOUZ);
									setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								}
								
							}
						};
					}
				});
				
				//show operations  

		livraisonColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Article>(cellData.getValue()));

		livraisonColumn.setCellFactory(new Callback<TableColumn<Article, Article>, TableCell<Article, Article>>() {
					@Override
					public TableCell<Article, Article> call(TableColumn<Article, Article> colAsistencia) {
						return new TableCell<Article, Article>() {
							@Override
							protected void updateItem(Article item, boolean empty) {
								super.updateItem(item, empty);

								if (empty) {
									setGraphic(null);
								} else {
									final Button AZOUZ = new Button("Livraison");
									AZOUZ.setOnAction((ActionEvent event) -> {

										try {
											FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Commandes.fxml"));
											fxmlLoader.setControllerFactory(applicationContext::getBean);
											nodeScene = fxmlLoader.load();
											Stage stage = (Stage) atelier.getScene().getWindow();
											Stage stage1 = new Stage();
											stage1.setScene(new Scene(nodeScene));
											CommandeController mainController = fxmlLoader
													.<CommandeController> getController();
											mainController.setSelectedArticle(item);
											stage1.show();
											stage.close();
										} catch (Exception e) {
										}

									});
									setGraphic(AZOUZ);
									setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
								}
								
							}
						};
					}
				});
	}
}
