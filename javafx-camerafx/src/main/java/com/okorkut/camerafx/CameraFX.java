package com.okorkut.camerafx;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.okorkut.camerafx.util.CameraFXConstants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class CameraFX extends Application {

	private static final Logger logger = LogManager.getLogger(CameraFX.class);
	
	public static void main(String[] args) {
		logger.trace("Tredaş Main App Started...");
		launch(args);
		logger.trace("Tredaş Main App Finished...");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		CameraFXConstants.PRIMARY_STAGE = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/CameraFX.fxml"));
		Scene scene = new Scene(root);
		Locale trlocale = Locale.forLanguageTag("tr");
		Path path = Paths.get("src/main/resources/xml");


		CameraFXConstants.PRIMARY_STAGE.setScene(scene);
		CameraFXConstants.PRIMARY_STAGE.setTitle("CameraFX");
		CameraFXConstants.PRIMARY_STAGE.setFullScreen(false);
		CameraFXConstants.PRIMARY_STAGE.setOnCloseRequest(event -> event.consume());
//		CameraFXConstants.PRIMARY_STAGE.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		CameraFXConstants.PRIMARY_STAGE.show();
	}

}
