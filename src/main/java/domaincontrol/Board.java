package domaincontrol;

import java.util.*;
import java.time.*;


public abstract class Board {
    //Inner structures
    protected Map<Integer, Integer> cellPositions;
    protected Vector<Cell> vectorCell;
    protected Map<Integer, ArrayList<Integer>> adjacencyMatrix;
    protected Map<Integer, Integer> cellPositionsProposalResult;

    //algorithm structures
    private static Integer MAXGENERATIONTRIES;
    protected Integer generationTries;
    private Instant first;
    private double TIMETOSTOP;

    //board generator variables
    protected Integer counter;

    //private libraries
    protected Utilities utils;


    public Board() {
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        adjacencyMatrix = new HashMap<>();
        cellPositionsProposalResult = new HashMap<>();
        generationTries = 0;
        utils = Utilities.getUtilities();
    }

    public void createBoard(Vector<Vector<String>> matrix, String adjacency) {
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;
        calculateAdjacencyMatrix(matrix, adjacency);
        setGlobalVariables(vectorCell.size());
    }


    //Getters and setters
    public void setCellPositions(Map<Integer, Integer> cellPositionsExterior) {
        cellPositions = cellPositionsExterior;
    }

    public Map<Integer, Integer> getCellPositionsProposalResult() {
        return cellPositionsProposalResult;
    }

    public void setVectorCell(Vector<Cell> vectorCellExterior) {
        vectorCell = vectorCellExterior;
    }

    public Vector<Cell> getVectorCell() {
        return vectorCell;
    }

    public void setAdjacencyMatrix(Map<Integer, ArrayList<Integer>> adjacencyMatrixExterior) { adjacencyMatrix = adjacencyMatrixExterior; }

    public Map<Integer, ArrayList<Integer>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }


    //solver algorithm

    public boolean solveHidato(){
        if(previousConditions())return Solver();
        else return false;
    }

    private boolean previousConditions() {  //Check some of hamiltonian graphs conditions to know if there is a possible path
        boolean first = false;
        int how_many_1 = 0;
        Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = adjacencyMatrix.entrySet().iterator();
        while (it.hasNext() && (how_many_1 < 3 || ((how_many_1 == 2) && (first == true)))){
            Map.Entry<Integer, ArrayList<Integer>> pair = it.next();
            if (pair.getKey().equals(1)) {first = true;}
            if (pair.getValue().size()== 1){++how_many_1;}
        }
        if (how_many_1 < 3 || ((how_many_1 == 2) && (first == true))) return true;
        if (adjacencyMatrix.get(1).size() == 0) return false;
        else return false;
    }

    private boolean Solver(){
        Boolean[] already_visited = new Boolean[vectorCell.size()]; //vector of visited cells
        Arrays.fill(already_visited, Boolean.FALSE); //filled with false
        int position_cell_id_1 = cellPositions.get(1); //id de la celda del número 1
        Map<Integer, Integer> cellPositionsRecursive = cellPositions;
        first = Instant.now();
        return recursiveSolver(position_cell_id_1, already_visited, 1, cellPositionsRecursive);

    }

    private boolean recursiveSolver(Integer cell_c0, Boolean[] already_visited_LevelUp, int number_c0, Map<Integer, Integer> cellPositionsLevelUp) {
        if (recursionStop()) return false;
        Map<Integer, Integer> cellPositionsRecursive = utils.copyMap(cellPositionsLevelUp);
        Boolean[] already_visited = utils.copyBoolean(already_visited_LevelUp);
        already_visited[cell_c0] = Boolean.TRUE;
        boolean route_found = false;
        ArrayList<Integer> adjacencies_cell_c0 = adjacencyMatrix.get(cell_c0); //id cell_c0 neighbours
        int position_cell_c1 = -1;
        if (number_c0 == cellPositionsRecursive.size()) {
            boolean checking = checkAllNumbersFull(cellPositionsRecursive);
            if (checking) route_found = true;
        }
        if (cellPositionsRecursive.containsKey(number_c0+1)) { //el número n+1 té celda assignada
            position_cell_c1 = cellPositionsRecursive.get(number_c0 + 1);
        }
        if (position_cell_c1 != -1){
            boolean found_cell_c1 = false;
            Iterator<Integer> iterator = adjacencies_cell_c0.iterator();
            while (!found_cell_c1 && iterator.hasNext()) {
                int next_value = iterator.next();
                if (next_value == position_cell_c1) {
                    found_cell_c1 = true;
                    route_found = recursiveSolver(next_value, already_visited, number_c0 + 1, cellPositionsRecursive);
                }
            }
            if (!found_cell_c1) return false;
        }
        else { // el número n+1 no té assignada cap celda
            Iterator<Integer> iterator = adjacencies_cell_c0.iterator(); //adjacencies c0
            boolean branchCut = false;
            while (!route_found && !branchCut && iterator.hasNext()) { //recorrent possibles c1
                int next_cell_c1 = iterator.next();
                if ((vectorCell.elementAt(next_cell_c1).getNumber() != -1) && (vectorCell.elementAt(next_cell_c1).getNumber() != number_c0 + 1)) {
                    already_visited[next_cell_c1] = true;
                }
                if(!already_visited[next_cell_c1]){
                    ArrayList<Integer> adjacencies_cell_c1 = adjacencyMatrix.get(next_cell_c1); //id cell_c1 neighbours
                    Iterator<Integer> iteratorToC2 = adjacencies_cell_c1.iterator(); //adjacencies c1
                    boolean someCellValid = false;
                    while (!someCellValid && iteratorToC2.hasNext()) {
                        int next_cell_c2 = iteratorToC2.next();
                        if (!already_visited[next_cell_c2] && ((vectorCell.elementAt(next_cell_c2).getNumber() == number_c0 + 2) || (vectorCell.elementAt(next_cell_c2).getNumber() == -1))) {
                            if (vectorCell.elementAt(next_cell_c1).getNumber() != number_c0 + 1 || vectorCell.elementAt(next_cell_c1).getNumber() == -1) {
                                someCellValid = true;
                            }
                        }
                    }
                    if (someCellValid) {
                        cellPositionsRecursive.put(number_c0 + 1, next_cell_c1);
                        route_found = recursiveSolver(next_cell_c1, already_visited, number_c0 + 1, cellPositionsRecursive);
                    } else {
                        cellPositionsRecursive.put(number_c0 + 1, next_cell_c1);
                        boolean checking = checkAllNumbersFull(cellPositionsRecursive);
                        if (checking) {
                            route_found = true;
                        }
                        else branchCut = true;
                    }
                }
            }
        }
        //Todo tractament quan el hidato només té una celda
        return route_found;
    }

    private boolean checkAllNumbersFull(Map<Integer, Integer> cellPositionsRecursive) {
        Iterator<Map.Entry<Integer, Integer>> iterator = cellPositionsRecursive.entrySet().iterator();
        Integer lastValue = -1;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> nextValue = iterator.next();
            if (nextValue.getKey() == 1) {
                lastValue = nextValue.getValue();
            }
            if (nextValue.getValue() == -1) return false;
            else if (nextValue.getKey() != 1) {
                ArrayList<Integer> adjacencies_numbers = adjacencyMatrix.get(nextValue.getValue());

                if (!adjacencies_numbers.contains(lastValue)) return false;
                lastValue = nextValue.getValue();
            }
        }
        cellPositionsProposalResult = utils.copyMap(cellPositionsRecursive);
        return true;
    }


    private boolean recursionStop(){
        Instant second = Instant.now();
        Duration duration = Duration.between(first, second);
        return duration.getSeconds() > TIMETOSTOP;
    }

    //board setup
    public abstract void calculateAdjacencyMatrix(Vector<Vector<String>> matrix, String adjcency);

    public void completeCellPositions(String value, Integer actual) {
        if (!value.equals("#") && !value.equals("*")) {
            if (value.equals("?")) ++counter;
            else cellPositions.put(Integer.parseInt(value), actual);
        }
    }

    public void fillCellPositions() {
        Integer total = cellPositions.size() + counter;
        for (int i = 1; i <= total; ++i) {
            if (!cellPositions.containsKey(i)) cellPositions.put(i, -1);
        }
        Map<Integer, Integer> sorted = new TreeMap<Integer, Integer>(cellPositions);
        cellPositions = sorted;
    }

    public void insertCell(int id, String value) {
        Cell c;
        switch (value) {
            case "#":
                c = new Cell(id, false, -3);
                break;

            case "*":
                c = new Cell(id, false, -2);
                break;

            case "?":
                c = new Cell(id, true, -1);
                break;

            default:
                c = new Cell(id, true, Integer.valueOf(value));
                break;
        }
        vectorCell.add(c);
    }

    public boolean accesible(String value) {
        return (!value.equals("#") && !value.equals("*"));
    }


    //generate board
    public int generateHidato(Vector<Vector<String>> matrix, int maxColumns, String adjacency, int holes, int toshow) {
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;
        calculateAdjacencyMatrix(matrix, adjacency);

        int numberCells = matrix.size()*maxColumns;

        setGlobalVariables(numberCells);

        //placing element 1
        int resultCode = setElement1(matrix, maxColumns);
        if (resultCode == 0) return 0;


        //remove up to "holes"
        int holesSet = 0;
        removeLastHoles(holes, holesSet, toshow);
        return 1;
    }

    private void removeLastHoles(int holes, int holesSet, int toShow) {
        Vector<Integer> lastPositions = new Vector<>();
        if (holesSet < holes) {
            lastPositions = utils.MapToVector(cellPositionsProposalResult);
        }
        while (holesSet < holes) {
            int size = cellPositionsProposalResult.size();
            int cellVector = lastPositions.elementAt(size - 1); //no está accediendo a la posición última, sino a la key size
            Cell temporalCell = vectorCell.get(cellVector);
            temporalCell.setAccessible(false);
            temporalCell.setNumber(-2);
            cellPositionsProposalResult.remove(size);
            lastPositions.remove(lastPositions.size() - 1);
            ++holesSet;
        }

        ShowOnlyAskedFilledPositions(toShow);
    }

    private void ShowOnlyAskedFilledPositions(int toShow) {
        Vector<Integer> toErase = new Vector<>();
        for (int i = 2; i <= cellPositionsProposalResult.size(); ++i) {
            toErase.add(i);
        }
        Collections.shuffle(toErase);
        for (int i = toShow - 1; i < toErase.size(); ++i) {
            cellPositionsProposalResult.replace(toErase.elementAt(i), -1);
        }
        for (int i = 0; i < toShow - 1; ++i) {
            Cell temporalCell = vectorCell.get(cellPositionsProposalResult.get(toErase.elementAt(i)));
            temporalCell.setAccessible(true);
            temporalCell.setNumber(toErase.elementAt(i));
        }
    }

    private Integer setElement1(Vector<Vector<String>> matrix, int maxColumns) {
        boolean onePlaced = false;
        int idCell;
        while (!onePlaced) {
            ++generationTries;
            if (generationTries > MAXGENERATIONTRIES) return 0;

            idCell = utils.getRandomNumber(0, (matrix.size() * maxColumns) - 1);
            if (vectorCell.elementAt(idCell).getNumber() == -1) {
                changeVectorCell(idCell,1);
                changeCellPositions(1,idCell);
                if (solveHidato()){
                    onePlaced = true;
                }
                else {
                    changeVectorCell(idCell,-1);
                    changeCellPositions(1,-1);
                    onePlaced = false;
                }
            }
        }
        return 1;
    }


    //global functions
    private void changeCellPositions(int positionSource, int idCell) {
        cellPositions.replace(positionSource, idCell);
    }

    private void changeVectorCell(int idCell, int i) {
        Cell temporalCell = vectorCell.get(idCell);
        temporalCell.setNumber(i);
    }

    private void setGlobalVariables(int numberCells) {
        if (numberCells < 10) {
            MAXGENERATIONTRIES = 30;
            TIMETOSTOP = 0.5;
        }
        else if (numberCells > 10 && numberCells < 50) {
            MAXGENERATIONTRIES = 20;
            TIMETOSTOP = 0.75;
        }
        else {
            MAXGENERATIONTRIES = 12;
            TIMETOSTOP = 1;
        }
    }


}
