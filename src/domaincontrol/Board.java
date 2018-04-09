package domaincontrol;

import java.util.*;

public class Board {
    private Map<Integer, Integer> cellPositions;
    private Vector<Cell> vectorCell;
    private Map<Integer, ArrayList<Integer>> adjacencyMatrix;

    public Board(){
        cellPositions = new HashMap<>();
        vectorCell = new Vector<>();
        adjacencyMatrix = new HashMap<>();
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
        Boolean[] already_visited = new Boolean[vectorCell.size()+1]; //vector of visited cells
        Arrays.fill(already_visited, Boolean.FALSE); //filled with false
        int position_cell_id_1 = cellPositions.get(1); //id de la celda del número 1
        Map<Integer, Integer> cellPositionsRecursive = cellPositions;
        return recursiveSolver(position_cell_id_1, already_visited, 1, cellPositionsRecursive);

    }

    private boolean recursiveSolver(Integer cell_c0, Boolean[] already_visited, int number_c0, Map<Integer, Integer> cellPositionsRecursive) {
        already_visited[cell_c0] = Boolean.TRUE;
        boolean route_found = false;
        ArrayList<Integer> adjacencies_cell_c0 = adjacencyMatrix.get(cell_c0); //id cell_c0 neighbours
        int position_cell_c1 = -1;
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
            //boolean next_cell_c0 = false;
            boolean branchCut = false;
            while (!route_found && !branchCut && iterator.hasNext()) { //recorrent possibles c1
                int next_cell_c1 = iterator.next();
                if(!already_visited[next_cell_c1]){
                    ArrayList<Integer> adjacencies_cell_c1 = adjacencyMatrix.get(next_cell_c1); //id cell_c1 neighbours
                    Iterator<Integer> iteratorToC2 = adjacencies_cell_c1.iterator(); //adjacencies c1
                    boolean someCellValid = false;
                    while (!someCellValid && iteratorToC2.hasNext()) {
                        int next_cell_c2 = iteratorToC2.next();
                        if (!already_visited[next_cell_c2] && ((vectorCell.elementAt(next_cell_c2).getNumber() == number_c0 + 2) || cellPositionsRecursive.get(next_cell_c2) == -1)) {
                            someCellValid = true;
                        }
                    }
                    if (someCellValid) {
                        cellPositionsRecursive.put(number_c0 + 1, next_cell_c1);
                        //next_cell_c0 = true;
                        route_found = recursiveSolver(next_cell_c1, already_visited, number_c0 + 1, cellPositionsRecursive);
                    } else {
                        cellPositionsRecursive.put(number_c0 + 1, next_cell_c1);
                        boolean checking = checkAllNumbersFull(cellPositionsRecursive);
                        if (checking) route_found = true;
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
        while (iterator.hasNext()){
            if (iterator.next().getValue() == -1) return false;
        }
        return true;
    }
    }
