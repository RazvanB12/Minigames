import javax.swing.*;

public class Main {
    public static void main (String[] args){
        JFrame window = new JFrame("Slide Puzzle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new SlidePuzzle());
        window.setBounds(800,800,800,800);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}
