/**
 * The Gatherer is a MovingActor that gathers fruits from trees, and deposits them to stockpiles.
 * Upon finishing depositing all fruits, they rest in front of fences.
 */
public class Gatherer extends MovingActor{
    /**
     * Type string to identify a Gatherer.
     */
    public static final String TYPE = "Gatherer";

    /**
     * Constructor for the Gatherer.
     * @param x This is the x axis of the creating Gatherer.
     * @param y This is the y axis of the creating Gatherer.
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        setDirection(Direction.LEFT);
    }

    public Gatherer(int x, int y, int direction){
        super("res/images/gatherer.png", TYPE, x, y);
        setDirection(direction);
    }

    /**
     * This method handles the events that occur when the Gatherer is on top of different kinds of actors.
     * This is called in the ShadowLife class when the program identifies that the Gatherer is on top of another actor.
     * Handles the actions taken by the FruitActors as well.
     * @param otherActor This is the other actor that the Gatherer is standing on.
     */
    @Override
    public void samePositionEvent(Actor otherActor){
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
            case MitosisPool.TYPE:
                runMitosisPoolEvent();
                break;
        }
    }

    /**
     * This is the method called when the Gatherer is standing on a Stockpile.
     * @param otherActor This is the Stockpile that the Gatherer is stepping on.
     */
    @Override
    public void runStockpileEvent(Actor otherActor){
        // Increment fruitCount if Gatherer is carrying a fruit
        if (isCarrying()){
            // Downcast to modify fruitCount
            ((FruitActor) otherActor).incrementFruitCount();
            setCarrying(false);
        }
        setDirection(rotatedDirection(180));
    }

    /**
     * This is the method called when the Gatherer is standing on a Hoard.
     * @param otherActor This is the Hoard that the Gatherer is stepping on.
     */
    @Override
    public void runHoardEvent(Actor otherActor){
        // Run stockpile event because they have the same implementation.
        runStockpileEvent(otherActor);
    }

    /**
     * This is the method called when the Gatherer is standing on a MitosisPool.
     * It creates new Gatherers moving to the left and right of the currently facing direction, and destroys itself.
     */
    public void runMitosisPoolEvent(){
        // Add new Gatherer facing to the right of current direction, and move one tile.
        Actor actor1 = new Gatherer(getX(), getY(), rotatedDirection(90));
        ShadowLife.addActor(actor1);
        actor1.update();

        // Add another Gatherer facing to the left of current direction, and move actor one tile.
        Actor actor2 = new Gatherer(getX(), getY(), rotatedDirection(270));
        ShadowLife.addActor(actor2);
        actor2.update();

        // Remove the current Gatherer.
        ShadowLife.removeActor(this);
    }

    /**
     * This is the update method for the Gatherer.
     * Moves in the direction they are currently facing.
     */
    @Override
    public void update(){
        if (isActive()){
            switch (getDirection()) {
                case Direction.UP:
                    move(0, -ShadowLife.TILE_SIZE);
                    break;
                case Direction.DOWN:
                    move(0, ShadowLife.TILE_SIZE);
                    break;
                case Direction.LEFT:
                    move(-ShadowLife.TILE_SIZE, 0);
                    break;
                case Direction.RIGHT:
                    move(ShadowLife.TILE_SIZE, 0);
                    break;
            }
        }
    }
}