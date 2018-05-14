package domaincontrol;

import datacontrol.DataCtrl;
import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.*;

public class DomainCtrl {
    Game game;
    Player player;
    Board board;
    Ranking rankingeasy;
    Ranking rankingmedium;
    Ranking rankinghard;
    DataCtrl datacontrol;

    public DomainCtrl () {
        datacontrol = new DataCtrl();
        rankingeasy = datacontrol.getRanking("Easy");
        rankingmedium = datacontrol.getRanking("Medium");
        rankinghard = datacontrol.getRanking("Hard");
        //rankingeasy.addToRanking("lil_john", new Time(System.currentTimeMillis()));
        //datacontrol.setRanking("Easy", rankingeasy);
        printRanking("Easy");
        printRanking("Medium");
        printRanking("Hard");
    }

    public void printRanking(String dificultat) {
        if (dificultat.equals("Easy")) {
            String dif = rankingeasy.getDifficulty();
            System.out.print(dif + "\n");
            ArrayList<Pair<String, Time>> players = rankingeasy.getResults(dif);
            for (Pair str : players) {
                System.out.print(str.getKey()+ " ");
                System.out.print(str.getValue() + "\n");

            }
        }
    }

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
