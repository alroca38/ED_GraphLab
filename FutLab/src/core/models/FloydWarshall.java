package core.models;

public class FloydWarshall {

    private static final int INF = Integer.MAX_VALUE;

    public static int relativeInfinity(){
        return INF;
    }
    
    public static int floydWarshall(int[][] grafo, Site nodoAdyacente, Site nodoLlegada) {
        int V = grafo.length;
        int[][] distancia = new int[V][V];

        // Inicializamos la matriz de distancias con los valores del grafo
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                distancia[i][j] = grafo[i][j];
            }
        }

        // Calcular las distancias mínimas entre todos los pares de nodos
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distancia[i][k] + distancia[k][j] < distancia[i][j]) {
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                    }
                }
            }
        }
        // Imprimir la matriz de distancias mínimas
        /*for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (distancia[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(distancia[i][j] + "\t");
                }
            }
            System.out.println();
        }*/
        int jIndex = nodoLlegada.getId();
        int iIndex = nodoAdyacente.getId();
        return distancia[iIndex][jIndex];
    }
}
