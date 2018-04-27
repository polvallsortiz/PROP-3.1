package domaincontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Hexagon extends Board {
    @Override
    public void calculateAdjacencyMatrix(Vector<Vector<String>> matrix,String adjacency) {
        System.out.print("\n A CALCULAR MATRIU ADJACENCIES HEXAGON");
        calculateBounds(matrix);
        /*switch (adjacency) {
            case "C":
                calculateBounds(matrix);
                break;

            case "CA":
                calculateBoundsVertexs(matrix);
                break;
        }*/
    }


    public void calculateBounds(Vector<Vector<String>> matrix) {
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
                value = vec.elementAt(j);
                Integer actual = (i*columns) + j;
                insertCell(actual,value);
                completeCellPositions(value,actual);
                ArrayList<Integer> aux = new ArrayList<Integer>();
                if(!value.equals("#") && !value.equals("*")) {
                    x1 = actual - columns;
                    x2 = x1 + 1;
                    x3 = actual - 1;
                    x4 = actual + 1;
                    x5 = actual + columns;
                    x6 = x5 + 1;
                    x7 = x5 - 1;
                    x8 = x1 - 1;

                    if (x1 >= 0 && accesible(matrix.elementAt(x1 / columns).elementAt(x1 % columns))) aux.add(x1);
                    if (x2 >= 0 && !(i % 2 == 0) && !actual.equals(rightbound) && accesible(matrix.elementAt(x2 / columns).elementAt(x2 % columns)))
                        aux.add(x2); //IF ITS NOT NEGATIVE, ITS NOT EVEN LINE AND IF ITS NOT RIGHTBOUND
                    if (x3 >= 0 && !actual.equals(leftbound) && accesible(matrix.elementAt(x3 / columns).elementAt(x3 % columns)))
                        aux.add(x3);
                    if (x4 < total && !actual.equals(rightbound) && accesible(matrix.elementAt(x4 / columns).elementAt(x4 % columns)))
                        aux.add(x4);
                    if (x5 < total && accesible(matrix.elementAt(x5 / columns).elementAt(x5 % columns))) aux.add(x5);
                    if (x6 < total && !actual.equals(rightbound) && !(i % 2 == 0) && accesible(matrix.elementAt(x6 / columns).elementAt(x6 % columns)))
                        aux.add(x6);
                    if (x7 < total && !actual.equals(leftbound) && (i % 2 == 0) && accesible(matrix.elementAt(x7 / columns).elementAt(x7 % columns)))
                        aux.add(x7);
                    if (x8 >= 0 && !actual.equals(leftbound) && (i % 2 == 0) && accesible(matrix.elementAt(x8 / columns).elementAt(x8 % columns)))
                        aux.add(x8);
                }
                Collections.shuffle(aux);
                adjacencyMatrix.put(actual,aux);
            }
        }
        fillCellPositions();
    }
}
