package domaincontrol;

import java.util.*;

public abstract class Board {
    Map<Integer, Integer> cellPositions;
    Vector<Cell> vectorCell;
    Map<Integer, ArrayList<Integer>> adjacencyMatrix;
    Integer counter;

    public void createBoard(Vector<Vector<String>> matrix,String adjacency) {
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;
        calculateAdjacencyMatrix(matrix,adjacency);
        printAdjacencyMatrix();
        printCellPositions();
        printCells();
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

    public void completeCellPositions(String value, Integer actual) {
        if(!value.equals("#") && !value.equals("*")) {
            if(value.equals("?")) ++counter;
            else cellPositions.put(Integer.parseInt(value),actual);
        }
    }

    public void fillCellPositions() {
        Integer total = cellPositions.size() + counter;
        for(int i = 1; i <= total; ++i) {
            if(!cellPositions.containsKey(i)) cellPositions.put(i,-1);
        }
        Map<Integer,Integer> sorted = new TreeMap<Integer, Integer>(cellPositions);
        cellPositions = sorted;
    }

    public void printCellPositions() {
        System.out.print("PRINT OF ALL CELL POSITIONS\n");
        Integer aux;
        for(Integer key : cellPositions.keySet()) {
            aux = cellPositions.get(key);
            System.out.print(key + " : " + aux + "\n");
        }
    }

    public void printCells() {
        System.out.print("///PRINT CELLS/// \n");
        for(Cell c : vectorCell) {
            c.printCell();
        }
    }

    public void insertCell(int id, String value) {
        Cell c;
        switch (value) {
            case "#" :
               c = new Cell(id,false,-2);
               break;

            case "*" :
                c = new Cell(id,false,-2);
                break;

            case "?" :
                c = new Cell(id,true,-1);
                break;

            default:
                c = new Cell(id,true,Integer.valueOf(value));
                break;
        }
        vectorCell.add(c);
    }
}
