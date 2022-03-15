/**
 * The Thief is a MovingActor that attempts to steal fruit from the stockpiles to place it in their hoards.
 * Upon finishing, they rest in front of fences.
 */
public class Thief extends MovingActor{
    // Thieves can be "consuming" fruits
    private boolean consuming;

    /**
     * Type string to identify a Thief.
     */
    public static final String TYPE = "Thief";


    /**
     * Constructor for the Thief. Consuming will initially be set to false.
     * @param x This is the x axis of the creating Thief.
     * @param y This is the y axis of the creating Thief.
     */
    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        setDirection(Direction.UP);
        consuming = false;
    }

    /**
     * Second constructor for the Thief. Consuming will initially be set to false.
     * @param x This is the x axis of the creating Thief.
     * @param y This is the y axis of the creating Thief.
     * @param direction This is the direction that
     */
    public Thief(int x, int y, int direction){
        super("res/images/gatherer.png", TYPE, x, y);
        setDirection(direction);
        consuming = false;
    }


    /**
     * This method handles the events that occur when the Thief is on top of different kinds of actors.
     * This is called in the ShadowLife class when the program identifies that the Thief is on top of another actor.
     * Handles the actions taken by the FruitActors as well.
     * @param otherActor This is the other actor that the Thief is standing on.
     */
    @Override
    public void samePositionEvent(Actor otherActor) {
        // Run event for the corresponding actor that the Thief is standing on.
        switch(otherActor.type){
            case Tree.TYPE:
                runTreeEvent(otherActor);
                break;
            case GoldenTree.TYPE:
                runGoldenTreeEvent(otherActor);
                break;
            case Stockpile.TYPE:
                runStockpileEvent(otherActor);
                break;
            case Hoard.TYPE:
                runHoardEvent(otherActor);
                break;
            case Fence.TYPE:
                runFenceEvent(otherActor);
                break;
            case Sign.TYPE_UP:
            case Sign.TYPE_RIGHT:
            case Sign.TYPE_DOWN:
            case Sign.TYPE_LEFT:
                runSignEvent(otherActor);
                break;
            case Pad.TYPE:
                consuming = true;
                break;
            case Gatherer.TYPE:
                setDirection(rotatedDirection(270));
                break;
            case MitosisPool.TYPE:
                runMitosisPoolEvent();
                break;
        }
    }

    /**
     * This is the method called when the Thief is standing on a Stockpile.
     * @param otherActor This is the Stockpile that the Thief is stepping on.
     */
    @Override
    public void runStockpileEvent(Actor otherActor){
        // Downcast to modify fruitCount
        Stockpile stockpile = ((Stockpile) otherActor);

        // Steal fruit from Stockpile if Thief is not carrying one
        if (!isCarrying()){
            if (stockpile.getFruitCount() > 0) {
                setCarrying(true);
                consuming = false;
                stockpile.decrementFruitCount();
                setDirection(rotatedDirection(90));
            }
        }
        // Otherwise, change direction.
        else{
            setDirection(rotatedDirection(90));
        }

    }

    /**
     * This is the method called when the Thief is standing on a Hoard.
     * @param otherActor This is the Hoard that the Thief is stepping on.
     */
    @Override
    public void runHoardEvent(Actor otherActor){
        // Downcast to modify fruitCount
        Hoard hoard = ((Hoard) otherActor);

        // If consuming and not carrying fruit, carry fruit from Hoard
        if (consuming){
            consuming = false;
            if (!isCarrying()){
                if (hoard.getFruitCount() > 0){
                    setCarrying(true);
                    hoard.decrementFruitCount();
                }
                // Change direction if Hoard is empty.
                else{
                    setDirection(rotatedDirection(90));
                }
            }
        }
        // If carrying fruit, place in Hoard
        else if (isCarrying()){
            setCarrying(false);
            hoard.incrementFruitCount();
            setDirection(rotatedDirection(90));
        }
    }

    /**
     * This is the method called when the Thief is standing on a MitosisPool.
     * It creates new Thieves moving to the left and right of the currently facing direction, and destroys itself.
     */
    @Override
    public void runMitosisPoolEvent(){
        // Add new Thief facing to the right of current direction, and move one tile.
        Actor actor1 = new Thief(getX(), getY(), rotatedDirection(90));
        Simulation.addActor(actor1);
        actor1.update();

        // Add another Thief facing to the left of current direction, and move actor one tile.
        Actor actor2 = new Thief(getX(), getY(), rotatedDirection(270));
        Simulation.addActor(actor2);
        actor2.update();

        // Remove the current Thief.
        Simulation.removeActor(this);
    }



    /**
     * This is the update method for the Thief.
     * Moves in the direction they are currently facing.
     */
    @Override
    public void update(){
        if (isActive()){
            switch (getDirection()) {
                case Direction.UP:
                    move(0, -Simulation.TILE_SIZE);
                    break;
                case Direction.DOWN:
                    move(0, Simulation.TILE_SIZE);
                    break;
                case Direction.LEFT:
                    move(-Simulation.TILE_SIZE, 0);
                    break;
                case Direction.RIGHT:
                    move(Simulation.TILE_SIZE, 0);
                    break;
            }
        }
    }
}