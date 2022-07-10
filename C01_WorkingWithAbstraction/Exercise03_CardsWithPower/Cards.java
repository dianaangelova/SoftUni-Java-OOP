package C01_WorkingWithAbstraction.Exercise03_CardsWithPower;

public class Cards {
    private SuitPowers suitPowers;
    private RankPowers rankPowers;

    public Cards(SuitPowers suitPowers, RankPowers rankPowers) {
        this.suitPowers = suitPowers;
        this.rankPowers = rankPowers;
    }

    public SuitPowers getSuitPowers() {
        return suitPowers;
    }

    public RankPowers getRankPowers() {
        return rankPowers;
    }

    public int getPower() {
        return suitPowers.getPower() + rankPowers.getPower();
    }

}
