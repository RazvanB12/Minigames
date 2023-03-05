import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Minesweeper extends JFrame{

    private JButton[] buttons = new JButton[25];
    private List<Integer> bombLocations = new ArrayList<>(3);
    private List<Integer> bombsNear = new ArrayList<>(25);

    public Minesweeper(){
        init();

        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        panel.setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 25; i++){
            buttons[i] = new JButton();
            buttons[i].setText(Integer.toString(i));
            buttons[i].setBackground(Color.white);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    int i = Arrays.asList(buttons).indexOf(button);

                    if (bombLocations.contains(i)){
                        button.setText("B");
                        button.setBackground(Color.red);

                        for (int j=0; j<25; j++){
                            buttons[j].setEnabled(false);
                        }
                    }
                    else {
                        button.setText(Integer.toString(bombsNear.get(i)));
                        if (bombsNear.get(i) > 0){
                            button.setBackground(Color.yellow);
                        }
                        else {
                            button.setBackground(Color.green);
                        }
                    }
                }
            });
            panel.add(buttons[i]);
        }

        this.add(panel);
        this.setVisible(true);
    }

    public void init(){
        for (int i=0; i<25; i++){
            bombsNear.add(0);
        }

        Random rand = new Random();
        while(bombLocations.size()<3){
            int random = rand.nextInt(25);

            if (!bombLocations.contains(random) && random > 0){
                bombLocations.add(random);

                if (random >= 6 && random % 5 > 0){
                    bombsNear.set(random-6, bombsNear.get(random-6) + 1);
                }

                if (random <= 18 && random % 5 < 4){
                    bombsNear.set(random+6, bombsNear.get(random+6) + 1);
                }

                if (random >= 5){
                    bombsNear.set(random-5, bombsNear.get(random-5) + 1);
                }

                if (random <= 19){
                    bombsNear.set(random+5, bombsNear.get(random+5) + 1);
                }

                if (random >= 5 && random % 5 < 4){
                    bombsNear.set(random-4, bombsNear.get(random-4) + 1);
                }

                if (random <= 19 && random % 5 > 0){
                    bombsNear.set(random+4, bombsNear.get(random+4) + 1);
                }

                if (random % 5 > 0){
                    bombsNear.set(random-1, bombsNear.get(random-1) + 1);
                }

                if (random % 5 < 4){
                    bombsNear.set(random+1, bombsNear.get(random+1) + 1);
                }

                System.out.println(random);
            }
        }

    }
}
