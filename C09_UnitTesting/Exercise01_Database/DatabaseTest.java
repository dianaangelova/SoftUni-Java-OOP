package C09_UnitTesting.Exercise01_Database;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    @Test
    public void test_CreateDatabase() throws OperationNotSupportedException {
        Integer[] numbers = {5, 8, 21, 68, 81, -5};
        Database database = new Database(5, 8, 21, 68, 81, -5);
        Integer[] dbElements = database.getElements();
        Assert.assertEquals(6, dbElements.length);

//        for (int i = 0; i < numbers.length; i++) {
//            Assert.assertEquals(numbers[i], dbElements[i]);
//        }

        Assert.assertArrayEquals(numbers, dbElements);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithLessThanOneArgument() throws OperationNotSupportedException {
        Integer [] emptyArray = new Integer[0];
        new Database(emptyArray);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithMoreThan16Arguments() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[17];
        new Database(bigArray);
    }


//    @Test
//    public void test_AddElementToDatabase() throws OperationNotSupportedException {
//        Database database = new Database(1, 2, 3);
//        Integer[] databaseElements = database.getElements();
//        int numberElements = databaseElements.length;
//        System.out.println("Before:" + numberElements);
//        database.add(4);
//        int actualCount = databaseElements.length;
//        System.out.println("After:" + numberElements);
//
//        Assert.assertEquals(3, actualCount);
//    }


}