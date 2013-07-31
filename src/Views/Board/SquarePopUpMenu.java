/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Views.GUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class SquarePopUpMenu extends JPopupMenu {
    private GUI gui; 
    
    private JTextField scoreTF;    
    private JTextField input1TF;    
    private JTextField input2TF;    
    
    private JButton setScoreBtn;
    private JButton addClimableBtn;
    
    private SquareComponent sqrComp;
       
    private JMenu setScoreMenu;
    private JMenu addUpOrDownMenu;
    private JPanel setScorePanel;
    
    private Color color;
    
    public SquarePopUpMenu(SquareComponent sqrComp) {
        this.gui=Main.gui;
        this.sqrComp=sqrComp;  
        this.color=new Color(250,250,250,200);
        setBackground(color);     
        setBorderPainted(false); 
                    
        setActive(false);
        
    }
    
    public void setActive(boolean active){
        if(active){
            setUpMenuItems();
            createScoreAdding();
            createClimableAdding();
        }else{
            removeAll();
            revalidate();
            repaint();
        }
    }
    
    private void setUpMenuItems(){      
       removeAll();
       setScoreMenu = new JMenu("Set Score");       
       setScoreMenu.setToolTipText("If player lands on this square he/she will receive this points");
       addUpOrDownMenu= new JMenu("Add Upper or Downer");     
       addUpOrDownMenu.setToolTipText("If head is higher than tail then downer will be added else upper");
       this.add(setScoreMenu);
       this.add(addUpOrDownMenu);
       revalidate();
       repaint();
    }
    
    private void createScoreAdding(){
        setScorePanel = new JPanel();               
        setScorePanel.setLayout(new GridLayout(1,2));        
        
        scoreTF = new JTextField();     
        scoreTF.setText("10");        
        
        setScoreBtn = new JButton("Set Score");        
        setScoreBtn.addActionListener(new SetScoreListener());        

        setScorePanel.setBackground(color);  
        setScorePanel.add(new JLabel("Point"));
        setScorePanel.add(scoreTF);
        setScorePanel.add(setScoreBtn);
        
        setScoreMenu.add(setScorePanel);
    }
    
    private void createClimableAdding(){
        JPanel panel = new JPanel();   
        panel.setLayout(new GridLayout(0,2)); 
        panel.setBackground(color);     
        
        input1TF = new JTextField(Integer.toString(sqrComp.getSquare().getNumber()));
        input1TF.setEnabled(false);
        input2TF = new JTextField();
        addClimableBtn = new JButton("Add");
        addClimableBtn.addActionListener(new AddClimableListener());
 
        panel.add(new JLabel("Origin"));
        panel.add(input1TF);
        panel.add(new JLabel("Destination"));
        panel.add(input2TF);
        panel.add(addClimableBtn);        
        addUpOrDownMenu.add(panel);
    }

        
    
    class AddClimableListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int headInt;
            int tailInt;
            try{
               headInt=Integer.parseInt(input1TF.getText());
               tailInt=Integer.parseInt(input2TF.getText());
               int difference = Math.abs(headInt-tailInt);
               int minDifference=2;
               if(difference<minDifference || tailInt < 1 || tailInt > BoardDrawingAlgorithms.getNumberOfFields()){
                    gui.showMessage("The difference between the head and the tail must be at least "+minDifference+" or you entered invalid number");
                    return;
               }else{
                   if(headInt>tailInt){
                       Main.model.getBoard().addDowner(headInt,tailInt);
                       Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().repaint();
                   }else{
                       Main.model.getBoard().addUpper(headInt,tailInt);  
                   }
                   setVisible(false);
               }
            }catch(Exception ex){  
                System.out.println("error" + ex);
                gui.showMessage("Could not enter that value!");
                return;
            }
             //sometimes after popup the board doesnt refresh therefore this repaint.this issue is only on windows.      
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().repaint();       
        }  
    }    

    class SetScoreListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try{                
                int score = Integer.parseInt(scoreTF.getText());
                if(score>254 || score<-254){
                    gui.showMessage("Max score is 254 or-254!");
                    return;
                }
                sqrComp.getSquare().setScore(score);                
                setVisible(false);
            }catch(Exception ex){
                gui.showMessage("Could not enter that value!");
            }
            //sometimes after popup the board doesnt refresh therefore this repaint.this issue is only on windows.      
            Main.gui.getMainFrame().getTotalContentPane().getGameContentPane().getBoardPanel().repaint(); 
        }        
    }
    
    
 
}
