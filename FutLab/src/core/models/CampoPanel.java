package core.models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class CampoPanel extends JPanel {

    private int[][] adjMatrix;

    public CampoPanel(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int numNodes = adjMatrix.length;
        int radius = 200;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            double angle = 2 * Math.PI * i / numNodes;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));
            points.add(new Point(x, y));
            g.setColor(Color.BLACK);
            g.fillOval(x - 10, y - 10, 20, 20);
            g.drawString("Node " + i, x - 15, y - 15);
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjMatrix[i][j] > 0) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(j);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}
