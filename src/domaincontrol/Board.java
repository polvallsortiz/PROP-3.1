package domaincontrol;

import java.util.*;

public abstract class Board {
    private static final Integer MAXGENERATIONTRIES = 5;
    protected Map<Integer, Integer> cellPositions;
    protected Vector<Cell> vectorCell;
    protected Map<Integer, ArrayList<Integer>> adjacencyMatrix;
    protected Integer counter;
    protected Integer generationTries;

    protected Map<Integer, Integer> cellPositionsProposalResult;

    public Board(){
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        adjacencyMatrix = new HashMap<>();
        cellPositionsProposalResult = new HashMap<>();
    }

    public void createBoard(Vector<Vector<String>> matrix,String adjacency) {
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;
        calculateAdjacencyMatrix(matrix,adjacency);
        printAdjacencyMatrix();
        printCellPositions();
        printCells();
    }

    public void setCellPositions(Map<Integer, Integer> cellPositionsExterior){
        cellPositions = cellPositionsExterior;
    }

    public void setVectorCell(Vector<Cell> vectorCellExterior){
        vectorCell = vectorCellExterior;
    }

    public void setAdjacencyMatrix(Map<Integer, ArrayList<Integer>> adjacencyMatrixExterior){
        adjacencyMatrix = adjacencyMatrixExterior;
    }
    public Map<Integer, ArrayList<Integer>> getAdjacencyMatrix(){
        return adjacencyMatrix;
    }

    public Map<Integer, Integer> getCellPositionsProposalResult() {
        return cellPositionsProposalResult;
    }

    public Vector<Cell> getVectorCell() {return vectorCell;}

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
        return recursiveSolver(position_cell_id_1, already_visited, 1, cellPositionsRecursive);

    }

    private boolean recursiveSolver(Integer cell_c0, Boolean[] already_visited_LevelUp, int number_c0, Map<Integer, Integer> cellPositionsLevelUp) {
        Map<Integer, Integer> cellPositionsRecursive = copyMap(cellPositionsLevelUp);
        Boolean[] already_visited = copyBoolean(already_visited_LevelUp);
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
            if (found_cell_c1 == false) return false;
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
                        //TODO vigilar los valores que pueden ser invalidos a aprte del -1
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

    private Boolean[] copyBoolean(Boolean[] already_visited_levelUp) {
        Boolean[] already_visited = new Boolean[already_visited_levelUp.length];
        for(int i = 0; i < already_visited.length; ++i){
            already_visited[i] = already_visited_levelUp[i];
        }
        return already_visited;
    }

    private Map<Integer, Integer> copyMap(Map<Integer, Integer> cellPositionsLevelUp) {
        Map<Integer, Integer> result = new HashMap<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = cellPositionsLevelUp.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> nextTemporal = iterator.next();
            result.put(nextTemporal.getKey(), nextTemporal.getValue());
        }
        return result;
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
        cellPositionsProposalResult = copyMap(cellPositionsRecursive);
        return true;
    }

    public abstract void calculateAdjacencyMatrix(Vector<Vector<String>> matrix,String adjcency);

    public void printAdjacencyMatrix() {
        ArrayList<Integer> aux;
        for(Integer key : adjacencyMatrix.keySet()){
            aux = adjacencyMatrix.get(key);
            System.out.print("\n" + key + " : ");
            for(Integer i : aux) {
                System.out.print(i + ",");
            }
            System.out.print("\n");
        }
    }

    public void completeCellPositions(String value, Integer actual) {
        if(!value.equals("#") && !value.equals("*")) {
            if(value.equals("?")) ++counter;
            else cellPositions.put(Integer.parseInt(value),actual);
        }
    }

    public void fillCellPositions() {
        Integer total = cellPositions.size() + counter;
        for(int i = 1; i <= total; ++i) {
            if(!cellPositions.containsKey(i)) cellPositions.put(i,-1);
        }
        Map<Integer,Integer> sorted = new TreeMap<Integer, Integer>(cellPositions);
        cellPositions = sorted;
    }

    public void printCellPositions() {
        System.out.print("PRINT OF ALL CELL POSITIONS\n");
        Integer aux;
        for(Integer key : cellPositions.keySet()) {
            aux = cellPositions.get(key);
            System.out.print(key + " : " + aux + "\n");
        }
    }

    public void printCells() {
        System.out.print("///PRINT CELLS/// \n");
        for(Cell c : vectorCell) {
            c.printCell();
        }
    }

    public void insertCell(int id, String value) {
        Cell c;
        switch (value) {
            case "#" :
                c = new Cell(id,false,-3);
                break;

            case "*" :
                c = new Cell(id,false,-2);
                break;

            case "?" :
                c = new Cell(id,true,-1);
                break;

            default:
                c = new Cell(id,true,Integer.valueOf(value));
                break;
        }
        vectorCell.add(c);
    }

    public boolean accesible(String value) {
        return (!value.equals("#") && !value.equals("*"));
    }


    /*public void generateHidato(Vector<Vector<String>> matrix, int maxColumns, String adjacency, int holes, int toshow) {
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;

        //placing element 1
        Random r = setElement1(matrix, maxColumns, adjacency);

        //placing maxHolesToSet holes
        int holesSet = setMaxHoles(matrix, maxColumns, adjacency, holes, r, toshow);

        //remove up to "holes"
        removeLastHoles(holes, holesSet, toshow);

    }*/

    public int generateHidato(Vector<Vector<String>> matrix, int maxColumns, String adjacency, int holes, int toshow) {
        ++generationTries;
        if (generationTries > MAXGENERATIONTRIES)return 0;
        adjacencyMatrix = new HashMap<>();
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        counter = 0;

        //placing element 1
        Random r = setElement1(matrix, maxColumns, adjacency);

        //placing maxHolesToSet holes
        int holesSet = setMaxHoles(matrix, maxColumns, adjacency, holes, r, toshow);

        //remove up to "holes"
        removeLastHoles(holes, holesSet, toshow);
        return 1;
    }

    private void removeLastHoles(int holes, int holesSet, int toShow) {
        Vector<Integer> lastPositions = new Vector<>();
        if (holesSet < holes) {
             lastPositions = MapToVector(cellPositionsProposalResult);
        }
        while(holesSet < holes){
            int size = cellPositionsProposalResult.size();
            int cellVector = lastPositions.elementAt(size-1); //no está accediendo a la posición última, sino a la key size
            Cell temporalCell = vectorCell.get(cellVector);
            temporalCell.setAccessible(false);
            temporalCell.setNumber(-2);
            cellPositionsProposalResult.remove(size);
            lastPositions.remove(lastPositions.size()-1);
            ++holesSet;
        }

        ShowOnlyAskedFilledPositions(toShow);
    }

    private void ShowOnlyAskedFilledPositions(int toShow) {
        Vector<Integer> toErase = new Vector<>();
        for (int i = 2; i <= cellPositionsProposalResult.size(); ++i){
            toErase.add(i);
        }
        Collections.shuffle(toErase);
        for (int i=toShow-1; i < toErase.size(); ++i){
            cellPositionsProposalResult.replace(toErase.elementAt(i), -1);
        }
        for (int i = 0; i < toShow-1; ++i){
            Cell temporalCell = vectorCell.get(cellPositionsProposalResult.get(toErase.elementAt(i)));
            temporalCell.setAccessible(true);
            temporalCell.setNumber(toErase.elementAt(i));
        }
    }

    private int setMaxHoles(Vector<Vector<String>> matrix, int maxColumns, String adjacency, int holes, Random r, int toshow) {
        int maxHolesToSet = 2; // this variable can be changed depending on performance limitations
        int holesSet = 0;
        int tries = 10;
        Vector<Vector<String>> matrixTemporal = copyMatrix(matrix);
        while (holes > 0 && holesSet < Math.min(holes, maxHolesToSet)) {
            int Result = getRandomResult(matrix, r);
            int Result2 = getRandomResultColumns(maxColumns, r);
            if (matrixTemporal.elementAt(Result).elementAt(Result2) == "?") {
                Vector<String> temp = matrixTemporal.get(Result);
                temp.set(Result2, "*");
                adjacencyMatrix = new HashMap<>();
                cellPositions = new HashMap<>();
                vectorCell = new Vector<>();
                counter = 0;
                calculateAdjacencyMatrix(matrix, adjacency);
                if (solveHidato()) ++holesSet;
                else {
                    if (tries > 0){
                        --tries;
                        temp.set(Result2, "?");
                    }
                    else generateHidato(matrix, maxColumns, adjacency, holes, toshow); //emergency call when the algorithm is unable to generate a path with the given board. We restart all the process and set a new position for the first element
                }
            }
        }
        if (holes == 0) solveHidato();
        return holesSet;
    }

    private Random setElement1(Vector<Vector<String>> matrix, int maxColumns, String adjacency) {
        boolean onePlaced = false;
        Random r = new Random();
        while (!onePlaced) {
            int Result = getRandomResult(matrix, r);
            int Result2 = getRandomResultColumns(maxColumns, r);
            if (matrix.elementAt(Result).elementAt(Result2) == "?") {
                onePlaced = true;
                Vector<String> temp = matrix.get(Result);
                temp.set(Result2, "1");
            }
        }
        calculateAdjacencyMatrix(matrix, adjacency);
        return r;
    }

    private Vector<Vector<String>> copyMatrix(Vector<Vector<String>> matrix) {
        Vector<Vector<String>> result = new Vector<Vector<String>> (matrix.size());
        for (int i = 0; i < matrix.size(); ++i){
            result.add(i, matrix.elementAt(i));
        }
        return result;
    }

    private int getRandomResultColumns(int maxColumns, Random r) {
        int LowColumn = 0;
        int HighColumn = maxColumns-1;
        return r.nextInt(HighColumn-LowColumn) + LowColumn;
    }

    private int getRandomResult(Vector<Vector<String>> matrix, Random r) {
        int LowRow = 0;
        int HighRow = matrix.size();
        return r.nextInt(HighRow-LowRow) + LowRow;
    }

    private Vector<Integer> MapToVector (Map<Integer, Integer> cellPositionsProposalResult){
        Vector<Integer> result = new Vector<>();
        for (Integer key : cellPositionsProposalResult.keySet()) {
            result.add(cellPositionsProposalResult.get(key));
        }
        return result;
    }

}
