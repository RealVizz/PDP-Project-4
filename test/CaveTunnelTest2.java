import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for CaveTunnel, for new functionalities.
 */
public class CaveTunnelTest2 {

  @Test
  public void getWeaponsList() {
    CaveTunnelInterface ct = new CaveTunnel();
    Assert.assertEquals(0, ct.getWeaponsList().size());
    ct.addWeapon(WeaponEnum.CROOKED_ARROW);
    Assert.assertEquals(1, ct.getWeaponsList().size());
  }

  @Test
  public void clearWeaponList() {
    CaveTunnelInterface ct = new CaveTunnel();
    Assert.assertEquals(0, ct.getWeaponsList().size());
    ct.addWeapon(WeaponEnum.CROOKED_ARROW);
    Assert.assertEquals(1, ct.getWeaponsList().size());
    ct.clearWeaponList();
    Assert.assertEquals(0, ct.getWeaponsList().size());
  }

  @Test
  public void addWeapon() {
    CaveTunnelInterface ct = new CaveTunnel();
    ct.addWeapon(WeaponEnum.CROOKED_ARROW);
    Assert.assertEquals(1, ct.getWeaponsList().size());
  }

  @Test
  public void getMonsters() {
    CaveTunnelInterface ct = new CaveTunnel();
    MonsterInterface m1 = new Monster();
    ct.addMonster(m1);
    Assert.assertEquals(m1, ct.getMonsters().get(0));
  }

  @Test
  public void addMonster() {
    CaveTunnelInterface ct = new CaveTunnel();
    MonsterInterface m1 = new Monster();
    Assert.assertEquals(0, ct.getMonsters().size());
    ct.addMonster(m1);
    Assert.assertEquals(1, ct.getMonsters().size());
  }

}