package org.montadhr.stockManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class StockManagementApplication extends Application {

	private ConfigurableApplicationContext applicationContext;
	private Parent nodeScene;

	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(StockManagementApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		nodeScene = fxmlLoader.load();
	}

	@Bean
	public TypeScreenFactory typeScreenFactory() {
		return new TypeScreenFactory();
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(nodeScene));
		stage.show();
	}

}
