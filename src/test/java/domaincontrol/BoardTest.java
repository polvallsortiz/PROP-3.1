package domaincontrol;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class BoardTest {
    Board board;
    @Before
    public void setUp() {
         board = new Square();
    }


    @Test
    public void TestingSolveHidato1(){
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
    public void TestingSolveHidato2(){
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
    public void TestingSolveHidato3(){
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
    public void TestingSolveHidato4(){
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
    public void TestingSolveHidato5(){
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
    public void TestingSolveHidato6(){
        Map<Integer, ArrayList<Integer>> adjacencyMatrix = new HashMap<>();
        adjacencyMatrix.put(0, new ArrayList<Integer>());
        adjacencyMatrix.put(1, new ArrayList<Integer>(Arrays.asList(2, 4, 5, 6)));
        adjacencyMatrix.put(2, new ArrayList<Integer>(Arrays.asList(1,5,6,7)));
        adjacencyMatrix.put(3, new ArrayList<Integer>());
        adjacencyMatrix.put(4, new ArrayList<Integer>(Arrays.asList(1,5,9)));
        adjacencyMatrix.put(5, new ArrayList<Integer>(Arrays.asList(1,2,4,6,9,10)));
        adjacencyMatrix.put(6, new ArrayList<Integer>(Arrays.asList(1,2,4,5,7,9,10)));
        adjacencyMatrix.put(7, new ArrayList<Integer>(Arrays.asList(2,6,10)));
        adjacencyMatrix.put(8, new ArrayList<Integer>());
        adjacencyMatrix.put(9, new ArrayList<Integer>(Arrays.asList(4,5,6,10)));
        adjacencyMatrix.put(10, new ArrayList<Integer>(Arrays.asList(5,6,7,9)));
        adjacencyMatrix.put(11, new ArrayList<Integer>());
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
        assertTrue(bool);
    }
    /*
    @Test
    public void GenerateHidatoTest1(){
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("CA");
        board.generateHidato(hidato,4,4, 4);
    }
    @Test
    public void GenerateHidatoTest2(){
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("CA");
        board.generateHidato(hidato,4,1, 4);
    }
    @Test
    public void GenerateHidatoTest3(){
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("CA");
        board.generateHidato(hidato,4,0, 4);
    }

    @Test
    public void GenerateHidatoTest4(){
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("CA");
        board.generateHidato(hidato,4,6, 6);
    } */
}