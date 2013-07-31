/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Models.Game;
import Models.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class BoardTokensPanel extends JPanel{
    private Game game;    

    public BoardTokensPanel() {
        this.game = Main.model;
        
    }
    
    private void paintTokens(Graphics2D g2d){
        
        //DRAW TOKENS Must draw backwards so the starting player is drawn on the top
        for(int i=this.game.getPlayers().size()-1;i>=0;i--){   
            
            //DRAW TOKENS
            Player p = game.getPlayers().get(i);
            Dimension coordinatesOfToken=BoardDrawingAlgorithms.getCoordinateOfPosition(game.getPositionsOfPlayers().get(i));            
            int width=0;
            int height=0;
            if(p.getPosition()==1){//Apply shift only at position 1 to see all the players
                width=(int)coordinatesOfToken.getWidth()+7;
                height=(int) coordinatesOfToken.getHeight()+i*2-7;
            }else{
                width=(int)coordinatesOfToken.getWidth();
                height=(int) coordinatesOfToken.getHeight();
            }
            g2d.drawImage(                               
                    p.getImage(),
                    width, 
                    height,
                    BoardDrawingAlgorithms.getWidthOfshape(),
                    BoardDrawingAlgorithms.getHeightOfshape(),
                    this);
            //DRAW NAME
            g2d.drawString(p.getName(), 
                    width+BoardDrawingAlgorithms.getHeightOfshape()/p.getName().length(),
                    height+BoardDrawingAlgorithms.getHeightOfshape()/2);
        }
    }    
   
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(255,255,255));//set color of number if fields 
        paintTokens(g2d);
    }
    
}
