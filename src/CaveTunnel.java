import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The CaveTunnel class implement the CaveTunnelInterface, in order to provide all the publicly
 * usable functionalities.
 * It represents both caves and tunnels.
 */
public class CaveTunnel implements CaveTunnelInterface {
  private CaveTunnelInterface rightNode;
  private CaveTunnelInterface leftNode;
  private CaveTunnelInterface upNode;
  private CaveTunnelInterface downNode;

  private int rightNodeLen;
  private int leftNodeLen;
  private int upNodeLen;
  private int downNodeLen;

  private String nodeName;
  private final List<Treasure> treasureList;

  private int[] matrixIndex;

  private List<MonsterInterface> monsterList;
  private final List<WeaponEnum> weaponList;

  //  public CaveTunnel(CaveTunnelInterface rightNode, CaveTunnelInterface leftNode,
  //                    CaveTunnelInterface upNode, CaveTunnelInterface downNode, int rightNodeLen,
  //                    int leftNodeLen, int upNodeLen,
  //                    int downNodeLen, String nodeName, List<Treasure> treasureList) {
  //    this.rightNode = rightNode;
  //    this.leftNode = leftNode;
  //    this.upNode = upNode;
  //    this.downNode = downNode;
  //    this.rightNodeLen = rightNodeLen;
  //    this.leftNodeLen = leftNodeLen;
  //    this.upNodeLen = upNodeLen;
  //    this.downNodeLen = downNodeLen;
  //    this.nodeName = nodeName;
  //    this.treasureList = treasureList;
  //  }

  /**
   * The constructor for CaveTunnel Class.
   * Sets the defaults values for the class.
   */
  public CaveTunnel() {
    this.rightNode = null;
    this.leftNode = null;
    this.upNode = null;
    this.downNode = null;

    this.rightNodeLen = -1;
    this.leftNodeLen = -1;
    this.upNodeLen = -1;
    this.downNodeLen = -1;

    this.nodeName = "default node name";
    this.treasureList = new ArrayList<>();
    this.monsterList = new ArrayList<>();
    this.weaponList = new ArrayList<>();
  }

  @Override
  public CaveTunnelInterface getRightNode() {
    return this.rightNode;
  }

  @Override
  public CaveTunnelInterface getLeftNode() {
    return this.leftNode;
  }

  @Override
  public CaveTunnelInterface getUpNode() {
    return this.upNode;
  }

  @Override
  public CaveTunnelInterface getDownNode() {
    return this.downNode;
  }


  @Override
  public void setRightNode(CaveTunnelInterface node) {
    this.rightNode = node;
  }

  @Override
  public void setLeftNode(CaveTunnelInterface node) {
    this.leftNode = node;
  }

  @Override
  public void setUpNode(CaveTunnelInterface node) {
    this.upNode = node;
  }

  @Override
  public void setDownNode(CaveTunnelInterface node) {
    this.downNode = node;
  }


  //@Override
  private int getRightNodeLen() {
    return rightNodeLen;
  }

  //@Override
  private int getLeftNodeLen() {
    return leftNodeLen;
  }

  //@Override
  private int getUpNodeLen() {
    return upNodeLen;
  }

  //@Override
  private int getDownNodeLen() {
    return downNodeLen;
  }


  //@Override
  private void setRightNodeLen(int rightNodeLen) {
    this.rightNodeLen = rightNodeLen;
  }

  //@Override
  private void setLeftNodeLen(int leftNodeLen) {
    this.leftNodeLen = leftNodeLen;
  }

  //@Override
  private void setUpNodeLen(int upNodeLen) {
    this.upNodeLen = upNodeLen;
  }

  //@Override
  private void setDownNodeLen(int downNodeLen) {
    this.downNodeLen = downNodeLen;
  }


  @Override
  public List<Treasure> getTreasureList() {
    return this.treasureList;
  }

  @Override
  public List<WeaponEnum> getWeaponsList() {
    return this.weaponList;
  }

  @Override
  public void clearTreasureList() {
    this.treasureList.clear();
  }

  @Override
  public void clearWeaponList() {
    this.weaponList.clear();
  }

  @Override
  public void addTreasure(Treasure treasure) {
    if (this.getPossibleMoves().size() != 2) {
      this.treasureList.add(treasure);
    }
  }

  @Override
  public void addWeapon(WeaponEnum weapon) {
    this.weaponList.add(weapon);
  }

  @Override
  public List<int[]> getPossibleMoves() {
    List<int[]> moves = new ArrayList<>();

    if (getUpNode() != null) {
      moves.add(getUpNode().getMatrixIndex());
    }
    if (getLeftNode() != null) {
      moves.add(getLeftNode().getMatrixIndex());
    }
    if (getRightNode() != null) {
      moves.add(getRightNode().getMatrixIndex());
    }

    if (getDownNode() != null) {
      moves.add(getDownNode().getMatrixIndex());
    }

    return moves;
  }


  @Override
  public int[] getMatrixIndex() {
    return matrixIndex;
  }

  @Override
  public void setMatrixIndex(int[] matrixIndex) {
    this.matrixIndex = matrixIndex;
  }


  @Override
  public void setNodeName(String name) {
    this.nodeName = name;
  }

  @Override
  public boolean isCave() {
    return this.getPossibleMoves().size() != 2;
  }

  private void updateMonster() {
    List<MonsterInterface> monsters = new ArrayList<>();
    for (MonsterInterface m : this.monsterList) {
      if (!m.isDead()) {
        monsters.add(m);
      }
    }
    this.monsterList = monsters;
  }

  @Override
  public List<MonsterInterface> getMonsters() {
    this.updateMonster();
    return this.monsterList;
  }

  @Override
  public void addMonster(MonsterInterface monster) {
    if (this.isCave()) {
      this.monsterList.add(monster);
    }
  }

  @Override
  public String toString() {
    String t = isCave() ? "Cave" : "Tunnel";
    return "CaveTunnel{" + "type: " + t
            + ", treasureList=" + treasureList + ", matrixIndex=" + Arrays.toString(matrixIndex)
            + ", monsterList=" + monsterList + ", weaponList=" + weaponList + '}';
  }


  //  public String getNodeName() {
  //    return nodeName;
  //  }
}
