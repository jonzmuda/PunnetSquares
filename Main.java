import punnet.PunnetSquare;

public class Main {
    public static void main(String[] args) {
        String[][] gametes = {
                {"AB", "Ab", "aB", "ab"},
                {"AB", "Ab", "aB", "ab"}
        };

        String[] offspring = PunnetSquare.calculatePunnet(gametes);

        double chance = PunnetSquare.probability(offspring, "AaBb");

        System.out.printf("Probability: %.1f%%%n", chance * 100);
    }
}