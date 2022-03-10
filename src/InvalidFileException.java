/**
 * This is an exceptions class that is thrown if the world file fails to open or if one of the lines are invalid.
 */
public class InvalidFileException extends Exception{
    /**
     * This is the constructor of InvalidFileException if the world file fails to open.
     */
    public InvalidFileException(String worldFile){
        super("error: file \"" + worldFile + "\" not found");
    }

    /**
     * This is the constructor of InvalidFileException if the any of the data contains errors.
     */
    public InvalidFileException(String worldFile, int lineNumber){
        super("error: file \"" + worldFile + "\" at line " + lineNumber);
    }
}
