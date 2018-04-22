package domaincontrol;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DomainCtrl {
    public void defineBoard(Vector<Vector<String>> matrix, String username, String adjacency, Character celltype) {
        Board b = null;
        switch(celltype) {
            case 'Q' :
                b = new Square();
                break;

            case 'T' :
                b = new Triangle();
                break;

            case 'H' :
                b = new Hexagon();
                break;
        }
        b.createBoard(matrix,adjacency);
        if(b.solveHidato()) {
             Map<Integer, Integer> cellPositionsProposalResult = new HashMap<>();
             cellPositionsProposalResult = b.getCellPositionsProposalResult();
             for(Integer key : cellPositionsProposalResult.keySet()) {
                 System.out.print(key + " "  + cellPositionsProposalResult.get(key) + "\n");
             }
             System.out.print("\n");
        }
        else {
            System.out.print("NO SOLUTION FOUND");
        }
    }
    public void generateHidato(){
            //et passaran celltype, adjacency, maxfiles, maxcols, nombre de forats, nombre
            //de nombres que es mostraran. A més, han d'enviar la id de les cel·les que volen que siguin innaccessibles.

            //ens has d'enviar una matriu plena d'interrogants i de les inaccessibles marcades ja ben formada
            //board.generateHidato(Vector<Vector<String>> matrix, maxcolumns,  adjacency, holes, toShow);

            //has d'agafar el vector cell, allà guardarem la solució a mostrar
    }
}
