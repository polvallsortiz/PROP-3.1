package domaincontrol;

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
                Vector<Integer> aux = new Vector<Integer>();
                x1 = actual - columns;
                x2 = actual - 1;
                x3 = actual + 1;
                x4 = actual + columns;
                if(x1 >= 0) aux.add(x1);
                if(x2 >= leftbound) aux.add(x2);
                if(x3 <= rightbound) aux.add(x3);
                if(x4 < total) aux.add(x4);
                adjacencyMatrix.put(actual,aux);
            }
        }
        printAdjacencyMatrix();
    }

    public void printAdjacencyMatrix() {
        Vector<Integer> aux;
        for(Integer key : adjacencyMatrix.keySet()){
            aux = adjacencyMatrix.get(key);
            System.out.print("\n" + key + " : ");
            for(int i = 0; i < aux.size(); ++i) {
                System.out.print(aux.elementAt(i) + ",");
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
            leftbound = i * columns;
            rightbound = leftbound + columns - 1;
            for(int j = 0; j < columns; ++j) {
                Integer actual = (i*columns) + j;
                Vector<Integer> aux = new Vector<Integer>();
                x1 = x3 = x6 = x8 = null;
                x2 = actual - columns;
                x4 = actual - 1;
                x5 = actual + 1;
                x7 = actual + columns;
                if(!actual.equals(leftbound)) { //LIMITLEFTOFMATRIX
                    x1 = (actual - columns) - 1;
                    x6 = (actual + columns) - 1;
                }
                if(!actual.equals(rightbound)) {  //LIMITRIGHTOFMATRIX
                    x3 = (actual - columns) + 1;
                    x8 = (actual + columns) + 1;
                }
                if(!actual.equals(leftbound) && x1 >= 0) aux.add(x1);
                if(x2 >= 0) aux.add(x2);
                if(!actual.equals(rightbound) && x3 >= 0) aux.add(x3);
                if(x4 >= leftbound) aux.add(x4);
                if(x5 <= rightbound) aux.add(x5);
                if(!actual.equals(leftbound) && x6 < total) aux.add(x6);
                if(x7 < total) aux.add(x7);
                if(!actual.equals(rightbound) && x8 < total) aux.add(x8);
                adjacencyMatrix.put(actual,aux);
            }
        }
        printAdjacencyMatrix();
    }
}
