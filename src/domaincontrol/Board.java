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
        if(previousConditions())return recursiveSolver();
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
        //if (adjacencyMatrix.get(1).size() == 0) return false;
        else return false;
    }
/*
    void dfs_rec (const graph& G, int u, vector<boolean>& vis, list<int>& L) {
        if (not vis[u]) {
            vis[u] = true; L.push_back(u);
            for (int v : G[u]) {
                dfs_rec(G, v, vis, L);
            } } }

    list<int> dfs_rec (const graph& G) {
        int n = G.size();
        list<int> L;
        vector<boolean> vis(n, false);
        for (int u = 0; u < n; ++u) {
            dfs_rec(G, u, vis, L);
        }
        return L;
    }
    */
    private boolean recursiveSolver(){
        Boolean[] already_visited = new Boolean[vectorCell.size()];
        Arrays.fill(already_visited, Boolean.FALSE);
        return true;
    }

    private boolean Solver() { //Solves the Hidato recursively
        Boolean[] already_visited = new Boolean[vectorCell.size()];
        Arrays.fill(already_visited, Boolean.FALSE);
        Stack<Integer> stack = null;
        stack.push(cellPositions.get(1));
        while (!stack.empty()){
            int next_move = stack.pop();
            if (!already_visited[next_move]){
                already_visited[next_move] = true;
                ArrayList<Integer> edges = adjacencyMatrix.get(next_move);
                for (int iterator = 0; iterator < edges.size(); ++iterator ){
                    stack.push(edges.get(iterator));
                }
            }

        }
        return true;
    }
}
