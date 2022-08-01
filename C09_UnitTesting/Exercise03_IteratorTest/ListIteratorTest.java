package C09_UnitTesting.Exercise03_IteratorTest;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ListIteratorTest {

    private ListIterator listIterator;

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator("Petra", "Diana", "Sophia", "Isabella");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateListIteratorWithNullShouldThrow() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void test_CreateListIteratorSuccess() throws OperationNotSupportedException {
        ListIterator listIterator1 = new ListIterator("Petra", "Diana", "Sophia", "Isabella");
    }

    //true -> hasNext
    //false -> no next

    @Test
    public void test_hasNext() {
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test
    public void test_move() {
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }

    @Test(expected = IllegalStateException.class)
    public void test_printNullElement() throws OperationNotSupportedException {
        ListIterator listIterator2 = new ListIterator();
        listIterator2.print();
    }

    @Test
    public void test_printSuccess() {
        String[] stringsArray = new String[]{"Petra", "Diana", "Sophia", "Isabella"};
        int index = 0;

        while (listIterator.hasNext() == true) {
            Assert.assertEquals(stringsArray[index], listIterator.print());
            listIterator.move();
            index++;
        }
    }

}