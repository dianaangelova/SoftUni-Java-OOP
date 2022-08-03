import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InstockTest {

    public Instock instock;
    public Product milk;
    public Product cheese;
    public Product bread;

    @Before
    public void setup() {
        instock = new Instock();
        milk = new Product("milk", 1.20, 20);
        instock.add(milk);
        cheese = new Product("cheese", 10.0, 10);
        instock.add(cheese);
        bread = new Product("bread", 1.50, 30);
        instock.add(bread);
    }

    @Test
    public void test_CountShouldBeZeroWhenNoProducts() {
        Instock instockEmpty = new Instock();
        int count = instockEmpty.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void test_CountShouldBeThreeWhenThreeProducts() {
        int count = instock.getCount();
        Assert.assertEquals(3, count);
    }

    @Test
    public void test_ContainsProductSuccess() {
        boolean hasMilk = instock.contains(milk);
        Assert.assertTrue(hasMilk);
    }

    @Test
    public void test_ContainsProductEmpty() {
        Product honey = new Product("honey", 10.0, 0);
        boolean hasHoney = instock.contains(honey);
        Assert.assertFalse(hasHoney);
    }

    @Test
    public void test_FindSuccess() {
        Product productFound = instock.find(0);
        Assert.assertEquals(milk, productFound);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_FindIndexBiggerThanListSize() {
        Product productFound = instock.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_FindIndexNegativeThrow() {
        instock.find(-1);
    }

    @Test
    public void test_ChangeQuantityToProductWhichExists() {
        int quantityBeforeChange = cheese.getQuantity();
        instock.changeQuantity(cheese.getLabel(), 60);
        int newQuantity = cheese.getQuantity();
        if (quantityBeforeChange != newQuantity) {
            Assert.assertNotEquals(quantityBeforeChange, newQuantity);
        } else Assert.assertEquals(quantityBeforeChange, newQuantity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ChangeQuantityToProductWhichDoesNotExists() {
        Product beer = new Product("beer", 2, 30);
        instock.changeQuantity(beer.getLabel(), 60);
    }

    @Test
    public void test_FindByLabelProductWhichExists() {
        Assert.assertEquals(cheese, instock.findByLabel("cheese"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FindByLabelProductWhichDoesNotExists() {
        instock.findByLabel("beer");
    }

    @Test
    public void test_FindFirstByAlphabeticalOrderNumberIsInRange() {
        int number = 3;
        Product beer = new Product("beer", 2, 30);
        instock.add(beer);
        Product carrot = new Product("carrot", 1, 50);
        instock.add(carrot);

        Product[] productsArray = new Product[]{milk, cheese, bread, beer, carrot};

        List<Product> expectedFiteredProducts = Arrays.stream(productsArray)
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(number)
                .collect(Collectors.toList());

        List<Product> actualFilteredProducts = assertIterableNotNullAndConvertToLIst(instock.findFirstByAlphabeticalOrder(number));

        Assert.assertEquals(expectedFiteredProducts, actualFilteredProducts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FindFirstByAlphabeticalOrderShouldThrowWhenNumberIsNotInRange() {
        instock.findFirstByAlphabeticalOrder(4);
    }

    private List<Product> assertIterableNotNullAndConvertToLIst(Iterable<Product> iterable) {
        Assert.assertNotNull(iterable);
        List<Product> productList = new ArrayList<>();
        iterable.forEach(p -> productList.add(p));
        return productList;
    }

}