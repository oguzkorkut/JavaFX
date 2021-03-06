package com.okorkut.javaui.controller.steamengine;

import java.net.URL;
import java.util.ResourceBundle;

import com.okorkut.javaui.Steamengine;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainUiController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        // TODO
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
            	Steamengine.stage.setX(event.getScreenX() - xOffset);
            	Steamengine.stage.setY(event.getScreenY() - yOffset);
            	Steamengine.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
        	Steamengine.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
        	Steamengine.stage.setOpacity(1.0f);
        });

    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void maximize(MouseEvent event) {
    }

    @FXML
    private void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

}
