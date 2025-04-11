import javax.swing.*;
import java.awt.*;

public class Player {
  int x;
  int y;
  int v = 0;
  int w = 20;
  int h = 150;

  Player (int x, int y) {
    this.x = x;
    this.y = y;

  }

  public void draw (Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, w, h);
  }

  public void update() {
    y += v;
  }
}
