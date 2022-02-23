import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Run a TicTacToe game interactively on the console.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;

    //System.out.println("'S' --> Starting Node/Loc , 'P' --> Player is here , "
    //        + "'E' --> Ending Node/Loc , 'T' --> This Node/Loc has treasure.\n");
    Scanner sc = new Scanner(System.in);
    System.out.println("Hello There, Please give following details for the dungeon below.");
    System.out.println("Number of rows of dungeon (min 2/ recommended 3+)       : ");
    int rows = sc.nextInt();
    System.out.println("Number of columns of dungeon (min 2/ recommended 8+)    : ");
    int cols = sc.nextInt();
    System.out.println("Interconnectivity (min 0/ recommended 0+)               : ");
    int interConnectivity = sc.nextInt();
    System.out.println("Is it a warping dungeon (False--> 0/ True--> any other) : ");
    int warpValue = sc.nextInt();
    System.out.println("Percentage of caves with treasure (min 0/ max 1)        : ");
    int trPr = sc.nextInt();
    System.out.println("Number of Monsters/ Difficulty Level                    : ");
    int mC = sc.nextInt();


    boolean warpAllowed;
    warpAllowed = warpValue != 0;


    System.out.println("Lets Auto create a new player and its details.");
    Player p1 = new Player();
    System.out.println(p1);

    System.out.println("\nLets create a Dungeon of size " + rows + "x" + cols
            + ", Treasure Percentage to be " + trPr + ", " + "Interconnectivity to be "
            + interConnectivity + " and Warping " + warpAllowed + "and total Monsters are "
            + mC + ".");

    DungeonInterface d = new Dungeon(rows + "x" + cols, trPr, interConnectivity,
            p1, warpAllowed, mC);
    ControllerInterface c = new Controller(input, output, d);
    c.playGame();
  }
}
