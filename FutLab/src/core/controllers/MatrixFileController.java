package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.SoccerField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatrixFileController {

    public static Response readMatrixFile(File matrixFile) {
        // Verificación de la extensión del archivo
        if (!matrixFile.getAbsolutePath().endsWith(".csv")) {
            return new Response("El archivo ingresado debe ser un archivo en formato CSV", Status.BAD_REQUEST);
        }
        // Manejo de excepciones
        try {
            // Valida que el archivo puede ser procesado por el programa
            try {
                BufferedReader reader = new BufferedReader(new FileReader(matrixFile));
                // Variable que guarda la linea actual del archivo
                String line = null;
                // Contador de lineas
                int lineCount = 0;
                // Bucle que recorre cada una de las lineas del archivo
                while ((line = reader.readLine()) != null) {
                    // Se suma una línea al contador
                    lineCount++;
                    // Arreglo contenedor de cada uno de los campos de la línea actual
                    String[] playerAdjacency = line.split(",");
                    // Validación de la cantidad de campos (Deben ser 12 por línea)
                    if (playerAdjacency.length != 11) {
                        return new Response("El archivo ingresado no tiene la cantidad de campos requerida", Status.BAD_REQUEST);
                    }
                    boolean isolated = true;
                    for (String string : playerAdjacency) {
                        if (!"0".equals(string)){
                            isolated = false;
                        }
                    }
                    if(isolated){
                        return new Response("No pueden haber jugadores aislados del resto del equipo", Status.BAD_REQUEST);
                    }
                    // Recorrido de los campos numéricos del jugador actual
                    for (int i = 0; i < playerAdjacency.length; i++) {
                        // Los campos únicamente pueden tomar el valor de 0 y 1
                        if (!"0".equals(playerAdjacency[i]) && !"1".equals(playerAdjacency[i])) {
                            return new Response("Los valores ingresados en los campos del archivo no son adecuados", Status.BAD_REQUEST);
                        }
                    }
                }
                reader.close();
                // El archivo solo puede contener 11 líneas
                if (lineCount > 11) {
                    return new Response("Solo pueden ser ingresados 11 jugadores", Status.BAD_REQUEST);
                }
                if (lineCount < 11) {
                    return new Response("Deben ser ingresados al menos 11 jugadores", Status.BAD_REQUEST);
                }
                int[][] auxMatrix = new int[11][11];
                //SoccerField.getInstance().createMatrix(matrixFile, auxMatrix);
                return new Response("Se ha leído el archivo correctamente", Status.OK);
            } catch (IOException fileException) {
                return new Response("El archivo no pudo ser accedido/encontrado por el programa", Status.BAD_REQUEST);
            }
        } catch (Exception unexpectedException) {
            return new Response("Ocurrió un error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
