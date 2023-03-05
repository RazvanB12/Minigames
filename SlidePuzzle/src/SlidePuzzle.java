import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlidePuzzle extends JPanel {
    private JButton[] buttons = new JButton[12];
    private int[][] comb = {{1,2,5,6,3,4,7,8}, {1,2,5,7,3,4,8,6}, {8,7,3,6,4,2,1,5}};
    private int curent;
    private int Level=3;
    private int moves;
    private int tries;

    public SlidePuzzle(){
        setLayout(new GridLayout(4,3));
        table();
    }

    public void table(){
        buttons[0] = new JButton();
        buttons[0].setFont(new Font ("TimesRoman", Font.BOLD, 30));
        buttons[0].setForeground(Color.GRAY);
        buttons[0].setText("Level "+String.valueOf(curent+1));
        add(buttons[0]);

        buttons[1] = new JButton();
        buttons[1].setFont(new Font ("TimesRoman", Font.BOLD, 30));
        buttons[1].setForeground(Color.GRAY);
        buttons[1].setText("Moves "+String.valueOf(moves));
        add(buttons[1]);

        buttons[2] = new JButton();
        buttons[2].setFont(new Font ("TimesRoman", Font.BOLD, 30));
        buttons[2].setForeground(Color.GRAY);
        buttons[2].setText("Wrong clicks "+String.valueOf(tries-moves));
        add(buttons[2]);

        buttons[3] = new JButton();
        buttons[3].setFont(new Font ("TimesRoman", Font.BOLD, 60));
        buttons[3].setForeground(Color.GRAY);
        buttons[3].setText("");
        buttons[3].setBackground(Color.GRAY);
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked = (JButton) e.getSource();
                boolean found = false;
                for (int j=3; j<=11 && found == false; j++){
                    if (buttons[j].getText().equals("") &&
                            ((j-1>=3 && buttons[j-1].getText().equals(buttonClicked.getText())) || (j+1<=11 && buttons[j+1].getText().equals(buttonClicked.getText())) ||
                                    (j-3>=3 && buttons[j-3].getText().equals(buttonClicked.getText())) || (j+3<=11 && buttons[j+3].getText().equals(buttonClicked.getText())))){
                        buttons[j].setText(buttonClicked.getText());
                        buttons[j].setBackground(Color.BLUE);
                        buttonClicked.setText("");
                        buttonClicked.setBackground(Color.GRAY);
                        moves++;
                        found = true;
                    }
                }
                tries++;
                buttons[0].setText("Level " + String.valueOf(curent+1));
                buttons[1].setText("Moves " + String.valueOf(moves));
                buttons[2].setText("Wrong clicks " + String.valueOf(tries-moves));
                win();
            }
        });
        add(buttons[3]);

        for (int i=4; i<=11; i++){
            buttons[i] = new JButton();
            buttons[i].setFont(new Font ("TimesRoman", Font.BOLD, 60));
            buttons[i].setForeground(Color.GRAY);
            buttons[i].setText(String.valueOf(comb[0][i-4]));
            buttons[i].setBackground(Color.BLUE);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    boolean found = false;
                    for (int j=3; j<=11 && found == false; j++){
                        if (buttons[j].getText().equals("") &&
                                ((j-1>=3 && buttons[j-1].getText().equals(buttonClicked.getText())) || (j+1<=11 && buttons[j+1].getText().equals(buttonClicked.getText())) ||
                                        (j-3>=3 && buttons[j-3].getText().equals(buttonClicked.getText())) || (j+3<=11 && buttons[j+3].getText().equals(buttonClicked.getText())))){
                            buttons[j].setText(buttonClicked.getText());
                            buttons[j].setBackground(Color.BLUE);
                            buttonClicked.setText("");
                            buttonClicked.setBackground(Color.GRAY);
                            moves++;
                            found = true;
                        }
                    }
                    tries++;
                    buttons[0].setText("Level " + String.valueOf(curent+1));
                    buttons[1].setText("Moves " + String.valueOf(moves));
                    buttons[2].setText("Wrong clicks " + String.valueOf(tries-moves));
                    win();
                }
            });
            add(buttons[i]);
        }
    }

    public void win (){
        boolean won = true;
        for (int i=3; i<=10; i++){
            if (!buttons[i].getText().equals(String.valueOf(i-2))) won = false;
        }

        if (won == true){
            JOptionPane pane = new JOptionPane();
            curent++;
            if (curent<Level){
                int dialogResult = JOptionPane.showConfirmDialog(pane, "CONGRATULATIONS! Next level?");
                if (dialogResult == JOptionPane.YES_OPTION) {
                    reset();
                }
                else System.exit(0);
            }
            else{
                int dialogResult = JOptionPane.showConfirmDialog(pane, "CONGRATULATIONS! New game?");
                if (dialogResult == JOptionPane.YES_OPTION) {
                    curent=0;
                    reset();
                }
                else System.exit(0);
            }
        }
    }

    public void reset(){
        moves=0;
        tries=0;
        buttons[0].setText("Level " + String.valueOf(curent+1));
        buttons[1].setText("Moves " + String.valueOf(moves));
        buttons[2].setText("Wrong clicks " + String.valueOf(tries-moves));

        buttons[3].setText("");
        buttons[3].setBackground(Color.GRAY);
        for (int i=4; i<=11; i++){
            buttons[i].setText(String.valueOf(comb[curent][i-4]));
            buttons[i].setBackground(Color.BLUE);
        }
    }
}
