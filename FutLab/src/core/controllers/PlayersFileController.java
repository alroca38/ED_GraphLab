package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class PlayersFileController {

    public static Response readPlayersFile(File playersFile, JList playersJList) {
        if (!playersFile.getAbsolutePath().endsWith(".csv")) {
            return new Response("El archivo ingresado debe ser un archivo en formato CSV", Status.BAD_REQUEST);
        }
        try {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(playersFile));
                String line = null;
                int lineCount = 0;
                ArrayList<String> playersNames = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    String[] playerStats = line.split(",");
                    if (playerStats.length != 4) {
                        return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                    }
                    char[] nameChar = playerStats[0].toCharArray();
                    if (playerStats[0].isEmpty() || nameChar[0] == ' ' || nameChar[nameChar.length - 1] == ' ') {
                        return new Response("El nombre del jugador debe tener al menos 1 caracter. Además, no puede inciar o terminar con un espacio", Status.BAD_REQUEST);
                    }
                    for (int i = 1; i < playerStats.length; i++) {
                        if (playerStats[i].isEmpty()) {
                            return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                        }
                        if ("0".equals(playerStats[i])) {
                            return new Response("Ningún jugador puede tener 0 puntos en alguno de sus atributos", Status.BAD_REQUEST);
                        }
                        char[] stats = playerStats[i].toCharArray();
                        for (char cht : stats) {
                            if (cht == ' ') {
                                return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                            }
                        }
                        try {
                            Integer.valueOf(playerStats[i]);
                        } catch (NumberFormatException numericException) {
                            return new Response("Los atributos de los jugadores deben ser numéricos", Status.BAD_REQUEST);
                        }
                    }
                    playersNames.add(playerStats[0]);
                }
                if (lineCount > 11) {
                    return new Response("Solo pueden ser ingresados 11 jugadores", Status.BAD_REQUEST);
                }
                if (lineCount < 11) {
                    return new Response("Deben ser ingresados al menos 11 jugadores", Status.BAD_REQUEST);
                }
                playersJList.removeAll();
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (int i = 0; i < playersNames.size(); i++) {
                    listModel.addElement(playersNames.get(i));
                }
                playersJList.setModel(listModel);
            } catch (IOException fileException) {
                return new Response("Este archivo no puede ser procesado por el programa", Status.UNPROCESSABLE_CONTENT);
            }
            return new Response("Se ha leído el archivo correctamente", Status.OK);
        } catch (Exception unexpectedException) {
            return new Response("Ocurrió un error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
