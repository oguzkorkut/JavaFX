/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerjavafx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private JFXButton btnHome;
    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton btnTimetable;
    @FXML
    private JFXButton btnSettings;
    @FXML
    private JFXButton btnUpdate;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       
    }
    
    private void handleButtonClicks(MouseEvent event) {
        System.out.println("You clicked me!");
        
        if(event.getSource() == btnHome){
            
        } else if(event.getSource() == btnStudents){
            
        } else if(event.getSource() == btnTimetable){
            
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }   

    private void click(MouseEvent event) {
        System.out.println("FXMLDocumentController.click()");
    }
    
}
