import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The class to represent a monster in dungeon.
 */
public class Monster implements MonsterInterface {
  private final List<WeaponEnum> hitList;
  private boolean isDead;

  /**
   * Default constructor for monster.
   */
  public Monster() {
    this.hitList = new ArrayList<>();
    this.isDead = false;
  }


  @Override
  public void killPlayer(Player player) {
    if (!this.isDead()) {
      if (hitList.contains(WeaponEnum.CROOKED_ARROW)) {
        int randNum = ThreadLocalRandom.current().nextInt();
        if (randNum % 2 == 0) { // If an even number appears, then kill player else don't.
          player.killPlayer();
        }
      } else {
        player.killPlayer();
      }

    }
  }

  @Override
  public void slayBy(WeaponEnum weapon) {
    hitList.add(weapon);
    if (hitList.size() >= 2) {
      this.isDead = true;
    }
  }

  @Override
  public boolean isDead() {
    return isDead;
  }

  @Override
  public List<WeaponEnum> getHitList() {
    return this.hitList;
  }

}
