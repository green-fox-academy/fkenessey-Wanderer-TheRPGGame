import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

  int testBoxX;
  int testBoxY;
  static IndexTable field = new IndexTable();


  public Board() {
    testBoxX = 300;
    testBoxY = 300;

    // set the size of your draw board
    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    graphics.fillRect(testBoxX, testBoxY, 100, 100);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    /*PositionedImage image = new PositionedImage("yourimage.png", 300, 300);
    image.draw(graphics);*/
    field.drawTilePlan(graphics);
    field.drawCharacterPlan(graphics);
    repaint();
  }

  /*public void rePaint(Graphics graphics, GameObject[][] matrix) {
    super.paint(graphics);
    graphics.fillRect(testBoxX, testBoxY, 100, 100);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    /*PositionedImage image = new PositionedImage("yourimage.png", 300, 300);
    image.draw(graphics);*/
    /*CharacterField field2 = new CharacterField(72);
    field2.drawPlan(graphics, matrix);
  }*/

  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener


  }

  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    // When the up or down keys hit, we change the position of our box

    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      testBoxY -= 100;

      field.moveCharacterRight(field.findCharacter("Hero").get(0));
      field.randomMovementGenerator(field.findCharacter("Boss"));
      field.randomMovementGenerator(field.findCharacter("Monster"));
    } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
      testBoxY += 100;

      field.moveCharacterLeft(field.findCharacter("Hero").get(0));
      field.randomMovementGenerator(field.findCharacter("Boss"));
      field.randomMovementGenerator(field.findCharacter("Monster"));
      //GameCharacter temp = new GameCharacter();
      //field.setCharacterAndTileLocationMatrix(temp.moveBossRight(field.getCharacterAndTileLocationMatrix()));
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      testBoxY += 100;
      //GameCharacter temp = new GameCharacter();
      field.moveCharacterDown(field.findCharacter("Hero").get(0));
      field.randomMovementGenerator(field.findCharacter("Boss"));
      field.randomMovementGenerator(field.findCharacter("Monster"));
      //GameCharacter temp = new GameCharacter();
      //field.setCharacterAndTileLocationMatrix(temp.moveBossUp(field.getCharacterAndTileLocationMatrix()));
    } else if(e.getKeyCode() == KeyEvent.VK_UP) {
      testBoxY += 100;

      field.moveCharacterUp(field.findCharacter("Hero").get(0));
      field.randomMovementGenerator(field.findCharacter("Boss"));
      field.randomMovementGenerator(field.findCharacter("Monster"));
      //GameCharacter temp = new GameCharacter();
      //field.setCharacterAndTileLocationMatrix(temp.moveBossDown(field.getCharacterAndTileLocationMatrix()));
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}