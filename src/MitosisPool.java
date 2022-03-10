/**
 * This is the MitosisPool class. When a moving actor steps into it, two of the same actors are created that
 * moves to the left and right of the currently facing direction.
 */
public class MitosisPool extends Actor{
    /**
     * This is the type string to identify a MitosisPool.
     */
    public static final String TYPE = "Pool";

    /**
     * This is the constructor of the MitosisPool.
     * @param x This is the x axis of the pool.
     * @param y This is the y axis of the pool.
     */
    public MitosisPool(int x, int y){
        super("res/images/pool.png", TYPE, x, y);
    }

    /**
     * This is the overridden update method. The MitosisPool takes no action upon every tick.
     */
    @Override
    public void update() {}
}
