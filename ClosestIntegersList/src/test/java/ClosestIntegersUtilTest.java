import org.junit.Test;

import static org.junit.Assert.*;

public class ClosestIntegersUtilTest {


    @Test
    public void compute_test1() {
        Integer[] input = {10, 2, 14, 4, 7, 6};
        int x = 5;
        int k = 3;

        Integer[] expected = {4, 6, 7};

        Integer[] actual = ClosestIntegersUtil.findValues(input, x, k);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void compute_test2() {
        Integer[] input = {-10, -50, 20, 17, 80};
        int x = 20;
        int k = 2;

        Integer[] expected = {17, 20};

        Integer[] actual = ClosestIntegersUtil.findValues(input, x, k);
        assertArrayEquals(expected, actual);
    }
}