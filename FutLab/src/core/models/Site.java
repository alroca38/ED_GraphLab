package core.models;

import java.util.ArrayList;

public class Site {

    private static int cv = 1;
    protected int id;
    protected ArrayList<Site> adjacent;

    public Site() {
        this.adjacent = new ArrayList();
        this.id = cv++;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Site> getAdjacent() {
        return adjacent;
    }

    public void addAdjacent(Site espacio) {
        adjacent.add(espacio);
    }

}
