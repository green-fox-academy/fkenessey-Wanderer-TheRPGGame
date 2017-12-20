public class Hero extends GameCharacter {

  static int heroStep;
  static int heroLevel;

  public Hero() {
    super("hero-down.png", 20 + 3 * d6(),2 * d6(), 5 + d6());
  }
}
