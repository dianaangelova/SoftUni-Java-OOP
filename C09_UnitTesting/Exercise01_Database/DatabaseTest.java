package C09_UnitTesting.Exercise01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {5, 8, 21, 68, 81, -5};

    @Before
    public void prepare() throws OperationNotSupportedException {
        Integer[] numbers = NUMBERS;
        database = new Database(numbers);
    }


    @Test
    public void test_CreateDatabase() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();
        Assert.assertEquals(6, dbElements.length);

//        for (int i = 0; i < numbers.length; i++) {
//            Assert.assertEquals(numbers[i], dbElements[i]);
//        }

        Assert.assertArrayEquals(NUMBERS, dbElements);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithLessThanOneArgument() throws OperationNotSupportedException {
        Integer[] emptyArray = new Integer[0];
        new Database(emptyArray);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithMoreThan16Arguments() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[17];
        new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddNullElementToDatabase() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_AddSuccessElementToDatabase() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        database.add(12);
        Integer[] dbElements = database.getElements();
        int lastElementFromDB = dbElements[dbElements.length - 1];

        Assert.assertEquals(12, lastElementFromDB);
        Assert.assertEquals(NUMBERS.length + 1, database.getElements().length);
    }


    @Test
    public void test_RemoveSuccess() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        database.remove();
        Integer[] currentElements = database.getElements();

        Assert.assertEquals(initialSize - 1, currentElements.length);

        int secondToLastBeforeDelete = NUMBERS[NUMBERS.length - 2];
        int lastElementAfterDelete = currentElements[currentElements.length - 1];
        Assert.assertEquals(secondToLastBeforeDelete, lastElementAfterDelete);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ShouldThrowForEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

}
