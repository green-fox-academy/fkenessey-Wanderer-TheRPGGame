public class Hero extends GameCharacter {

  public Hero() {
    super("hero-down.png");
  }

  public void moveLeft() {
    setFilePath("hero-left.png");
  }

  public void moveRight() {
    setFilePath("hero-right.png");
  }

  public void moveDown() {
    setFilePath("hero-down.png");
  }

  public void moveUp() {
    setFilePath("hero-up.png");
  }
}
