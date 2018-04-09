package domaincontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    Board board;
    @BeforeEach
    void setUp() {
         board = new Board();
    }


    @Test
    void TestingSolveHidato1(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(0, 2)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 0);
        cellPositions.put(2, -1);
        cellPositions.put(3, -1);
        cellPositions.put(4, -1);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c2 = new Cell(0, true, 1);
        vectorCell.add(c2);
        Cell c3 = new Cell(-1, false, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, false, -1);
        vectorCell.add(c4);
        Cell c5 = new Cell(-1, false, -1);
        vectorCell.add(c5);
        board.setVectorCell(vectorCell);
        assertTrue(board.solveHidato());
    }

    @Test
    void TestingSolveHidato2(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(0, 2)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 0);
        cellPositions.put(2, -1);
        cellPositions.put(3, 2);
        cellPositions.put(4, -1);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c1 = new Cell(0, true, 1);
        vectorCell.add(c1);
        Cell c2 = new Cell(-1, false, -1);
        vectorCell.add(c2);
        Cell c3 = new Cell(2, true, 3);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, false, -1);
        vectorCell.add(c4);
        board.setVectorCell(vectorCell);
        assertTrue(board.solveHidato());
    }

    @Test
    void TestingSolveHidato3(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(0, 2)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 0);
        cellPositions.put(2, -1);
        cellPositions.put(3, 1);
        cellPositions.put(4, -1);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c1 = new Cell(0, true, 1);
        vectorCell.add(c1);
        Cell c2 = new Cell(1, true, 3);
        vectorCell.add(c2);
        Cell c3 = new Cell(-1, false, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, false, -1);
        vectorCell.add(c4);
        board.setVectorCell(vectorCell);
        assertFalse(board.solveHidato());
    }

}