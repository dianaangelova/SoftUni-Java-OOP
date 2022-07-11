package C04_InterfacesAndAbstraction.Exercise03_BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Birthable> thingsWithBirthDate = new ArrayList<>();

        while (!input.equals("End")) {
            String[] inputArr = input.split("\\s+");
            String typeCommand = inputArr[0];


            switch (typeCommand) {
                case "Citizen":
                    String nameC = inputArr[1];
                    int ageC = Integer.parseInt(inputArr[2]);
                    String idC = inputArr[3];
                    String birthDateC = inputArr[4];

                    Citizen citizen = new Citizen(nameC, ageC, idC, birthDateC);
                    thingsWithBirthDate.add(citizen);
                    break;

                case "Pet":
                    String nameP = inputArr[1];
                    String birthDateP = inputArr[2];

                    Pet pet = new Pet(nameP, birthDateP);
                    thingsWithBirthDate.add(pet);
                    break;

                case "Robot":
                    break;
            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();

        //   citizens.stream().filter(citizen -> citizen.getBirthDate().contains(year));
        //   pets.stream().filter(pet -> pet.getBirthDate().contains(year));

        for (Birthable b : thingsWithBirthDate) {
            if (b.getBirthDate().endsWith(year)) {
                System.out.println(b.getBirthDate());
            } else {
                System.out.println("<no output>");
            }
        }

    }
}
