package C01_WorkingWithAbstraction.Exercise03_CardsWithPower;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RankPowers rankPowers = RankPowers.valueOf(scanner.nextLine());
        SuitPowers suitPowers = SuitPowers.valueOf(scanner.nextLine());

        Cards card = new Cards(suitPowers, rankPowers);

        System.out.printf("Card name: %s of %s; Card power: %d", card.getRankPowers(), card.getSuitPowers(), card.getPower());

    }
}
