package com.okorkut.javaui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.okorkut.javaui.util.CameraFXConstants;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UploadKitUI extends Application {

	private static final Logger logger = LogManager.getLogger(UploadKitUI.class);

	public static void main(String[] args) {
		logger.trace("UploadKitUI Main App Started...");
		launch(args);
		logger.trace("UploadKitUI Main App Finished...");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		CameraFXConstants.PRIMARY_STAGE = primaryStage;

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/UploadKitFXUI.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
