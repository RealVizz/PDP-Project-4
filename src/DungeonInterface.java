import java.util.List;

/**
 * This DungeonInterface keeps all the public behaviours of a Dungeon.
 */
public interface DungeonInterface {

  /**
   * Gives the node object in a particular location in dungeon.
   *
   * @param location The integer array of [row, col] of the node.
   * @return The node at specified location.
   */
  CaveTunnelInterface getCaveTunnelFromLocation(int[] location);

  /**
   * This function moves the player to a desired / specified location.
   *
   * @param newLocation The location object (int []).
   */
  public void movePlayer(int[] newLocation);


  /**
   * Gives the moves current Cave/Tunnel at which the player is present.
   *
   * @return The List of integer array object having possible row, column locations.
   */
  public List<int[]> getCurrentMoves();

  /**
   * Gives the players current location in the dungeon.
   *
   * @return Payers current location object.
   */
  int[] getPlayerCurrentLocation();


  /**
   * Gives the list of all the Caves in the Dungeon.
   *
   * @return The list of caves present in the Dungeon.
   */
  public List<CaveTunnelInterface> getAllCaves();


  /**
   * Gives the list of all the Tunnels in the Dungeon.
   *
   * @return The list of Tunnels present in the Dungeon.
   */
  public List<CaveTunnelInterface> getAllTunnels();


  /**
   * Gives the ending position object (int []).
   *
   * @return The ending position object.
   */
  int[] getEndPosition();


  /**
   * Gives the player object.
   *
   * @return Return the player object.
   */
  Player getPlayer();


  /**
   * Gives   0 --> No Smell  |  1 --> Mild Smell  |  2 --> Heavy Smell.
   *
   * @return Smell Intensity Integer.
   */
  int playerSmell();
}
