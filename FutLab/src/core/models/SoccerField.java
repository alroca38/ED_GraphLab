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
        BufferedReader br = new BufferedReader(new FileReader(matriz));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] player = line.split(",");
            for (int i = 0; i < player.length; i++) {
                for (int j = 1; j < player.length; j++) {
                    int index = Integer.parseInt(player[j]);
                    mat[i][j] = index;
                }
            }
        }
    }

    public void createPlayers(ArrayList<String> names, ArrayList<Integer> paces, ArrayList<Integer> posessions, ArrayList<Integer> shootings) {
        int i = 0;
        ArrayList<Player> plyrs = new ArrayList<>();
        for (String n : names) {
            Player player = new Player(n, paces.get(i), posessions.get(i), shootings.get(i));
            plyrs.add(player);
            grafo.put(player, new ArrayList<>());
            System.out.println(plyrs.get(i).getName() + ", " + plyrs.get(i).getPace() + ", " + plyrs.get(i).getPosession() + ", " + plyrs.get(i).getShooting());
            i++;
        }
    }

    public void expandGraph() {
        // Ejemplo de expansi�n del grafo: A�adir posiciones intermedias
        List<Player> interNodes = new ArrayList<>();

        // Crear nodos intermedios
        for (int i = 1; i <= 22; i++) {
            Player position = new Player("Posicion" + i, 0, 0, 0);  // Nodos de posici�n sin atributos
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
