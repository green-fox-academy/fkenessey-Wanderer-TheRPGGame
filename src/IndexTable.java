import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IndexTable {

  int size;
  String tileAccessPath = "Tilefield.txt";
  String characterAndTileLocation = "Gamefield.txt";
  GameObject[][] tileLocationMatrix = readPlan(tileAccessPath);
  GameObject[][] characterAndTileLocationMatrix = readPlan(characterAndTileLocation);

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
        PositionedImage tile = new PositionedImage(tileLocationMatrix[i][j].filePath,j * size,i * size);
        tile.draw(g);
      }
    }
  }

  public void drawCharacterPlan(Graphics g) {
    for (int i = 0; i < characterAndTileLocationMatrix.length; i++) {
      for (int j = 0; j < characterAndTileLocationMatrix[i].length; j++) {
        PositionedImage tile = new PositionedImage(characterAndTileLocationMatrix[i][j].filePath,j * size,i * size);
        tile.draw(g);
      }
    }
  }
}
