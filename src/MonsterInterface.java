import java.util.List;

/**
 * This MonsterInterface keeps all the public behaviours of a Monster.
 */
public interface MonsterInterface {

  /**
   * Kills a player.
   *
   * @param player The player to be killed.
   */
  void killPlayer(Player player);

  /**
   * Slay the player by a weapon.
   *
   * @param weapon The weapon object.
   */
  void slayBy(WeaponEnum weapon);

  /**
   * Tells whether the player is dead or not.
   *
   * @return The boolean status.
   */
  boolean isDead();


  /**
   * Gives the weapon list by which the monster was slayed so far.
   *
   * @return The weapon list bny which the monster was slayed so far.
   */
  List<WeaponEnum> getHitList();

}
