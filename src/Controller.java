import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The controller class for the Dungeon Game.
 */
public class Controller implements ControllerInterface {

  private final Appendable out;
  private final Scanner scan;
  private final DungeonInterface dungeon;


  /**
   * The constructor takes 3 values, in stream , out stream and model to make it work.
   *
   * @param in      The Input stream.
   * @param out     The Output stream.
   * @param dungeon The model object.
   */
  public Controller(Readable in, Appendable out, DungeonInterface dungeon) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    this.dungeon = dungeon;
  }


  /**
   * Runs the game interactively via user input.
   */
  @Override
  public void playGame() {
    //System.out.println(dungeon);
    int[] endLoc = dungeon.getEndPosition();
    //System.out.println("Start loc for navigation [Test Only Line] :"
    //        + Arrays.toString(dungeon.getPlayerCurrentLocation()));
    //System.out.println("End loc for navigation [Test Only Line] :"
    //       + Arrays.toString(endLoc));
    int[] currLoc = dungeon.getPlayerCurrentLocation();
    while (!dungeon.getPlayer().isDead() && (currLoc[0] != endLoc[0] || currLoc[1] != endLoc[1])) {
      try {
        currLoc = dungeon.getPlayerCurrentLocation();
        CaveTunnelInterface currNode = dungeon.getCaveTunnelFromLocation(currLoc);
        List<int[]> currMoves = dungeon.getCurrentMoves();
        StringBuilder currLocSB = new StringBuilder();
        currLocSB.append("\n\nYou are at location ").append(Arrays.toString(currLoc));
        currLocSB.append(", This is a ").append(currNode.isCave() ? "Cave." : "Tunnel.");
        //currLocSB.append(dungeon.getEndPosition() == dungeon.getPlayerCurrentLocation()
        //        ? " This is the End Location." : "");
        this.out.append(currLocSB);

        this.out.append("\nDetails Of Location  : ").append(currNode.toString());
        this.out.append("\nDetails of Player    : ").append(dungeon.getPlayer().toString());

        StringBuilder smellSB = new StringBuilder();
        if (dungeon.playerSmell() == 0) {
          smellSB.append("\nPlayer do not smell anything pungent.");
        } else if (dungeon.playerSmell() == 1) {
          smellSB.append("\nPlayer smells something mildly pungent nearby.");
        } else if (dungeon.playerSmell() == 2) {
          smellSB.append("\nPlayer smells something heavily pungent nearby.");
        }

        this.out.append(smellSB);
        this.out.append("\nPlayer Options : Move[M]    Pick[P]     Shoot[S]     Quit[Q]\n");
        String usrInput = this.scan.next();
        //System.out.println(usrInput);

        switch (usrInput) {
          case "M":
            StringBuilder moveSB = new StringBuilder();
            moveSB.append("Possible move loc are --> ");
            for (int[] x : currMoves) {
              moveSB.append(Arrays.toString(x));
              String dir = getDirOfLoc(dungeon.getPlayerCurrentLocation(), x);
              moveSB.append("[").append(dir).append("] ").append(",  ");
            }
            this.out.append(moveSB.append("\n"));
            while (true) {
              String move = this.scan.next();
              if (move.equals("U") || move.equals("D") || move.equals("L") || move.equals("R")) {
                dungeon.movePlayer(getLocOfDir(move));
                currLoc = dungeon.getPlayerCurrentLocation();
                break;
              } else {
                this.out.append("\nOk Jon, We No Elon or Jeff....we cant move in space...Try Again");
              }
            }

            if (dungeon.getPlayer().isDead()) {
              this.out.append("\nThe Player is Eaten by the monster....Gone!!!");
            }

            if (currLoc[0] == endLoc[0] && currLoc[1] == endLoc[1]) {
              this.out.append("\nYou Reached the end cave...Hell yeah.. You won..!!");
            }
            break;


          case "P":
            StringBuilder pickSB = new StringBuilder();
            List<WeaponEnum> weaponList = currNode.getWeaponsList();
            List<Treasure> treasureList = currNode.getTreasureList();
            pickSB.append("Player Picked : ");
            for (WeaponEnum w : weaponList) {
              pickSB.append(w.toString()).append(", ");
            }
            for (Treasure t : treasureList) {
              pickSB.append(t.toString()).append(", ");
            }
            if (weaponList.size() == 0 && treasureList.size() == 0) {
              pickSB.append("Nothing.");
            }
            dungeon.getPlayer().pickWeapons();
            dungeon.getPlayer().pickTreasure();
            this.out.append(pickSB);
            this.out.append("\nNew player stats : ").append(dungeon.getPlayer().toString());
            break;


          case "S":
            int dist;
            while (true) {
              this.out.append("\nEnter Distance in Caves [1-5] : ");
              try {
                dist = this.scan.nextInt();
                if (dist < 1 || dist > 5) {
                  this.out.append("\nYou entered invalid distance, Try again.");
                } else {
                  break;
                }
              } catch (InputMismatchException e) {
                this.out.append("\nYou entered invalid distance, Try again.");
              }

            }

            while (true) {
              this.out.append("\nEnter Direction,options are [U -> Up] [D -> Down] [L -> Left]"
                      + " [R -> Right] : ");
              String dir = this.scan.next();
              if (dir.equals("U") || dir.equals("D") || dir.equals("L") || dir.equals("R")) {
                dungeon.getPlayer().shootArrow(dir, dist);
                break;
              } else {
                this.out.append("\nYou have entered wrong direction, Try again.");
              }
            }

            break;


          case "Q":
            this.out.append("\nI see you wanna leave, now worries.... Good Luck for next time.");
            return;
          //break;
          default:
            this.out.append("\nOk Jim, that is an invalid option... Try Again.");
            break;
        }


      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
    }
  }


  private String getDirOfLoc(int[] currLocation, int[] movableLocation) {
    CaveTunnelInterface currNode = dungeon.getCaveTunnelFromLocation(currLocation);
    CaveTunnelInterface nodeInDirection = dungeon.getCaveTunnelFromLocation(movableLocation);
    String newDirection = null;
    if (currNode.getDownNode() == nodeInDirection) {
      newDirection = "D";
    } else if (currNode.getUpNode() == nodeInDirection) {
      newDirection = "U";
    } else if (currNode.getRightNode() == nodeInDirection) {
      newDirection = "R";
    } else if (currNode.getLeftNode() == nodeInDirection) {
      newDirection = "L";
    }

    return newDirection;
  }

  private int[] getLocOfDir(String direction) {
    int[] currLoc = dungeon.getPlayerCurrentLocation();
    CaveTunnelInterface currNode = dungeon.getCaveTunnelFromLocation(currLoc);
    CaveTunnelInterface nodeInDirection;

    switch (direction) {
      case "D":
        nodeInDirection = currNode.getDownNode();
        break;
      case "R":
        nodeInDirection = currNode.getRightNode();
        break;
      case "U":
        nodeInDirection = currNode.getUpNode();
        break;
      case "L":
        nodeInDirection = currNode.getLeftNode();
        break;
      default:  //  this condition is just for the sake of it.......
        nodeInDirection = currNode;
        break;
    }

    if (nodeInDirection != null) {
      return nodeInDirection.getMatrixIndex();
    } else {
      System.out.println("No such way Josh... Enter a right input..");
      return currLoc;
    }
  }
}
