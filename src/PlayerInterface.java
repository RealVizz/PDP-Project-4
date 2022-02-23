import java.util.List;

/**
 * This PlayerInterface keeps all the public behaviours of a Player.
 */
public interface PlayerInterface {

  /**
   * This method check if there is a treasure present in the current cave, and picks it if there is.
   */
  void pickTreasure();

  /**
   * This function moves the player to a desired / specified location.
   *
   * @param newLocation      The location object (int []).
   * @param newCaveCumTunnel The cave/tunnel object.
   */
  void moveInDungeon(int[] newLocation, CaveTunnelInterface newCaveCumTunnel);

  /**
   * Gives the possible moves of the Cave/Tunnel the player is present in.
   *
   * @return The List of integer array object having possible row, column locations.
   */
  List<int[]> getPossibleMoves();

  /**
   * The current location of the player in the dungeon.
   *
   * @return The location object (int[]).
   */
  int[] getCurrLoc();

  /**
   * Gives list of Collected treasures.
   *
   * @return collected treasures.
   */
  List<Treasure> getCollectedTreasures();


  /**
   * Gives status of players dead or alive.
   *
   * @return The boolean value for being dead or not.
   */
  boolean isDead();


  /**
   * Calling this function will kill the player instantly.
   */
  void killPlayer();


  /**
   * This method check if there is a weapon present in the current cave, and picks it if there is.
   */
  void pickWeapons();

  /**
   * Takes Directions from the player [D --> Down, U --> Up, R --> Right, L --> Left].
   * Takes the distance from player to how far the arrow should travel, note that the distance of
   * Caves are counted but not of Tunnels.
   *
   * @param direction       The Direction in which the player wants to shoot.
   * @param distanceInCaves The distance the player want to shoot.
   */
  void shootArrow(String direction, int distanceInCaves);


  /**
   * Gives list of Collected weapons.
   *
   * @return collected weapons.
   */
  List<WeaponEnum> getCollectedWeapons();
}
