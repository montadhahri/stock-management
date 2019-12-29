package org.montadhr.stockManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class LoginController {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private Parent nodeScene;

	@FXML
	private Text actiontarget;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		if (usernameField.getText().equals("slah") && passwordField.getText().equals("12345")) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Erp.fxml"));
				fxmlLoader.setControllerFactory(applicationContext::getBean);
				nodeScene = fxmlLoader.load();
				Stage stage = (Stage) usernameField.getScene().getWindow();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(nodeScene));
				stage1.show();
				stage.close();
			} catch (Exception e) {
			}
		} else {
			actiontarget.setText("User not found");
		}
	}
}
