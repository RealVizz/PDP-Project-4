import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class.
 */
public class Driver {
  /**
   * The Function fo running a demo case.
   *
   * @param args Nothing.
   */
  // Driver Code
  public static void main(String[] args) {
    System.out.println("'S' --> Starting Node/Loc , 'P' --> Player is here , "
            + "'E' --> Ending Node/Loc , 'T' --> This Node/Loc has treasure.\n");
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


    boolean warpAllowed;
    warpAllowed = warpValue != 0;


    System.out.println("Lets Auto create a new player and its details.");
    Player p1 = new Player();
    System.out.println(p1);

    System.out.println("\nLets create a Dungeon of size " + rows + "x" + cols
            + ", Treasure Percentage to be " + trPr + ", " + "Interconnectivity to be "
            + interConnectivity + "and Warping" + warpAllowed);


    Dungeon d = new Dungeon(rows + "x" + cols, trPr, interConnectivity, p1, warpAllowed, 1);
    System.out.println(d);

    System.out.println("We want our player to visit every node/location from the start until end.");

    HashSet<int[]> visitedNodes = new HashSet<>();
    List<int[]> queue = new ArrayList<>();

    visitedNodes.add(p1.getCurrLoc());

    while (true) {
      for (int[] x : d.getCurrentMoves()) {
        if (!visitedNodes.contains(x)) {
          queue.add(x);
        }
      }

      int[] nextLoc = queue.get(queue.size() - 1);
      queue.remove(queue.size() - 1);
      if (!visitedNodes.contains(nextLoc)) {
        d.movePlayer(nextLoc);
        visitedNodes.add(p1.getCurrLoc());
      }


      System.out.println("Player Desc. Before Picking Down Below Treasure --> " + p1.toString());
      System.out.println(d);
      p1.pickTreasure();
      System.out.println("Player Desc. After Picking Up Above Treasure --> " + p1.toString());
      System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

      if (queue.size() == 0) {
        break;
      }

      if (p1.getCurrLoc()[0] == d.getEndPosition()[0]
              && p1.getCurrLoc()[1] == d.getEndPosition()[1]) {
        break;
      }

    }

  }
}
