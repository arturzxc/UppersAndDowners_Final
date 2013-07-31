/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.SidePanel.Buttons;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author arturzxc
 */
public class ButtonsPanel extends JPanel{
    
    private ExitBtn exitBtn;
    private MoveBackButton moveBackBtn;
    private MoveForwardBtn moveForwardBtn;
    private RollBtn rollBtn;
    
    public ButtonsPanel() {
        setLayout(new GridLayout(0, 1));
        setOpaque(false);
        
        setUpButtons();
    }
    
    private void setUpButtons(){
        rollBtn = new RollBtn("none");
        moveForwardBtn = new MoveForwardBtn("moveForwardToolTip.png");
        moveBackBtn = new MoveBackButton("moveBackwardToolTip.png");
        exitBtn = new ExitBtn("none");
        
        add(rollBtn);
        add(moveForwardBtn);
        add(moveBackBtn);
        add(exitBtn);
    }
    
    public void disableAll(){//when token moves no button should be enabled apart from exit
        rollBtn.setEnabled(false);
        moveForwardBtn.setEnabled(false);
        moveBackBtn.setEnabled(false);        
    }

    public ExitBtn getExitBtn() {
        return exitBtn;
    }

    public MoveBackButton getMoveBackBtn() {
        return moveBackBtn;
    }

    public MoveForwardBtn getMoveForwardBtn() {
        return moveForwardBtn;
    }

    public RollBtn getRollBtn() {
        return rollBtn;
    }
    
    
}
