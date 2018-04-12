package domaincontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {
    Board board;
    @BeforeEach
    void setUp() {
         board = new Square();
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
        Cell c3 = new Cell(-1, true, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, true, -1);
        vectorCell.add(c4);
        Cell c5 = new Cell(-1, true, -1);
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
        Cell c2 = new Cell(-1, true, -1);
        vectorCell.add(c2);
        Cell c3 = new Cell(2, true, 3);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, true, -1);
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
        Cell c3 = new Cell(-1, true, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(-1, true, -1);
        vectorCell.add(c4);
        board.setVectorCell(vectorCell);
        assertFalse(board.solveHidato());
    }

    @Test
    void TestingSolveHidato4(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1, 4)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2, 5)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3, 6)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(2)));
        adjacencyMatrix.put(4, new ArrayList<Integer>(Arrays.asList(0, 5)));
        adjacencyMatrix.put(5, new ArrayList<Integer>(Arrays.asList(1, 4, 6, 7)));
        adjacencyMatrix.put(6, new ArrayList<Integer>(Arrays.asList(2, 5, 8)));
        adjacencyMatrix.put(7, new ArrayList<Integer>(Arrays.asList(5, 8)));
        adjacencyMatrix.put(8, new ArrayList<Integer>(Arrays.asList(6, 7)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 4);
        cellPositions.put(2, -1);
        cellPositions.put(3, 1);
        cellPositions.put(4, -1);
        cellPositions.put(5, -1);
        cellPositions.put(6, -1);
        cellPositions.put(7, -1);
        cellPositions.put(8, -1);
        cellPositions.put(9, -1);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c0 = new Cell(0, true, -1);
        vectorCell.add(c0);
        Cell c1 = new Cell(1, true, 3);
        vectorCell.add(c1);
        Cell c2 = new Cell(2, true, -1);
        vectorCell.add(c2);
        Cell c3 = new Cell(3, true, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(4, true, 1);
        vectorCell.add(c4);
        Cell c5 = new Cell(5, true, -1);
        vectorCell.add(c5);
        Cell c6 = new Cell(6, true, -1);
        vectorCell.add(c6);
        Cell c7 = new Cell(7, true, -1);
        vectorCell.add(c7);
        Cell c8 = new Cell(8, true, -1);
        vectorCell.add(c8);
        board.setVectorCell(vectorCell);
        assertTrue(board.solveHidato());
    }

    @Test
    void TestingSolveHidato5(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2, 4)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3, 4)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(2)));
        adjacencyMatrix.put(4, new ArrayList<Integer>(Arrays.asList(1, 2, 5)));
        adjacencyMatrix.put(5, new ArrayList<Integer>(Arrays.asList(4)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 0);
        cellPositions.put(2, -1);
        cellPositions.put(3, -1);
        cellPositions.put(4, -1);
        cellPositions.put(5, -1);
        cellPositions.put(6, -1);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c0 = new Cell(0, true, 1);
        vectorCell.add(c0);
        Cell c1 = new Cell(1, true, -1);
        vectorCell.add(c1);
        Cell c2 = new Cell(2, true, -1);
        vectorCell.add(c2);
        Cell c3 = new Cell(3, true, -1);
        vectorCell.add(c3);
        Cell c4 = new Cell(4, true, -1);
        vectorCell.add(c4);
        Cell c5 = new Cell(5, true, -1);
        vectorCell.add(c5);
        board.setVectorCell(vectorCell);
        assertFalse(board.solveHidato());
    }

    @Test
    void TestingSolveHidato6(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>(Arrays.asList(1,4,5)));
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(0, 2, 4, 5,6)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1, 3, 5,6,7)));
        adjacencyMatrix.put(3, new ArrayList<Integer>(Arrays.asList(2,6,7)));
        adjacencyMatrix.put(4, new ArrayList<Integer>(Arrays.asList(0,1,5,8,9)));
        adjacencyMatrix.put(5, new ArrayList<Integer>(Arrays.asList(0,1,2,4,6,8,9,10)));
        adjacencyMatrix.put(6, new ArrayList<Integer>(Arrays.asList(1,2,3,5,7,9,10,11)));
        adjacencyMatrix.put(7, new ArrayList<Integer>(Arrays.asList(2,3,6,10,11)));
        adjacencyMatrix.put(8, new ArrayList<Integer>(Arrays.asList(4,5,9)));
        adjacencyMatrix.put(9, new ArrayList<Integer>(Arrays.asList(4,5,6,8,10)));
        adjacencyMatrix.put(10, new ArrayList<Integer>(Arrays.asList(5,6,7,9,11)));
        adjacencyMatrix.put(11, new ArrayList<Integer>(Arrays.asList(6,7,10)));
        board.setAdjacencyMatrix(adjacencyMatrix);
        Map<Integer, Integer> cellPositions = new HashMap<>();
        cellPositions.put(1, 1);
        cellPositions.put(2, -1);
        cellPositions.put(3, -1);
        cellPositions.put(4, -1);
        cellPositions.put(5, -1);
        cellPositions.put(6, -1);
        cellPositions.put(7, -1);
        cellPositions.put(8, 6);
        board.setCellPositions(cellPositions);
        Vector<Cell> vectorCell = new Vector<>();
        Cell c0 = new Cell(0, false, -3);
        vectorCell.add(c0);
        Cell c1 = new Cell(1, true, 1);
        vectorCell.add(c1);
        Cell c2 = new Cell(2, true, -1);
        vectorCell.add(c2);
        Cell c3 = new Cell(3, false, -3);
        vectorCell.add(c3);
        Cell c4 = new Cell(4, true, -1);
        vectorCell.add(c4);
        Cell c5 = new Cell(5, true, -1);
        vectorCell.add(c5);
        Cell c6 = new Cell(6, true, 8);
        vectorCell.add(c6);
        Cell c7 = new Cell(7, true, -1);
        vectorCell.add(c7);
        Cell c8 = new Cell(8, false, -2);
        vectorCell.add(c8);
        Cell c9 = new Cell(9, true, -1);
        vectorCell.add(c9);
        Cell c10 = new Cell(10, true, -1);
        vectorCell.add(c10);
        Cell c11 = new Cell(11, false, -2);
        vectorCell.add(c11);
        board.setVectorCell(vectorCell);
        boolean bool = board.solveHidato();
        Map<Integer, Integer> cellPositionsProposalResult = new HashMap<>();
        cellPositionsProposalResult = board.getCellPositionsProposalResult();
        assertTrue(bool);
    }

}