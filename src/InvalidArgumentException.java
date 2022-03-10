import java.lang.Exception;

/**
 * This is an exceptions class that is thrown if arguments are invalid.
 */
public class InvalidArgumentException extends Exception{
    /**
     * This is the constructor of InvalidArgumentException, which sends an error message of
     * the proper usage of arguments to the Exception class.
     */
    public InvalidArgumentException(){
        super("usage: ShadowLife <tick rate> <max ticks> <world file>");
    }

}
