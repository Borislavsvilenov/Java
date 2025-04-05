import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener{
  private class Tile {
    int x;
    int y;

    Tile(int x, int y) {
      this.x = x;
      this.y = y;
    }
  } 

  int w;
  int h;
  int SIZE = 25;

  int Vx;
  int Vy;

  ArrayList<Tile> snake;
  Tile snakeH;
  Tile food;

  Random rand;

  Timer LOOP;
  boolean gameOver = false;

  Game(int w, int h) {
    this.w = w;
    this.h = h;
    setPreferredSize(new Dimension(this.w, this.h));
    setBackground(Color.black);
    addKeyListener(this);
    setFocusable(true);
    

    rand = new Random();
    
    snake = new ArrayList<Tile>();
    snakeH = new Tile(rand.nextInt(w/SIZE), rand.nextInt(h/SIZE));
    food = new Tile(0, 0);

    placeFood();

    Vx = 0;
    Vy = 0;

    LOOP = new Timer(100, this);
    LOOP.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    //  SNAKE
    g.setColor(Color.green);
    g.fillRect(snakeH.x * SIZE, snakeH.y * SIZE, SIZE, SIZE);
  
    for (int i = 0; i < snake.size(); i++) {
      Tile part = snake.get(i);
      g.fillRect(part.x * SIZE, part.y * SIZE, SIZE, SIZE);
    }

    //  FOOD
    g.setColor(Color.red);
    g.fillRect(food.x * SIZE, food.y * SIZE, SIZE, SIZE);

  }

  public void placeFood() {
    food.x = rand.nextInt(w/SIZE);
    food.y = rand.nextInt(h/SIZE);
  }

  public boolean checkCollision(Tile tile1, Tile tile2) {
    if (tile1.x == tile2.x && tile1.y == tile2.y) {
      return true;
    } else {
      return false;
    }
  }

  public void update() {
    if (checkCollision(snakeH, food)) {
      snake.add(new Tile(snakeH.x, snakeH.y));
      placeFood();
    }
    
    for (int i = snake.size() - 1; i >= 0; i--) {
      Tile part = snake.get(i);
      if (i > 0) {
        part.x = snake.get(i-1).x;
        part.y = snake.get(i-1).y;
      } else {
        part.x = snakeH.x;
        part.y = snakeH.y;
      }
    }

    snakeH.x += Vx;
    snakeH.y += Vy;

    for (int i = 0; i < snake.size(); i++) {
      if (checkCollision(snakeH, snake.get(i))) {
        gameOver = true;
      }
    }

    if (snakeH.x < 0 || snakeH.x > w/SIZE - 1 || snakeH.y < 0 || snakeH.y > h/SIZE - 1) {
      gameOver = true;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(!gameOver) {
      update();
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP && Vy != 1) {
      Vx = 0;
      Vy = -1;
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN && Vy != -1) {
      Vx = 0;
      Vy = 1;
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT && Vx != 1) {
      Vx = -1;
      Vy = 0;
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT && Vx != -1) {
      Vx = 1;
      Vy = 0;
    }

    if (e.getKeyCode() == KeyEvent.VK_R) {
      placeFood();
      snake.clear();
      snakeH.x = rand.nextInt(w/SIZE);
      snakeH.y = rand.nextInt(h/SIZE);
      Vx = 0;
      Vy = 0;
      gameOver = false;
    }

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










