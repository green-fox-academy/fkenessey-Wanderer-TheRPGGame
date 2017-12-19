import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TileField {

  int size;

  public TileField(int size) {
    this.size = size;
  }

  public GameObject[][] readPlan() {
    List<String> content = new ArrayList<>();
    Path filePath = Paths.get("Tilefield.txt");
    try {
      content = Files.readAllLines(filePath);
    } catch (IOException e) {
      System.out.println("Reading error");
    }
    GameObject[][] coordList = new GameObject[content.size()][content.get(0).length()];
    for (int i = 0; i < content.size(); i++) {
      for (int j = 0; j < content.get(i).length(); j++) {
        if (content.get(i).charAt(j) == 'w') {
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
        PositionedImage tile = new PositionedImage(matrix[i][j].filePath,i * size,j * size);
        tile.draw(g);
      }
    }
  }
}
