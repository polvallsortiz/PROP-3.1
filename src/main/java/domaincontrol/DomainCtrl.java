package domaincontrol;
import datacontrol.DataCtrl;
import javafx.util.Pair;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private Hidato currentHidato;

    public DomainCtrl () {
        datacontrol = new DataCtrl();
        rankingeasy = datacontrol.getRanking("Easy");
        rankingmedium = datacontrol.getRanking("Medium");
        rankinghard = datacontrol.getRanking("Hard");
    }

    public void newGame(String username) {
        game = new Game(username);
        datacontrol.createGame();
        datacontrol.createHidato();
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
        currentHidato = hidato;
        if (b.generateHidato(hidato, matrix.get(0).size(), holes, predefined) == 0) return null;
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
            currentHidato.setHidato(mat);
            return mat;
        }

    }

    public Vector<Vector<String>> defineHidato(Hidato hidato) {
        Board b = null;
        currentHidato = hidato;
        Vector<Vector<String>> matrix = hidato.getHidato();
        Character celltype = hidato.getCelltype();
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
        tempBoard = b;
        if (b.solveHidato()) return currentHidato.getHidato();
        else return null;
    }

    public Hidato loadHidato(String path) { //Paula
        Hidato hidato = datacontrol.getBoard(path); // here we must call the function at domain ctrl
        currentHidato = hidato;
        Vector<Vector<String>> hidatoLoaded = defineHidato(hidato);
        return hidato;
    }

    public int saveHidato(String Path){ //Paula
        //guardar hidato com a taulell
        return datacontrol.writeHidato(Path+".hidato", currentHidato);
        //guardem currentHidato
        //int errorCode = saveHidato(currentHidato, Path)
        //return errorCode
    }

    public Vector<Vector<String>> solveHidato() {
        if (tempBoard.solveHidato()) {
            Vector<Vector<String>> matrix = currentHidato.getHidato();
            Map<Integer, Integer> cellPositionsProposal = new HashMap<>();
            cellPositionsProposal = tempBoard.getCellPositionsProposalResult();
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

    public String playHidato() {
        //si es fa playHidato, l'usuari accepta el taulell i es comença la partida
        //POL: no sé si necessites que retorni alguna cosa
        board = tempBoard;
        game.setBoard(currentHidato);
        game.defineGame(currentHidato);
        game.startGame();
        game.addMovement(currentHidato.copy());
        String dificulty = game.getDifficulty();
        return dificulty;
    }

    public Character nextMovement(int idCell, String nextValue) {
        //retorno 'C' per completat
        //'W' per erroni
        //'O' per okey
        Vector<Vector<String>> matrix = currentHidato.getHidato();
        Vector<String> vec = matrix.get(idCell/currentHidato.getColumns());
        vec.set(idCell % currentHidato.getColumns(), nextValue);
        //game.addMovement(currentHidato);
        board.changeCellPositions(Integer.parseInt(nextValue), idCell);
        board.changeVectorCell(idCell, Integer.parseInt(nextValue));
        if (board.solveHidato()){
            currentHidato.setHidato(matrix);
            game.addMovement(currentHidato.copy());
            if (board.lastMovement()) {
                Time endTime = new Time(System.currentTimeMillis());
                int scoretime = game.getScore();
                scoretime += getTimeDifference(endTime);
                game.setScore(scoretime);
                return 'C';
            }
            else return 'O';
        }
        else {
            if(game.getDifficulty().equals("Easy")){
                board.changeCellPositions(Integer.parseInt(nextValue), -1);
                board.changeVectorCell(idCell, -1);
            }
            else {
                currentHidato.setHidato(matrix);
                game.addMovement(currentHidato.copy());
            }
            return 'W';
        }

    }

    public int getTimeDifference (Time endTime) {
        int timeStart;
        int timeEnd;
        int seconds;
        int hoursEnd = endTime.getHours();
        int hoursStart = game.getTempsinici().getHours();
        int minutesEnd = endTime.getMinutes();
        int minutesStart = game.getTempsinici().getMinutes();
        int secondsEnd = endTime.getSeconds();
        int secondsStart = game.getTempsinici().getSeconds();
        if (hoursStart > hoursEnd) hoursEnd += 24;
        timeStart = hoursStart*3600 + minutesStart*60 +secondsStart;
        timeEnd = hoursEnd*3600 + minutesEnd*60 +secondsEnd;
        seconds = timeEnd - timeStart;
        return seconds;
    }
    public Pair<Integer, String> Hint(){ //Joan
        game.incrementTime(10);
        Pair<Integer, String> nextMove;
        if (board.solveHidato()) {
            nextMove = board.getHint();
            //nextMovement(nextMove.getKey(), nextMove.getValue());
        }
        else nextMove = new Pair<Integer, String>(-1, "-1");
        return nextMove;
    }

    public Hidato rollbackMovement() { //Paula
        game.deleteLastMovement();
        Map<Integer, Hidato> allmoves = game.getMovements();
        int lastMove = game.getLastMove();
        Hidato result = allmoves.get(lastMove);
        defineHidato(result.copy());
        board = tempBoard;
        return result;
    }


    public int saveGame(String path) { //Paula
        Time endTime = new Time(System.currentTimeMillis());
        int scoretime = game.getScore();
        scoretime += getTimeDifference(endTime);
        game.setScore(scoretime);
        return datacontrol.writeGame(path+".partida", game);
    }

    public Hidato loadGame(String Path){ //Paula //TODO revisar
        //es retorna l'Hidato de l'últim moviment
        game = datacontrol.getGame(Path);
        Map<Integer, Hidato> allmoves = game.getMovements();
        int lastMove = game.getLastMove();
        Hidato result = allmoves.get(lastMove);
        //TODO: temps inici
        defineHidato(result);
        Time iniTime = new Time(System.currentTimeMillis());
        game.setTempsInici(iniTime);
        playHidato();
        return result;
    }

    public Vector<Vector<String>> rebootGame() { //Paula
        //retorna la matriu inicial i es reinicia tot
        Map<Integer, Hidato> allmoves = game.getMovements();
        currentHidato = allmoves.get(1);
        game.rebootMovements();
        Vector<Vector<String>> hidatoLoaded = defineHidato(currentHidato);
        playHidato();
        return hidatoLoaded;
    }

    public Ranking loadRanking(String tipus) {
        switch (tipus) {
            case "Easy":
                return rankingeasy;
            case "Medium":
                return rankingmedium;
            case "Hard":
                return rankinghard;
        }
        return null;
    }

    public void addToRanking() { //guarda en estructures internes
        String username = game.getPlayer().getId();
        switch (game.getDifficulty()) {
            case "Easy":
                rankingeasy.addToRanking(username, game.getScore());
                break;
            case "Medium":
                rankingmedium.addToRanking(username, game.getScore());
                break;
            case "Hard":
                rankinghard.addToRanking(username, game.getScore());
                break;
        }
    }
    public void saveRanking() { //guardar en dades el fitxer
        String tipus = game.getDifficulty();
        switch (tipus) {
            case "Easy":
                datacontrol.setRanking("Easy", rankingeasy);
                break;
            case "Medium":
                datacontrol.setRanking("Medium", rankingmedium);
                break;
            case "Hard":
                datacontrol.setRanking("Hard", rankinghard);
                break;
        }
    }
    public int firstEmptyNumber(){
        //si retorna 0 és que no ha trobat cap nombre buit, sinó retorna el nombre.
        return board.getFirstEmptyNumber();
    }

    public int finalTime(){
        return game.getScore();
    }

    public Hidato getCurrentHidato() {
        return currentHidato;
    }

    public String getDifficult(){
        return game.getDifficulty();
    }

    public int getCurrentTime () {
        Time endTime = new Time(System.currentTimeMillis());
        int scoretime = game.getScore();
        scoretime += getTimeDifference(endTime);
        return scoretime;
    }

}
