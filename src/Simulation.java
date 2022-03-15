import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The Simulation class contains the main method to run a graphical simulation of a world and its inhabitants,
 * load the actors, and continue calling the update method for each actor at the specified tick rate.
 * @author Eisaku Daniel Tanaka
 * @version 2.0
 */
public class Simulation extends AbstractGame {
    /* Attributes to store fundamental data needed to run the simulation.
    * Includes arguments, tick rate, actor arraylist, etc.*/
    private static String[] args;
    private long lastTick;
    private static long TICK_RATE;
    private static long MAX_TICKS;
    private static ArrayList<Actor> actors = new ArrayList<Actor>();
    private final Image BACKGROUND = new Image("res/images/background.png");
    private long tickCounter = 0;

    /**
     * Simulation is divided into tiles, and mobile actors move by TILE_SIZE (64 pixels) every tick.
     */
    public static final int TILE_SIZE = 64;

    /**
     * This method adds actors to the actor ArrayList.
     * Used when reading the world file or when actors step on a Mitosis Pool.
     * @param actor This is the actor to add into the arraylist.
     */
    public static void addActor(Actor actor){
        actors.add(actor);
    }

    /**
     * This method removes actors from the actor ArrayList.
     * Used when actors step on a Mitosis Pool.
     * @param actor This is the actor to remove from the arraylist.
     */
    public static void removeActor(Actor actor){
        actors.remove(actor);
    }

    /*
     * This method is used to load actors in the actor arraylist.
     * Does not take any parameters but will load actors from world file.
     */
    private void loadActors() throws InvalidFileException {
        // Store worldFile name from argument
        String worldFile = args[2];

        // Keep a line counter to identify which line the error occurred at
        int lineCounter = 0;
        // Try-catch with resources to read from worldFile
        try (BufferedReader reader = new BufferedReader(new FileReader(worldFile))) {
            String line;


            while ((line = reader.readLine()) != null) {
                lineCounter++;

                // Line format is: type,x,y
                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                // Create corresponding actor and image to be rendered
                switch (type) {
                    case Gatherer.TYPE:
                        addActor(new Gatherer(x, y));
                        break;
                    case Thief.TYPE:
                        addActor(new Thief(x, y));
                        break;
                    case Tree.TYPE:
                        addActor(new Tree(x, y));
                        break;
                    case GoldenTree.TYPE:
                        addActor(new GoldenTree(x, y));
                        break;
                    case Stockpile.TYPE:
                        addActor(new Stockpile(x, y));
                        break;
                    case Hoard.TYPE:
                        addActor(new Hoard(x, y));
                        break;
                    case Pad.TYPE:
                        addActor(new Pad(x, y));
                        break;
                    case Fence.TYPE:
                        addActor(new Fence(x, y));
                        break;
                    case MitosisPool.TYPE:
                        addActor(new MitosisPool(x, y));
                        break;
                    case Sign.TYPE_LEFT:
                    case Sign.TYPE_RIGHT:
                    case Sign.TYPE_UP:
                    case Sign.TYPE_DOWN:
                        addActor(new Sign(type, x, y));
                        break;
                    default:
                        // Throw exception if reached here.
                        throw new InvalidFileException(worldFile, lineCounter);
                }
            }
        } catch (NumberFormatException e){
            throw new InvalidFileException(worldFile, lineCounter);
        }
        catch (IOException e) {
            throw new InvalidFileException(worldFile);
        }
    }

    /** This is the Simulation constructor that throws exceptions if arguments read from "res/args.txt" is invalid.
     * Calls loadActors() method for actors to be loaded.
     * @throws InvalidArgumentException This is an exception to catch any invalid arguments
     */
    public Simulation() throws InvalidArgumentException{
        // Read arguments
        args = argsFromFile();

        // Handle invalid length
        if (args == null || args.length != 3){
            throw new InvalidArgumentException();
        }

        // Handle invalid inputs
        try {
            TICK_RATE = Long.parseLong(args[0]);
            MAX_TICKS = Long.parseLong(args[1]);
        } catch (NumberFormatException e){
            throw new InvalidArgumentException();
        }

        // Load Actors if arguments are valid, and exit with message if invalid file.
        try{
            loadActors();
        } catch(InvalidFileException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }


    @Override
    protected void update(Input input) {
        // At each update, create a copy of the actors ArrayList.
        ArrayList<Actor> actorsCopy = new ArrayList<>(actors);

        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > TICK_RATE) {
            lastTick = System.currentTimeMillis();
            if (++tickCounter > MAX_TICKS){
                timedOut();
            }
            // Run tick for each actor
            for (Actor actor : actorsCopy) {
                if (actor != null) {
                    actor.tick();
                }
            }
            // Check if any actor is on top of each other, and run event if so.
            for (Actor actor : actorsCopy){
                if (actor != null){
                    for (Actor otherActor : actorsCopy){
                        if (actor.isSamePosition(otherActor)){
                            actor.samePositionEvent(otherActor);
                        }
                    }
                }
            }
        }
        // Draw all elements
        BACKGROUND.drawFromTopLeft(0, 0);
        for (Actor actor : actorsCopy) {
            if (actor != null) {
                actor.render();
            }
        }

        // If all actors are not active, end simulation.
        if (endOfSimulationCheck()){
            endSimulation();
        }
    }

    /* This is a helper function to check if all of the moving actors are inactive.
    * It will return true if all moving actors are not active.
    */
    private boolean endOfSimulationCheck(){
        // Loop through arraylist to check actors
        for (Actor actor : actors){
            if (actor instanceof MovingActor){
                MovingActor movingActor = ((MovingActor) actor);
                if (movingActor.isActive()){
                    return false;
                }
            }
        }
        return true;
    }

    /* This is a function called after the program identifies that all moving actors are inactive.
    * It will print the number of ticks and the number of fruits at each stockpile or hoard,
    * in the order that they appear on the world file. */
    private void endSimulation(){
        // Print ticks
        System.out.println(tickCounter + " ticks");

        // Loop through actors to print fruits in order, because ArrayList maintains insertion order.
        for (Actor actor : actors){
            if (actor instanceof Stockpile || actor instanceof Hoard){
                System.out.println(((FruitActor) actor).getFruitCount());
            }
        }
        System.exit(0);
    }

    // Function called when ticks exceed the specified maximum number of ticks.
    private void timedOut(){
        System.out.println("Timed Out");
        System.exit(-1);
    }

    /**
     * This is the main method that runs the simulation and catches any "InvalidArgumentException"s.
     * @param args This is the command line arguments. Currently reading arguments from "args.txt" due to bug in args.
     */
    public static void main(String[] args) {

        // Run ShadowLife program and catch invalid arguments
        try{
            new Simulation().run();
        } catch(InvalidArgumentException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }

    /* Function that reads in arguments from args.txt */
    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("res/args.txt"), Charset.defaultCharset()).split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
