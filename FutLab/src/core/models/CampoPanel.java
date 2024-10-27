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
    private Timer timer1;
    private List<Point> points;
    Image image;
    private Player pl;

    public CampoPanel(int[][] adjMatrix, String path) {
        this.adjMatrix = adjMatrix;
        this.points = new ArrayList<>();
        targetX = new ArrayList<>();
        image = new ImageIcon(getClass().getResource(path)).getImage();
        this.pl = (Player) SoccerField.getInstance().getSites().getFirst();
        SoccerField.getInstance().setTactic(new PosessionTactic());

        // Definición de las posiciones para una formación 4-3-3
        points.add(new Point(0, 250)); // Portero
        points.add(new Point(50, 100)); // Defensa 4
        points.add(new Point(50, 200)); // Defensa 3
        points.add(new Point(50, 300)); // Defensa 2
        points.add(new Point(50, 400)); // Defensa 1
        points.add(new Point(100, 350)); // Medio 3
        points.add(new Point(100, 250)); // Medio 2
        points.add(new Point(100, 150)); // Medio 1
        points.add(new Point(150, 400)); // Delantero 3
        points.add(new Point(150, 100)); // Delantero 1
        points.add(new Point(150, 250)); // Delantero 2
        points.add(new Point(0, 250)); // Balon

        for (Point point : points) {
            targetX.add(point.x + 700);  // Ejemplo: moverse 100 unidades hacia la derecha
        }

        //Configurar el Timer para actualizar la posición
        timer = new Timer(20, e -> movePoints());

        timer.start();
    }
    int c = 0;

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
            int currentPlayerIndex = pl.getId();
            Point currentBallPosition = points.get(11);
            int tx = targetX.get(i);
            Point b;
            Point targetPosition;
            if (c > 100) {
                int nextPlayerIndex;
                if (pl != SoccerField.getInstance().getTactic().getInterestingPlayer()) {
                    nextPlayerIndex = nextPlayer(pl); // Actualiza el siguiente jugador con `nextPlayer`
                }else{
                    nextPlayerIndex = pl.getId(); // Actualiza el siguiente jugador con `nextPlayer
                }
                targetPosition = points.get(nextPlayerIndex); // Posición del siguiente jugador
                c = 0;
            } else {
                targetPosition = points.get(currentPlayerIndex);

            }

            if (currentBallPosition.x < targetPosition.x) {
                currentBallPosition.x += 1; // Ajusta la velocidad aumentando el incremento
                allReachedTarget = false;
            } else if (currentBallPosition.x > targetPosition.x) {
                currentBallPosition.x -= 1;
                allReachedTarget = false;
            }

            if (currentBallPosition.y < targetPosition.y) {
                currentBallPosition.y += 1;
                allReachedTarget = false;
            } else if (currentBallPosition.y > targetPosition.y) {
                currentBallPosition.y -= 1;
                allReachedTarget = false;
            }

            if (currentBallPosition.equals(targetPosition)) {
                this.pl = (Player) SoccerField.getInstance().getSites().get(currentPlayerIndex); // Actualiza el jugador actual con el balón
            }

            // Mover el punto hacia el objetivo en el eje X
            if (p.x < tx) {
                p.x += 1;  // Ajusta la velocidad aumentando el incremento
                allReachedTarget = false;
            } else if (p.x > tx) {
                p.x -= 1;
                allReachedTarget = false;
            }

            if (a.x > 100) {
                a.x = 100;
            }
            if (d1.x > 300) {
                d1.x = 300;
            }
            if (d2.x > 250) {
                d2.x = 250;
            }
            if (d3.x > 250) {
                d3.x = 250;
            }
            if (d4.x > 300) {
                d4.x = 300;
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
        c++;
    }

    public int nextPlayer(Player currentPlayer) {
        Player nextPlayer = null;
        int currentDistance = FloydWarshall.relativeInfinity();
        ArrayList<Site> adjacents = currentPlayer.getAdjacent();
        for (Site adjacent : adjacents) {
            if (FloydWarshall.floydWarshall(SoccerField.getInstance().getAdjacency(), adjacent, SoccerField.getInstance().getTactic().getInterestingPlayer()) <= currentDistance) {
                nextPlayer = (Player) adjacent;
                currentDistance = FloydWarshall.floydWarshall(SoccerField.getInstance().getAdjacency(), nextPlayer, SoccerField.getInstance().getTactic().getInterestingPlayer());
            }
        }
        this.pl = nextPlayer;
        return nextPlayer.getId();
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

            } else if (i == 11) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.RED);
            }
            Point p = points.get(i);
            g.fillOval(p.x - 7, p.y - 7, 15, 15);

            if (i < 11) {
                if (SoccerField.getInstance().getSites().get(i) instanceof Player player) {
                    g.setColor(Color.WHITE);
                    g.drawString(player.getName(), p.x - 15, p.y - 15);
                }
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
