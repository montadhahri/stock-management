package org.montadhr.stockManagement.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.TypeScreenFactory;
import org.montadhr.stockManagement.entity.Article;
import org.montadhr.stockManagement.entity.Piece;
import org.montadhr.stockManagement.entity.SousEquipemet;
import org.montadhr.stockManagement.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

@Component
public class PieceController implements Initializable {

	@Autowired
	private MachineService machineService;

	@Autowired
	private TypeScreenFactory typeScreenFactory;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private Text titleScenePiece;

	@FXML
	private Parent nodeScene;

	// Table View cell
	@FXML
	private TableView<Piece> pieceTableView;
	@FXML
	private TableColumn<Piece, String> numPiece;
	@FXML
	private TableColumn<Piece, String> description;
	@FXML
	private TableColumn<Piece, String> partie;
	@FXML
	private TableColumn<Piece, String> nmbrePiece;
	@FXML
	private TableColumn<Piece, Piece> showoperation;
	@FXML
	private TableColumn<Piece, Piece> editColumn;
	@FXML
	private TableColumn<Piece, Piece> deleteColumn;

	// Add New Piece
	@FXML
	private TextField numPieceField;
	@FXML
	private TextField descriptionField;
	@FXML
	private TextField partieField;
	@FXML
	private TextField nbrPieceField;
	@FXML
	private Text errorText;

	@FXML
	protected void handleSaveButtonAction(ActionEvent event) {
		try {
			if(!descriptionField.getText().isEmpty() && !partieField.getText().isEmpty()){
				errorText.setText("");
				Piece piece = new Piece();
				piece.setNumPiece(Integer.parseInt(numPieceField.getText()));
				piece.setDescription(descriptionField.getText());
				if (machineService.getArticleByName(partieField.getText()) != null) {
					piece.setArticle(machineService.getArticleByName(partieField.getText()));
				} else {
					Article article = new Article();
					article.setName(partieField.getText());
					article.setStock(0);
					article = machineService.saveArticle(article);
					piece.setArticle(article);
				}
				piece.setNmbrePiece(Integer.parseInt(nbrPieceField.getText()));
				piece.setSousEquipement(machineService.getSousEquipementById(typeScreenFactory.getId()));
				machineService.savePiece(piece);
				pieceTableView.getItems().add(piece);
				numPieceField.setText("");
				descriptionField.setText("");
				partieField.setText("");
				nbrPieceField.setText("");
			}
		} catch (NumberFormatException e) {
			errorText.setText("erreur donnes saisie ");
		}
	}

	@FXML
	protected void handleReturnButtonAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
			typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.MACHINE);
			fxmlLoader.setControllerFactory(applicationContext::getBean);
			Stage stage = (Stage) numPieceField.getScene().getWindow();
			stage.close();
			nodeScene = fxmlLoader.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(nodeScene));
			stage1.show();
		} catch (Exception e) {
			System.out.println("Exception on FXMLLoader.load()");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SousEquipemet sousEquipement = machineService.getSousEquipementById(typeScreenFactory.getId());
		List<Piece> pieces = machineService.getPieceBySousEquipement(sousEquipement);
		ObservableList<Piece> pieceData = FXCollections.observableArrayList(pieces);
		pieceTableView.getItems().addAll(pieceData);
		numPiece.setCellValueFactory(new PropertyValueFactory<Piece, String>("numPiece"));
		description.setCellValueFactory(new PropertyValueFactory<Piece, String>("description"));
		partie.setCellValueFactory(new PropertyValueFactory<Piece, String>("partie"));
		nmbrePiece.setCellValueFactory(new PropertyValueFactory<Piece, String>("nmbrePiece"));

		//show operations  

		showoperation.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Piece>(cellData.getValue()));

		showoperation.setCellFactory(new Callback<TableColumn<Piece, Piece>, TableCell<Piece, Piece>>() {
			@Override
			public TableCell<Piece, Piece> call(TableColumn<Piece, Piece> colAsistencia) {
				return new TableCell<Piece, Piece>() {
					@Override
					protected void updateItem(Piece item, boolean empty) {
						super.updateItem(item, empty);

						if (empty) {
							setGraphic(null);
						} else {
							final Button AZOUZ = new Button("show operations");
							AZOUZ.setOnAction((ActionEvent event) -> {

								try {
									FXMLLoader fxmlLoader = new FXMLLoader(
											getClass().getResource("/fxml/Showoperation.fxml"));
									fxmlLoader.setControllerFactory(applicationContext::getBean);
									nodeScene = fxmlLoader.load();
									Stage stage = (Stage) titleScenePiece.getScene().getWindow();
									stage.close();
									Stage stage1 = new Stage();
									stage1.setScene(new Scene(nodeScene));
									ShowOperationsController mainController = fxmlLoader
											.<ShowOperationsController> getController();
									mainController.setArticle(item.getArticle());
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
		// change piece button
		editColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Piece>(cellData.getValue()));

		editColumn.setCellFactory(new Callback<TableColumn<Piece, Piece>, TableCell<Piece, Piece>>() {
			@Override
			public TableCell<Piece, Piece> call(TableColumn<Piece, Piece> colAsistencia) {
				return new TableCell<Piece, Piece>() {
					@Override
					protected void updateItem(Piece item, boolean empty) {
						super.updateItem(item, empty);

						if (empty) {
							setGraphic(null);
						} else {
							final Button AZOUZ = new Button("change article");
							AZOUZ.setOnAction((ActionEvent event) -> {

								try {
									FXMLLoader fxmlLoader = new FXMLLoader(
											getClass().getResource("/fxml/Changearticle.fxml"));
									fxmlLoader.setControllerFactory(applicationContext::getBean);
									nodeScene = fxmlLoader.load();
									Stage stage1 = new Stage();
									stage1.setScene(new Scene(nodeScene));
									ChangeArticleController mainController = fxmlLoader
											.<ChangeArticleController> getController();
									mainController.setArticle(item.getArticle());
									mainController.setEquipement(machineService.getEquipementBySousEquipement(sousEquipement));
									mainController.setNbarticle(item.getNmbrePiece());
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
		// delete Column button

		deleteColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Piece>(cellData.getValue()));

		deleteColumn.setCellFactory(new Callback<TableColumn<Piece, Piece>, TableCell<Piece, Piece>>() {
			@Override
			public TableCell<Piece, Piece> call(TableColumn<Piece, Piece> colAsistencia) {
				return new TableCell<Piece, Piece>() {
					@Override
					protected void updateItem(Piece item, boolean empty) {
						super.updateItem(item, empty);

						if (empty) {
							setGraphic(null);
						} else {
							final Button delete = new Button("Delete");
							delete.setOnAction((ActionEvent event) -> {
								machineService.deletePiece(item);
								pieceTableView.getItems().remove(item);
							});
							setGraphic(delete);
							setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						}
					}
				};
			}
		});
		pieceTableView.setRowFactory( tv -> new TableRow<Piece>() {
	      @Override
	      protected void updateItem( final Piece item, final boolean empty ){
	    	  if ( !empty && item != null ){
	            this.styleProperty().bind( Bindings.createStringBinding( () -> {
	              if ( item.getArticle().getStock() <= item.getArticle().getStockMin() ){
	                return "-fx-background-color: red;";
	              }
	              return " ";
	            }));
	          }
	      }
	    });
	}
}
