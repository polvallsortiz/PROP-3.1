package domaincontrol;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by Joan on 16/05/2018.
 */
public class GameTest {
    Game g;
    @Before
    public void setUp(){
        g = new Game();
    }
    @Test
    public void defineGame() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(4);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","5","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#")));
        hidato.setHidato(matrix);
        hidato.setColumns(4);
        hidato.setLines(4);
        assertEquals("Easy", g.defineGame(hidato));
    }

    @Test
    public void defineGame2() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(5);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","5","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#","?")));
        matrix.add(4, new Vector<String>(Arrays.asList("?","?","?","?","?")));
        hidato.setHidato(matrix);
        hidato.setColumns(5);
        hidato.setLines(5);
        assertEquals("Easy", g.defineGame(hidato));
    }

    @Test
    public void defineGame3() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(6);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","5","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#","?","?")));
        matrix.add(4, new Vector<String>(Arrays.asList("?","?","?","?","?","?")));
        matrix.add(5, new Vector<String>(Arrays.asList("?","?","?","?","?","?")));
        hidato.setHidato(matrix);
        hidato.setColumns(6);
        hidato.setLines(6);
        assertEquals("Medium", g.defineGame(hidato));
    }

    @Test
    public void defineGame4() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(7);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","5","?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#","?","?","?")));
        matrix.add(4, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?")));
        matrix.add(5, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?")));
        matrix.add(6, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?")));
        hidato.setHidato(matrix);
        hidato.setColumns(7);
        hidato.setLines(7);
        assertEquals("Medium", g.defineGame(hidato));
    }

    @Test
    public void defineGame5() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(8);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?","?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","5","?","?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#","?","?","?","?")));
        matrix.add(4, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(5, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(6, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(7, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        hidato.setHidato(matrix);
        hidato.setColumns(8);
        hidato.setLines(8);
        assertEquals("Hard", g.defineGame(hidato));
    }
    @Test
    public void defineGame6() throws Exception {
        Hidato hidato = new Hidato();
        Vector<Vector<String>> matrix = new Vector<Vector<String>>(8);
        matrix.add(0, new Vector<String>(Arrays.asList("1","?","?","?","?","?","?","?")));
        matrix.add(1, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(2, new Vector<String>(Arrays.asList("3","?","?","?","?","?","?","?")));
        matrix.add(3, new Vector<String>(Arrays.asList("?","?","?","#","?","?","?","?")));
        matrix.add(4, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(5, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(6, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        matrix.add(7, new Vector<String>(Arrays.asList("?","?","?","?","?","?","?","?")));
        hidato.setHidato(matrix);
        hidato.setColumns(8);
        hidato.setLines(8);
        assertEquals("Hard", g.defineGame(hidato));
    }

    @Test
    public void startGame(){
        g.startGame();
    }

}