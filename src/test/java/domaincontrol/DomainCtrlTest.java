package domaincontrol;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by Joan on 16/05/2018.
 */
public class DomainCtrlTest {
    DomainCtrl dc;
    @Before
    public void setUp() throws Exception {
        dc = new DomainCtrl();
    }

    @Test
    public void test1(){
        dc.newGame("Joan");
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("1","?","?","#")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("C");
        hidato.setCelltype('Q');
        hidato.setLines(4);
        hidato.setColumns(4);
        Vector<Vector<String>> actualMatrix = dc.defineHidato(hidato);
        String dificulty = dc.playHidato();
        Character nextMov = dc.nextMovement(8, "2");
        nextMov = dc.nextMovement(4,"3");
        nextMov = dc.nextMovement(5,"4");

    }

    @Test
    public void test2(){
        dc.newGame("Joan");
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(3);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("1","?","?")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("C");
        hidato.setCelltype('Q');
        hidato.setLines(3);
        hidato.setColumns(3);
        Vector<Vector<String>> actualMatrix = dc.defineHidato(hidato);
        String dificulty = dc.playHidato();
        Character nextMov = dc.nextMovement(3, "2");
        nextMov = dc.nextMovement(0,"3");
        nextMov = dc.nextMovement(1,"4");
        nextMov = dc.nextMovement(4,"5");
        nextMov = dc.nextMovement(7,"6");
        nextMov = dc.nextMovement(8,"7");
        nextMov = dc.nextMovement(5,"8");
        nextMov = dc.nextMovement(2,"9");

    }

    @Test
    public void test3(){
        dc.newGame("Joan");
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","1")));
        matrix.add(2, new Vector<String>(Arrays.asList("11","?","?","*")));
        matrix.add(3, new Vector<String>(Arrays.asList("12","?","?","*")));
        Hidato hidato = new Hidato();
        hidato.setHidato(matrix);
        hidato.setAdjacencytype("CA");
        hidato.setCelltype('Q');
        hidato.setLines(4);
        hidato.setColumns(4);
        Vector<Vector<String>> actualMatrix = dc.defineHidato(hidato);
        String dificulty = dc.playHidato();
        Character nextMov = dc.nextMovement(3, "2");
        nextMov = dc.nextMovement(0,"3");
        nextMov = dc.nextMovement(1,"4");
        nextMov = dc.nextMovement(4,"5");
        nextMov = dc.nextMovement(7,"6");
        nextMov = dc.nextMovement(8,"7");
        nextMov = dc.nextMovement(5,"8");
        nextMov = dc.nextMovement(2,"9");

    }

}