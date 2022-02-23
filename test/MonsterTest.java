import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Monster.
 */
public class MonsterTest {
  MonsterInterface monster = new Monster();

  @Test
  public void killPlayer() {
    Player p = new Player();
    Assert.assertFalse(p.isDead());
    monster.killPlayer(p);
    Assert.assertTrue(p.isDead());
  }

  @Test
  public void slayBy() {
    WeaponEnum w = WeaponEnum.CROOKED_ARROW;

    Assert.assertEquals(0, monster.getHitList().size());

    monster.slayBy(w);
    Assert.assertEquals(w, monster.getHitList().get(0));

  }

  @Test
  public void isDead() {
    Assert.assertFalse(monster.isDead());

    WeaponEnum w = WeaponEnum.CROOKED_ARROW;
    monster.slayBy(w);
    Assert.assertFalse(monster.isDead());
    monster.slayBy(w);
    Assert.assertTrue(monster.isDead());

  }
}