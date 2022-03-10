/**
 * Thieves steal fruit from the stockpiles and place it in these Hoards.
 */
public class Hoard extends FruitActor {
    /**
     * Type string to identify a Hoard in the world file by ShadowLife class.
     */
    public static final String TYPE = "Hoard";

    /**
     * This is the constructor of the Hoard.
     * Starts off with 0 fruit.
     * @param x x-coordinate of the Hoard.
     * @param y y-coordinate of the Hoard.
     */
    public Hoard(int x, int y){
        super("res/images/hoard.png", TYPE, x, y);
    }

    /**
     * Overridden update method of Actor. The Hoard takes no action upon each tick.
     */
    @Override
    public void update(){}
}