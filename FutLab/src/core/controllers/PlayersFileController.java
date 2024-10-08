package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlayersFileController {

    public static Response readPlayersFile(File playersFile) {
        try {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(playersFile));
                String line = null;
                int lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    String[] playerStats = line.split(",");
                    if (playerStats.length != 4) {
                        return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                    }
                    if (playerStats[0].isEmpty()) {
                        return new Response("El nombre del jugador debe tener al menos 1 caracter", Status.BAD_REQUEST);
                    }
                    for (int i = 1; i < playerStats.length; i++) {
                        if (playerStats[i].isEmpty()) {
                            return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                        }
                        if ("0".equals(playerStats[i])) {
                            return new Response("Ningún jugador puede tener 0 puntos en alguno de sus atributos", Status.BAD_REQUEST);
                        }
                        try {
                            Integer.valueOf(playerStats[i]);
                        } catch (NumberFormatException numericException) {
                            return new Response("Los atributos de los jugadores deben ser numéricos", Status.BAD_REQUEST);
                        }
                    }
                }
                if (lineCount > 11) {
                    return new Response("Solo pueden ser ingresados 11 jugadores", Status.BAD_REQUEST);
                }
                if (lineCount < 11) {
                    return new Response("Deben ser ingresados al menos 11 jugadores", Status.BAD_REQUEST);
                }
                return new Response("Se ha leído el archivo correctamente", Status.OK);
            } catch (IOException fileException) {
                return new Response("El archivo no pudo ser accedido/encontrado por el programa", Status.BAD_REQUEST);
            }
        } catch (Exception unexpectedException) {
            return new Response("Ocurrió un error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
