package Views.Board;

import Utils.ResourceLoader;
import Views.GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import uppersanddowners_final.Main;

/**
 *
 * @author Mathan
 */
public class MiniScorePanel extends JPanel{
    
    private GUI gui;
    private ArrayList<JLabel> labels;
    private JPanel labelCont;
    private GridBagConstraints cons;
    
    public MiniScorePanel(){
        super(new GridBagLayout());
        setUpConstraints();
        gui = Main.gui;
        setUpMiniScorePanel();
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,43,60)), "Current Scores"));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,43,60)),
                       "Current Scores", 
                       TitledBorder.LEFT,TitledBorder.DEFAULT_POSITION, 
                       new Font("SansSerif", Font.PLAIN, 10), 
                       Color.WHITE));
        
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
        cons.anchor = GridBagConstraints.CENTER;
    }

    private void setUpMiniScorePanel() {
        this.labels = new ArrayList<JLabel>();
        this.labels.clear();
        this.labelCont = new JPanel(new GridBagLayout());
        this.removeAll();
        
        for(int i = 0; i<Main.model.getPlayers().size(); i++){
            this.labels.add(new JLabel(Main.model.getPlayers().get(i).getName() + " : " +  Main.model.getPlayers().get(i).getCurrentScore()));
            labels.get(i).setForeground(Color.WHITE);
            setConstraints(i, 0, 1, 1, 1, 1);
            add(labels.get(i),cons);
        }
    }
    
    public void refresh(){
        //System.out.println("refreshing data");
        this.setUpMiniScorePanel();
        //Main.gui.getMainFrame().getTotalContentPane().switchToGameContentPane();
        this.validate();
        for(int i = 0; i<labels.size(); i++){
            labels.get(i).setText(Main.model.getPlayers().get(i).getName() + " : " +  Main.model.getPlayers().get(i).getCurrentScore());
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Image img = ResourceLoader.loadImage("scorebg.png");
        g2d.drawImage(img, 0,0,getWidth(), getHeight(),this);
    }
    
}
