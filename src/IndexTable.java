import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IndexTable {

  private int size;
  private String tileAccessPath = "Tilefield.txt";
  private String characterAndTileLocation = "Gamefield.txt";
  private GameObject[][] tileLocationMatrix = readPlan(tileAccessPath);
  private GameObject[][] characterAndTileLocationMatrix = readPlan(characterAndTileLocation);


  public IndexTable() {
    this.size = 72;
  }

  public GameObject[][] getTileLocationMatrix() {
    return tileLocationMatrix;
  }

  public GameObject[][] getCharacterAndTileLocationMatrix() {
    return characterAndTileLocationMatrix;
  }

  public void setCharacterAndTileLocationMatrix(GameObject[][] characterAndTileLocationMatrix) {
    this.characterAndTileLocationMatrix = characterAndTileLocationMatrix;
  }



  public GameObject[][] readPlan(String fileAccessPath) {
    List<String> content = new ArrayList<>();
    Path filePath = Paths.get(fileAccessPath);
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

  public void drawTilePlan(Graphics g) {
    for (int i = 0; i < tileLocationMatrix.length; i++) {
      for (int j = 0; j < tileLocationMatrix[i].length; j++) {
        PositionedImage tile = new PositionedImage(tileLocationMatrix[i][j].filePath, j * size, i * size);
        tile.draw(g);
      }
    }
  }

  public void drawCharacterPlan(Graphics g) {
    for (int i = 0; i < characterAndTileLocationMatrix.length; i++) {
      for (int j = 0; j < characterAndTileLocationMatrix[i].length; j++) {
        PositionedImage tile = new PositionedImage(characterAndTileLocationMatrix[i][j].filePath, j * size, i * size);
        tile.draw(g);
      }
    }
  }

  public void moveCharacterRight(int[] startCoordinates) {
    if (startCoordinates[1] != characterAndTileLocationMatrix[0].length - 1) {
      if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] + 1] instanceof Wall)) {
        if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] + 1] instanceof GameCharacter)) {
          GameObject temp = characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] =
                  characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] + 1];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] + 1] = temp;
          if (characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] + 1] instanceof Hero) {
            Hero.heroStep++;
          }
        } else {
          //attack
        }
      } else if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] instanceof Hero)){
        moveAgain(startCoordinates);
      }
    }
  }

  public void moveCharacterLeft(int[] startCoordinates) {
    if (startCoordinates[1] != 0) {
      if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] - 1] instanceof Wall)) {
        if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] - 1] instanceof GameCharacter)) {
          GameObject temp = characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] =
                  characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] - 1];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] - 1] = temp;
          if (characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1] - 1] instanceof Hero) {
            Hero.heroStep++;
          }
        } else {
          //attack
        }
      } else if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] instanceof Hero)) {
        moveAgain(startCoordinates);
      }
    }
  }

  public void moveCharacterDown(int[] startCoordinates) {
    if (startCoordinates[0] != characterAndTileLocationMatrix.length - 1) {
      if (!(characterAndTileLocationMatrix[startCoordinates[0] + 1][startCoordinates[1]] instanceof Wall)) {
        if (!(characterAndTileLocationMatrix[startCoordinates[0] + 1][startCoordinates[1]] instanceof GameCharacter)) {
          GameObject temp = characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] =
                  characterAndTileLocationMatrix[startCoordinates[0] + 1][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0] + 1][startCoordinates[1]] = temp;
          if (characterAndTileLocationMatrix[startCoordinates[0] + 1][startCoordinates[1]] instanceof Hero) {
            Hero.heroStep++;
          }
        } else {
          //attack
        }
      } else if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] instanceof Hero)) {
        moveAgain(startCoordinates);
      }
    }
  }

  public void moveCharacterUp(int[] startCoordinates) {
    if (startCoordinates[0] != 0) {
      if (!(characterAndTileLocationMatrix[startCoordinates[0] - 1][startCoordinates[1]] instanceof Wall)) {
        if (!(characterAndTileLocationMatrix[startCoordinates[0] - 1][startCoordinates[1]] instanceof GameCharacter)) {
          GameObject temp = characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] =
                  characterAndTileLocationMatrix[startCoordinates[0] - 1][startCoordinates[1]];
          characterAndTileLocationMatrix[startCoordinates[0] - 1][startCoordinates[1]] = temp;
          if (characterAndTileLocationMatrix[startCoordinates[0] - 1][startCoordinates[1]] instanceof Hero) {
            Hero.heroStep++;
          }
        } else {
          //attack
        }
      } else if (!(characterAndTileLocationMatrix[startCoordinates[0]][startCoordinates[1]] instanceof Hero)) {
        moveAgain(startCoordinates);
      }
    }
  }

  public void randomMovementGenerator(List<int[]> startCoordinates) {
    if (Hero.heroStep % 2 == 0) {
      for (int i = 0; i < startCoordinates.size(); i++) {
        int direction = (int)(Math.random() * 100);
        if (direction < 25) {
          moveCharacterRight(startCoordinates.get(i));
        } else if (direction >= 25 && direction < 50) {
          moveCharacterLeft(startCoordinates.get(i));
        } else if (direction >= 50 && direction < 75) {
          moveCharacterDown(startCoordinates.get(i));
        } else {
          moveCharacterUp(startCoordinates.get(i));
        }
      }
    }
  }

  public void moveAgain(int[] startCoordinates) {
    List<int[]> coordinates = new ArrayList<>();
    coordinates.add(startCoordinates);
    randomMovementGenerator(coordinates);
  }

  public List<int[]> findCharacter(String className) {
    List<int[]> coordinates = new ArrayList<>();
    for (int i = 0; i < characterAndTileLocationMatrix.length; i++) {
      for (int j = 0; j < characterAndTileLocationMatrix[i].length; j++) {
        if (className.equals("Hero") && (characterAndTileLocationMatrix[i][j] instanceof Hero)) {
          int[]characterCoordinates = new int[2];
          characterCoordinates[0] = i;
          characterCoordinates[1] = j;
          coordinates.add(characterCoordinates);
        } else if (className.equals("Boss") && (characterAndTileLocationMatrix[i][j] instanceof Boss)) {
          int[]characterCoordinates = new int[2];
          characterCoordinates[0] = i;
          characterCoordinates[1] = j;
          coordinates.add(characterCoordinates);
        } else if (className.equals("Monster") && (characterAndTileLocationMatrix[i][j] instanceof Monster)) {
          int[]characterCoordinates = new int[2];
          characterCoordinates[0] = i;
          characterCoordinates[1] = j;
          coordinates.add(characterCoordinates);
        }
      }
    }
    return coordinates;
  }

}
