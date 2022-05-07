 package Game;

import javax.swing.*;
import java.awt.*;

// this is a class that builds a car, it takes a picture, x, y, and frame.
public class My_Image extends Component {

    private ImageIcon imageCar;
    private int x;
    private int y;
    public Frame frame;

    public My_Image(ImageIcon imageCar, int x, int y, Frame frame){
        this.imageCar = imageCar;
        this.x = x;
        this.y = y;
        this.frame = frame;
     }

    public void paint(Graphics g){
        this.frame.paint(g);
        this.imageCar.paintIcon(this, g, this.x, this.y);
    }

    public void moveRight(){
        this.x += 6;
    }
    public void moveLeft(){
        this.x -= 6;
    }

//    public void moveUp(){
//        this.y -= 3;
//    }
//    public void moveDown(){
//        this.y += 3;
//    }

    public void moveCarsDown(){
        this.y += 1;
    }

    public int getX() {
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
