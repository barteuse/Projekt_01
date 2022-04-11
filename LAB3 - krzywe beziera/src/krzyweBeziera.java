import java.awt.*;
import java.util.*;

public class krzyweBeziera extends Frame {

    Point[] points = new Point[4];;
    ArrayList<Point[]> krzywe = new ArrayList<Point[]>();
    int numPoints = 0;
    int mf = 5;

    public krzyweBeziera() {
        setTitle("Krzywe Beziera");
        init();
    }

    public void init() {
        setSize(800,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g) {
        setBackground(Color.black);

        for(int k=0;k<krzywe.size();k++) {
            Point[] points = krzywe.get(k);
            for(int i=0;i<4;i++)
            {
                g.setColor(Color.blue);
                g.fillOval(points[i].x-2, points[i].y-2,8,8);
            }
            counting(g,points);
        }
        for(int i=0;i<numPoints;i++) {
            g.setColor(Color.blue);
            g.fillOval(points[i].x-2, points[i].y-2,8,8);
        }
    }

    public void counting(Graphics g,Point[] punkty) {
        double x1,x2,y1,y2;
        double t;
        double k = 0.025;

        x1 = punkty[0].x;
        y1 = punkty[0].y;

        for(t=k;t<=1+k;t+=k) {
            x2 = (punkty[0].x + t * (-punkty[0].x * 3 + t * (3 * punkty[0].x - punkty[0].x * t))) + t * (3 * punkty[1].x +
                    t * (-6 * punkty[1].x + punkty[1].x*3*t))+t*t*(punkty[2].x*3-punkty[2].x*3*t)+punkty[3].x*t*t*t;
            y2 = (punkty[0].y + t * (-punkty[0].y * 3 + t * (3 * punkty[0].y - punkty[0].y * t))) + t * (3 * punkty[1].y +
                    t * (-6 * punkty[1].y + punkty[1].y * 3 * t)) + t * t * (punkty[2].y * 3-punkty[2].y * 3 * t) + punkty[3].y * t * t * t;
            g.setColor(Color.white);
            g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
            x1 = x2;
            y1 = y2;
        }
    }

    public boolean mouseDown(Event evt, int x, int y) {
        Point point = new Point(x,y);

        if(numPoints < 4) {
            points[numPoints] = point;
            numPoints++;
            repaint();
        }
        if(numPoints == 4) {
            krzywe.add(points);
            for(int i=0;i<numPoints;i++)
                for(int j=-2;j<3;j++)
                    for(int l=-2;l<3;l++)
                        if(point.equals(new Point(points[i].x+j,points[i].y+l)))
                            mf = i;
            points = new Point[4];
            numPoints = 0;
            repaint();
        }
        return true;
    }

    public boolean mouseDrag(Event evt, int x, int y) {
        if(mf < numPoints) {
            points[mf].move(x,y);
            repaint();
        }
        return true;
    }

    public boolean mouseUp(Event evt, int x, int y) {
        mf = 5;
        return true;
    }

}