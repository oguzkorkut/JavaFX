package com.okorkut.javaui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.okorkut.javaui.Bounce;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LoadingFXController implements Initializable {

	@FXML
	private Circle circle1;
	
	@FXML
	private Circle circle2;
	
	@FXML
	private Circle circle3;

	// ------------------------------------------------------------------------------//

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("initialize LoadingFXController");
		new Bounce(circle1).setCycleCount(4).setDelay(Duration.valueOf("500ms")).play();
		new Bounce(circle2).setCycleCount(4).setCycleCount(4).setDelay(Duration.valueOf("1000ms")).play();
		new Bounce(circle3).setCycleCount(4).setCycleCount(4).setDelay(Duration.valueOf("1100ms")).play();
		

	}

}
