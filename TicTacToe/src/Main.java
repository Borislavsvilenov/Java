import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    int Width = 900;
    int Height = 900;

    JFrame frame = new JFrame("TicTacToe");
    frame.setSize(Width, Height);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    Game game = new Game(Width, Height, 1);
    frame.add(game);
    frame.pack();

    frame.setVisible(true);

    game.requestFocus();
  }
}
