public class GameCharacter extends GameObject {

  public GameCharacter() {
  }

  public GameCharacter(String filePath) {
    super(filePath);
  }

  public int[] readHeroLocation(GameObject[][] matrix) {
    int[] heroCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Hero) {
          heroCoord[0] = i;
          heroCoord[1] = j;
        }
      }
    }
    return heroCoord;
  }

  public int[] readBossLocation(GameObject[][] matrix) {
    int[] bossCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Boss) {
          bossCoord[0] = i;
          bossCoord[1] = j;
        }
      }
    }
    return bossCoord;
  }

  public GameObject[][] moveHeroRight(GameObject[][] matrix) {
    int[] heroCoord;
    GameCharacter temp = new GameCharacter();
    heroCoord = temp.readHeroLocation(matrix);
    if (heroCoord[1] != matrix[0].length - 1) {
      if (!(matrix[heroCoord[0]][heroCoord[1] + 1] instanceof Wall)) {
        Floor floor = new Floor();
        matrix[heroCoord[0]][heroCoord[1]] = floor;
        Hero hero = new Hero();
        hero.moveRight();
        matrix[heroCoord[0]][heroCoord[1] + 1] = hero;
      }
    }
    return matrix;
  }

  public GameObject[][] moveHeroLeft(GameObject[][] matrix) {
    int[] heroCoord;
    GameCharacter temp = new GameCharacter();
    heroCoord = temp.readHeroLocation(matrix);
    if (heroCoord[1] != 0) {
      if (!(matrix[heroCoord[0]][heroCoord[1] - 1] instanceof Wall)) {
        Floor floor = new Floor();
        matrix[heroCoord[0]][heroCoord[1]] = floor;
        Hero hero = new Hero();
        hero.moveLeft();
        matrix[heroCoord[0]][heroCoord[1] - 1] = hero;
      }
    }
    return matrix;
  }

  public GameObject[][] moveHeroDown(GameObject[][] matrix) {
    int[] heroCoord;
    GameCharacter temp = new GameCharacter();
    heroCoord = temp.readHeroLocation(matrix);
    if (heroCoord[0] != matrix.length - 1) {
      if (!(matrix[heroCoord[0] + 1][heroCoord[1]] instanceof Wall)) {
        Floor floor = new Floor();
        matrix[heroCoord[0]][heroCoord[1]] = floor;
        Hero hero = new Hero();
        hero.moveDown();
        matrix[heroCoord[0] + 1][heroCoord[1]] = hero;
      }
    }
    return matrix;
  }

  public GameObject[][] moveHeroUp(GameObject[][] matrix) {
    int[] heroCoord;
    GameCharacter temp = new GameCharacter();
    heroCoord = temp.readHeroLocation(matrix);
    if (heroCoord[0] != 0) {
      if (!(matrix[heroCoord[0] - 1][heroCoord[1]] instanceof Wall)) {
        Floor floor = new Floor();
        matrix[heroCoord[0]][heroCoord[1]] = floor;
        Hero hero = new Hero();
        hero.moveUp();
        matrix[heroCoord[0] - 1][heroCoord[1]] = hero;
      }
    }
    return matrix;
  }
}
