import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener, KeyListener {
  private class Tile {
    int x;
    int y;
    int player;
    int scaleX;
    int scaleY;

    Tile(int x, int y, int p, int sx, int sy) {
      this.x = x;
      this.y = y;
      this.player = p;
      this.scaleX = sx;
      this.scaleY = sy;
    }

    public void draw(Graphics g) {
      g.setColor(Color.white);

      if (this.player == 0) {
        return;
      }

      if (this.player == 1) {
        g.drawLine(x*scaleX, y*scaleY, (x+1)*scaleX, (y+1)*scaleY);
        g.drawLine((x+1)*scaleX, y*scaleY, x*scaleX, (y+1)*scaleY);
      }

      if (this.player == -1) {
        g.drawOval(x, y, scaleX, scaleY);
      }
    }

    public void makeMove(int turn) {
      if (this.player == 0) {
        this.player = turn;
      }
    }
  }

  int w;
  int h;
  int SIZE;
  int scaleX;
  int scaleY;

  Timer Loop;

  ArrayList<Tile> Board;
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

    Board = new ArrayList<Tile>();
    rand  = new Random();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        Board.add(new Tile(j, i, 0, scaleX, scaleY));
      }
    }

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

    for (int i = 0; i < Board.size(); i++) {
      Tile t = Board.get(i);
      t.draw(g);
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

}
