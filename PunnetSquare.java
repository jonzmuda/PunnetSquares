package punnet;

public class PunnetSquare {

    /*
    If max is even
    If input is letters
    If consecutive chars are same type of trait
    alleles[i].length = number of characters in allele
    alleles[i][j] = one character from string
    alleles.length = number of parents / rows
    */

    public static String sortOffspring(String genotype) {
        char[] alleles = genotype.toCharArray();

        // Sort all alleles alphabetically, uppercase before lowercase
        for (int i = 0; i < alleles.length - 1; i++) {
            for (int j = i + 1; j < alleles.length; j++) {

                char first = Character.toLowerCase(alleles[i]);
                char second = Character.toLowerCase(alleles[j]);

                if (first > second ||
                        (first == second
                                && Character.isLowerCase(alleles[i])
                                && Character.isUpperCase(alleles[j]))) {

                    char temp = alleles[i];
                    alleles[i] = alleles[j];
                    alleles[j] = temp;
                }
            }
        }

        return new String(alleles);
    }

    public static String[] calculatePunnet(String[][] gamete_array) {
        int total = gamete_array[0].length * gamete_array[1].length;
        String[] phenotypes = new String[total];

        int index = 0;

        for (int i = 0; i < gamete_array[0].length; i++) {
            for (int j = 0; j < gamete_array[1].length; j++) {

                phenotypes[index] = sortOffspring(
                        gamete_array[0][i] + gamete_array[1][j]
                );
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


    // Sort each allele pair so uppercase comes first
    public static String sortGenotype(String genotype) {
        String sorted = "";

        for (int i = 0; i < genotype.length() - 1; i += 2) {
            char first = genotype.charAt(i);
            char second = genotype.charAt(i + 1);

            if (Character.isUpperCase(first)
                    && Character.isLowerCase(second)) {
                sorted += "" + first + second;
            } else if (Character.isLowerCase(first)
                    && Character.isUpperCase(second)) {
                sorted += "" + second + first;
            } else {
                sorted += "" + first + second;
            }
        }

        return sorted;
    }

    // Check whether two genotypes have the same trait pairs,
    // regardless of the order of those pairs
    public static boolean matchesTrait(String genotype, String trait) {
        genotype = sortGenotype(genotype);
        trait = sortGenotype(trait);

        for (int i = 0; i < trait.length(); i += 2) {
            String targetPair = trait.substring(i, i + 2);

            boolean found = false;

            for (int j = 0; j < genotype.length() - 1; j += 2) {
                if (genotype.substring(j, j + 2).equals(targetPair)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    // Calculate the probability of a matching genotype
    public static double probability(String[] phenotypes, String trait) {
        if (phenotypes.length == 0) {
            return 0.0;
        }

        int counter = 0;

        for (int i = 0; i < phenotypes.length; i++) {
            phenotypes[i] = sortGenotype(phenotypes[i]);

            if (matchesTrait(phenotypes[i], trait)) {
                counter++;
            }
        }

        return (double) counter / phenotypes.length;
    }




    }
