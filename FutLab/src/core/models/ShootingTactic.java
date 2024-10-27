package core.models;

import java.util.ArrayList;

public class ShootingTactic extends Tactic{

    public ShootingTactic() {
        super();
    }
    
    @Override
    public void lookForPlayer() {
        ArrayList<Site> players = SoccerField.getInstance().getSites();
        for (Site player : players) {
            Player currentPlayer = (Player) player;
            if (interestingPlayer == null) {
                interestingPlayer = (Player) player;
            } else if (currentPlayer.getShooting() > interestingPlayer.getShooting()) {
                interestingPlayer = currentPlayer;
            }
        }
    }
}
