package domaincontrol;

import java.util.*;

public class Utilities {

    public Map<Integer, Integer> copyMap(Map<Integer, Integer> cellPositionsLevelUp) {
        Map<Integer, Integer> result = new HashMap<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = cellPositionsLevelUp.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> nextTemporal = iterator.next();
            result.put(nextTemporal.getKey(), nextTemporal.getValue());
        }
        return result;
    }

    public Boolean[] copyBoolean(Boolean[] already_visited_levelUp) {
        Boolean[] already_visited = new Boolean[already_visited_levelUp.length];
        for(int i = 0; i < already_visited.length; ++i){
            already_visited[i] = already_visited_levelUp[i];
        }
        return already_visited;
    }

    public void printAdjacencyMatrix(Map<Integer, ArrayList<Integer>> adjacencyMatrix) {
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

    public void printCellPositions(Map<Integer, Integer> cellPositions) {
        System.out.print("PRINT OF ALL CELL POSITIONS\n");
        Integer aux;
        for(Integer key : cellPositions.keySet()) {
            aux = cellPositions.get(key);
            System.out.print(key + " : " + aux + "\n");
        }
    }

    public void printCells(Vector<Cell> vectorCell) {
        System.out.print("///PRINT CELLS/// \n");
        for(Cell c : vectorCell) {
            c.printCell();
        }
    }

    public Vector<Vector<String>> copyMatrix(Vector<Vector<String>> matrix) {
        Vector<Vector<String>> result = new Vector<Vector<String>> (matrix.size());
        for (int i = 0; i < matrix.size(); ++i){
            result.add(i, matrix.elementAt(i));
        }
        return result;
    }


    public int getRandomNumber(Integer minValue, Integer maxValue){
        Random r = new Random();
        return r.nextInt(maxValue-minValue) + minValue;
    }

    public Vector<Integer> MapToVector (Map<Integer, Integer> cellPositionsProposalResult){
        Vector<Integer> result = new Vector<>();
        for (Integer key : cellPositionsProposalResult.keySet()) {
            result.add(cellPositionsProposalResult.get(key));
        }
        return result;
    }
}
