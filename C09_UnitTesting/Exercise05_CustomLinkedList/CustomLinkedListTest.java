package C09_UnitTesting.Exercise05_CustomLinkedList;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTest {

    public CustomLinkedList customLinkedList;

    @Before
    public void setup() {
        customLinkedList = new CustomLinkedList();
        customLinkedList.add("Diana");
        customLinkedList.add("Ivan");
        customLinkedList.add("Petra");
    }

    @Test
    public void test_AddSuccessToEmptyList() {
        CustomLinkedList customLinkedList1 = new CustomLinkedList<>();
        customLinkedList1.add("Diana");
        Assert.assertEquals("Diana", customLinkedList1.get(0));
    }

    @Test
    public void test_AddSuccess() {
        Assert.assertEquals("Ivan", customLinkedList.get(1));
    }

    @Test
    public void test_RemoveAtSuccess() {;
        customLinkedList.removeAt(0);
        Assert.assertEquals("Ivan", customLinkedList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveAtOutOfBoundsBiggerIndexThrow() {
        customLinkedList.removeAt(5);
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveAtOutOfBoundsNegativeIndexThrow() {
        customLinkedList.removeAt(-1);
    }

    @Test
    public void test_RemoveAtNormalSuccess() {
        customLinkedList.removeAt(1);
        Assert.assertEquals("Petra", customLinkedList.get(1));
    }

    @Test
    public void test_RemoveFirstElementSuccess() {
        customLinkedList.remove("Diana");
        Assert.assertEquals("Ivan", customLinkedList.get(0));
    }

    @Test
    public void test_RemoveMiddleElementSuccess() {
        customLinkedList.remove("Ivan");
        Assert.assertEquals("Petra", customLinkedList.get(1));
    }

    @Test
    public void test_RemoveElementNotInList() {
        int index = customLinkedList.remove("Gosho");

        Assert.assertEquals(-1, index);
    }

    @Test
    public void test_RemoveLastElement_ListBecomesEmpty() {
        customLinkedList.add("Sonya");
        customLinkedList.remove("Sonya");
        boolean isInList = customLinkedList.contains("Sonya");
        Assert.assertFalse(isInList);
    }

    @Test
    public void test_RemoveLastElementSuccess() {
        customLinkedList.remove("Petra");
        boolean isInList = customLinkedList.contains("Petra");
        Assert.assertFalse(isInList);
    }

    @Test
    public void test_IndexOfSuccess() {
        int index = customLinkedList.indexOf("Ivan");

        Assert.assertEquals(1, index);
    }


    @Test
    public void test_IndexOfNotInListElement() {
        int index = customLinkedList.indexOf("Gosho");

        Assert.assertEquals(-1, index);
    }


    @Test
    public void test_ContainsTrue() {
        boolean isInList = customLinkedList.contains("Diana");

        Assert.assertTrue(isInList);
    }

    @Test
    public void test_ContainsFalse() {
        boolean isInList = customLinkedList.contains("Gosho");

        Assert.assertFalse(isInList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetThrow() {
        customLinkedList.get(5);
    }

    @Test
    public void test_SetSuccess() {
        customLinkedList.set(0, "Gosho");

        Assert.assertEquals("Gosho", customLinkedList.get(0));
    }

    @Test
    public void test_SetMiddleElementSuccess() {
        customLinkedList.set(1, "Ivanka");

        Assert.assertEquals("Ivanka", customLinkedList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetIndexLessThanZeroThrow() {
        customLinkedList.set(-5, "Gosho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetIndexMoreThanListSizeThrow() {
        customLinkedList.set(100, "Gosho");
    }
}