# PunnetSquare

A simple Java library for calculating Punnett squares and genetic inheritance probabilities.

## Features

- Validates genotype input.
- Separates parent genotypes into individual traits.
- Generates possible gametes for each parent.
- Calculates possible offspring genotype combinations.
- Sorts offspring genotypes into a consistent format.
- Calculates the probability of a specified genotype.

## Example

```java
import punnet.PunnetSquare;

String[][] gametes = {
    {"AB", "Ab", "aB", "ab"},
    {"AB", "Ab", "aB", "ab"}
};

String[] offspring = PunnetSquare.calculatePunnet(gametes);

double chance = PunnetSquare.probability(offspring, "AaBb");

System.out.printf("Probability: %.1f%%%n", chance * 100);
```

## Installation

1. Download `PunnetSquare.jar`.
2. Add the JAR file to your Java project's dependencies or classpath.
3. Import the library using `import punnet.PunnetSquare;`.

## Input Format

Genotypes should use letters to represent alleles, with each trait represented by two consecutive letters.

Examples: `Aa`, `AABB`, `AaBb`.

## Requirements

- Java Development Kit (JDK)
- A Java IDE or build environment that supports external JAR files
