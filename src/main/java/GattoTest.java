import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GattoTest {
    @Test
    public void testMiagolare1() {
        Gatto gatto = new Gatto();
        assertTrue(gatto.miagolare(1) == "Miaow");
    }

    @Test
    public void testMiagolare2() {
        Gatto gatto = new Gatto();
        assertEquals("not Miaow", "MIAOW", gatto.miagolare(1));
    }
}
