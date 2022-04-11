import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    static final int screenWidth = 750;
    static final int screenHeight = 750;
    static final int size = 25;
    static final int gameUnits = (screenWidth*screenHeight)/(size*size);
    static final int delay = 100;
    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if(running) {
            g.setColor(Color.green);
            g.fillOval(appleX, appleY, size, size);

            for(int i = 0; i< bodyParts;i++) {
                g.setColor(Color.red);
                g.fillRect(x[i], y[i], size, size);
            }
            g.setColor(Color.white);
            g.setFont( new Font("Arial",Font.BOLD, 32));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Wynik: "+applesEaten, (screenWidth - metrics.stringWidth("Wynik: "+applesEaten))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }

    }

    public void newApple(){
        appleX = random.nextInt((int)(screenWidth/size))*size;
        appleY = random.nextInt((int)(screenHeight/size))*size;
    }

    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - size;
                break;
            case 'D':
                y[0] = y[0] + size;
                break;
            case 'L':
                x[0] = x[0] - size;
                break;
            case 'R':
                x[0] = x[0] + size;
                break;
        }

    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions() {

        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                running = false;
            }
        }

        if(x[0] < 0) {
            running = false;
        }

        if(x[0] > screenWidth) {
            running = false;
        }

        if(y[0] < 0) {
            running = false;
        }

        if(y[0] > screenHeight) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {

        g.setColor(Color.white);
        g.setFont( new Font("Arial",Font.BOLD, 32));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Wynik: "+applesEaten, (screenWidth - metrics1.stringWidth("Wynik: "+applesEaten))/2, g.getFont().getSize());

        g.setColor(Color.white);
        g.setFont( new Font("Arial",Font.BOLD, 60));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over :(", (screenWidth - metrics2.stringWidth("Game Over :("))/2, screenHeight/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}