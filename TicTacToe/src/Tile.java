import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Tile {
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

    if (this.layer != 0 && this.player == 0) {
      for (int i = 0; i < 9; i++) {
        Board.get(i).draw(g);
      }
    } else if (this.player == 0) {
      return;
    }

    if (this.player == 1) {
      g.drawLine(x*scaleX, y*scaleY, (x+1)*scaleX, (y+1)*scaleY);
      g.drawLine((x+1)*scaleX, y*scaleY, x*scaleX, (y+1)*scaleY);
    }

    if (this.player == -1) {
      g.drawOval(x*scaleX, y*scaleY, scaleX, scaleY);
    }
  }

  public boolean makeMove(int x, int y, int turn, int lx, int ly, int SIZE) {
    if (this.layer == 0) {
      if (this.x == x && this.y == y) {
        if (checkAllowed(x, y, lx, ly, SIZE)) {
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
        if (Board.get(i).makeMove(x, y, turn, lx, ly, SIZE)) {
          return true;
        }
      }
    } 
    return false;
  }

  public void checkVictory() {
    if(!checkWin() && layer != 0 && player == 0) {
      for(int i = 0; i < 9; i++) {
        Board.get(i).checkVictory();
      }
    }  
    checkWin();
  }

  public boolean checkWin() {
    if (layer == 0) return false;

    int[][] winCombos = {
      {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
      {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
      {0, 4, 8}, {2, 4, 6}             // diagonals
    };

    for (int[] combo : winCombos) {
      int p1 = Board.get(combo[0]).player;
      int p2 = Board.get(combo[1]).player;
      int p3 = Board.get(combo[2]).player;

      if (p1 != 0 && p1 == p2 && p2 == p3) {
        this.player = p1;
        return true;
      }
    }

    return false;
  }  

  public boolean checkAllowed(int x, int y, int lx, int ly, int SIZE) {
    if (lx == -1 && ly == -1 && player == 0) {
      return true;
    } else {
      return x / (SIZE / 3) == lx % (SIZE / 3) && y / (SIZE / 3) == ly % (SIZE / 3);
    }
  }
}

