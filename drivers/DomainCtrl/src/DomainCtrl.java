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
            System.out.print("HIJUEPUTA NO FUNSIONA");
        }
    }
}
