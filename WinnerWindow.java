package Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// this is a winner class, if the user get a 100,000 points he is win.
public class WinnerWindow extends JFrame {

        public WinnerWindow(){
            this.setSize(600, 600);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setLayout(null);

            JLabel label1 = new JLabel();
            JLabel label2 = new JLabel();

            label1.setText("You won!");
            label1.setForeground(Color.RED);
            label1.setFont(new Font("Toma", Font.PLAIN, 80));
            label1.setBounds(120, 200, 400, 100);
            this.add(label1);

            JLabel text = new JLabel("You got the most points: 100000!!");
            text.setBounds(60, 300, 500, 50);
            text.setFont(new Font("Arial", Font.PLAIN, 30));
            this.add(text);

            // It adds a button to the window
            JButton button = new JButton();
            label2.setText("Play Again");
            label2.setFont(new Font("Arial", Font.PLAIN, 35));
            button.add(label2);
            button.setBounds(180, 400, 200, 100);
            // When you press the button
            // [a] The Entrance Window is closed [b] It's open a Window Game
            button.addActionListener((event) -> {
                this.dispose();
                MainWindow.close();
                try {
                    EntranceWindow entranceWindow = new EntranceWindow();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            });
            this.add(button);

            this.setVisible(true);
        }

}