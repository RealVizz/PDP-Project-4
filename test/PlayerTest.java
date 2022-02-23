import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Player.
 */
public class PlayerTest {

  int[] currLocation = {-1, -1};
  List<Treasure> emptyTreasureList = new ArrayList<>();
  CaveTunnelInterface currCaveCumTunnel = new CaveTunnel();
  Player p1 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);

  int interconnectivity = 8;
  int treasurePerc = 20;
  Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);

  @Test
  public void pickTreasure() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    Treasure t1 = new Treasure();
    currCaveCumTunnel.setMatrixIndex(currLocation);
    currCaveCumTunnel.addTreasure(t1);
    p2.pickTreasure();
    Assert.assertEquals(t1, p2.getCollectedTreasures().get(0));
  }

  @Test
  public void moveInDungeon() {
    int[] newLocation = {30, 30};
    CaveTunnelInterface newCaveCumTunnel = new CaveTunnel();
    p1.moveInDungeon(newLocation, newCaveCumTunnel);
    Assert.assertEquals(newLocation, p1.getCurrLoc());
  }

  /**
   * When no moves are possible.
   */
  @Test
  public void getPossibleMoves1() {
    int[] newLocation = {30, 30};
    CaveTunnelInterface newCaveCumTunnel = new CaveTunnel();
    p1.moveInDungeon(newLocation, newCaveCumTunnel);
    Assert.assertEquals(0, p1.getPossibleMoves().size());
  }

  /**
   * When some moves by player are possible.
   */
  @Test
  public void getPossibleMoves2() {
    int[] newLocation = {30, 30};
    d.movePlayer(newLocation);
    if (d.getCurrentMoves().size() > 0) {
      Assert.assertTrue(true);
    } else {
      Assert.fail();
    }
  }


  @Test
  public void getCurrLoc() {
    int[] newLoc = {2, 2};
    p1.moveInDungeon(newLoc, new CaveTunnel());
    Assert.assertEquals(newLoc, p1.getCurrLoc());
  }

  @Test
  public void getCollectedTreasures() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    Treasure t1 = new Treasure();
    Treasure t2 = new Treasure();
    currCaveCumTunnel.setMatrixIndex(currLocation);
    currCaveCumTunnel.addTreasure(t1);
    currCaveCumTunnel.addTreasure(t2);
    List<Treasure> treasuresList = new ArrayList<>();
    treasuresList.add(t1);
    treasuresList.add(t2);

    p2.pickTreasure();

    Assert.assertEquals(treasuresList, p2.getCollectedTreasures());
  }


  @Test
  public void testToString() {
    p1.moveInDungeon(new int[]{0, 0}, new CaveTunnel());
    Assert.assertEquals("Player{currLocation=[0, 0], playerTreasureList=[], "
            + "currCaveCumTunnel=Cave{caveTreasureList=[], matrixIndex=null}}", p1.toString());
  }
}