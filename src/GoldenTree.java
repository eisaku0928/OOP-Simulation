/**
 * The GoldenTree has infinite reserve of fruit.
 */
public class GoldenTree extends FruitActor {
    /**
     * Type string to identify a GoldenTree in the world file by ShadowLife class.
     */
    public static final String TYPE = "GoldenTree";

    /**
     * This is the constructor of the Golden Tree.
     * @param x x-coordinate of the Golden Tree.
     * @param y y-coordinate of the Golden Tree.
     */
    public GoldenTree(int x, int y) {
        super("res/images/gold-tree.png", TYPE, x, y);
        /* Set fruitCount to negative to not draw fruitCount,
        because fruitCounts above or equal to 0 will only be drawn. */
        setFruitCount(-1);
    }


    /**
     * Overridden update method of Actor.
     */
    @Override
    public void update() {}
}
