package Teste;

import core.models.CampoPanel;
import core.models.SoccerField;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test {

    public static void main(String[] args) throws IOException {
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

        JFrame frame = new JFrame("Grafo Dibujado");
        File matriz = new File("C:\\Users\\DELL\\Documents\\GitHub\\ED_GraphLab\\FutLab\\src\\main\\players.csv");
        int[][] mat=new int[5][6];
        campo.createMatrix(matriz, mat);
        CampoPanel panel = new CampoPanel(mat);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
