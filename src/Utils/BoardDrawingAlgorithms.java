/**
 * This is a static class that calculates coordinates of fields
 * tokens and others. It is used in conjunction with the BoardPanl
 * 
 */
package Utils;

import Models.Climable;
import Models.Downer;
import Models.Upper;
import java.awt.Dimension;
import java.util.ArrayList;
/**
 *
 * @author arturzxc, mv300
 */

public class BoardDrawingAlgorithms{    
    
    private static ArrayList<Dimension> positions = new ArrayList<Dimension>(); 
    private static int widthOfshape;
    private static int heightOfshape;
    private static int heightOfBoard;
    private static int widthOfBoard;
    private static int fieldsInRow;
    
    /**
     * Returns the Current Height of the board.
     * @return 
     */
    public static int getHeightOfBoard() {
        return heightOfBoard;
    }
    
    /**
     * Returns the number of fields in the board.
     * @return 
     */
    public static int getFieldsInRow() {
        return fieldsInRow;
    }

    /**
     * Returns the width of the board.
     * @return 
     */
    public static int getWidthOfBoard() {
        return widthOfBoard;
    }
    
    /**
     * Returns the height of the fields.
     * @return 
     */
    public static int getHeightOfshape() {
        return heightOfshape;
    }

    /**
     * Returns the width of the shape.
     * @return 
     */
    public static int getWidthOfshape() {
        return widthOfshape;
    }       

    /**
     * Translates the board into a "Snake - like" format.
     * @param <T>
     * @param a
     * @param rowMultiplierReverse
     * @return 
     */
    public static <T> ArrayList<T> translateBoard(ArrayList<T> a, int rowMultiplierReverse){   //row reverse multip. is to eaither reverse odd or even rows.     
        
        if(rowMultiplierReverse>1 || rowMultiplierReverse <0){
            rowMultiplierReverse =0;
        }
        
        a =  reverse(a);
        int fieldsPerRow = (int)Math.sqrt(a.size());
        int from = 0;
        int to = fieldsPerRow;

        /*Creates an ArrayList of ArrayList called "whole" which then adds dimensions to the inner ArrayList*/
        ArrayList<ArrayList<T>> whole  = new ArrayList<ArrayList<T>>();
        for (int i = 0; i<fieldsPerRow;i++) {
            whole.add(new ArrayList<T>());
            for(int j = from; j < to ;j++) {
                whole.get(i).add(a.get(j));
            }

            from += fieldsPerRow;
            to += fieldsPerRow;
        }

        for(int i = rowMultiplierReverse; i<whole.size();i+=2) {
            whole.set(i, reverse(whole.get(i)));
        }

        ArrayList<T> joined = new ArrayList<T>();

        for(int i = 0; i<whole.size(); i++) {
            joined.addAll(whole.get(i));
        }

        return joined;



    }

    /**
     * Reverses all elements in an ArrayList. Used to create snake like format.
     * @param <T>
     * @param a
     * @return 
     */
    private static <T> ArrayList<T> reverse(ArrayList<T> a){
        ArrayList<T> b = new ArrayList<T>();

        for(int i=a.size()-1; i>=0; i--){
            b.add(a.get(i));
        }
        return b;
    }
    
    /**
     * Sets positions of all the fields in the board as Dimension objects. To be used for calculations.
     * @param width
     * @param height
     * @param fields 
     */
    public static void setPositions(int width, int height, int fields){ 
        
        positions.removeAll(positions);//clear the old arratlist
        
        widthOfBoard=width;
        heightOfBoard=height;

        fieldsInRow=(int)Math.sqrt(fields);                 
        if(fieldsInRow*fieldsInRow!=fields)
            throw new Error("Wrong number of fields passed!"); 

        widthOfshape = width/fieldsInRow; 

        heightOfshape = height/fieldsInRow; 
        int leftPadding=0;
        widthOfBoard+=leftPadding;
        int heightIncrement=0;
        int widthIncrement=leftPadding;
        for(int i=0;i<fieldsInRow;i++){
            for(int j=0;j<fieldsInRow;j++){
                positions.add(new Dimension(widthIncrement, heightIncrement));//keeep adding row element
                widthIncrement+=widthOfshape;
            }
            widthIncrement=leftPadding;//reset width for next row
            heightIncrement+=heightOfshape;   //after a row has been added, increment height and caryon to next row and fill it in from beginning        
        }   
        positions=translateBoard(positions,0);//translate positions
        
        for(int i = 0; i<positions.size() ; i++){
            //System.out.println("Pos:" + (i+1) + " " + positions.get(i).height + "," + positions.get(i).width);
        }
    }
    
    /**
     * Returns the ArrayList containing all positions of fields.
     * @return 
     */
    public static ArrayList<Dimension> getPositions() {
        return positions;
        
    }

    /**
     * Returns the number of fields on the board.
     * @return 
     */
    public static int getNumberOfFields(){
        return positions.size();
    }
    
    /**
     * Returns a dimension of the requested field.
     * @param position
     * @return 
     */
    public static Dimension getCoordinateOfPosition(int position){        
        if(position>=0)
            return positions.get(position-1);
        else{
            return new Dimension(0,heightOfBoard-heightOfshape-50);
        }

    }
    
    /**
     * Returns the angle for rotation of climbables on the board.
     * @param head
     * @param tail
     * @param instance
     * @return 
     */
    public static int findAngle(int head, int tail, Climable instance){
        double angle = 0.0;
        double hyp,op;
        if(instance instanceof Downer){ //downer going left to right
            if(getCoordinateOfPosition(head).width > getCoordinateOfPosition(tail).width){
                hyp = findLength(head,tail);
                op = getCoordinateOfPosition(head).width - getCoordinateOfPosition(tail).width;
                double sintheta = op/hyp;
                angle = Math.toDegrees(Math.asin(sintheta));
                return (int)angle;
            }
            
            else if(getCoordinateOfPosition(head).width < getCoordinateOfPosition(tail).width){
                //downer going right to left
                hyp = findLength(head, tail);
                op = getCoordinateOfPosition(tail).width - getCoordinateOfPosition(head).width;
                double sintheta = op/hyp;
                angle = Math.toDegrees(Math.asin(sintheta));
                angle = angle*-1;
                return (int)angle;
            }
            
            else {
                return (int)angle;
            }
        }
        
        else if (instance instanceof Upper){
            if(getCoordinateOfPosition(head).width > getCoordinateOfPosition(tail).width) {
                //uppers going right to left
                hyp = findLength(head,tail);
                op = getCoordinateOfPosition(head).width - getCoordinateOfPosition(tail).width;
                double sintheta = op/hyp;
                angle = Math.toDegrees(Math.asin(sintheta));
                angle = angle*-1; //turn into negatve
                return (int)angle;
            }

            else if (getCoordinateOfPosition(head).width < getCoordinateOfPosition(tail).width) {
                //uppers going left to right
                hyp = findLength(head, tail);
                op = getCoordinateOfPosition(tail).width - getCoordinateOfPosition(head).width;
                double sintheta = op/hyp;
                angle = Math.toDegrees(Math.asin(sintheta));
                return (int)angle;
            }

            else {
               //uppers going straight up
               return (int)angle;
            }
        }
        
        return -1;
    }
    
    /**
     * Calculates the length for a Climbable.
     * @param head
     * @param tail
     * @return 
     */
    public static int findLength(int head, int tail){
        double center = getCoordinateOfPosition(tail).width/2; // we want to find length from center of square, not corner
        double dx = (getCoordinateOfPosition(head).width + center) - (getCoordinateOfPosition(tail).width + center);
        double dy = (getCoordinateOfPosition(head).height + center) - (getCoordinateOfPosition(tail).height + center);
        double length = Math.sqrt(dx*dx + dy*dy);//distance using pythagoras theorem
        return (int)length;
    }
    
    /**
     * Converts any dimension object into a field Position.
     * @param dim
     * @return 
     */
    public static int covertDimToPos(Dimension dim){
        for(int i = 0; i<positions.size(); i++){
            if(dim.height<positions.get(i).height+widthOfshape && dim.height>positions.get(i).height){
                if(dim.width<positions.get(i).width+widthOfshape && dim.width>positions.get(i).width){
                    return i+1;
                }
            }
        }
        return -1;
    }
}