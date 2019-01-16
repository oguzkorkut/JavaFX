package com.okorkut.javaui.controller.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.okorkut.javaui.DashboardLaunch;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class UIController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private AnchorPane parent;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeStageDrageable();
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
                DashboardLaunch.stage.setX(event.getScreenX() - xOffset);
                DashboardLaunch.stage.setY(event.getScreenY() - yOffset);
                DashboardLaunch.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            DashboardLaunch.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            DashboardLaunch.stage.setOpacity(1.0f);
        });

    }

    @FXML
    private void open_settings(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/dashboard/SettingsUI.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(fxml);
    }

    @FXML
    private void open_user_profile(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/dashboard/UserProfileUI.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(fxml);
    }

}
