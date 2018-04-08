package domaincontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    Board board;
    @BeforeEach
    void setUp() {
         board = new Board();
    }

    @Test
    void TestingPreviousConditionsGraph1(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1,3)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0,2)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1,3)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(0,2)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        adjacencyMatrix = board.getAdjacencyMatrix();
        assertTrue(board.solveHidato());
    }
}