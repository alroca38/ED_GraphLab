package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatrixFileController {

    public static Response readMatrixFile(File matrixFile) {
        try {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(matrixFile));
                String line = null;
                int lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    String[] playerAdjacency = line.split(",");
                    if (playerAdjacency.length != 12) {
                        return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                    }
                    if (playerAdjacency[0].isEmpty()) {
                        return new Response("El nombre del jugador debe tener al menos 1 caracter", Status.BAD_REQUEST);
                    }
                    for (int i = 1; i < playerAdjacency.length; i++) {
                        if (!"0".equals(playerAdjacency[i]) && !"1".equals(playerAdjacency[i])) {
                            return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
                        }
                        try {
                            Integer.valueOf(playerAdjacency[i]);
                        } catch (NumberFormatException numericException) {
                            return new Response("La matriz de adyacencia solo admite valores numéricos", Status.BAD_REQUEST);
                        }
                    }
                }
                reader.close();
                if (lineCount != 11) {
                    return new Response("El archivo ingresado no tiene la estructura correcta", Status.BAD_REQUEST);
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
