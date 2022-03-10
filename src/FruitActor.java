import bagel.Font;

/**
 * This is the superclass of any fruit-carrying actor.
 * MovingActors can carry away or bring fruit to these actors depending on what kind of FruitActor they are.
 * Current fruit is drawn at the top-left of its image.
 */
public abstract class FruitActor extends Actor{
    // Each type of FruitActor has a fruit counter and a font to draw it (except for the Golden Tree)
    private int fruitCount;
    private final Font FONT = new Font("res/VeraMono.ttf", 20);

    /**
     * Constructor for a fruit actor.
     * @param filename This is the filename of the creating actor.
     * @param TYPE This is the type name of the creating actor.
     * @param x This is the x coordinate of the actor.
     * @param y This is the y coordinate of the actor.
     */
    public FruitActor(String filename, String TYPE, int x, int y){
        super(filename, TYPE, x, y);
    }

    /**
     * This method increments the fruit counter by 1.
     */
    public void incrementFruitCount(){
        ++fruitCount;
    }

    /**
     * This method decrements the fruit counter by 1.
     */
    public void decrementFruitCount(){
        --fruitCount;
    }

    /**
     * This method is a getter for the fruit counter
     * @return int This is the fruitCount of the FruitActor.
     */
    public int getFruitCount() {
        return fruitCount;
    }

    /**
     * This method sets the fruitCount of the FruitActor.
     * Used upon creating FruitActors.
     * @param fruitCount This is the int that the FruitActor sets.
     */
    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }

    /**
     * This method is overriding the render method of the actor to draw the fruitCount of each FruitActor
     * at its top-left corner.
     */
    @Override
    public void render(){
        super.render();
        // Don't draw negative fruitCount.
        if (fruitCount >= 0){
            FONT.drawString("" + fruitCount, getX(), getY());
        }
    }

    /**
     * This is the update method meant to be overridden by each FruitActor.
     */
    @Override
    public abstract void update();
}
