/**
 * This is the Sign class. There are 4 possible directions (up, right, down, left),
 * and whenever an actor steps on it, they will change their direction according to the Sign.
 */
public class Sign extends Actor{
    /**
     * This is the type string to identify a sign pointing up.
     */
    public static final String TYPE_UP = "SignUp";

    /**
     * This is the type string to identify a sign pointing right.
     */
    public static final String TYPE_RIGHT = "SignRight";

    /**
     * This is the type string to identify a sign pointing down.
     */
    public static final String TYPE_DOWN = "SignDown";

    /**
     * This is the type string to identify a sign pointing left.
     */
    public static final String TYPE_LEFT = "SignLeft";

    /**
     * This is the constructor of any Sign.
     * @param type This is type string sent in when reading the world file to identify which sign to create.
     * @param x This is the x-coordinate of the creating sign.
     * @param y This is the y-coordinate of the creating sign.
     */
    public Sign(String type, int x, int y){
        super(returnFileName(type), returnType(type), x, y);
    }

    /* This is a helper function to return the specific image file of the creating Sign. */
    private static String returnFileName(String type){
        if (type.equals(TYPE_UP)){
            return "res/images/up.png";
        }
        else if (type.equals(TYPE_RIGHT)){
            return "res/images/right.png";
        }
        else if (type.equals(TYPE_DOWN)){
            return "res/images/down.png";
        }
        else {
            return "res/images/left.png";
        }
    }

    /* This is a helper function to return the type string of the creating Sign. */
    private static String returnType(String type){
        if (type.equals(TYPE_UP)){
            return TYPE_UP;
        }
        else if (type.equals(TYPE_RIGHT)){
            return TYPE_RIGHT;
        }
        else if (type.equals(TYPE_DOWN)){
            return TYPE_DOWN;
        }
        else {
            return TYPE_LEFT;
        }
    }

    /**
     * This is the overridden update method of any sign. Signs take no actions upon ticks.
     */
    @Override
    public void update(){}

}
