import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
  public class Ball {
    int x;
    int y;
  
    long vx;
    long vy;

    int w;
    int h;

    int SIZE = 20;

    Ball(int w, int h, int vx, int vy) {
      x = w/2;
      y = h/2;
      this.vx = vx;
      this.vy = vy;
      this.w = w;
      this.h = h;
    }

    public void draw(Graphics g) {
      g.fillOval(x-(SIZE/2), y-(SIZE/2), SIZE, SIZE);
    }

    public void collideWalls(Box walls) {
      if (!walls.contains(x, y, SIZE)) {
        if (y < h/2) {
          vy = Math.abs(vy);
        } else if (y > h/2) {
          vy = -Math.abs(vy);
        }
      }
    }

    public void update() {
      x += vx;
      y += vy;
      collideWalls(box);
    }

    public void reset() {
      x = w/2;
      y = h/2;
      vx = rand.nextInt(5, 15);
      vy = rand.nextInt(5, 15);
    }
  }

  int w;
  int h;

  Player p1;
  Player p2;

  int score1 = 0;
  int score2 = 0;

  Ball ball;

  Box box;

  Timer Loop;
  Random rand;

  Game(int w, int h) {
    this.w = w;
    this.h = h;

    p1 = new Player(20, 225, 20, 150);
    p2 = new Player(this.w - 40, 225, 20, 150);

    rand = new Random();

    ball = new Ball(this.w, this.h, rand.nextInt(5, 15), rand.nextInt(5, 15));

    box = new Box(10, 10, this.w - 20, this.h - 20);
    
    Loop = new Timer(25, this);
    Loop.start();

    setPreferredSize(new Dimension(this.w, this.h));
    setBackground(Color.black);
    setFocusable(true);
    addKeyListener(this);
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }


  public void draw(Graphics g) {
    // draw players
    p1.fill(g);
    p2.fill(g);
    
    // draw ball
    ball.draw(g);
    
    // draw field
    box.outLine(g);
    g.drawLine(w/2, 10, w/2, h - 10);
    g.drawOval(w/2 -100, h/2 -100, 200, 200);

    // draw score
    g.setFont(new Font("Arial", Font.PLAIN, 30));
    g.drawString(String.valueOf(score1), w/2 - 200, h/2 + 10);
    g.drawString(String.valueOf(score2), w/2 + 200, h/2 + 10);
  }

  public void update() {
    p1.update(h);
    p2.update(h);
    ball.update();

    if(p1.collides(ball.x, ball.y, ball.SIZE)) {
      ball.vx = Math.abs(ball.vx) + 1;
      ball.vy = (long) Math.ceil(ball.vy*rand.nextDouble(-2, 2) + 0.5);
    }

    if (p2.collides(ball.x, ball.y, ball.SIZE)) {
      ball.vx = -(Math.abs(ball.vx) + 1);
      ball.vy = (long) Math.ceil(ball.vy*rand.nextDouble(-2, 2) + 0.5);
    }

    if (ball.x < 10) {
      score2++;
      ball.reset();
    }

    if (ball.x > w - 10) {
      score1++;
      ball.reset();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      System.exit(0);
    }

    if (e.getKeyCode() == KeyEvent.VK_W) {
      p1.v = -10;
    } else if (e.getKeyCode() == KeyEvent.VK_S) {
      p1.v = 10;
    }

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      p2.v = -10;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      p2.v = 10;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    update();
    repaint();
  }  

  @Override
  public void keyTyped(KeyEvent e) {
    // no need
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W) {
      p1.v = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_S) {
      p1.v = 0;
    }

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      p2.v = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      p2.v = 0;
    }
  } 
}
