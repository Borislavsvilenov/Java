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
    if (y <= 10 && v < 0) {
      return;
    } else if (y >= h - 10 - this.h && v > 0) {
      return;
    } else {
      y += v;
    }
  }
}
