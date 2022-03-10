/**
 * This is the Pad. Whenever a thief stands on a pad, it will be consuming.
 */
public class Pad extends Actor {
    /**
     * This is the type string to identify a Pad.
     */
    public static final String TYPE = "Pad";

    /**
     * This is the constructor for the pad, setting the coordinates of where it is placed.
     * @param x This is the x-coordinate of the Pad.
     * @param y This is the y-coordinate of the Pad.
     */
    public Pad(int x, int y){
        super("res/images/pad.png", TYPE, x, y);
    }

    /**
     * This is the overridden update method of the Pad. The pad takes no action upon every tick.
     */
    @Override
    public void update() {}
}
