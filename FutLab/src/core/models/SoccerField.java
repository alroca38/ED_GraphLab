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

    public void CreatePlayers(ArrayList<String> names, ArrayList<Integer> paces, ArrayList<Integer> posessions, ArrayList<Integer> shootings) {
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

    /*public void createPlayers(File players) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(players));
        String line;
        List<Player> plyrs = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] playerStats = line.split(",");
            Player player = new Player(playerStats[0], Integer.parseInt(playerStats[1]), Integer.parseInt(playerStats[2]), Integer.parseInt(playerStats[3]));
            plyrs.add(player);
            grafo.put(player, new ArrayList<>());
        }
        br.close();
        br = new BufferedReader(new FileReader(players));

        int row = 0;
        while ((line = br.readLine()) != null) {
            String[] datas = line.split(",");
            for (int col = 4; col < datas.length; col++) {
                if (Integer.parseInt(datas[col]) == 1) {
                    grafo.get(plyrs.get(row)).add(plyrs.get(col - 4));
                }
            }
            row++;
        }
        br.close();
    }*/
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
