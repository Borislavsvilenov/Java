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
    int layer;

    ArrayList<Tile> Board;



    Tile(int x, int y, int p, int sx, int sy, int layer) {
      this.x = x;
      this.y = y;
      this.player = p;
      this.scaleX = sx/3;
      this.scaleY = sy/3;
      this.layer = layer;
        
      Board = new ArrayList<Tile>();

      if (layer != 0) {
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            Board.add(new Tile(j+(3*x), i+(3*y), 0, scaleX, scaleY, layer - 1));
          }
        }
      }
    }

    public void draw(Graphics g) {
      g.setColor(Color.white);
      
      if (layer == 0) {
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
      } else {
        for (int i = 0; i < 9; i++) {
          Board.get(i).draw(g);
        }
      }
    }

    public boolean makeMove(int x, int y, int turn) {
      if (this.layer == 0) {
        if (this.x == x && this.y == y) {
          if (this.player == 0) {
            this.player = turn;
            return true;
          } else {
            return false;
          }
        } else { 
          return false; 
        }
      } else {
        for (int i = 0; i < 9; i++) {
          if (Board.get(i).makeMove(x, y, turn)) {
            return true;
          }
        }
      } 
      return false;
    }
      
    public void checkVictory() {
      if(!checkWin() && layer != 0) {
        for(int i = 0; i < 9; i++) {
          Board.get(i).checkVictory();
        }
      }
    }

    public boolean checkWin() {
      if (layer == 0) {
        return false;
      } else if (Board.get(0).player == 1 && Board.get(1).player == 1 && Board.get(2).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(3).player == 1 && Board.get(4).player == 1 && Board.get(5).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(6).player == 1 && Board.get(7).player == 1 && Board.get(8).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(0).player == 1 && Board.get(3).player == 1 && Board.get(6).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(1).player == 1 && Board.get(4).player == 1 && Board.get(7).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(2).player == 1 && Board.get(5).player == 1 && Board.get(8).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(0).player == 1 && Board.get(4).player == 1 && Board.get(8).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(2).player == 1 && Board.get(4).player == 1 && Board.get(6).player == 1) {
        this.player = 1;
        return true;
      } else if (Board.get(0).player == -1 && Board.get(1).player == -1 && Board.get(2).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(3).player == -1 && Board.get(4).player == -1 && Board.get(5).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(6).player == -1 && Board.get(7).player == -1 && Board.get(8).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(0).player == -1 && Board.get(3).player == -1 && Board.get(6).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(1).player == -1 && Board.get(4).player == -1 && Board.get(7).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(2).player == -1 && Board.get(5).player == -1 && Board.get(8).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(0).player == -1 && Board.get(4).player == -1 && Board.get(8).player == -1) {
        this.player = -1;
        return true;
      } else if (Board.get(2).player == -1 && Board.get(4).player == -1 && Board.get(6).player == -1) {
        this.player = -1;
        return true;
      } else {
        return false;
      }
    }
  }

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


    Board = new Tile(0, 0, 0, w*3, h*3, scale);
    rand  = new Random();

    Loop = new Timer(100, this);
    Loop.start();

    move();
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
  
  public void move() {
    if(Board.makeMove(1, 2, turn)) {
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

}
