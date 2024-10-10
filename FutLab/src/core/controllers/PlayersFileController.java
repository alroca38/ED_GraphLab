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

    // M�todo para validar que la estructura del archivo sea correcta y procesable por el programa
    public static Response readPlayersFile(File playersFile, JList playersJList) {
        // Verificaci�n de la extensi�n del archivo
        if (!playersFile.getAbsolutePath().endsWith(".csv")) {
            return new Response("El archivo ingresado debe ser un archivo en formato CSV", Status.BAD_REQUEST);
        }
        // Manejo de excepciones
        try {
            // Valida que el archivo puede ser procesado por el programa
            try {
                BufferedReader reader = new BufferedReader(new FileReader(playersFile));
                String line = null;
                // Contador de las l�neas del archivo
                int lineCount = 0;
                // Lista para los nombres de los jugadores ingresados
                ArrayList<String> playersNames = new ArrayList<>();
                // Bucle que recorre cada una de las lineas del archivo
                while ((line = reader.readLine()) != null) {
                    // Se suma una l�nea al contador
                    lineCount++;
                    // Arreglo contenedor de cada uno de los campos de la l�nea actual
                    String[] playerStats = line.split(",");
                    // Validaci�n de la cantidad de campos (Deben ser 4 por l�nea)
                    if (playerStats.length != 4) {
                        return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                    }
                    // Arreglo contenedor de los caracteres que componen el nombre del jugador
                    char[] nameChar = playerStats[0].toCharArray();
                    // Validaci�n del nombre del jugador
                    if (playerStats[0].isEmpty() || nameChar[0] == ' ' || nameChar[nameChar.length - 1] == ' ') {
                        return new Response("El nombre del jugador debe tener al menos 1 caracter. Adem�s, no puede inciar o terminar con un espacio", Status.BAD_REQUEST);
                    }
                    // Recorrido de los campos num�ricos del jugador actual
                    for (int i = 1; i < playerStats.length; i++) {
                        // No puede haber campos vac�os
                        if (playerStats[i].isEmpty()) {
                            return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                        }
                        // No puede haber un atributo en 0
                        if ("0".equals(playerStats[i])) {
                            return new Response("Ning�n jugador puede tener 0 puntos en alguno de sus atributos", Status.BAD_REQUEST);
                        }
                        // No puede haber campos en blanco
                        char[] stats = playerStats[i].toCharArray();
                        for (char cht : stats) {
                            if (cht == ' ') {
                                return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                            }
                        }
                        // Los atributos del jugador deben ser num�ricos
                        try {
                            Integer.valueOf(playerStats[i]);
                        } catch (NumberFormatException numericException) {
                            return new Response("Los atributos de los jugadores deben ser num�ricos", Status.BAD_REQUEST);
                        }
                    }
                    // Se a�ade el jugador a la lista de nombres
                    playersNames.add(playerStats[0]);
                }
                // Si el archivo posee m�s o menos de 11 l�neas, se env�a un error
                if (lineCount > 11) {
                    return new Response("Solo pueden ser ingresados 11 jugadores", Status.BAD_REQUEST);
                }
                if (lineCount < 11) {
                    return new Response("Deben ser ingresados al menos 11 jugadores", Status.BAD_REQUEST);
                }
                //Creaci�n de una lista con los jugadores para imprimirlos en las vistas del programa
                playersJList.removeAll();
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (int i = 0; i < playersNames.size(); i++) {
                    listModel.addElement(playersNames.get(i));
                }
                playersJList.setModel(listModel);
            } catch (IOException fileException) {
                return new Response("Este archivo no puede ser procesado por el programa", Status.UNPROCESSABLE_CONTENT);
            }
            return new Response("Se ha le�do el archivo correctamente", Status.OK);
        } catch (Exception unexpectedException) {
            return new Response("Ocurri� un error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
