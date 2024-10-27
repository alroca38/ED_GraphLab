package core.models;

public abstract class Tactic {
    protected Player interestingPlayer;
    public Tactic(){
        interestingPlayer = null;
        lookForPlayer();
    }
    
    public Player getInterestingPlayer(){
        return this.interestingPlayer;
    }
    abstract void lookForPlayer();
}
