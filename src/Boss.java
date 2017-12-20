public class Boss extends GameCharacter {

  public Boss() {
    super("boss.png", 2 * Hero.heroLevel * d6() + d6(),Hero.heroLevel / 2 * d6() + d6() / 2, Hero.heroLevel * d6() + Hero.heroLevel);
  }
}
