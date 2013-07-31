package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Models.Climable;
import Models.Downer;
import Models.Game;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class BoardUpAndDownPanel extends JPanel{
    
    private Game game;

    public BoardUpAndDownPanel() {
        this.game = Main.model;
    }

        private void paintUppersAndDowners(Graphics2D g2d){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            AffineTransform origTransform = g2d.getTransform();//original transform

            //DRAW UPPERS AND DOWNERS        
            for(int i=0;i<this.game.getBoard().getClaimables().size();i++){   
                Dimension coordinates;
                if(this.game.getBoard().getClaimables().get(i) instanceof Downer){
                    coordinates=BoardDrawingAlgorithms.getCoordinateOfPosition(
                            this.game.getBoard().getClaimables().get(i).getHead());//edited            
                }

                else{
                    coordinates=BoardDrawingAlgorithms.getCoordinateOfPosition(
                            this.game.getBoard().getClaimables().get(i).getTail());//edited
                }

                AffineTransform transformer = new AffineTransform();

                Climable current = this.game.getBoard().getClaimables().get(i);
                //set rotation for each ladder
                int angle = BoardDrawingAlgorithms.findAngle(this.game.getBoard().getClaimables().get(i).getHead(),
                                                             this.game.getBoard().getClaimables().get(i).getTail(),
                                                             current);
                //System.out.println("angle:" + angle);
                transformer.rotate(Math.toRadians(angle), 
                                   coordinates.width, 
                                   coordinates.height);
                g2d.setTransform(transformer);

                Image climable=game.getBoard().getClaimables().get(i).getImg();

                int widthpadding=0;
                int heightpadding=0;
                if(angle <0){
                    widthpadding = -10;
                    heightpadding = 50;
                }
                else if(angle >0){
                    widthpadding = 30;
                    heightpadding = 15;
                }

                else {
                    widthpadding = 15;
                    heightpadding = 25;
                }

                if(this.game.getBoard().getClaimables().get(i) instanceof Downer){ //different scaling
                    g2d.drawImage(
                            climable,
                            coordinates.width+widthpadding, 
                            coordinates.height+heightpadding,
                            BoardDrawingAlgorithms.getWidthOfshape(),
                            BoardDrawingAlgorithms.findLength(
                                this.game.getBoard().getClaimables().get(i).getHead(), 
                                this.game.getBoard().getClaimables().get(i).getTail()),
                            this);
                }

                else{
                    g2d.drawImage(
                            climable,
                            coordinates.width+widthpadding, 
                            coordinates.height+heightpadding,
                            BoardDrawingAlgorithms.getWidthOfshape()/2,
                            BoardDrawingAlgorithms.findLength(
                                this.game.getBoard().getClaimables().get(i).getHead(), 
                                this.game.getBoard().getClaimables().get(i).getTail()),
                            this);
                }


            }        
            g2d.setTransform(origTransform);//set the transform back to its oryginal form                
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        paintUppersAndDowners(g2d);
        
    }
    
    
    
}
