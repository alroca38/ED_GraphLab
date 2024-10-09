package main;

import core.models.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            SoccerField campo = SoccerField.getInstance();
            try {
                File players = new File("players.csv");
                campo.createPlayers(players);
                campo.expandGraph();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Campo de Fútbol");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CampoPanel(campo));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
