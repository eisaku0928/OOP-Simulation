/**
 * This is the Fence Actor. When MovingActors finish their jobs, they rest in front of fences.
 */
public class Fence extends Actor{
    /**
     * This is the type string used to identify a Fence.
     */
    public static final String TYPE = "Fence";

    /**
     * This is the constructor of any Fence, setting the coordinates of where it is placed.
     * @param x This is the x-coordinate of the Fence.
     * @param y This is the y-coordinate of the Fence.
     */
    public Fence(int x, int y){
        super("res/images/fence.png", TYPE, x, y);
    }

    /**
     * This is the update method of the Fence.
     */
    @Override
    public void update() {}
}
