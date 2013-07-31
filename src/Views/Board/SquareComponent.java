/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Models.*;
import Views.GUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import uppersanddowners_final.Main;

/**
 *
 * @author Mike
 */
public class SquareComponent extends JLabel implements MouseListener{

private Image currentImage;
private Square square;
private GUI gui;
private Game game;
private Die die;   
private Player player;
private SquarePopUpMenu popupMenu;


    public SquareComponent(Square square){
        this.square=square;       
        this.gui=Main.gui;
        this.currentImage = square.getImage();
        this.setSize(new Dimension(200,200));
        this.setPreferredSize(new Dimension(BoardDrawingAlgorithms.getWidthOfshape(),BoardDrawingAlgorithms.getHeightOfshape()));                
        
        game = Main.model;     
        die = game.getBoard().getDie();        
//        player = gui.getGame().getPlayerWhosTurn();
        
        this.addMouseListener(this);
        this.setComponentPopupMenu(popupMenu=new SquarePopUpMenu(this));        
        
        setEnabled(false);
    }

    public SquarePopUpMenu getPopupMenu() {
        return popupMenu;
    }

    
    
    public Square getSquare() {
        return square;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    
    //NEEDS ATTENTION!
    @Override    
    public void mousePressed(MouseEvent e) {   

        /*
            if(isEnabled()){
                game.movePlayer(true);              
                gui.getMoveForwardBtn().setEnabled(false);
                gui.getMoveBackButton().setEnabled(false);
                gui.getRollBtn().setEnabled(true);  
                gui.getBoardPanel().getBoardSquarePanel().setEnableAll(false);
                repaint();
            }else{
                gui.displayMsg("Clicking on squares locked untill you roll");
            }
      
      * 
      */
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.currentImage = square.getImageHover();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.currentImage = square.getImage();
    }
    
    private void pickImage(){
        this.currentImage=square.getImage();
        if(square.isOccupied() && square.isOccupiedByUpper()){
            this.currentImage=square.getImageForward();
        }        
        if(square.isOccupied() && !square.isOccupiedByUpper()){
            this.currentImage=square.getImageBackward();
        }       
        if(isEnabled()){
            if(square.getNumber()==game.getPlayerWhosTurn().getPrevPos()+game.getBoard().getDie().getNumberRolled()){
                Image outlinePos = new ImageIcon(Object.class.getResource("/resources/images/positionOutline.png")).getImage();
                this.currentImage=outlinePos;
            }
            if(square.getNumber()==game.getPlayerWhosTurn().getPrevPos()-game.getBoard().getDie().getNumberRolled()){
                Image outlinePos = new ImageIcon(Object.class.getResource("/resources/images/positionOutline2.png")).getImage();
                this.currentImage=outlinePos;
            }        
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d= (Graphics2D)g;                
        pickImage();
        g2d.drawImage(this.currentImage,0,0,getWidth(),getHeight(),this);        
        paintScore(g2d);
    }
    
    private void paintScore(Graphics2D g2d){
                
        Color bg = new Color(205,201,201, 150);
        g2d.setColor(bg);        
        
        g2d.setColor(Color.white);
        String str=Integer.toString(square.getNumber());
        g2d.setFont(new Font("sansserif", Font.BOLD, 10));
        g2d.drawString(str, 10, 15);    //Square number
        
        int score=square.getScore();
        if(score!=0){
            if(score<0){
                bg = new Color(Math.abs(score),0,0, 200);
            }else{
                bg = new Color(0,Math.abs(score),0, 200);
            }
            g2d.setColor(bg);
            g2d.fillRect(0, 30, 35, 20);  


            g2d.setColor(Color.white);
            str=Integer.toString(square.getScore());
            g2d.setFont(new Font("sansserif", Font.BOLD, 10));
            g2d.drawString(str, 15, 40);    //Square number
        }
              
    }
    
}
