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
    Game currentGame;
    DataCtrl datacontrol;

    public DomainCtrl () {
        datacontrol = new DataCtrl();
        rankingeasy = datacontrol.getRanking("Easy");
        rankingmedium = datacontrol.getRanking("Medium");
        rankinghard = datacontrol.getRanking("Hard");
        datacontrol.createGame( "polete");
        currentGame = datacontrol.getGame("resources/files/Games/02:18:40.json");
        //rankingeasy.addToRanking("lil_john", 20);
        //datacontrol.setRanking("Easy", rankingeasy);
        //Hidato guapo = new Hidato("guapo");
        //Vector<Vector<String>> fila = new Vector<>();
        //Vector<String> relleno = new Vector<>();
        //relleno.add("?");
        //relleno.add("?");
        //relleno.add("*");
        //relleno.add("?");
        //fila.add(relleno);
        //guapo.setHidato(fila);
        //guapo.setAdjacencytype("C");
        //guapo.setCelltype('Q');
        //guapo.setColumns(4);
        //guapo.setLines(1);
        //datacontrol.guardarHidato(guapo);
        Hidato putoamo = datacontrol.getBoard("resources/files/Boards/guapo.json");
        System.out.print(putoamo.getNom()+ "\n");
        System.out.print(putoamo.getAdjacencytype() + "\n");
        System.out.print(putoamo.getCelltype() + "\n");
        System.out.print(putoamo.getLines() + "\n");
        System.out.print(putoamo.getColumns() + "\n");
        Vector<Vector<String>> sol = putoamo.getHidato();
        for (int x = 0; x < sol.size(); ++x) {
            for (int y = 0; y < putoamo.getColumns(); ++y) {
                System.out.print(sol.get(x).get(y)+ " ");
            }
            System.out.print("\n");
        }
        printRanking("Easy");
        printRanking("Medium");
        printRanking("Hard");
        currentGame.setDifficulty(1);
        currentGame.addMovement(putoamo);
        datacontrol.writeGame("resources/files/Games/02:18:40.json", currentGame);
    }

    public void printRanking(String dificultat) {
        if (dificultat.equals("Easy")) {
            String dif = rankingeasy.getDifficulty();
            System.out.print(dif + "\n");
            ArrayList<Pair<String, Integer>> players = rankingeasy.getResults(dif);
            for (Pair str : players) {
                System.out.print(str.getKey()+ " ");
                System.out.print(str.getValue() + "\n");

            }
        }
    }

    public void generateHidato(){}
    public void defineHidato(){}
    public void loadHidato(){}
    public void solveHidato(){}
    public void playHidato(){}
    public void nextMovement(){}
    public void rollbackMovement(){}
    public void validateHidato(){}
    public void saveGame(){}
    public void rebootGame(){}
    public void loadRanking(){}

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
