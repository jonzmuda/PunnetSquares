
package punnet;

import java.util.Scanner;

public class Example {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter the father's genotype (example: AaBb):");
        String father = scnr.next();

        System.out.println("Enter the mother's genotype (example: AaBb):");
        String mother = scnr.next();

        char[][] alleles = {
                father.toCharArray(),
                mother.toCharArray()
        };

        if (PunnetSquare.isValidInput(alleles)) {

            String[][] genotypes =
                    PunnetSquare.getGenotypes(alleles);

            String[][] gametes =
                    PunnetSquare.getGametes(genotypes);

            String[] offspring =
                    PunnetSquare.calculatePunnet(gametes);

            System.out.println("\nGenotypes:");
            for (int i = 0; i < genotypes.length; i++) {
                for (int j = 0; j < genotypes[i].length; j++) {
                    System.out.print(genotypes[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("\nGametes:");
            for (int i = 0; i < gametes.length; i++) {
                for (int j = 0; j < gametes[i].length; j++) {
                    System.out.print(gametes[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("\nOffspring genotype combinations:");
            for (String genotype : offspring) {
                System.out.print(genotype + " ");
            }
            System.out.println();

            // Calculate probability of a specific genotype
            System.out.println("Input specific trait: ");
            double result =
                    PunnetSquare.probability(offspring, scnr.next());

            System.out.printf(
                    "\nProbability: %.1f%%%n",
                    result * 100
            );

        } else {
            System.out.println("Invalid genotype input.");
        }

        scnr.close();
    }
}
