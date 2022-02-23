import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for CaveTunnel.
 */
public class CaveTunnelTest {

  @Test
  public void setNodeCheck() {
    CaveTunnelInterface node = new CaveTunnel();
    CaveTunnelInterface nodeRight = new CaveTunnel();
    CaveTunnelInterface nodeLeft = new CaveTunnel();
    CaveTunnelInterface nodeUp = new CaveTunnel();
    CaveTunnelInterface nodeDown = new CaveTunnel();

    node.setRightNode(nodeRight);
    node.setLeftNode(nodeLeft);
    node.setUpNode(nodeUp);
    node.setDownNode(nodeDown);

    CaveTunnelInterface nr = node.getRightNode();
    CaveTunnelInterface nl = node.getLeftNode();
    CaveTunnelInterface nu = node.getUpNode();
    CaveTunnelInterface nd = node.getDownNode();

    if (nr == nodeRight && nu == nodeUp && nl == nodeLeft && nd == nodeDown) {
      Assert.assertTrue(true);
    } else {
      Assert.fail();
    }


  }

  @Test
  public void tunnelHaveNoTreasure() {
    CaveTunnelInterface node = new CaveTunnel();
    CaveTunnelInterface nodeRight = new CaveTunnel();
    CaveTunnelInterface nodeLeft = new CaveTunnel();
    node.setRightNode(nodeRight);
    node.setLeftNode(nodeLeft);
    node.addTreasure(new Treasure());
    node.addTreasure(new Treasure());
    Assert.assertEquals(0, node.getTreasureList().size());

  }

  @Test
  public void caveCanHaveMultipleTreasure() {
    CaveTunnelInterface node = new CaveTunnel();
    CaveTunnelInterface nodeRight = new CaveTunnel();
    node.setRightNode(nodeRight);

    node.addTreasure(new Treasure());
    node.addTreasure(new Treasure());
    Assert.assertEquals(2, node.getTreasureList().size());

  }

  @Test
  public void addTreasure() {
    CaveTunnelInterface node = new CaveTunnel();
    Treasure t1 = new Treasure(TreasureContent.RUBIES);
    node.addTreasure(t1);

    Assert.assertEquals(TreasureContent.RUBIES, node.getTreasureList().get(0).getTreasure());

  }

  @Test
  public void setMatrixIndex() {
    CaveTunnelInterface node = new CaveTunnel();
    int[] loc = new int[]{1, 1};
    node.setMatrixIndex(loc);
    Assert.assertEquals(loc, node.getMatrixIndex());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Cave{caveTreasureList=[], matrixIndex=null}",
            new CaveTunnel().toString());
  }
}