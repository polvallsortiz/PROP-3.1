package domaincontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class Board {
    Map<Integer, Integer> cellPositions;
    Vector<Cell> vectorCell;
    Map<Integer, ArrayList<Integer>> adjacencyMatrix;

    public void createBoard(Vector<Vector<String>> matrix,String adjacency) {
        adjacencyMatrix = new HashMap<>();
        calculateAdjacencyMatrix(matrix,adjacency);
    }

    public abstract void calculateAdjacencyMatrix(Vector<Vector<String>> matrix,String adjcency);

    public void printAdjacencyMatrix() {
        ArrayList<Integer> aux;
        for(Integer key : adjacencyMatrix.keySet()){
            aux = adjacencyMatrix.get(key);
            System.out.print("\n" + key + " : ");
            for(Integer i : aux) {
                System.out.print(i + ",");
            }
            System.out.print("\n");
        }
    }
}
