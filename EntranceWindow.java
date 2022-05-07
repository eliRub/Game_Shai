package Game;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EntranceWindow extends JFrame {

    // Here we have a cool picture of the entrance to the game
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        EntranceWindow entranceWindow = new EntranceWindow();
    }

    public EntranceWindow() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        // This creates a new window
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);


        //It adds an image to the window
        ImageIcon image = new ImageIcon("EntrancePic.png");
        JLabel label = new JLabel();
        label.setBounds(50, 0, 400, 300);
        label.setIcon(image);
        this.add(label);
        this.setVisible(true);



        // It adds a button to the window
        JButton button = new JButton("New Game");
        button.setBounds(200, 300, 100, 100);
        // When you press the button
        // [a] The Entrance Window is closed [b] It's open a Window Game

        button.addActionListener((event) -> {
            this.dispose();
            MainWindow window = new MainWindow();
            window.run();

        });
        this.add(button);
    }

}
