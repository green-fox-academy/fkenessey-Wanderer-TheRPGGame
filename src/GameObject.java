public class GameObject {

  String filePath;

  public GameObject() {
  }

  public GameObject(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
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
