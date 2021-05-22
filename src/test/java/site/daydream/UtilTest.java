package site.daydream;

import org.junit.Test;
import static org.junit.Assert.*;
import static site.daydream.Util.*;

public class UtilTest {
    @Test
    public void testIntToByteArray() {
        assertArrayEquals(intToByteArray(0), new byte[] {0, 0, 0, 0});
        assertArrayEquals(intToByteArray(1), new byte[] {0, 0, 0, 1});
        assertArrayEquals(intToByteArray(Integer.MAX_VALUE), new byte[] {127, -1, -1, -1});
        assertArrayEquals(intToByteArray(Integer.MIN_VALUE), new byte[] {-128, 0, 0, 0});
    }

    @Test
    public void testByteArrayToInt() {
        assertEquals(byteArrayToInt(new byte[] {0, 0, 0, 0}), 0);
        assertEquals(byteArrayToInt(new byte[] {0, 0, 0, 1}), 1);
        assertEquals(byteArrayToInt(new byte[] {127, -1, -1, -1}), Integer.MAX_VALUE);
        assertEquals(byteArrayToInt(new byte[] {-128, 0, 0, 0}), Integer.MIN_VALUE);
    }
}
