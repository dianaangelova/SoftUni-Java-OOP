package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.*;


public class GiftFactoryTests {
    private Gift gift1;
    private Gift gift2;
    private Gift gift3;
    List<Gift> testGiftsList;

    @Before
    public void setup() {
        gift1 = new Gift("Flower", 3.5);
        gift2 = new Gift("Book", 9.1);
        gift3 = new Gift("Chocolate", 15.0);
        testGiftsList = new ArrayList<>();
        testGiftsList.add(gift1);
        testGiftsList.add(gift2);
        testGiftsList.add(gift3);
    }

    @Test
    public void test_ConstructorSuccess() {
        GiftFactory data = new GiftFactory();
    }

    @Test
    public void test_CreateGiftSuccess() {
        GiftFactory data = new GiftFactory();
        data.createGift(gift1);

        Assert.assertEquals(1, data.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateGiftThrowDuplicate() {
        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift1);
    }

    @Test
    public void test_GetCountSuccess() {
        GiftFactory data = new GiftFactory();

        Assert.assertEquals(0, data.getCount());

        data.createGift(gift1);

        Assert.assertEquals(1, data.getCount());
    }

    @Test
    public void test_RemoveGiftSuccess() {
        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
        data.createGift(gift3);
        boolean expectedRemoved = data.removeGift("Chocolate");
        Assert.assertTrue(expectedRemoved);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGiftThrow() {
        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
        data.createGift(gift3);
        data.removeGift("   ");
    }

    @Test
    public void test_GetPresentWithLeastMagicSuccess() {
        Gift expectedGift = testGiftsList
                .stream()
                .min(Comparator.comparingDouble(Gift::getMagic))
                .orElse(null);

        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
        data.createGift(gift3);

        Gift actualGift = data.getPresentWithLeastMagic();

        Assert.assertEquals(expectedGift, actualGift);
    }

    @Test
    public void test_GetPresentSuccess() {
        Gift expectedGift = testGiftsList
                .stream()
                .filter(p -> p.getType().equals("Chocolate"))
                .findFirst()
                .orElse(null);

        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
        data.createGift(gift3);

        Gift actualGift = data.getPresent("Chocolate");

        Assert.assertEquals(expectedGift, actualGift);
    }

    @Test
    public void test_GetPresentsSuccess() {
        GiftFactory data = new GiftFactory();

    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetPresentShouldReturnUnmodifiableList() {
        GiftFactory data = new GiftFactory();
        data.createGift(gift1);
        data.createGift(gift2);
        data.createGift(gift3);

        Collection<Gift> giftList = data.getPresents();
        giftList.remove(1);
    }


}
