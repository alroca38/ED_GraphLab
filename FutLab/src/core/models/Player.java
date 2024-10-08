package core.models;

public class Player extends Site{

    private int pace, posession, shooting;

    public Player(int pace, int posession, int shooting) {
        super();
        this.pace = pace;
        this.posession = posession;
        this.shooting = shooting;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getPosession() {
        return posession;
    }

    public void setPosession(int posession) {
        this.posession = posession;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }
    
}
