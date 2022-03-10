/**
 * The Gatherers deposit the gathered fruit at these Stockpiles until they are finished collecting all fruit.
 */
public class Stockpile extends FruitActor {
    /**
     * Type string to identify a Stockpile in the world file by ShadowLife class.
     */
    public static final String TYPE = "Stockpile";

    /**
     * This is the constructor of the Stockpile.
     * Starts off with 0 fruit.
     * @param x x-coordinate of the Stockpile.
     * @param y y-coordinate of the Stockpile.
     */
    public Stockpile(int x, int y){
        super("res/images/cherries.png", TYPE, x, y);
    }

    /**
     * Overridden update method of Actor. The Stockpile takes no action upon each tick.
     */
    @Override
    public void update(){}
}
