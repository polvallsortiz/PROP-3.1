package domaincontrol;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DomainCtrl {
    Game game;
    Player player;
    Board board;

    public Vector<Vector<String>> defineBoard(Vector<Vector<String>> matrix, String username, String adjacency, Character celltype) {
        Board b = null;
        Integer dificultat = game.defineGame();
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
        game.setBoard(b);
        board = b;
        if(b.solveHidato()) {
            Map<Integer,Integer> cellPositionsProposal = new HashMap<>();
            cellPositionsProposal = b.getCellPositionsProposalResult();
            Integer lines = matrix.size();
            Integer columns = matrix.get(0).size();
            for(int num : cellPositionsProposal.keySet()) {
                Integer pos = cellPositionsProposal.get(num);
                Vector<String> vec = matrix.get(pos/columns);
                vec.set(pos%columns,String.valueOf(num));
                matrix.set(pos/columns,vec);
            }
           return matrix;
        }
        else return null;
    }

    public Vector<Vector<String>> generateHidato(Vector<Vector<String>> matrix, String adjacency, Character celltype, int holes, int predefined){
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
                        case -3 :
                            aux.add(j,"#");
                            break;
                        case -2:
                            aux.add(j,"*");
                            break;
                        case -1:
                            aux.add(j,"?");
                            break;
                        default:
                            aux.add(j,""+c.getNumber());
                            break;
                    }
                }
                mat.add(i,aux);
            }
            return mat;
        }

    }

    public void newGame(String username) {
        game = new Game(username);
        player = game.getPlayer();
    }
}
