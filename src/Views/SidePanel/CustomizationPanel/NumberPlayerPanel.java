
package Views.SidePanel.CustomizationPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Mathan
 */
public class NumberPlayerPanel extends JLabel{
    
    private JRadioButton two, three, four;
    private ButtonGroup radioGroup;
    private CustomPlayerPanel customPlayerPanel;
    private GridBagConstraints cons;
    
    public NumberPlayerPanel(CustomPlayerPanel cpp){
        
        this.customPlayerPanel = cpp;
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Number of Players"));
        setUpConstraints();
        setUpRadioButtons();
    }
    
    private void setUpConstraints(){
        cons = new GridBagConstraints();
        cons.anchor=GridBagConstraints.NORTHWEST;
        cons.fill=GridBagConstraints.BOTH;
        cons.weightx=1;
        cons.weighty=1;
        cons.gridheight=1;
        cons.gridwidth=1;                
    }
    
    private void setConstraints(int gridx, int gridy,double weightx, double weighty, int gridheight,int gridwidth){
        cons.gridx=gridx;
        cons.gridy=gridy;
        cons.weightx=weightx;
        cons.weighty=weighty; 
        cons.gridheight=gridheight;
        cons.gridwidth=gridwidth;
    }
    

    private void setUpRadioButtons() {
        this.two = new JRadioButton("2");
        this.three = new JRadioButton("3");
        this.four = new JRadioButton("4");
        
        two.addActionListener(new RadioActionListener());
        three.addActionListener(new RadioActionListener());
        four.addActionListener(new RadioActionListener());
        
        radioGroup = new ButtonGroup();
        radioGroup.add(two);
        radioGroup.add(three);
        radioGroup.add(four);
        
        this.setConstraints(0, 0, 1, 0.1, 1, 1);
        add(two);
        this.setConstraints(1, 0, 1, 0.1, 1, 1);
        add(three);
        this.setConstraints(2, 0, 1, 0.1, 1, 1);
        add(four);
        
        
    }
    
    public JRadioButton getRadioInfo(int num){
        if (num==2){
            return two;
        }
        else if(num==3){
            return three;
        }
        
        else if(num==4){
            return four;
        }
        
        else{
            return null;
        }
    }
    
    public class RadioActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("2")){
                customPlayerPanel.getPlayerPanel().enableRows(2);
            }
           
            else if (ae.getActionCommand().equals("3")){
                customPlayerPanel.getPlayerPanel().enableRows(3);
            }
            
            else if (ae.getActionCommand().equals("4")){
                customPlayerPanel.getPlayerPanel().enableRows(4);
            }
        }
        
    }
}
