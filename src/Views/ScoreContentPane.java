package Views;

import Utils.ResourceLoader;
import Views.ScorePanelElements.BodyCell;
import Views.ScorePanelElements.TopCell;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class ScoreContentPane extends JPanel {
    
    private GridBagConstraints cons;
    private ArrayList<BodyCell>nameWrap;
    private ArrayList<BodyCell>scoreWrap;
    private ArrayList<BodyCell>squareReachedWrap;
    
    public ScoreContentPane(){
        setLayout(new GridBagLayout());                 
        setUpCconsonstraints();

    }
    
    public void composeComponents(){
        //top row
        setConstraints(0, 0, 0.3, 0.25, 1, 1);
        add(new TopCell("Names"),cons);
        setConstraints(1, 0, 0.3, 0.25, 1, 1);
        add(new TopCell("Scores"),cons);
        setConstraints(2, 0, 0.3, 0.25, 1, 1);
        add(new TopCell("Square"),cons);
        
        //body rows
        for(int i=0;i<Main.model.getPlayers().size();i++){
            setConstraints(0, i+1, 0.3, 0.25, 1, 1);
            add(nameWrap.get(i),cons);
            
            setConstraints(1, i+1, 0.3, 0.25, 1, 1);            
            add(scoreWrap.get(i),cons);
            
            setConstraints(2, i+1, 0.3, 0.25, 1, 1);
            add(squareReachedWrap.get(i),cons);            
        }
        
    }

    public void initComponents(){
        nameWrap=new ArrayList<BodyCell>();
        scoreWrap=new ArrayList<BodyCell>();
        squareReachedWrap=new ArrayList<BodyCell>();
        for(int i=0;i<Main.model.getPlayers().size();i++){
            nameWrap.add(new BodyCell(Main.model.getPlayers().get(i).getName()));
            scoreWrap.add(new BodyCell(Main.model.getPlayers().get(i).getCurrentScore()+""));
            squareReachedWrap.add(new BodyCell(Main.model.getPlayers().get(i).getPosition()+""));
            
        }
    }
    
    
    /**
     * Sets up GridBag constraints for MenuContentPane.
     */
    private void setUpCconsonstraints(){
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
    }
    
    @Override
    public void paintComponent(Graphics g){
        this.setBorder(BorderFactory.createEmptyBorder(getHeight()/5, getWidth()/4, getHeight()/5, getWidth()/4));
        g.drawImage(ResourceLoader.loadImage("squarelighter.png"), 0, 0,this.getWidth(),this.getHeight(), this);
    }
    
}