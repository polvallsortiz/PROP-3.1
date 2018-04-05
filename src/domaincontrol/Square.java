package domaincontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Square extends Board {
    @Override
    public void calculateAdjacencyMatrix(Vector<Vector<String>> matrix,String adjacency) {
        System.out.print("\n A CALCULAR MATRIU ADJACENCIES QUADRAT");
        switch (adjacency) {
            case "C":
                calculateBounds(matrix);
                break;

            case "CA":
                calculateBoundsVertexs(matrix);
                break;
        }
    }

    public void calculateBounds(Vector<Vector<String>> matrix) {
        //MATRIX.SIZE() * MATRIX.GET(0).SIZE() == TOTAL OF CELLS
        int lines = matrix.size();
        int columns = matrix.get(0).size();
        int total = lines * columns;
        Integer x1,x2,x3,x4,leftbound,rightbound;
        for(int i = 0; i < lines; ++i) {
            leftbound = i * columns;
            rightbound = leftbound + columns - 1;
            for(int j = 0; j < columns; ++j) {
                Integer actual = (i*columns) + j;
                ArrayList<Integer> aux = new ArrayList<>();
                x1 = actual - columns;
                x2 = actual - 1;
                x3 = actual + 1;
                x4 = actual + columns;
                if(x1 >= 0) aux.add(x1);
                if(x2 >= leftbound) aux.add(x2);
                if(x3 <= rightbound) aux.add(x3);
                if(x4 < total) aux.add(x4);
                Collections.sort(aux);
                adjacencyMatrix.put(actual,aux);
            }
        }
        printAdjacencyMatrix();
    }

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

    public void calculateBoundsVertexs(Vector<Vector<String>> matrix) {
        //MATRIX.SIZE() * MATRIX.GET(0).SIZE() == TOTAL OF CELLS
        Integer lines = matrix.size();
        Integer columns = matrix.get(0).size();
        Integer total = lines * columns;
        Integer x1,x2,x3,x4,x5,x6,x7,x8,leftbound,rightbound;
        for(int i = 0; i < lines; ++i) {
            //TRAVERSE ALL THE LINES OF THE MATRIX AND CALCULATE LEFTNUMBER AND RIGHTNUMBER
            leftbound = i * columns;
            rightbound = leftbound + columns - 1;

            //TRAVERSE ALL THE ELEMENTS OF EACH LINE
            for(int j = 0; j < columns; ++j) {
                Integer actual = (i*columns) + j;
                ArrayList<Integer> aux = new ArrayList<Integer>();
                x1 = x3 = x6 = x8 = null;
                x1 = actual - columns;
                x2 = actual - 1;
                x3 = actual + 1;
                x4 = actual + columns;
                x5 = (actual - columns) - 1;
                x6 = (actual + columns) - 1;
                x7 = (actual - columns) + 1;
                x8 = (actual + columns) + 1;

                if(x1 >= 0) aux.add(x1);
                if(x2 >= leftbound) aux.add(x2);
                if(x3 <= rightbound) aux.add(x3);
                if(x4 < total) aux.add(x4);
                if(!actual.equals(leftbound) && x5 >= 0) aux.add(x5);
                if(!actual.equals(leftbound) && x6 < total) aux.add(x6);
                if(!actual.equals(rightbound) && x7 >= 0) aux.add(x7);
                if(!actual.equals(rightbound) && x8 < total) aux.add(x8);
                Collections.sort(aux);
                adjacencyMatrix.put(actual,aux);
            }
        }
        printAdjacencyMatrix();
    }
}
