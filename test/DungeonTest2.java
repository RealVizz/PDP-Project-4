import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Dungeon.
 */
public class DungeonTest2 {
  int[] currLocation = {-1, -1};
  List<Treasure> emptyTreasureList = new ArrayList<>();
  CaveTunnelInterface currCaveCumTunnel = new CaveTunnel();
  Player p1 = new Player(currLocation, emptyTreasureList, currCaveCumTunnel);


  @Test
  public void minimumOneMonster() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 0;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();

    int monstersInDungeon = 0;
    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        monstersInDungeon += 1;
      }
    }

    Assert.assertEquals(1, monstersInDungeon);
  }


  @Test
  public void monsterAtEnd() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 0;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();


    CaveTunnelInterface endCave = d.getCaveTunnelFromLocation(d.getEndPosition());
    Assert.assertEquals(1, endCave.getMonsters().size());
  }

  @Test
  public void noMonsterAtStart() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 0;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();


    CaveTunnelInterface startCave = d.getCaveTunnelFromLocation(d.getPlayerCurrentLocation());
    Assert.assertEquals(0, startCave.getMonsters().size());
  }


  @Test
  public void correctAmountOfMonsters() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();
    //System.out.println(list.size());

    int monstersInDungeon = 0;
    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        monstersInDungeon += 1;
      }
    }

    Assert.assertEquals(monsterCount, monstersInDungeon);
  }

  @Test
  public void noMonsterInTunnel() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllTunnels();
    //System.out.println(list.size());

    int monstersInDungeon = 0;
    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        monstersInDungeon += 1;
      }
    }

    Assert.assertEquals(0, monstersInDungeon);
  }


  @Test
  public void monsterKillPlayerIfNotSlayed() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();

    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        d.movePlayer(x.getMatrixIndex());
        break;
      }
    }

    Assert.assertTrue(p1.isDead());
  }


  @Test
  public void monsterDoNotKillPlayerIfSlayed() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();

    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        int[] safeLoc = new int[]{x.getMatrixIndex()[0] - 1, x.getMatrixIndex()[1]};
        d.movePlayer(safeLoc);
        x.getMonsters().get(0).slayBy(WeaponEnum.CROOKED_ARROW);
        x.getMonsters().get(0).slayBy(WeaponEnum.CROOKED_ARROW);
        d.movePlayer(x.getMatrixIndex());
        break;
      }
    }

    Assert.assertFalse(p1.isDead());
  }


  @Test
  public void monster50_50_Chance_KillPlayerIfHalfSlayed() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    List<Boolean> multipleTriesList = new ArrayList<>();

    for (int k = 0; k < 50; k++) {
      Player newPlayer = new Player();
      DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity,
              newPlayer, true, monsterCount);
      List<CaveTunnelInterface> list = d.getAllCaves();

      for (CaveTunnelInterface x : list) {
        if (x.getMonsters().size() > 0) {
          int[] safeLoc = new int[]{x.getMatrixIndex()[0] - 1, x.getMatrixIndex()[1]};
          if (x.getMatrixIndex()[0] <= 0) {
            break; // If something is in row 0, then it will give IndexOOB exception. So......
          }
          d.movePlayer(safeLoc);
          x.getMonsters().get(0).slayBy(WeaponEnum.CROOKED_ARROW);
          d.movePlayer(x.getMatrixIndex());
          multipleTriesList.add(newPlayer.isDead());
          break;
        }
        if (newPlayer.isDead()) {
          break;
        }
      }
    }


    Assert.assertTrue(multipleTriesList.contains(true) && multipleTriesList.contains(false));
  }


  @Test
  public void treasurePercentageCheck() {
    int interconnectivity = 0;
    int treasurePerc = 10;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            false, 1);

    List<CaveTunnelInterface> cavesList = d.getAllCaves();
    List<CaveTunnelInterface> tunnelList = d.getAllTunnels();
    int weaponFullNodes = 0;
    for (CaveTunnelInterface item : cavesList) {
      if (item.getWeaponsList().size() > 0) {
        weaponFullNodes += 1;
      }
    }
    for (CaveTunnelInterface item : tunnelList) {
      if (item.getWeaponsList().size() > 0) {
        weaponFullNodes += 1;
      }
    }

    int totalNodes = cavesList.size() + tunnelList.size();

    double perc = (weaponFullNodes * 100.0) / (totalNodes);

    if (perc >= treasurePerc) {
      Assert.assertTrue(true);
    } else {
      Assert.fail();
    }

  }


  @Test
  public void weaponsFoundInCaveAndTunnelBoth() {
    int interconnectivity = 0;
    int treasurePerc = 10;
    Dungeon d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            false, 1);

    List<CaveTunnelInterface> cavesList = d.getAllCaves();
    List<CaveTunnelInterface> tunnelList = d.getAllTunnels();
    List<Integer> weaponFullNodes = new ArrayList<>();
    for (CaveTunnelInterface item : cavesList) {
      if (item.getWeaponsList().size() > 0) {
        weaponFullNodes.add(1); // add 1 if any weapon found in any Cave.
      }
    }
    for (CaveTunnelInterface item : tunnelList) {
      if (item.getWeaponsList().size() > 0) {
        weaponFullNodes.add(0); // add 0 if any weapon found in any Tunnel.
      }
    }

    Assert.assertTrue(weaponFullNodes.contains(0) && weaponFullNodes.contains(1));
  }


  @Test
  public void twoHitsToKillAMonster() {
    int interconnectivity = 1000;
    int treasurePerc = 20;
    int monsterCount = 1;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();
    int shootDistance = 1;

    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        int[] safeLoc = new int[]{x.getMatrixIndex()[0] - 1, x.getMatrixIndex()[1]};
        if (x.getMatrixIndex()[0] <= 0) {
          continue; // If something is in row 0, then it will give IndexOOB exception. So......
        }

        if (d.getCaveTunnelFromLocation(safeLoc).getDownNode() != null) {
          d.movePlayer(safeLoc);

          p1.shootArrow("D", shootDistance);
          p1.shootArrow("D", shootDistance);

          d.movePlayer(x.getMatrixIndex());
          break;
        }

      }
    }


    Assert.assertFalse(p1.isDead());

  }


  @Test
  public void exactDistanceToKillAMonster() {
    int interconnectivity = 8;
    int treasurePerc = 20;
    int monsterCount = 8;
    DungeonInterface d = new Dungeon("50x50", treasurePerc, interconnectivity, p1,
            true, monsterCount);
    List<CaveTunnelInterface> list = d.getAllCaves();
    int shootDistance = 2;

    for (CaveTunnelInterface x : list) {
      if (x.getMonsters().size() > 0) {
        int[] safeLoc = new int[]{x.getMatrixIndex()[0] - 1, x.getMatrixIndex()[1]};
        if (x.getMatrixIndex()[0] <= 0) {
          continue; // If something is in row 0, then it will give IndexOOB exception. So......
        }
        if (d.getCaveTunnelFromLocation(safeLoc).getDownNode() != null) {
          d.movePlayer(safeLoc);

          p1.shootArrow("D", shootDistance);
          p1.shootArrow("D", shootDistance);

          d.movePlayer(x.getMatrixIndex());
          break;
        }
      }
    }


    Assert.assertTrue(p1.isDead());

  }


}