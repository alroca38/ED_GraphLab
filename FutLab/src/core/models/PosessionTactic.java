package core.models;

import java.util.ArrayList;

public class PosessionTactic extends Tactic {

    public PosessionTactic() {
        super();
    }

    @Override
    public void lookForPlayer() {
        ArrayList<Site> players = SoccerField.getInstance().getSites();
        for (Site player : players) {
            Player currentPlayer = (Player) player;
            if (interestingPlayer == null) {
                interestingPlayer = (Player) player;
            } else if (currentPlayer.getPosession() > interestingPlayer.getPosession()) {
                interestingPlayer = currentPlayer;
            }
        }
    }
}
