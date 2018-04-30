package domaincontrol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    Player player;
    @Before
    public void SetUp(){
        player = new Player("Miquel");
    }
    @Test
    public void setId() throws Exception {
        player.setId("Jordi");
        assertEquals("Jordi", player.getId());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("Miquel", player.getId());
    }

}