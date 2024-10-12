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
        names.add("Rodriguezzzz");

        paces.add(10);
        paces.add(30);
        paces.add(40);
        paces.add(67);
        paces.add(62);

        posessions.add(35);
        posessions.add(53);
        posessions.add(56);
        posessions.add(23);
        posessions.add(25);

        shootings.add(34);
        shootings.add(45);
        shootings.add(37);
        shootings.add(76);
        shootings.add(769);
        
        int [][] matriz1=new int[5][6];

        JFrame frame = new JFrame("Grafo Dibujado");
        File matriz = new File("C:\\Users\\DELL\\Documents\\GitHub\\ED_GraphLab\\FutLab\\src\\main\\players.csv");
        campo.createPlayers(names, paces, posessions, shootings);
        campo.createMatrix(matriz, matriz1);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.println(campo.getAdjacency()[i][j]);
            }
        }
    }
}
