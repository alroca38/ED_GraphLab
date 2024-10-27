package core.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class CampoPanel extends JPanel {

    private int[][] adjMatrix;
    private List<Integer> targetX;  // Lista de puntos finales en X para cada vértice
    private Timer timer;
    private List<Point> points;
    Image image;

    public CampoPanel(int[][] adjMatrix, String path) {
        this.adjMatrix = adjMatrix;
        this.points = new ArrayList<>();
        targetX = new ArrayList<>();
        image = new ImageIcon(getClass().getResource(path)).getImage();

        // Definición de las posiciones para una formación 4-3-3
        points.add(new Point(0, 220)); // Portero
        points.add(new Point(50, 295)); // Defensa 4
        points.add(new Point(50, 245)); // Defensa 3
        points.add(new Point(50, 195)); // Defensa 2
        points.add(new Point(50, 145)); // Defensa 1
        points.add(new Point(100, 270)); // Medio 3
        points.add(new Point(100, 220)); // Medio 2
        points.add(new Point(100, 170)); // Medio 1
        points.add(new Point(150, 320)); // Delantero 3
        points.add(new Point(150, 120)); // Delantero 1
        points.add(new Point(150, 220)); // Delantero 2

        for (Point point : points) {
            targetX.add(point.x + 700);  // Ejemplo: moverse 100 unidades hacia la derecha
        }

        //Configurar el Timer para actualizar la posición
        timer = new Timer(20, e -> movePoints());

        timer.start();
    }

    private void movePoints() {
        boolean allReachedTarget = true;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            Point a = points.get(0);
            Point d1 = points.get(1);
            Point d2 = points.get(2);
            Point d3 = points.get(3);
            Point d4 = points.get(4);
            Point m1 = points.get(5);
            Point m2 = points.get(6);
            Point m3 = points.get(7);
            Point de1 = points.get(8);
            Point de2 = points.get(9);
            Point de3 = points.get(10);
            int tx = targetX.get(i);

            // Mover el punto hacia el objetivo en el eje X
            if (p.x < tx) {
                p.x += 2;  // Ajusta la velocidad aumentando el incremento
                allReachedTarget = false;
            } else if (p.x > tx) {
                p.x -= 2;
                allReachedTarget = false;
            }
            if (a.x > 100) {
                a.x = 100;
            }
            if (d1.x > 250) {
                d1.x = 250;
            }
            if (d2.x > 200) {
                d2.x = 200;
            }
            if (d3.x > 200) {
                d3.x = 200;
            }
            if (d4.x > 250) {
                d4.x = 250;
            }
            if (m1.x > 400) {
                m1.x = 400;
            }
            if (m2.x > 500) {
                m2.x = 500;
            }
            if (m3.x > 400) {
                m3.x = 400;
            }
            if (de1.x > 700) {
                de1.x = 700;
            }
            if (de2.x > 700) {
                de2.x = 700;
            }
            if (de3.x > 600) {
                de3.x = 600;
            }
        }

        repaint();

        // Detener el timer si todos los puntos alcanzaron sus objetivos
        if (allReachedTarget) {
            timer.stop();
        }
    }

    // Método para obtener los puntos según la formación seleccionada
    @Override
    public void paintComponent(Graphics g) {
        // Dibuja los nodos de los jugadores
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        for (int i = 0; i < points.size(); i++) {
            if (i == 0) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            Point p = points.get(i);
            g.fillOval(p.x - 7, p.y - 7, 15, 15);

            if (SoccerField.getInstance().getSites().get(i) instanceof Player player) {
                g.setColor(Color.WHITE);
                g.drawString(player.getName(), p.x - 15, p.y - 15);
            }
        }

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] > 0) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(j);
                    g.setColor(Color.BLACK);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}
