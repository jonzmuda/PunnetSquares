
import java.util.Scanner;

public class PunnetSquare {

    /*
    If max is even
    If input is letters
    If consecutive chars are same type of trait
    alleles[i].length = number of characters in allele
    alleles[i][j] = one character from string
    alleles.length = number of parents / rows
    */
    public static String[] calculatePunnet(String[][] gamete_array) {
        int total = gamete_array[0].length * gamete_array[1].length;
        String[] phenotypes = new String[total];

        int index = 0;

        for (int i = 0; i < gamete_array[0].length; i++) {
            for (int j = 0; j < gamete_array[1].length; j++) {

                phenotypes[index] = gamete_array[0][i] + gamete_array[1][j];
                index++;
            }
        }

        return phenotypes;
    }
    public static boolean isValidInput(char[][] alleles) {
        for (int i = 0; i < alleles.length; i++) {

            // If it's not even
            if (alleles[i].length % 2 != 0) {
                return false;
            }

            // If input is not letters
            for (int j = 0; j < alleles[i].length; j++) {
                if (!Character.isLetter(alleles[i][j])) {
                    return false;
                }
            }

            // If consecutive chars are not the same trait
            for (int j = 0; j < alleles[i].length - 1; j += 2) {
                if (Character.toLowerCase(alleles[i][j]) !=
                        Character.toLowerCase(alleles[i][j + 1])) {
                    return false;
                }
            }
        }

        return true;
    }

    public static String[][] getGenotypes(char[][] alleles) {
        String[][] genotype_array = new String[alleles.length][];

        for (int i = 0; i < alleles.length; i++) {
            genotype_array[i] = new String[alleles[i].length / 2];

            for (int j = 0; j < alleles[i].length - 1; j += 2) {
                String genotype = "" + alleles[i][j] + alleles[i][j + 1];
                genotype_array[i][j / 2] = genotype;
            }
        }

        return genotype_array;
    }

    // genotype_array[i] = one parent's genotypes
    public static String[][] getGametes(String[][] genotype_array) {
        String[][] gamete_array = new String[genotype_array.length][];

        for (int i = 0; i < genotype_array.length; i++) {

            int numGametes = (int) Math.pow(2, genotype_array[i].length);
            gamete_array[i] = new String[numGametes];

            // For each gamete from this parent
            for (int j = 0; j < numGametes; j++) {
                gamete_array[i][j] = "";

                // For each trait in the genotype
                for (int k = 0; k < genotype_array[i].length; k++) {

                    // Determine whether to select the first or second allele
                    int alleleIndex = (j / (int) Math.pow(2,
                            genotype_array[i].length - 1 - k)) % 2;

                    // Append allele
                    gamete_array[i][j] +=
                            genotype_array[i][k].charAt(alleleIndex);
                }
            }
        }

        return gamete_array;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        String father_allele = scnr.next();
        String mother_allele = scnr.next();

        // Allow each parent to have a different input length
        char[][] alleles = new char[2][];

        alleles[0] = father_allele.toCharArray();
        alleles[1] = mother_allele.toCharArray();

        if (isValidInput(alleles)) {
            String[][] genotype_array = getGenotypes(alleles);
            String[][] gamete_array = getGametes(genotype_array);
            String[] phenotypes = calculatePunnet(gamete_array);
// Print genotypes
            System.out.println("Genotypes:");
            for (int i = 0; i < genotype_array.length; i++) {
                System.out.println("Parent " + (i + 1) + ": "
                        + genotype_array[i].length + " genotypes");

                for (int j = 0; j < genotype_array[i].length; j++) {
                    System.out.print(genotype_array[i][j] + " ");
                }
                System.out.println();
            }

// Print gametes
            System.out.println("Gametes:");
            for (int i = 0; i < gamete_array.length; i++) {
                System.out.println("Parent " + (i + 1) + ": "
                        + gamete_array[i].length + " gametes");

                for (int j = 0; j < gamete_array[i].length; j++) {
                    System.out.print(gamete_array[i][j] + " ");
                }
                System.out.println();
            }

// Print offspring combinations
            System.out.println("Offspring combinations: " + phenotypes.length);
            for (int i = 0; i < phenotypes.length; i++) {
                System.out.print(phenotypes[i] + " ");
            }
            System.out.println();

        } else {
            System.out.println("Invalid genotype input.");
        }

        scnr.close();
    }
}
