package Teste;

import core.models.CampoPanel;
import core.models.SoccerField;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test {

    public static void main(String[] args) {
        SoccerField campo = SoccerField.getInstance();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> paces = new ArrayList<>();
        ArrayList<Integer> posessions = new ArrayList<>();
        ArrayList<Integer> shootings = new ArrayList<>();

        names.add("Martin");
        names.add("Alejandro");
        names.add("Torres");
        names.add("Rodriguez");

        paces.add(10);
        paces.add(30);
        paces.add(40);
        paces.add(67);

        posessions.add(35);
        posessions.add(53);
        posessions.add(56);
        posessions.add(23);

        shootings.add(34);
        shootings.add(45);
        shootings.add(37);
        shootings.add(76);

        SwingUtilities.invokeLater(() -> {
            campo.CreatePlayers(names, paces, posessions, shootings);
            campo.expandGraph();

            JFrame frame = new JFrame("Campo de Fútbol");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CampoPanel(campo));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
