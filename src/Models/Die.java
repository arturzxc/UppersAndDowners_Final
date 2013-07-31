package Models;
/**
 *
 * @author Software Engineering 2011-2012 (Group G)
 */
public class Die {
    private int numberOfSides;
    private int numberRolled;

    /**
     * The Default constructor. Six sided die.
     */
    public Die() {
        this.numberOfSides = 6;
    }
    /**
     * Custom Die Constructor.
     * @param numberOfSides 
     */
    public Die(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }
    /**
     * Will return the noOfSides.
     * @return 
     */
    public int getNumberOfSides() {
        return numberOfSides;        
    }
    /**
     * Will return the number last rolled.
     * @return 
     */
    public int getNumberRolled() {
        return numberRolled;                       
        //return 99; 
    }
    
    /**
     * Will set the current rolled number to the argument.
     * @param numberRolled
     * @throws IllegalArgumentException 
     */
    public void setNumberRolled(int numberRolled)
            throws IllegalArgumentException{
        if(numberRolled < 0 || numberRolled > this.getNumberOfSides()){
            throw new IllegalArgumentException("Must be a number between 1 and "
                     +this.getNumberOfSides());
        }

        this.numberRolled = numberRolled;
    }

    /**
     * Random number between 1 and number of sides
     * @return Random number between 1 and number of sides
     */
    public void roll(){
        int random = (int)(this.getNumberOfSides() * Math.random()) + 1;
        //System.out.println(random);
        this.numberRolled=random;
    }
}