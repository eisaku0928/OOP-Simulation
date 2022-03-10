/**
 * This is the superclass of any mobile actor.
 * Three main characteristics are carrying a fruit, being active, and setting a direction.
 */
public abstract class MovingActor extends Actor{


    // Declare the attributes that define a moving actor
    private boolean active;
    private boolean carrying;
    private int direction;

    /**
     * Constructor for any moving actor.
     * @param filename This is the filename of the creating actor.
     * @param TYPE This is the type name of the creating actor.
     * @param x This is the x coordinate of the actor.
     * @param y This is the y coordinate of the actor.
     */
    public MovingActor(String filename, String TYPE, int x, int y){
        // Pass the filename, type, and coordinates to the parent "Actor" class.
        super(filename, TYPE, x, y);

        // Actors are not carrying any fruit and are active when created
        carrying = false;
        active = true;
    }

    /**
     * This method is for the MovingActors to check if they are currently active.
     * They stop moving if they are not active.
     * @return boolean This is a boolean for if they are active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method is to set if the MovingActor is active or not.
     * Each moving actor is set to active at the beginning of the simulation, and is turned to not active
     * once they finish their job.
     * @param active This is the boolean to be set for the active attribute.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This is the getter for the carrying attribute.
     * @return boolean This is a boolean for whether the MovingActor is holding a fruit or not.
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * This is the setter for the carrying attribute.
     * Called when an actor stands on a FruitActor.
     * @param carrying This is the carrying boolean to set to.
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * This method sets the moving direction of any moving actor.
     * @param direction This is the int to which direction the actor faces.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * This method gets the direction that the actor is currently moving in.
     * Used for the update method of actors to know which direction to move in.
     * @return int This returns the current facing direction of the actor.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * This is a method to rotate the direction of the MovingActor.
     * Called when an event of being on top of another actor occurs.
     * Returns int so rotated direction can be used in Mitosis Pool event.
     * @param rotateBy This is the number of degrees to rotate the facing direction of the actor by.
     * @return int This is the rotated direction.
     */
    public int rotatedDirection(int rotateBy){
        int currentDirection = direction;
        for (int i = 0; i < rotateBy; i += 90){
            currentDirection++;
            if (currentDirection > 3){
                currentDirection = 0;
            }
        }
        return currentDirection;
    }


    /**
     * This is the event that occurs if any moving actor lands on a Tree.
     * If the tree contains fruits, actors can carry fruits from them.
     * @param otherActor The tree is passed in here.
     */
    public void runTreeEvent(Actor otherActor){
        // Downcast tree to modify fruitCount.
        Tree tree = ((Tree) otherActor);

        // Take fruit from tree if it exists and actor is not carrying any fruit.
        if (!carrying && tree.getFruitCount() > 0){
            tree.decrementFruitCount();
            carrying = true;
            // If caller of this method is a Gatherer, rotate 180 degrees.
            if (this instanceof Gatherer){
                setDirection(rotatedDirection(180));
            }
        }
    }

    /**
     * This is the event that occurs if any moving actor lands on a GoldenTree.
     * Actors can carry fruit if they already are not.
     * @param otherActor The GoldenTree is passed in here.
     */
    public void runGoldenTreeEvent(Actor otherActor){
        // Carry fruit if not already carrying.
        if (!carrying){
            carrying = true;
        }
    }

    /**
     * This is the method called when any MovingActor is standing on a Stockpile.
     * Made an abstract method because implementation depends on the type of MovingActor.
     * @param otherActor This is the Stockpile that the MovingActor is stepping on.
     */
    public abstract void runStockpileEvent(Actor otherActor);

    /**
     * This is the method called when any MovingActor is standing on a Hoard.
     * Made an abstract method because implementation depends on the type of MovingActor.
     * @param otherActor This is the Hoard that the MovingActor is stepping on.
     */
    public abstract void runHoardEvent(Actor otherActor);

    /**
     * This is the event that occurs if any moving actor lands on a Fence.
     * After actors finish their job, they rest in front of the fences.
     * @param otherActor The Fence actor is passed in here.
     */
    public void runFenceEvent(Actor otherActor){
        // If standing on a fence, move to position in previous tick, and deactivate actor.
        setDirection(rotatedDirection(180));
        update();
        setActive(false);
    }

    /**
     * This is the event that occurs if any moving actor lands on a Sign.
     * The actor's moving direction changes according to the direction that the sign points to.
     * @param otherActor The Sign actor is passed in here.
     */
    public void runSignEvent(Actor otherActor) {
        switch(otherActor.type){
            case Sign.TYPE_UP:
                direction = Direction.UP;
                break;
            case Sign.TYPE_RIGHT:
                direction = Direction.RIGHT;
                break;
            case Sign.TYPE_DOWN:
                direction = Direction.DOWN;
                break;
            case Sign.TYPE_LEFT:
                direction = Direction.LEFT;
                break;
        }
    }

    /**
     * This is the method called when any MovingActor is standing on a MitosisPool.
     * Made an abstract method because implementation depends on the type of MovingActor.
     */
    public abstract void runMitosisPoolEvent();

    /**
     * This is the public update method to be overridden by child classes.
     */
    @Override
    public abstract void update();
}
