import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    int Width = 900;
    int Height = 600;

    JFrame frame = new JFrame("Pong");
    frame.setSize(Width, Height);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    Game game = new Game(Width, Height);
    frame.add(game);
    frame.pack();

    frame.setVisible(true);

    game.requestFocus();
  }
}
