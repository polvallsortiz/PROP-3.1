package domaincontrol;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class Board {
    Map<Integer, Integer> cellPositions;
    Vector<Cell> vectorCell;
    Map<Integer, Vector<Integer>> adjacencyMatrix;

    public void createBoard(Vector<Vector<String>> matrix,String adjacency) {
        adjacencyMatrix = new HashMap<>();
        calculateAdjacencyMatrix(matrix,adjacency);
    }

    public abstract void calculateAdjacencyMatrix(Vector<Vector<String>> matrix,String adjcency);
}
