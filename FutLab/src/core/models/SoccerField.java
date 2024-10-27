package core.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

public class SoccerField {

    private static SoccerField instance;
    private ArrayList<Site> sites;
    private int[][] adjacencyMatrix;
    Map<Player, List<Player>> grafo = new HashMap<>();

    private SoccerField() {
        this.sites = new ArrayList<>();
    }

    public Map<Player, List<Player>> getGrafo() {
        return grafo;
    }

    public void addSites(Player jugador) {
        sites.add(jugador);
    }

    public void sitesConnection(Site espacio1, Site espacio2) {

    }

    public static SoccerField getInstance() {
        if (instance == null) {
            instance = new SoccerField();
        }
        return instance;
    }

    public void resetSoccerField() {
        instance = new SoccerField();
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public int[][] getAdjacency() {
        return adjacencyMatrix;
    }

    public void setAdjacency(int[][] adjacency) {
        this.adjacencyMatrix = adjacency;
    }

    public void createMatrix(File matriz, int[][] mat) throws FileNotFoundException, IOException {
        JFrame frame = new JFrame();
        BufferedReader br = new BufferedReader(new FileReader(matriz));
        String line = null;
        int i = 0; // índice de fila
        while ((line = br.readLine()) != null) {
            String[] player = line.split(";");
            for (int j = 0; j < player.length; j++) { // recorre cada elemento en la fila
                int index = Integer.parseInt(player[j]);
                mat[i][j] = index; // asigna a la matriz en la posición [i][j]
            }
            i++; // pasa a la siguiente fila
        }
        br.close();
        setAdjacency(mat);
        CampoPanel cam = new CampoPanel(adjacencyMatrix);
        frame.add(cam);
        frame.setSize(800, 490);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void Adjacents() {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    sites.get(i).addAdjacent(sites.get(j));
                    sites.get(j).addAdjacent(sites.get(i));
                }
            }
        }
    }

    public void createPlayers(ArrayList<String> names, ArrayList<Integer> paces, ArrayList<Integer> posessions, ArrayList<Integer> shootings) {
        int i = 0;
        sites = new ArrayList<>();
        for (String n : names) {
            Player player = new Player(n, paces.get(i), posessions.get(i), shootings.get(i));
            sites.add(player);
            grafo.put(player, new ArrayList<>());

            if (sites.get(i) instanceof Player p) {

                System.out.println(p.getName() + ", " + p.getPace() + ", " + p.getPosession() + ", " + p.getShooting());
            }
            i++;
        }

    }

    public void expandGraph() {
        // Ejemplo de expansión del grafo: Añadir posiciones intermedias
        List<Player> interNodes = new ArrayList<>();

        // Crear nodos intermedios
        for (int i = 1; i <= 22; i++) {
            Player position = new Player("Posicion" + i, 0, 0, 0);  // Nodos de posición sin atributos
            interNodes.add(position);
            grafo.put(position, new ArrayList<>());
        }

        // Conectar los nodos de jugadores con posiciones intermedias
        for (Player player : grafo.keySet()) {
            for (Player position : interNodes) {
                if (!grafo.get(player).contains(position)) {
                    grafo.get(player).add(position);
                    grafo.get(position).add(player);
                }
            }
        }

        // Conectar posiciones intermedias solo con sus vecinos inmediatos
        for (int i = 0; i < interNodes.size() - 1; i = i + 2) {
            Player current = interNodes.get(i);
            Player next = interNodes.get(i + 1);

            grafo.get(current).add(next);
            grafo.get(next).add(current);
        }
    }

}
