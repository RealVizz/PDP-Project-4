import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;

/**
 * Test class for ControllerTest.
 */
public class ControllerTest {

  @Test
  public void validInputOutput() {
    StringReader input = new StringReader("M R Q");
    Appendable gameLog = new StringBuffer();

    StringBuffer log = new StringBuffer();
    DungeonInterface mockModel = new MockDungeon(log);

    Controller controller = new Controller(input, gameLog, mockModel);
    controller.playGame();
    Assert.assertEquals("getEndPosition  getPlayerCurrentLocation  "
            + "getPlayerCurrentLocation  [0, 0]  getCurrentMoves  getPlayerCurrentLocation  [0, 0]"
            + "  [0, 1]  getPlayerCurrentLocation  [0, 0]  [0, 1]  getPlayerCurrentLocation  "
            + "getPlayerCurrentLocation  [0, 1]  getCurrentMoves", log.toString().strip());
  }
}