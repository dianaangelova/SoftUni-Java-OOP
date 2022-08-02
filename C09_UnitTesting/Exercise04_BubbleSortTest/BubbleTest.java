package C09_UnitTesting.Exercise04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void test_Sort() {
        int[] numbers = {9, -5, 6, 1, 12, 100};
        Bubble.sort(numbers);
        int[] sortedArray = {-5, 1, 6, 9, 12, 100};
        Assert.assertArrayEquals(sortedArray, numbers);
    }
}