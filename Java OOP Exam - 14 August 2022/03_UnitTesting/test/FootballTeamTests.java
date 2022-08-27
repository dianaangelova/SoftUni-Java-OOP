// test
package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FootballTeamTests {
    private Footballer footballer1;
    private Footballer footballer2;
    private Footballer footballer3;
    private List<Footballer> test_footballerList;

    @Before
    public void setup() {
        footballer1 = new Footballer("Martin");
        footballer2 = new Footballer("Petur");
        footballer3 = new Footballer("Koko");
    }

    @Test
    public void test_ConstructorSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 5);
    }


    @Test(expected = NullPointerException.class)
    public void test_ConstructorSetNameEmptyThrow() {
        FootballTeam footballTeam = new FootballTeam("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ConstructorSetVacantPositionsNegativeThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski", -1);
    }


    @Test
    public void test_GetNameSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 5);

        Assert.assertEquals("Levski", footballTeam.getName());
    }


    @Test
    public void test_GetVacantPositionsSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 5);

        Assert.assertEquals(5, footballTeam.getVacantPositions());
    }

    @Test
    public void test_GetFootballersSizeSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 5);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        Assert.assertEquals(3, footballTeam.getCount());
    }

    @Test
    public void test_AddFootballersSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 5);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        Assert.assertEquals(3, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFootballersFullThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski", 2);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveFootballerNotInListThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski", 3);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);

        footballTeam.removeFootballer("Koko");
    }

    @Test
    public void test_RemoveFootballerSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 3);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        footballTeam.removeFootballer("Koko");

        Assert.assertEquals(2, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FootballerForSaleNotInListThrow() {
        FootballTeam footballTeam = new FootballTeam("Levski", 3);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        footballTeam.footballerForSale("Diana");
    }

    @Test
    public void test_FootballerForSaleSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 3);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        Assert.assertEquals(3, footballTeam.getCount());

        footballTeam.footballerForSale("Koko");

        Assert.assertEquals(3, footballTeam.getCount());

        Assert.assertFalse(footballer3.isActive());
    }


    public void test_GetStatisticsSuccess() {
        FootballTeam footballTeam = new FootballTeam("Levski", 3);
        footballTeam.addFootballer(footballer1);
        footballTeam.addFootballer(footballer2);
        footballTeam.addFootballer(footballer3);

        test_footballerList.add(footballer1);
        test_footballerList.add(footballer2);
        test_footballerList.add(footballer3);

        String expected = test_footballerList.stream().map(Footballer::getName).collect(Collectors.joining(", "));

        String actual = footballTeam.getStatistics();

        Assert.assertEquals(expected, actual);
    }

}
