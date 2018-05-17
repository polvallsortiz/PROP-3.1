package domaincontrol;

import java.util.Vector;

public class Hidato {
    private String nom;
    private Vector<Vector<String>>  hidato;
    private Character celltype;
    private String adjacencytype;
    private int lines;
    private int columns;

    public Hidato() {
    }
    public Hidato(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setHidato(Vector<Vector<String>> hidato) {
        this.hidato = hidato;
    }

    public Vector<Vector<String>> getHidato() {
        Vector<Vector<String>> mat = new Vector<>();
        for(int i = 0; i < lines; ++i) {
            Vector<String> aux = new Vector<>();
            for(int j = 0; j < columns; ++j) {
                aux.add(hidato.get(i).get(j));
            }
            mat.add(aux);
        }
        return mat;
    }

    public void setCelltype(Character celltype) {
        this.celltype = celltype;
    }

    public Character getCelltype() {
        return this.celltype;
    }

    public void setAdjacencytype(String adjacencytype) {
        this.adjacencytype = adjacencytype;
    }

    public String getAdjacencytype() {
        return adjacencytype;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getLines() {
        return lines;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }
}
