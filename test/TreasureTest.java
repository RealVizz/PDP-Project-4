import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Test class for Treasure.
 */
public class TreasureTest {
  Treasure t = new Treasure();

  @Test
  public void getTreasure() {
    Assert.assertEquals("RUBIES", new Treasure(TreasureContent.RUBIES).toString());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("RUBIES", new Treasure(TreasureContent.RUBIES).toString());
  }

  @Test
  public void randomButLimitedTreasures() {
    HashSet<String> set = new HashSet<>();

    for (int i = 0; i < 100; i++) {
      set.add(new Treasure().toString());
    }

    if (set.size() <= TreasureContent.values().length - 1) {
      Assert.assertTrue(true);
    } else {
      Assert.fail();
    }
  }


}