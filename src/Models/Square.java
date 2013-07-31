package Models;

import java.awt.Image;
/** 
 * @author Group G
 * 
 * A board is made out of this squares. There is a wrapper class in the view
 * that uses the square as its attribute. They make a complete square component
 */
public class Square {

private int number;
private boolean hasScore;
private int score;
private Image image;
private Image imageHover;
private Image imageForward;
private Image imageBackward;
private boolean occupied;
private boolean occupiedByUpper;

    public Square(int number, Image image, Image imageHover, Image imageForward, Image imageBackward) {
        this.number = number;
        this.score = 0;
        this.hasScore = false;
        this.image = image;
        this.imageHover = imageHover;
        this.imageForward = imageForward;
        this.imageBackward = imageBackward;
        this.occupied=false;
    }

    public boolean isOccupiedByUpper() {
        return occupiedByUpper;
    }

    public void setOccupiedByUpper(boolean occupiedByUpper) {
        this.occupiedByUpper = occupiedByUpper;
    }
    
    public boolean hasScore(){
        return hasScore;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Image getImage() {
        return image;
    }

    public int getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if(score != 0){
            this.hasScore = true;
        }
    }

    public Image getImageBackward() {
        return imageBackward;
    }

    public Image getImageForward() {
        return imageForward;
    }

    public Image getImageHover() {
        return imageHover;
    }
    
    

}