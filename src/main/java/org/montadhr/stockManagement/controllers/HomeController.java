package org.montadhr.stockManagement.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.montadhr.stockManagement.TypeScreenFactory;
import org.montadhr.stockManagement.entity.Equipement;
import org.montadhr.stockManagement.entity.Machine;
import org.montadhr.stockManagement.entity.SousEquipemet;
import org.montadhr.stockManagement.service.MachineService;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class HomeController implements Initializable {

	@Autowired
	private TypeScreenFactory typeScreenFactory;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private MachineService machineService;

	@FXML
	private BorderPane root;

	@FXML
	private Parent parent;

	@FXML
	private TextField newMachine;

	@FXML
	private Text title;

	@FXML
	private Text titleScene;

	@FXML
	private ListView<Object> machineView;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		if(!newMachine.getText().isEmpty()){
			if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.MACHINE)) {
				Machine machine = new Machine();
				machine.setName(newMachine.getText());
				machineService.saveMachine(machine);
				newMachine.setText("");
				machineView.getItems().add(machine);
			} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.EQUIPEMENT)) {
				Equipement equipement = new Equipement();
				equipement.setName(newMachine.getText());
				equipement.setMachine(machineService.getMachineById(typeScreenFactory.getId()));
				machineService.saveEquipement(equipement);
				newMachine.setText("");
				machineView.getItems().add(equipement);
			} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.SOUSEQUIPEMENT)) {
				SousEquipemet sousEquipement = new SousEquipemet();
				sousEquipement.setName(newMachine.getText());
				sousEquipement.setEquipement(machineService.getEquipementById(typeScreenFactory.getId()));
				machineService.saveSousEquipement(sousEquipement);
				newMachine.setText("");
				machineView.getItems().add(sousEquipement);
			}	
		}
	}

	@FXML
	protected void handleReturnButtonAction(ActionEvent event) {
		if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.MACHINE)) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Modules.fxml"));
				fxmlLoader.setControllerFactory(applicationContext::getBean);
				parent = fxmlLoader.load();
				Stage stage = (Stage) newMachine.getScene().getWindow();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(parent));
				stage1.show();
				stage.close();
			} catch (Exception e) {
			}
		} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.EQUIPEMENT)) {
			try {
				typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.MACHINE);
				machineView.getItems().removeAll(machineView.getItems());
				List<Machine> machines = machineService.getAllMachines();
				ObservableList<Object> ob = FXCollections.observableArrayList(machines);
				machineView.setItems(ob);
				title.setText("Machine");
				titleScene.setText("All machines");
				loadScreen("fxml/Home.fxml", new HomeController());
			} catch (Exception e) {
			}
		} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.SOUSEQUIPEMENT)) {
			Equipement equipement = machineService.getEquipementById(typeScreenFactory.getId());
			Machine machine = machineService.getMachineByEquipement(equipement);
			try {
				typeScreenFactory.setId(machine.getId());
				typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.EQUIPEMENT);
				machineView.getItems().removeAll(machineView.getItems());
				List<Equipement> machines = machineService.getEquipementByMachine(machine);
				ObservableList<Object> ob = FXCollections.observableArrayList(machines);
				machineView.setItems(ob);
				title.setText("Equipement");
				titleScene.setText("Machine : " + machine.getName());
				loadScreen("fxml/Home.fxml", new HomeController());
			} catch (Exception e) {
			}
		}
	}

	@FXML
	protected void setOnMouseClicked(MouseEvent event) {
		if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.MACHINE)) {
			Machine machine = (Machine) machineView.getSelectionModel().getSelectedItem();
			try {
				typeScreenFactory.setId(machine.getId());
				typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.EQUIPEMENT);
				machineView.getItems().removeAll(machineView.getItems());
				List<Equipement> equipements = machineService
						.getEquipementByMachine(machineService.getMachineById(typeScreenFactory.getId()));
				ObservableList<Object> ob = FXCollections.observableArrayList(equipements);
				machineView.setItems(ob);
				title.setText("Equipement");
				titleScene.setText(machine.getName());
				loadScreen("fxml/Home.fxml", new HomeController());
			} catch (Exception e) {
			}
		} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.EQUIPEMENT)) {
			Equipement equipement = (Equipement) machineView.getSelectionModel().getSelectedItem();
			try {
				typeScreenFactory.setId(equipement.getId());
				typeScreenFactory.setTypeScreen(TypeScreenFactory.TYPE.SOUSEQUIPEMENT);
				machineView.getItems().removeAll(machineView.getItems());
				List<SousEquipemet> equipements = machineService
						.getSousEquipementByEquipement(machineService.getEquipementById(typeScreenFactory.getId()));
				ObservableList<Object> ob = FXCollections.observableArrayList(equipements);
				machineView.setItems(ob);
				title.setText("Sous Equipement");
				titleScene.setText(equipement.getName());
				loadScreen("fxml/Home.fxml", new HomeController());
			} catch (Exception e) {
			}
		} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.SOUSEQUIPEMENT)) {
			SousEquipemet sousEquipement = (SousEquipemet) machineView.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SousEquipement.fxml"));
				typeScreenFactory.setId(sousEquipement.getId());
				fxmlLoader.setControllerFactory(applicationContext::getBean);
				parent = fxmlLoader.load();
				Stage stage = (Stage) newMachine.getScene().getWindow();
				stage.close();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(parent));
				stage1.show();
			} catch (Exception e) {
				System.out.println("Exception on FXMLLoader.load()");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.MACHINE)) {
			List<Machine> machines = machineService.getAllMachines();
			ObservableList<Object> ob = FXCollections.observableArrayList(machines);
			machineView.setItems(ob);
			title.setText("Machine");
			titleScene.setText("All Machines");
		} else if (typeScreenFactory.getTypeScreen().equals(TypeScreenFactory.TYPE.SOUSEQUIPEMENT)) {
			Equipement equipement = machineService.getEquipementById(typeScreenFactory.getId());
			List<SousEquipemet> sousEquipements = machineService.getSousEquipementByEquipement(equipement);
			ObservableList<Object> ob = FXCollections.observableArrayList(sousEquipements);
			machineView.setItems(ob);
			title.setText("Sous Equipement ");
			titleScene.setText(equipement.getName());
		}
	}

	private void loadScreen(String resource, Object controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
			loader.setController(controller);
			root.setCenter(loader.load());
		} catch (IOException exc) {
			exc.printStackTrace();
			root.setCenter(null);
		}
	}

}
