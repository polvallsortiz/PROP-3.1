package domaincontrol;

import java.util.Vector;

public class DomainCtrl {
    public void defineBoard(Vector<Vector<String>> matrix, String username, String adjacency, Character celltype) {
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
        b.createBoard(matrix,adjacency);
    }
}
