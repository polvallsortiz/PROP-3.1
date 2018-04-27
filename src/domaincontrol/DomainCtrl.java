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
    public Vector<Vector<String>> generateHidato(Vector<Vector<String>> matrix, String adjacency, Character celltype, int holes, int predefined){
            //et passaran celltype, adjacency, maxfiles, maxcols, nombre de forats, nombre
            //de nombres que es mostraran. A més, han d'enviar la id de les cel·les que volen que siguin innaccessibles.

            //ens has d'enviar una matriu plena d'interrogants i de les inaccessibles marcades ja ben formada
            //board.generateHidato(Vector<Vector<String>> matrix, maxcolumns,  adjacency, holes, toShow);

            //has d'agafar el vectorCell, allà es mostren tot elq ue li hem d'ensenyar a l'usuari
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
        if(b.generateHidato(matrix,matrix.get(0).size(),adjacency,holes,predefined) == 0) return null;
        else {
            Vector<Cell> vectorCell = new Vector<>();
            vectorCell = b.getVectorCell();
            Vector<Vector<String>> mat = new Vector<>();
            int lines = matrix.size();
            int columns = matrix.get(0).size();
            for(int i = 0; i < lines; ++i) { //LINIA
                Vector<String> aux = new Vector<>();
                for(int j = 0; j < columns; ++j) {
                    Cell c = vectorCell.get(i*columns+j);
                    switch (c.getNumber()) {
                        case -2 :
                            aux.set(j,"#");
                            break;
                        default:
                            aux.set(j,""+c.getNumber());
                            break;
                    }
                }
            }
            return mat;
        }

    }
}
