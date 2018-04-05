package domaincontrol;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class Board {
    Map<Integer, Integer> cellPositions;
    Vector<Cell> vectorCell;
    Map<Integer, Vector<Integer> > adjacecieMatrix;

    public boolean solveHidato(){
        Boolean[] already_visited = new Boolean[vectorCell.size()];
        Arrays.fill(already_visited, Boolean.FALSE);
        Stack<Integer> stack = null;
        stack.push(cellPositions.get(1));
        while (!stack.empty()){
            int next_move = stack.pop();
            if (!already_visited[next_move]){
                already_visited[next_move] = true;
                Vector<Integer> edges = adjacecieMatrix.get(next_move);
                for (int iterator = 0; iterator < edges.size(); ++iterator ){
                    stack.push(edges.get(iterator));
                }
            }

        }
        return true;
    }
}
