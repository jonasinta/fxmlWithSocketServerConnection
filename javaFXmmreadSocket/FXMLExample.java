package javaFXmmreadSocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class FXMLExample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL xmlUrl = getClass().getResource("boing.fxml");
		FXMLLoader loader = new FXMLLoader();
		// loader.setLocation(new URL("file:boing.fxml"));
		loader.setController(new MainSceneController());

		loader.setLocation(xmlUrl);
		Parent root = loader.load();

		// VBox vbox = loader.<VBox>load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
