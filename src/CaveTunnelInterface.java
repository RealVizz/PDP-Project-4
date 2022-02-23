import java.util.List;

/**
 * This CaveTunnelInterface keeps  all the public behaviours of a Cave and Tunnels.
 */
public interface CaveTunnelInterface {

  /**
   * Get the right node of the current node if path to it exists.
   *
   * @return rightNode.
   */
  CaveTunnelInterface getRightNode();

  /**
   * Get the left node of the current node if path to it exists.
   *
   * @return leftNode.
   */
  CaveTunnelInterface getLeftNode();

  /**
   * Get the up node of the current node if path to it exists.
   *
   * @return upNode.
   */
  CaveTunnelInterface getUpNode();

  /**
   * Get the down node of the current node if path to it exists.
   *
   * @return downNode.
   */
  CaveTunnelInterface getDownNode();

  /**
   * Set the right node of the current node and create a path for it.
   *
   * @param node The node to be on the right.
   */
  void setRightNode(CaveTunnelInterface node);

  /**
   * Set the left node of the current node and create a path for it.
   *
   * @param node The node to be on the left.
   */
  void setLeftNode(CaveTunnelInterface node);

  /**
   * Set the up node of the current node and create a path for it.
   *
   * @param node The node to be on the up.
   */
  void setUpNode(CaveTunnelInterface node);

  /**
   * Set the down node of the current node and create a path for it.
   *
   * @param node The node to be on the down.
   */
  void setDownNode(CaveTunnelInterface node);


  //  int getRightNodeLen();
  //
  //  void setRightNodeLen(int rightNodeLen);
  //
  //  int getLeftNodeLen();
  //
  //  void setLeftNodeLen(int leftNodeLen);
  //
  //  int getUpNodeLen();
  //
  //  void setUpNodeLen(int upNodeLen);
  //
  //  int getDownNodeLen();
  //
  //  void setDownNodeLen(int downNodeLen);

  /**
   * Gives out The list containing all the treasures of current node.
   *
   * @return The list containing all the treasures of this node.
   */
  List<Treasure> getTreasureList();

  /**
   * Adds a treasure to the current node/ loc / cave/ tunnel.
   *
   * @param t The treasure object t.
   */
  void addTreasure(Treasure t);


  /**
   * Gives the index of the location in the matrix.
   *
   * @return The integer array object having current row, column.
   */
  int[] getMatrixIndex();


  /**
   * Sets the index of the location in the matrix.
   *
   * @param matrixIndex The integer array object having current row, column.
   */
  void setMatrixIndex(int[] matrixIndex);

  /**
   * Gives the possible moves of a Cave/Tunnel.
   *
   * @return The List of integer array object having possible row, column locations.
   */
  List<int[]> getPossibleMoves();

  /**
   * Clears Treasure of the cave.
   */
  void clearTreasureList();

  //  String getNodeName();

  /**
   * Sets the name of the node.
   *
   * @param name The name of the node.
   */
  void setNodeName(String name);

  /**
   * Adds up the monster in to the cave/tunnel.
   * For now, only caves can have monsters.
   *
   * @param monster The monster object.
   */
  void addMonster(MonsterInterface monster);

  /**
   * Tells whether this node is a cave or not, if not then it is a tunnel.
   *
   * @return The boolean value whether this tunnel is a Cave or not.
   */
  boolean isCave();

  /**
   * Gives The list of the monsters.
   *
   * @return The list of the monsters.
   */
  List<MonsterInterface> getMonsters();


  /**
   * Add weapons to the caves and tunnels.
   *
   * @param weapon The weapons provided (Must be from WeaponEnum).
   */
  void addWeapon(WeaponEnum weapon);


  /**
   * Gives out The list containing all the Weapons of current node.
   *
   * @return The list containing all the Weapons of current node.
   */
  List<WeaponEnum> getWeaponsList();


  /**
   * Clears the weapons list from the cave/tunnel .
   */
  void clearWeaponList();

  String toString();

}
