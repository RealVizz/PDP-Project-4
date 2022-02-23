import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for PlayerTest2, for new functionalities.
 */
public class PlayerTest2 {
  int[] currLocation = {-1, -1};
  List<Treasure> emptyTreasureList = new ArrayList<>();
  CaveTunnelInterface currCaveCumTunnel = new CaveTunnel();

  @Test
  public void pickWeapons() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    currCaveCumTunnel.setMatrixIndex(currLocation);
    currCaveCumTunnel.addWeapon(WeaponEnum.CROOKED_ARROW);
    p2.pickWeapons();
    Assert.assertEquals(WeaponEnum.CROOKED_ARROW, p2.getCollectedWeapons().get(0));
  }

  @Test
  public void isDead() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    Assert.assertFalse(p2.isDead());
    p2.killPlayer();
    Assert.assertTrue(p2.isDead());
  }

  @Test
  public void killPlayer() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    p2.killPlayer();
    Assert.assertTrue(p2.isDead());
  }

  @Test
  public void shootArrow() {
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    p2.shootArrow("R", 1);
    Assert.assertEquals(2, p2.getCollectedWeapons().size());
  }

  @Test
  public void ArrowGoesRightDirectionRightDistance() {
    int interconnectivity = 1000;
    int treasurePerc = 20;
    int monsterCount = 1;
    Player p2 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p2,
            false, monsterCount);
    d.movePlayer(new int[]{49, 49});
    CaveTunnelInterface currNode = d.getCaveTunnelFromLocation(d.getPlayerCurrentLocation());
    int[] currLoc = currNode.getMatrixIndex();
    CaveTunnelInterface c = d.getPlayer().getTargetCave("R", 1, currNode, currLoc);

    Assert.assertNull(c);
  }

  @Test
  public void smellCheck() {
    int interconnectivity = 1000;
    int treasurePerc = 20;
    int monsterCount = 1;
    Player p1 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();

    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        int[] safeLoc = new int[]{x.getMatrixIndex()[0] - 1, x.getMatrixIndex()[1]};
        if (x.getMatrixIndex()[0] <= 0) {
          continue; // If something is in row 0, then it will give IndexOOB exception. So......
        }

        if (d.getCaveTunnelFromLocation(safeLoc).getDownNode() != null) {
          d.movePlayer(safeLoc);
          Assert.assertEquals(2, d.playerSmell());
          break;
        }
      }
    }
  }



}