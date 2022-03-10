/**
 * The tree initially has 3 fruits, which the Gatherers can collect from.
 */
public class Tree extends FruitActor {
    /**
     * Type string to identify a Tree in the world file by ShadowLife class.
     */
    public static final String TYPE = "Tree";

    /**
     * This is the constructor of the Tree.
     * @param x x-coordinate of the Tree.
     * @param y y-coordinate of the Tree.
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        setFruitCount(3);
    }

    /**
     * Overridden update method of Actor.
     */
    @Override
    public void update() {}
}
