/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesaj.profil;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import mesaj.bin.Config;

/**
 * FXML Controller class
 *
 * @author oguz
 */
public class Profil extends AnchorPane {

   public Profil(){
       FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("Profil.fxml"));
       
       fXMLLoader.setRoot(this);
       fXMLLoader.setController(this);
       
       try {
           fXMLLoader.load();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
    
   @FXML
   private void initialize(){
       Config.anchorPaneConst(this, 0.0, 0.0, 0.0, 0.0);
   }
}
