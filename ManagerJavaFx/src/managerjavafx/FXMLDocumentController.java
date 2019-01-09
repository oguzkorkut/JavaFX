/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerjavafx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author oguz
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane anc;
    @FXML
    private VBox btnCeasses;
    
    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton btnTimetable;
    @FXML
    private JFXButton btnSettings;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDashboard;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       
    }
    
    private void handleButtonClicks(ActionEvent event) {
        System.out.println("You clicked me!");
        
        if(event.getSource() == btnDashboard){
            loadStages("/managerjavafx/Dashboard.fxml");
        } else if(event.getSource() == btnStudents){
             loadStages("/managerjavafx/Students.fxml");
        } else if(event.getSource() == btnTimetable){
             loadStages("/managerjavafx/Students.fxml");
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }   

    private void click(MouseEvent event) {
        System.out.println("FXMLDocumentController.click()");
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleButtonClicks(MouseEvent event) {
    }
    
    
    private void loadStages(String fxml){
        try{
           Parent root = FXMLLoader.load(getClass().getResource(fxml));
           
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
          
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
