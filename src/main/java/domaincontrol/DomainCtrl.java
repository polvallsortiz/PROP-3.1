package domaincontrol;
import datacontrol.DataCtrl;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DomainCtrl {
    //Global atributes
    private Game game;
    private Player player;
    private Board board;
    Ranking rankingeasy;
    Ranking rankingmedium;
    Ranking rankinghard;
    DataCtrl datacontrol;

    //Local atributes
    private Board tempBoard;

    public DomainCtrl () {
        datacontrol = new DataCtrl();
        rankingeasy = datacontrol.getRanking("Easy");
        rankingmedium = datacontrol.getRanking("Medium");
        rankinghard = datacontrol.getRanking("Hard");

    }

    public void newGame(String username) {
        game = new Game(username);
        player = game.getPlayer();
    }

    public Vector<Vector<String>> generateHidato(Hidato hidato, int holes, int predefined) {
        Vector<Vector<String>> matrix = hidato.getHidato();
        String adjacency = hidato.getAdjacencytype();
        Character celltype = hidato.getCelltype();
        Board b = null;
        switch (celltype) {
            case 'Q':
                b = new Square();
                break;

            case 'T':
                b = new Triangle();
                break;

            case 'H':
                b = new Hexagon();
                break;
        }
        Hidato newHidato = new Hidato();
        newHidato.setHidato(matrix);
        newHidato.setAdjacencytype(adjacency);
        if (b.generateHidato(newHidato, matrix.get(0).size(), holes, predefined) == 0) return null;
        else {
            tempBoard = b;
            Vector<Cell> vectorCell = new Vector<>();
            vectorCell = b.getVectorCell();
            Vector<Vector<String>> mat = new Vector<>();
            int lines = matrix.size();
            int columns = matrix.get(0).size();
            for (int i = 0; i < lines; ++i) { //LINIA
                Vector<String> aux = new Vector<>();
                for (int j = 0; j < columns; ++j) {
                    Cell c = vectorCell.get(i * columns + j);
                    switch (c.getNumber()) {
                        case -3:
                            aux.add(j, "#");
                            break;
                        case -2:
                            aux.add(j, "*");
                            break;
                        case -1:
                            aux.add(j, "?");
                            break;
                        default:
                            aux.add(j, "" + c.getNumber());
                            break;
                    }
                }
                mat.add(i, aux);
            }
            return mat;
        }

    }

    public Vector<Vector<String>> defineHidato(Hidato hidato) {
        Board b = null;
        Vector<Vector<String>> matrix = hidato.getHidato();
        String adjacency = hidato.getAdjacencytype();
        Character celltype = hidato.getCelltype();
        String dificultat = game.defineGame();
        switch (celltype) {
            case 'Q':
                b = new Square();
                break;

            case 'T':
                b = new Triangle();
                break;

            case 'H':
                b = new Hexagon();
                break;
        }
        b.createBoard(hidato);
        game.setBoard(b);
        board = b;
        if (b.solveHidato()) {
            tempBoard = b;
            Map<Integer, Integer> cellPositionsProposal = new HashMap<>();
            cellPositionsProposal = b.getCellPositionsProposalResult();
            Integer lines = matrix.size();
            Integer columns = matrix.get(0).size();
            for (int num : cellPositionsProposal.keySet()) {
                Integer pos = cellPositionsProposal.get(num);
                Vector<String> vec = matrix.get(pos / columns);
                vec.set(pos % columns, String.valueOf(num));
                matrix.set(pos / columns, vec);
            }
            return matrix;
        } else return null;
    }

    public Vector<Vector<String>> loadHidato(String path) { //Paula
        //farem les crides a datactrl i demanarem el path solicitat
        //retornem la matriu
        Vector<Vector<String>> hidatoLoaded = null;
        return hidatoLoaded;
    }

    public int saveHidato(String Path){ //Paula
        //guardar hidato com a taulell
        return 0;
    }

    public Vector<Vector<String>> solveHidato() {
        //retorna el taulell resolt
        Vector<Vector<String>> hidatoLoaded = null;
        return hidatoLoaded;
    }

    public void playHidato() {
        //si es fa playHidato, l'usuari accepta el taulell i es comença la partida
        //POL: no sé si necessites que retorni alguna cosa
    }

    public Character nextMovement(int idCell, String nextValue){
        //retorno 'C' per completat
        //'W' per erroni
        //'O' per okey
        return 'O';
    }

    public Pair<Integer, String> Hint(){
        Pair<Integer, String> nextMove = new Pair<Integer, String>(0, "1");
        return nextMove;
    }

    public Vector<Vector<String>> rollbackMovement() {
        Vector<Vector<String>> hidatoLoaded = null;
        return hidatoLoaded;
    }

    public boolean validateHidato() {
        //Crida final. Quan l´usuari ha completat tot l'hidato, pulsa aquest botó per a validar si està bé o no
        return true;
    }

    public int saveGame() { //Paula
        //retorna codi d'error. 1 tot ok 0 error
        return 1;
    }

    public Vector<Vector<String>> loadGame(){ //Paula
        //es retorna l'Hidato de l'últim moviment
        Vector<Vector<String>> hidatoLoaded = null;
        return hidatoLoaded;
    }

    public Vector<Vector<String>> rebootGame() {
        //retorna la matriu inicial i es reinicia tot
        Vector<Vector<String>> hidatoLoaded = null;
        return hidatoLoaded;
    }

    public Ranking loadRanking(String tipus) {
        if (tipus == "Easy") {
            return rankingeasy;
        }
        else if (tipus == "Medium") {
            return rankingmedium;
        }
        else if (tipus == "Hard"){
            return rankinghard;
        }
        return null;
    }
    public void addToRanking() {
        if (game.getDifficulty() == "Easy") {
            rankingeasy.addToRanking("lil_john", 20);
        }
        else if (game.getDifficulty() == "Medium") {
            rankingmedium.addToRanking("vallsortizpol", 20);
        }
        else if (game.getDifficulty() == "Hard"){
            rankinghard.addToRanking("pauleta_6", 20);
        }
    }


}
