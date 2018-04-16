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
        String value;
        for(int i = 0; i < lines; ++i) {
            leftbound = i * columns;
            rightbound = leftbound + columns - 1;
            Vector<String> vec = matrix.elementAt(i);
            for(int j = 0; j < columns; ++j) {
                value = vec.elementAt(j);
                Integer actual = (i*columns) + j;
                insertCell(actual,value);
                completeCellPositions(value,actual);
                ArrayList<Integer> aux = new ArrayList<>();
                x1 = actual - columns;
                x2 = actual - 1;
                x3 = actual + 1;
                x4 = actual + columns;
                if(x1 >= 0 && accesible(matrix.elementAt(x1/columns).elementAt(x1%columns))) aux.add(x1);
                if(x2 >= leftbound && accesible(matrix.elementAt(x2/columns).elementAt(x2%columns))) aux.add(x2);
                if(x3 <= rightbound && accesible(matrix.elementAt(x3/columns).elementAt(x3%columns))) aux.add(x3);
                if(x4 < total && accesible(matrix.elementAt(x4/columns).elementAt(x4%columns))) aux.add(x4);
                Collections.sort(aux);
                adjacencyMatrix.put(actual,aux);
            }
        }
        fillCellPositions();
    }



    public void calculateBoundsVertexs(Vector<Vector<String>> matrix) {
        //MATRIX.SIZE() * MATRIX.GET(0).SIZE() == TOTAL OF CELLS
        Integer lines = matrix.size();
        Integer columns = matrix.get(0).size();
        Integer total = lines * columns;
        Integer x1,x2,x3,x4,x5,x6,x7,x8,leftbound,rightbound;
        String value;
        for(int i = 0; i < lines; ++i) {
            //TRAVERSE ALL THE LINES OF THE MATRIX AND CALCULATE LEFTNUMBER AND RIGHTNUMBER
            leftbound = i * columns;
            rightbound = leftbound + columns - 1;
            Vector<String> vec = matrix.elementAt(i);
            //TRAVERSE ALL THE ELEMENTS OF EACH LINE
            for(int j = 0; j < columns; ++j) {
                ArrayList<Integer> aux = new ArrayList<>();
                value = vec.elementAt(j);
                Integer actual = (i*columns) + j;
                insertCell(actual,value);
                completeCellPositions(value,actual);
                x1 = actual - columns;
                x2 = actual - 1;
                x3 = actual + 1;
                x4 = actual + columns;
                x5 = (actual - columns) - 1;
                x6 = (actual + columns) - 1;
                x7 = (actual - columns) + 1;
                x8 = (actual + columns) + 1;

                if(x1 >= 0 && accesible(matrix.elementAt(x1/columns).elementAt(x1%columns))) aux.add(x1);
                if(x2 >= leftbound && accesible(matrix.elementAt(x2/columns).elementAt(x2%columns))) aux.add(x2);
                if(x3 <= rightbound && accesible(matrix.elementAt(x3/columns).elementAt(x3%columns))) aux.add(x3);
                if(x4 < total && accesible(matrix.elementAt(x4/columns).elementAt(x4%columns))) aux.add(x4);
                if(!actual.equals(leftbound) && x5 >= 0 && accesible(matrix.elementAt(x5/columns).elementAt(x5%columns))) aux.add(x5);
                if(!actual.equals(leftbound) && x6 < total && accesible(matrix.elementAt(x6/columns).elementAt(x6%columns))) aux.add(x6);
                if(!actual.equals(rightbound) && x7 >= 0 && accesible(matrix.elementAt(x7/columns).elementAt(x7%columns))) aux.add(x7);
                if(!actual.equals(rightbound) && x8 < total && accesible(matrix.elementAt(x8/columns).elementAt(x8%columns))) aux.add(x8);
                Collections.sort(aux);
                adjacencyMatrix.put(actual,aux);
            }
        }
        fillCellPositions();
    }
}
