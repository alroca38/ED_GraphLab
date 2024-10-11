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
        int size = grafo.size();
        int gridSize = (int) Math.ceil(Math.sqrt(size));
        int nodeSize = 20;
        int padding = 100;

        List<Player> nodes = new ArrayList<>(grafo.keySet());

        for (int index = 0; index < nodes.size(); index++) {
            Player player = nodes.get(index);
            int row = index / gridSize;
            int col = index % gridSize;
            int x = col * (nodeSize + padding) + padding;
            int y = row * (nodeSize + padding) + padding;

            g.setColor(Color.BLACK);
            g.fillOval(x, y, nodeSize, nodeSize);
            g.drawString(player.getName(), x, y + nodeSize + 10);

            List<Player> conexiones = grafo.get(player);
            for (Player conexion : conexiones) {
                int conexionIndex = nodes.indexOf(conexion);
                int row2 = conexionIndex / gridSize;
                int col2 = conexionIndex % gridSize;
                int x2 = col2 * (nodeSize + padding) + padding;
                int y2 = row2 * (nodeSize + padding) + padding;
                g.drawLine(x + nodeSize / 2, y + nodeSize / 2, x2 + nodeSize / 2, y2 + nodeSize / 2);
            }
        }
    }
}
