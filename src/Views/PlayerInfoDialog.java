
package Views;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class PlayerInfoDialog extends JDialog implements ActionListener{
    
    private GUI gui;
    private JLabel nameLabel, errorMsg;
    private String error;
    private JTextField name;
    private JButton ok;
    
    private GridBagConstraints cons;
    
    public PlayerInfoDialog(){
        gui = Main.gui;
        this.setLayout(new GridBagLayout());
        setUpConstraints();
        setUpDialog();     

    }
    
    /**
     * Sets up GridBag constraints for MenuContentPane.
     */
    private void setUpConstraints(){
        cons = new GridBagConstraints();
        cons.anchor=GridBagConstraints.NORTHWEST;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        cons.gridheight=1;
        cons.gridwidth=1;                
    }
    
    /**
     * This method is used to modify GridBagConstraints for PlayerInfoDialog.
     * @param gridx
     * @param gridy
     * @param weightx
     * @param weighty
     * @param gridheight
     * @param gridwidth 
     */
    private void setConstraints(int gridx, int gridy,double weightx, double weighty, int gridheight,int gridwidth){
        cons.gridx=gridx;
        cons.gridy=gridy;
        cons.weightx=weightx;
        cons.weighty=weighty; 
        cons.gridheight=gridheight;
        cons.gridwidth=gridwidth;
        cons.insets = new Insets(4,4,4,4);
        cons.anchor = GridBagConstraints.CENTER;
    }
    
    /**
     * Sets up Player Info Dialog.
     */
    private void setUpDialog() {
        this.nameLabel = new JLabel("Enter your name:");
        this.errorMsg = new JLabel(error);
        errorMsg.setForeground(Color.red);
        
        setLocationRelativeTo(null);
        setModal(true);
        
        this.name = new JTextField();
        this.ok = new JButton("Ok");
        this.ok.addActionListener(this);
        
        setConstraints(0, 0, 1, 1, 1, 1);
        this.add(nameLabel,cons);
        
        setConstraints(1, 0, 1, 1, 1, 1);
        this.add(name,cons);
        
        setConstraints(1, 1, 1, 1, 1, 1);
        this.add(ok,cons);
        
        setConstraints(0, 2, 1, 1, 1, 2);
        this.add(errorMsg,cons);
        
        this.setUndecorated(true);
        this.pack();
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!name.getText().isEmpty()){
            Main.model.addHumanPlayer(name.getText());
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getMiniScorePanel().refresh();
            this.setVisible(false);
        }
        
        else { 
            errorMsg.setText("Enter the required fields!");   
        }
    }
    
}
