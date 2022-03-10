import bagel.Image;
import bagel.util.Point;

/**
 * The ShadowLife class delegates all actors read from the world file into this class.
 * The actor class creates an actor, renders them, and calls the update method of any subclass actors every tick.
 */
public abstract class Actor {
    // Declare variables for position and "Image" class image for each actor
    private int x;
    private int y;
    private final Image image;

    /**
     * The "type" is passed in from subclasses, to specify which kind of actor to be created.
     * Used in events of actors standing on another actor to identify which actor they are stepping on.
     */
    public final String type;

    /**
     * This is the constructor for an actor that creates the image of the actor from the filename,
     * and sets the type and position of the specific actor.
     * @param filename This is the filename of the creating actor.
     * @param type This is the type name of the creating actor.
     * @param x This is the x coordinate of the actor.
     * @param y This is the y coordinate of the actor.
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x-coordinate of current actor.
     * @return int This is the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate of current actor.
     * @return int This is the y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * This method is called every tick from ShadowLife, and will call the update method of subclasses.
     */
    public final void tick() {
        update();
    }

    /**
     * This is called from the update method of ShadowLife and will draw each actor.
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /**
     * Any mobile actors can call the move method to update the x or y coordinates to "move" the actor.
     * @param deltaX This is the change in x coordinate of the actor.
     * @param deltaY This is the change in y coordinate of the actor.
     */
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    /**
     * This method checks if the actors are at the same position.
     * @param otherActor This is the other actor to check if the current actor is on top of it.
     * @return boolean Returns true if actors are at same position. Returns false if not.
     */
    public boolean isSamePosition(Actor otherActor){
        return otherActor.getPoint().equals(this.getPoint()) && this != otherActor;
    }

    /**
     * This is a method that gets called if the actors are at the same position.
     * Left blank to be overridden by each child classes of MovingActor.
     * @param otherActor This is the other actor used by the MovingActor
     *                   to identify what kind of action they must take.
     */
    public void samePositionEvent(Actor otherActor){}

    // This method creates a point class object to be used in the isSamePosition function.
    private Point getPoint(){
        return new Point(x, y);
    }

    /**
     * This is an abstract update method, meant to be overridden by subclasses.
     */
    public abstract void update();
}
