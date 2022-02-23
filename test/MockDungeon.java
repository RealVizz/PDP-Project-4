import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mock Model for testing controller.
 */
public class MockDungeon implements DungeonInterface {

  private final StringBuffer log;
  CaveTunnelInterface currentNode;
  CaveTunnelInterface rightNode;
  int[] currLoc;
  int[] playerLoc;

  /**
   * Default constructor.
   */
  public MockDungeon(StringBuffer log) {
    this.log = log;
    currentNode = new CaveTunnel();

    currLoc = new int[]{0, 0};
    currentNode.setMatrixIndex(currLoc);

    playerLoc = new int[]{0, 0};

    rightNode = new CaveTunnel();
    rightNode.setMatrixIndex(new int[]{0, 1});
    currentNode.setRightNode(rightNode);
  }

  @Override
  public CaveTunnelInterface getCaveTunnelFromLocation(int[] location) {
    log.append(Arrays.toString(location)).append("  ");
    if (location[0] == currLoc[0] && location[1] == currLoc[1]) {
      return this.currentNode;
    } else {
      return this.rightNode;
    }

  }

  @Override
  public void movePlayer(int[] newLocation) {
    log.append(Arrays.toString(newLocation)).append("  ");
    playerLoc = newLocation;
  }

  @Override
  public List<int[]> getCurrentMoves() {
    log.append("getCurrentMoves  ");
    List<int[]> x = new ArrayList<>();
    x.add(this.rightNode.getMatrixIndex());
    return x;
  }

  @Override
  public int[] getPlayerCurrentLocation() {
    log.append("getPlayerCurrentLocation  ");
    return this.playerLoc;
  }

  @Override
  public List<CaveTunnelInterface> getAllCaves() {
    return null;
  }

  @Override
  public List<CaveTunnelInterface> getAllTunnels() {
    return null;
  }

  @Override
  public int[] getEndPosition() {
    log.append("getEndPosition  ");
    return new int[]{10, 10};
  }

  @Override
  public Player getPlayer() {
    return new Player();
  }

  @Override
  public int playerSmell() {
    return 0;
  }
}
