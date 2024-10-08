package core.models;

import java.util.ArrayList;

public class SoccerField {

    private static SoccerField instance;
    private ArrayList<Site> sites;
    private int[][] adjacencyMatrix;

    private SoccerField() {
        this.sites = new ArrayList<>();
    }

    public void addSites(Player jugador) {
        sites.add(jugador);
    }

    public void sitesConnection(Site espacio1, Site espacio2) {
        
    }
    
    public static SoccerField getInstance(){
        if(instance == null){
            instance = new SoccerField();
        }
        return instance;
    }
    
    public void resetSoccerField(){
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

}
