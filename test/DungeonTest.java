import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Dungeon.
 */
public class DungeonTest {
  int[] currLocation = {-1, -1};
  List<Treasure> emptyTreasureList = new ArrayList<>();
  CaveTunnelInterface currCaveCumTunnel = new CaveTunnel();
  Player p1 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);


  @Test
  public void invalidRowCountTest1() {
    try {
      new Dungeon("0x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidRowCountTest2() {
    try {
      new Dungeon("-1x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidColCountTest1() {
    try {
      new Dungeon("2x0", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidColCountTest2() {
    try {
      new Dungeon("1x-2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }


  @Test
  public void invalidStartRowTest1() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidStartRowTest2() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidStartColumnTest1() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidStartColumnTest2() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidEndRowTest1() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }


  @Test
  public void invalidEndRowTest2() {
    try {
      new Dungeon("2x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }


  @Test
  public void invalidEndColumnTest1() {
    try {
      new Dungeon("1x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidEndColumnTest2() {
    try {
      new Dungeon("1x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidTreasurePercentageTest1() {
    try {
      new Dungeon("5x5", -20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidTreasurePercentageTest2() {
    try {
      new Dungeon("5x5", 200, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }


  @Test
  public void impossibleLength5Constraint() {
    try {
      new Dungeon("1x2", 20, 0, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void invalidInterconnectivity() {
    try {
      new Dungeon("6x9", 20, -1, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void tooHighInterConnectivity() {
    try {
      new Dungeon("6x9", 20, 180, p1, false, 1);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void pathLengthCheck() {
    try {
      Dungeon d = new Dungeon("6x9", 20, 0, p1, false, 1);
      int[] start = d.getStartPosition();
      int[] end = d.getEndPosition();
      if (Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]) >= 5) {
        Assert.assertTrue(true);
      } else {
        Assert.fail();
      }
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void interconnectivityTest1() {
    int interconnectivity = 0;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("5x5", treasurePerc, interconnectivity, p1, false, 1);
    int totalEdges = d.getDungeonEdges().size();
    Assert.assertEquals(d.getTotalNodes() + interconnectivity - 1, totalEdges);
  }

  @Test
  public void interconnectivityTest2() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    int totalEdges = d.getDungeonEdges().size();
    Assert.assertEquals(d.getTotalNodes() + interconnectivity - 1, totalEdges);
  }

  @Test
  public void MSTTest() {
    int interconnectivity = 0;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    int totalEdges = d.getDungeonEdges().size();
    Assert.assertEquals(d.getTotalNodes() - 1, totalEdges);
  }

  @Test
  public void interconnectivityWithWarpTest1() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    int totalEdges = d.getDungeonEdges().size();
    Assert.assertEquals(d.getTotalNodes() + interconnectivity - 1, totalEdges);
  }


  @Test
  public void warpTestLeft() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    d.forceSetStartPosition(new int[]{0, 0});
    d.movePlayer(new int[]{0, 0});
    List<int[]> moveList = d.getCurrentMoves();
    int warpLeftRow = 0;
    int warpLeftCol = 50 - 1;
    for (int[] x : moveList) {
      if (x[0] == warpLeftRow && x[1] == warpLeftCol) {
        Assert.assertTrue(true);
        return;
      }
    }
    Assert.fail();
  }

  @Test
  public void warpTestUp() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    d.forceSetStartPosition(new int[]{0, 0});
    d.movePlayer(new int[]{0, 0});
    List<int[]> moveList = d.getCurrentMoves();
    int warpRow = 50 - 1;
    int warpCol = 0;
    for (int[] x : moveList) {
      if (x[0] == warpRow && x[1] == warpCol) {
        Assert.assertTrue(true);
        return;
      }
    }
    Assert.fail();
  }

  @Test
  public void warpTestRight() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    d.forceSetStartPosition(new int[]{0, 50 - 1});
    d.movePlayer(new int[]{0, 50 - 1});
    List<int[]> moveList = d.getCurrentMoves();
    int warpRow = 0;
    int warpCol = 0;
    for (int[] x : moveList) {
      if (x[0] == warpRow && x[1] == warpCol) {
        Assert.assertTrue(true);
        return;
      }
    }
    Assert.fail();
  }

  @Test
  public void warpTestRightFail() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    d.forceSetStartPosition(new int[]{0, 50 - 1});
    d.movePlayer(new int[]{0, 50 - 1});
    List<int[]> moveList = d.getCurrentMoves();
    int warpRow = 0;
    int warpCol = 0;
    for (int[] x : moveList) {
      if (x[0] == warpRow && x[1] == warpCol) {
        Assert.fail();
        return;
      }
    }
    Assert.assertTrue(true);
  }


  @Test
  public void warpTestDown() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    d.forceSetStartPosition(new int[]{50 - 1, 50 - 1});
    d.movePlayer(new int[]{50 - 1, 50 - 1});
    List<int[]> moveList = d.getCurrentMoves();
    int warpRow = 0;
    int warpCol = 50 - 1;
    for (int[] x : moveList) {
      if (x[0] == warpRow && x[1] == warpCol) {
        Assert.assertTrue(true);
        return;
      }
    }
    Assert.fail();
  }

  @Test
  public void warpTestDownFail() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    d.forceSetStartPosition(new int[]{50 - 1, 50 - 1});
    d.movePlayer(new int[]{50 - 1, 50 - 1});
    List<int[]> moveList = d.getCurrentMoves();
    int warpRow = 0;
    int warpCol = 50 - 1;
    for (int[] x : moveList) {
      if (x[0] == warpRow && x[1] == warpCol) {
        Assert.fail();
        return;
      }
    }
    Assert.assertTrue(true);
  }


  @Test
  public void startEndNodeRandom() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);

    for (int i = 0; i < 20; i++) {
      d.setRandomStartEnd();
      int[] s1 = d.getStartPosition();
      int[] e1 = d.getEndPosition();
      d.setRandomStartEnd();
      int[] s2 = d.getStartPosition();
      int[] e2 = d.getEndPosition();

      if (s1 == s2 && e1 == e2) {
        Assert.fail();
        return;
      }
    }
    Assert.assertTrue(true);

  }

  @Test
  public void checkMinPathLength() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);

    Assert.assertTrue(d.minPathLengthCheck(d.getStartPosition(), d.getEndPosition()));

  }


  @Test
  public void treasurePercentageCheck() {
    int interconnectivity = 0;
    int treasurePerc = 10;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);

    List<CaveTunnelInterface> ctList = d.getTreasureFullContainersList();
    int treasureCaves = 0;
    for (CaveTunnelInterface item : ctList) {
      if (item.getTreasureList().size() > 0) {
        treasureCaves += 1;
      }
    }

    int totalCaves = d.getAllCaves().size();

    //int totalCaves = 0;
    //for (int i = 0; i < 50; i++) {
    //  for (int j = 0; j < 50; j++) {
    //    d.movePlayer(new int[]{i, j});
    //    if (d.getCurrentMoves().size() != 2) {
    //      totalCaves += 1;
    //    }
    //  }
    //}

    double perc = (treasureCaves * 100.0) / (totalCaves);

    if (perc >= treasurePerc) {
      Assert.assertTrue(true);
    } else {
      Assert.fail();
    }

  }


  @Test
  public void playerAtStart() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);

    Assert.assertEquals(p1.getCurrLoc(), d.getStartPosition());
  }


  @Test
  public void playerLocation() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    int[] newLoc = {2, 2};
    d.movePlayer(newLoc);
    Assert.assertEquals(newLoc, d.getPlayerCurrentLocation());
  }

  @Test
  public void playerReachEndNode() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, false, 1);
    int[] endLoc = d.getEndPosition();
    d.movePlayer(endLoc);
    Assert.assertEquals(endLoc, d.getPlayerCurrentLocation());
  }

  @Test
  public void playerReachAll4Nodes() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1, true, 1);
    d.forceSetStartPosition(new int[]{0, 0});
    d.movePlayer(new int[]{0, 0});

    List<int[]> movesList = d.getCurrentMoves();
    for (int[] x : movesList) {
      d.movePlayer(x);
      if (d.getPlayerCurrentLocation() != x) {
        Assert.fail();
      }
    }
    Assert.assertTrue(true);
  }

}