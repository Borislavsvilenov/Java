import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener {
  int w;
  int h;
  int SIZE;
  int scaleX;
  int scaleY;
  int turn = 1;

  Timer Loop;

  Tile Board;
  Random rand;

  Game(int w, int h, int scale) {
    this.w = w;
    this.h = h;
    this.SIZE = (int)Math.round(Math.pow(3, scale));
    scaleX = w/SIZE;
    scaleY = h/SIZE;
    setPreferredSize(new Dimension(this.w, this.h));
    setBackground(Color.black);
    setFocusable(true);
    addKeyListener(this);
    addMouseListener(this);


    Board = new Tile(0, 0, 0, w*3, h*3, scale);
    rand  = new Random();

    Loop = new Timer(100, this);
    Loop.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    // Grid
    g.setColor(Color.white);
    for (int i = 1; i < SIZE; i++) {
      g.drawLine(i*scaleX, 0, i*scaleX, h);
    }

    for (int i = 1; i < SIZE; i++) {
      g.drawLine(0, i*scaleY, w, i*scaleY);
    }

    Board.draw(g);
    
  }
  
  public void move(int x, int y) {
    if(Board.makeMove(x, y, turn)) {
      turn *= -1;
      Board.checkVictory();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      System.exit(0);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }  

  @Override
  public void keyTyped(KeyEvent e) {
    // no need
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // no need
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    int mx = (int)Math.floor(e.getX()/scaleX);
    int my = (int)Math.floor(e.getY()/scaleY);

    move(mx, my);
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

}
