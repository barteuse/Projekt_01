import javax.swing.*;
import java.awt.*;

class Surfaces extends JPanel{
    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(180, 245, 255));
        g2d.fillRect(0, 0, 800, 550);

        g2d.setColor(new Color(80, 210, 95));
        g2d.fillRect(0, 550, 800, 800);

        g2d.setColor(new Color(45, 15, 15));
        g2d.fillRect(200, 400, 350, 250);

        int[] xPoints_dach = {200,375,550};
        int[] yPoints_dach = {400,200,400};
        g2d.setColor(new Color(255, 40, 40));
        g2d.fillPolygon(xPoints_dach,yPoints_dach,3);

        g2d.setColor(new Color(70,140,255));
        g2d.fillRect(251,476,74,74);

        g2d.setColor(new Color(0,0,0));
        g2d.drawLine(250,475,325,475);
        g2d.drawLine(250,475,250,550);
        g2d.drawLine(250,550,325,550);
        g2d.drawLine(325,475,325,550);
        g2d.drawLine(286,475,286,550);
        g2d.drawLine(250,511,325,511);

        g2d.setColor(new Color(0, 0, 0));
        g2d.drawRect(400, 523, 81, 126);
        g2d.setColor(new Color(110, 38, 14));
        g2d.fillRect(401, 524, 80, 125);

        g2d.setColor(new Color(245,255,50));
        g2d.fillOval(500,100,125,125);
    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class rysunek extends JFrame {
    public rysunek(){
        initUI();
    }
    private void initUI(){
        add(new Surfaces());
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                rysunek rys = new rysunek();
                rys.setVisible(true);
            }
        });
    }
}