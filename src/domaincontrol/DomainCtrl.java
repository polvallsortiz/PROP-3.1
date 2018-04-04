package domaincontrol;

import java.util.Vector;

public class DomainCtrl {
    public void defineBoard(Vector<Vector<String>> matrix, String username) {
        Board b = new Board();
        b.createBoard(matrix);
    }
}
