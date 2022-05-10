package Game;

//import com.company.Window;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameOver extends JFrame {

    public GameOver(int point) {

                // This creates a new window
                this.setSize(500, 500);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setLayout(null);


                //It adds an image to the window
                ImageIcon image = new ImageIcon("gameOver.jpg");
                JLabel label = new JLabel();
                label.setBounds(75, 25, 350, 273);
                label.setIcon(image);
                this.add(label);

                // to add points and text of "play again"
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();


                label1.setText("Your total points = " + point);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Toma", Font.PLAIN, 20));
                label1.setBounds(140, 285, 300, 100);
                this.add(label1);

                // It adds a button to the window
                JButton button = new JButton();
                label2.setText("Play Again");
                label2.setFont(new Font("Arial", Font.PLAIN, 35));
                button.add(label2);
                button.setBounds(145, 360, 200, 70);
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