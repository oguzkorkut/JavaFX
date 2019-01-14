package com.okorkut.javaui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UploadKitFXUIController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private Label lbl1;
    @FXML
    private ImageView img2;
    @FXML
    private Label lbl2;
    @FXML
    private ImageView img3;
    @FXML
    private Label lbl3;
    @FXML
    private ImageView img4;
    @FXML
    private Label lbl4;
    @FXML
    private ImageView img5;
    @FXML
    private Button scanBtn;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;

    private RotateTransition rotateTransition1, rotateTransition2, rotateTransition3,
            rotateTransition4, rotateTransition5;
    @FXML
    private Label lbl_msg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void start_scan(ActionEvent event) {
        img1.setImage(new Image("images/syn.png"));
        text1.setText("Checking For Updates");
        rotateTransition1 = new RotateTransition(Duration.seconds(5), img1);
        rotateTransition2 = new RotateTransition(Duration.seconds(5), img2);
        rotateTransition3 = new RotateTransition(Duration.seconds(5), img3);
        rotateTransition4 = new RotateTransition(Duration.seconds(5), img4);
        rotateTransition5 = new RotateTransition(Duration.seconds(5), img5);
        RotateTransition transition[] = {rotateTransition1, rotateTransition2,
            rotateTransition3, rotateTransition4, rotateTransition5};
        for (RotateTransition rTransition : transition) {
            rTransition.setCycleCount(1);
            rTransition.setAutoReverse(false);
            rTransition.setFromAngle(720);
            rTransition.setToAngle(0);
        }
        rotateTransition1.play();
        rotateTransition1.setOnFinished((e) -> {
            img1.setImage(new Image("images/ok.png"));
            lbl1.setStyle("-fx-background-color:#45A563");
            img2.setImage(new Image("images/syn.png"));
            text2.setText("Pre-scan Operations");
            rotateTransition2.play();
        });

        rotateTransition2.setOnFinished((e) -> {
            img2.setImage(new Image("images/ok.png"));
            lbl2.setStyle("-fx-background-color:#45A563");
            img3.setImage(new Image("images/syn.png"));
            text3.setText("Scanning Memory");
            rotateTransition3.play();
        });
        rotateTransition3.setOnFinished((e) -> {
            img3.setImage(new Image("images/ok.png"));
            lbl3.setStyle("-fx-background-color:#45A563");
            img4.setImage(new Image("images/syn.png"));
            text4.setText("Scanning Registry");
            rotateTransition4.play();
        });
        rotateTransition4.setOnFinished((e) -> {
            img4.setImage(new Image("images/ok.png"));
            lbl4.setStyle("-fx-background-color:#45A563");
            img5.setImage(new Image("images/syn.png"));
            text5.setText("Finalizing The Scans Reports");
            rotateTransition5.play();
        });
        rotateTransition5.setOnFinished((e) -> {
            img5.setImage(new Image("images/ok.png"));
            lbl_msg.setVisible(true);
        });
    }

}
