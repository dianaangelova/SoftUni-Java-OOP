package C09_UnitTesting.Exercise02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private Person person1;
    private Person person2;
    private Person[] arrayPerson;

    @Before
    public void prepare() throws OperationNotSupportedException {
        person1 = new Person(12, "Diana");
        person2 = new Person(30, "Milka");
        arrayPerson = new Person[]{person1, person2};
        database = new Database(person1, person2);
    }

    @Test
    public void test_CreateDatabase() throws OperationNotSupportedException {
        Assert.assertEquals(2, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithLessThanOneArgument() throws OperationNotSupportedException {
        Person[] emptyArray = new Person[0];
        new Database(emptyArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithMoreThan16Arguments() throws OperationNotSupportedException {
        Person[] bigArray = new Person[17];
        new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddNullPersonToDatabase() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_AddSuccessPersonToDatabase() throws OperationNotSupportedException {
        Person person3 = new Person(40, "Gosho");
        database.add(person3);

        Assert.assertEquals(3, database.getElements().length);

        Person[] peopleFromDB = database.getElements();
        Assert.assertEquals(person3, peopleFromDB[peopleFromDB.length - 1]);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_RemoveNullElementThrow() throws OperationNotSupportedException {

        for (int i = 0; i < arrayPerson.length; i++) {
            database.remove();
        }
        database.remove();

    }

    @Test
    public void test_RemoveLastPersonSuccessElementThrow() throws OperationNotSupportedException {
        database.remove();
        int countAfterRemove = database.getElements().length;

        Assert.assertEquals(arrayPerson.length - 1, countAfterRemove);

        Person lastBeforeRemovalArray = arrayPerson[arrayPerson.length - 2];
        Person[] arrayDB = database.getElements();
        Person lastAfterRemovalDB = arrayDB[arrayDB.length - 1];
        Assert.assertEquals(lastBeforeRemovalArray, lastAfterRemovalDB);


    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsernameNullShouldThrow() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void test_FindByUsernameSuccess() throws OperationNotSupportedException {
        Person person = database.findByUsername("Diana");
        Assert.assertEquals("Diana", person.getUsername());
        Assert.assertEquals(12, person.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsernameMoreThanOne() throws OperationNotSupportedException {
        Person person3 = new Person(14, "Diana");
        database.add(person3);
        database.findByUsername("Diana");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsernameNoneMatchingShouldThrow() throws OperationNotSupportedException {
        database.findByUsername("Vanko");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByIDMoreThanOneMatchingShouldThrow() throws OperationNotSupportedException {
        Person person3 = new Person(12, "Petra");
        database.add(person3);
        database.findById(36);
    }

    @Test
    public void test_FindByIDSuccess() throws OperationNotSupportedException {
        Person person = database.findById(12);
        Assert.assertEquals(12, person.getId());
        Assert.assertEquals("Diana", person.getUsername());
    }
}