package com.okorkut.javaui.controller.splashscreenlogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.okorkut.javaui.Splashscreenlogin;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SplashUIController implements Initializable {

    @FXML
    private AnchorPane parent;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        FadeTransition.applyFadeTransition(parent, Duration.seconds(5), (e) -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/splashscreenlogin/loginUI.fxml"));
                
                parent.getChildren().removeAll();
                parent.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(SplashUIController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Splashscreenlogin.stage.setX(event.getScreenX() - xOffset);
                Splashscreenlogin.stage.setY(event.getScreenY() - yOffset);
                Splashscreenlogin.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            Splashscreenlogin.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            Splashscreenlogin.stage.setOpacity(1.0f);
        });

    }
}
