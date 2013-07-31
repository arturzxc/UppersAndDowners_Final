package Views.Board;

import Utils.BoardDrawingAlgorithms;
import Views.GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import uppersanddowners_final.Main;

/**
 *
 * @author arturzxc
 */
public class BoardSquarePanel extends JPanel{

    private GUI gui;
    private ArrayList<SquareComponent> squareComponents;
    
    public BoardSquarePanel() {
        this.gui=Main.gui;        
        this.setLayout(new GridLayout(BoardDrawingAlgorithms.getFieldsInRow(),BoardDrawingAlgorithms.getFieldsInRow()));   
        this.squareComponents = new ArrayList<SquareComponent>();
        this.addFieldsToArrayList();
        this.repositionFields();
        this.addAllComponents();
    }
    private void repositionFields(){ 
        if(BoardDrawingAlgorithms.getFieldsInRow()%2==0){
            squareComponents.addAll(BoardDrawingAlgorithms.translateBoard(squareComponents,1));//using algorithms from our BoardAlgorithms.                   
        }else{
            squareComponents.addAll(BoardDrawingAlgorithms.translateBoard(squareComponents,0));//using algorithms from our BoardAlgorithms.     
        }
    }
    
    private void addAllComponents(){
        for(int i=0;i<squareComponents.size();i++){
            this.add(squareComponents.get(i));
        }
    }
    
    private void addFieldsToArrayList(){
        for(int i=0;i<BoardDrawingAlgorithms.getNumberOfFields();i++){
                squareComponents.add(new SquareComponent(Main.model.getBoard().getSquares().get(i)));
        }
    }
    
    public void setEnableAll(boolean enabled){
        for(int i=0;i<squareComponents.size();i++){
            squareComponents.get(i).setEnabled(enabled);            
        }
    }
    
    public void setActivePopupMenus(boolean active){
        for(int i=0;i<squareComponents.size();i++){
            squareComponents.get(i).getPopupMenu().setActive(active);           
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;        
        try{
            BoardDrawingAlgorithms.setPositions(getWidth(), getHeight(), Main.model.getBoard().getNumOfFields());//everytime board is repainted, the algorithms are refreshed.        
        }catch(Exception e){//if the board is not yet constructed.            
        }
    }
}
