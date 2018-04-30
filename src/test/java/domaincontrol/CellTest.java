package domaincontrol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {
    Cell cell;
    @Before
    public void setUp() throws Exception {
        cell = new Cell(1,true,12);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(1, cell.getId());
    }

    @Test
    public void getAccessible() throws Exception {
        assertTrue(cell.getAccessible());
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(12, cell.getNumber());
    }

    @Test
    public void setId() throws Exception {
        cell.setId(8);
        assertEquals(8, cell.getId());
    }

    @Test
    public void setAccessible() throws Exception {
        cell.setAccessible(false);
        assertFalse(cell.getAccessible());
    }

    @Test
    public void setNumber() throws Exception {
        cell.setNumber(56);
        assertEquals(56, cell.getNumber());
    }


}