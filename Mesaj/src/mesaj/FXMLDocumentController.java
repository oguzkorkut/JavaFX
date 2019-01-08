/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesaj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import mesaj.profil.Profil;

/**
 *
 * @author oguz
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane profilAnchor;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Profil profil = new Profil();
        
        profilAnchor.getChildren().add(profil);
    }    
    
}
