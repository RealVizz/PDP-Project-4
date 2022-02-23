import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The treasure class represents a treasure it can have, from the given strict,
 * which is complied by the TreasureContent Enum.
 */
public class Treasure {
  private final TreasureContent treasure;

  /**
   * Initialises the value of the newly created Treasure object.
   *
   * @param t The TreasureContent object.
   */
  Treasure(TreasureContent t) {
    this.treasure = t;
  }


  /**
   * Randomly Initialises the value of the newly created Treasure object.
   * Also, Randomly Initialises how many of the items will be present in that object.
   */
  Treasure() {
    List<TreasureContent> treasureContentList = List.of(TreasureContent.values());

    // treasureContentList.size() -1  --> so that last item "NONE" does not get selected.
    int randNum = ThreadLocalRandom.current().nextInt(treasureContentList.size() - 1);
    this.treasure = treasureContentList.get(randNum);
  }

  TreasureContent getTreasure() {
    return this.treasure;
  }

  @Override
  public String toString() {
    return treasure.toString();
  }
}
