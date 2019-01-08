/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesaj.bin;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 *
 * @author oguz
 */
public class Config {

    public static void anchorPaneConst(Region region, double top, double left, double right, double botton){
        AnchorPane.setBottomAnchor(region, botton);
        AnchorPane.setTopAnchor(region, top);
        AnchorPane.setLeftAnchor(region, left);
        AnchorPane.setRightAnchor(region, right);
    }
}
