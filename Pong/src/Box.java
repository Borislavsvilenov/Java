import java.awt.*;

public class Box {
    int x;
    int y;
    int w;
    int h;

    Box(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean collides(int x, int y, int r) {
        float closestX = Math.max(this.x, Math.min(x, this.x + this.w));
        float closestY = Math.max(this.y, Math.min(y, this.y + this.h));

        float dx = x - closestX;
        float dy = y - closestY;

        return (dx * dx + dy * dy) < (r * r);
    }

    public void outLine (Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);
    }

    public void fill (Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
    }
}
