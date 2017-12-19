import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CharacterField {

  int size;

  public CharacterField(int size) {
    this.size = size;
  }

  public GameObject[][] readPlan() {
    List<String> content = new ArrayList<>();
    Path filePath = Paths.get("Gamefield.txt");
    try {
      content = Files.readAllLines(filePath);
    } catch (IOException e) {
      System.out.println("Reading error");
    }
    GameObject[][] coordList = new GameObject[content.size()][content.get(0).length()];
    for (int i = 0; i < content.size(); i++) {
      for (int j = 0; j < content.get(i).length(); j++) {
        if (content.get(i).charAt(j) == 'h') {
          Hero hero = new Hero();
          coordList[i][j] = hero;
        } else if (content.get(i).charAt(j) == 'b') {
          Boss boss = new Boss();
          coordList[i][j] = boss;
        } else if (content.get(i).charAt(j) == 'm') {
          Monster monster = new Monster();
          coordList[i][j] = monster;
        } else if (content.get(i).charAt(j) == 'w') {
          Wall wall = new Wall();
          coordList[i][j] = wall;
        } else if (content.get(i).charAt(j) == 'p') {
          Floor floor = new Floor();
          coordList[i][j] = floor;
        }
      }
    }
    return coordList;
  }

  public void drawPlan(Graphics g, GameObject[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        PositionedImage tile2 = new PositionedImage(matrix[i][j].filePath,j * size,i * size);
        tile2.draw(g);
      }
    }
  }

  public GameObject[][] moveHeroRight(GameObject[][] matrix) {
    int[] heroCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Hero) {
          heroCoord[0] = i;
          heroCoord[1] = j;
        }
      }
    }
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
    int[] heroCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Hero) {
          heroCoord[0] = i;
          heroCoord[1] = j;
        }
      }
    }
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
    int[] heroCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Hero) {
          heroCoord[0] = i;
          heroCoord[1] = j;
        }
      }
    }
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
    int[] heroCoord = new int[2];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] instanceof Hero) {
          heroCoord[0] = i;
          heroCoord[1] = j;
        }
      }
    }
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
