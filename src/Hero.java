public class Hero extends GameCharacter {

  public Hero() {
    super("hero-down.png");
  }

  public void moveLeft() {
    setFilePath("hero-left.png");
  }
}
