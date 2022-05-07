package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

// this is the main class, where the road and the cars builds.
public class GameScene extends JPanel {
    private  int points;
    private int pixel;
    // here I   declare on the road.
    private Rectangles BOARD;
    private Rectangles ROAD_1;
    private Rectangles ROAD_2;
    private Rectangles ROAD_3;
    private Rectangles FRAME_RIGHT;
    private Rectangles FRAME_LEFT;

    //here I declare on the obstacles cars.
    private Frame[] frames;
    private Rectangles[] rectangles;
    private My_Image[] obstacles;
    private ImageIcon cars;

    //here I declare on the user car.
    private Frame frame;
    private Rectangles rectangle;
    private My_Image imageCar;
    private ImageIcon image;

    public GameScene(int x, int y, int width, int height) {

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        // It builds the road of the game
        this.BOARD = new Rectangles(0, -130000, width, 130000+height, Color.darkGray);
        ROAD_1 = new Rectangles((width / 4), 0, 6, height, Color.white);
        ROAD_2 = new Rectangles((width / 2), 0, 6, height, Color.white);
        ROAD_3 = new Rectangles((width/4)*3, 0, 6, height, Color.white);
        FRAME_RIGHT = new Rectangles(width-19, 0, 5, height, Color.green);
        FRAME_LEFT = new Rectangles(0, 0, 5, height, Color.green);

        // It builds the user car.
        this.rectangle = new Rectangles(230, height-200, 80, 160, Color.darkGray);
        this.frame = new Frame(this.rectangle);
        this.image = new ImageIcon("MyCar.png");
        this.imageCar = new My_Image(image, 220, height-200, frame);

        // It builds the obstacles cars.
        this.obstacles = new My_Image[300];
        this.frames = new Frame[300];
        this.rectangles = new Rectangles[300];
        Random random = new Random();
        // here is where th first car will appear, on -250.
        int min = -250;
        int max = min + 80;
        for(int i = 0; i < this.obstacles.length; i++){
            int x1 = random.nextInt(4);
            int X;
            int Y = random.nextInt( max - min) + min;
//random.nextInt(max - min) + min
// max = 30; min = -10;
//Will yield a random int between -10 and 30 (exclusive).
            switch (x1){
                case 0:
                    X = 45;
                    break;
                case 1:
                    X = 180+45;
                    break;
                case 2:
                    X = 180*2+45;
                    break;
                case 3:
                    X = 180*3+45;
                    break;
                default:
                    X = 0;
            }
            // in each new car that appears, its 'y' starts 250 pixels before the car in front of it,
            // and it chooses randomly between minus 250 to plus 80 where to start.
            min -= 250;
            max = min + 80;
            // here it chooses randomly, what photo will appear.
            ImageIcon randomCars;
            int x2 = random.nextInt(3);
            switch (x2){
                case 0:
                    randomCars = new ImageIcon("cars.png");
                    break;
                case 1:
                    randomCars = new ImageIcon("cars2.png");
                    break;
                default:
                    randomCars = new ImageIcon("car3.png");
                    break;
            }
            rectangles[i] = new Rectangles(X-3, Y, 90, 160, Color.darkGray);
            obstacles[i] = new My_Image(
                    this.cars = randomCars,
                    X-10,
                    Y,
                    this.frames[i] = new Frame(this.rectangles[i])
            );
        }
         this.mainGameLoop();
         this.countPoints();
       }

    // this is a pint function.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.BOARD.paint(g);
        this.ROAD_1.paint(g);
        this.ROAD_2.paint(g);
        this.ROAD_3.paint(g);
        this.FRAME_LEFT.paint(g);
        this.FRAME_RIGHT.paint(g);

        this.imageCar.paint(g);
        this.frame.paint(g);

        for(int i = 0; i < this.obstacles.length; i++){
            this.obstacles[i].paint(g);
            this.frames[i].paint(g);
        }
        g.setFont(new Font("Thoma", Font.PLAIN, 40));
        g.setColor(Color.RED);
        g.drawString(String.valueOf(points), 20, 50);
    }

    // this is a thread that counts the points of the user.
    public void countPoints(){
       new Thread(()->{
          first:
          while (true) {
              try {
                  if(this.points >= 100000){
                      break first;
                  }
                  for(int i = 0; i < this.obstacles.length; i++){
                     if(this.frame.checkCollision(frames[i])){
                        break first;
                     }
                  }
                  Thread.sleep(50);
                  pixel ++;
                  while(pixel == 35){
                      points++;
                      pixel=0;
                  }

                  repaint();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
       }).start();
    }

    // this is a thread that responsible for the movements of the road.
    public void mainGameLoop(){
        new Thread(()->{
            PlayerMovement_IMAGE_CAR board = new PlayerMovement_IMAGE_CAR(this.BOARD, this.imageCar, this.frame, this.obstacles, this.frames);
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(board);
            first:
            while(true){
                try {
                    for(int i = 0; i < this.obstacles.length; i++){
                        if(this.frame.checkCollision(frames[i])){
                            this.rectangle =  new Rectangles(this.frame.getX(), this.frame.getY(), 160,80 , Color.gray);
                            this.frame = new Frame(this.rectangle);
                            this.image = new ImageIcon("MyCarBroked.png");
                            this.imageCar = new My_Image(image, this.frame.getX() , this.frame.getY(), frame);
                            repaint();

                            GameOver gameOver = new GameOver(points);
                            break first;
                        }
                        if(this.points >= 100000){
                            WinnerWindow window = new WinnerWindow();
                            break first;
                        }
                    }
                    this.BOARD.moveDown();
                    for(int i = 0; i < this.obstacles.length; i++){
                        this.obstacles[i].moveCarsDown();
                        this.frames[i].moveDown();
                    }
                    Thread.sleep(5);
                    repaint();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}