package domaincontrol;

import java.util.Map;
import java.util.Vector;

public abstract class Board {
    Map<Integer, Integer> cellPositions;
    Vector<Cell> vectorCell;
    Map<Integer, Vector<Integer>> adjacecyMatrix;

    public void createBoard(Vector<Vector<String>> matrix) {

    }

    public abstract void calcularMatriuAdjacencies();
}
