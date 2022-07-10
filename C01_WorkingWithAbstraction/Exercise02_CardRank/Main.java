package C01_WorkingWithAbstraction.Exercise02_CardRank;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        Arrays.stream(Rank.values()).forEach(cards -> System.out.printf("Ordinal value: %d; Name value: %s%n", cards.ordinal(), cards.name()));

    }
}
