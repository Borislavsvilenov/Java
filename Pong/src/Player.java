import java.awt.*;

public class Player extends Box {
  int v = 0;

  Player (int x, int y, int w, int h) {
    super(x , y, w, h);
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public void update(int h) {
    if (y >= h - 10 && y <=10) {
      return;
    }
    y += v;
  }
}
