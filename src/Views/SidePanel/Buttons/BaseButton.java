/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.Buttons;

import Utils.ResourceLoader;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.JLabel;

/**
 *
 * @author arturzxc
 */
public abstract class BaseButton extends JLabel implements MouseListener{
    
    protected Image currentImage;
    
    public BaseButton(String tooltipUrl) {        
        URL url = ResourceLoader.getImageURL(tooltipUrl);
        String tooltiptext =  "<html><img src=\"" +url+"\"></html>"; 
        if(!tooltipUrl.equalsIgnoreCase("none")){
            setToolTipText(tooltiptext);
        }
        setOpaque(false);                                
    }


    
}
