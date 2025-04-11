import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener, KeyListener{
  int w;
  int h;

  Player p1;
  Player p2;

  Timer Loop;

  Game(int w, int h) {
    this.w = w;
    this.h = h;

    p1 = new Player(20, 225);
    p2 = new Player(this.w - 40, 225);
    
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
    p1.draw(g);
    p2.draw(g);
    
    // draw field
    g.drawLine(w/2, 10, w/2, h - 10);
    g.drawLine(10, 10, w - 10, 10);
    g.drawLine(10, h - 10, w - 10, h - 10);
    g.drawLine(10, 10, 10, h - 10);
    g.drawLine(w - 10, 10, w - 10, h - 10);

    g.drawOval(w/2 -100, h/2 -100, 200, 200);
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
    p1.update();
    p2.update();
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
