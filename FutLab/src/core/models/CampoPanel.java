package core.models;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class CampoPanel extends JPanel {

    private final SoccerField campo;

    public CampoPanel(SoccerField campo) {
        this.campo = campo;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Map<Player, List<Player>> grafo = campo.getGrafo();
        int i = 1;

        for (Map.Entry<Player, List<Player>> entry : grafo.entrySet()) {
            Player player = entry.getKey();
            int x = i * 100;
            int y = 100;
            g.setColor(Color.BLACK);
            g.fillOval(x, y, 20, 20);
            g.drawString(player.getName(), x, y + 30);

            List<Player> conexiones = entry.getValue();
            for (Player conexion : conexiones) {
                int j = new ArrayList<>(grafo.keySet()).indexOf(conexion) + 1;
                int x2 = j * 100;
                int y2 = 100;
                g.drawLine(x + 10, y + 10, x2 + 10, y2 + 10);
            }
            i++;
        }
    }
}
