package com.example;

import java.util.ArrayList;
import java.util.Random;

public class DriverCreature {

    // Method to check if any Creature is alive
    public static boolean stillAlive(ArrayList<? extends Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                return true; // If any creature is alive, return true
            }
        }
        return false; // If no creature is alive, return false
    }

    // Method to calculate the total mass of all Creatures in the list
    public static double totalMass(ArrayList<? extends Creature> creatures) {
        double totalMass = 0.0;
        for (Creature creature : creatures) {
            totalMass += creature.getSize(); // Add the size of each creature to totalMass
        }
        return Math.round(totalMass * 100.0) / 100.0; // Round to 2 decimal places
    }

    public static void main(String[] args) {

        // Create some Creature objects for testing
        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Plant(5.0, 0.2));
        plants.add(new Plant(3.0, 0.1));

        // Create some PlantEater objects for testing
        ArrayList<PlantEater> plantEaters = new ArrayList<>();
        plantEaters.add(new PlantEater(10.0, 0.3, plants));

        // Create a combined list of creatures (could be a mix of Plants and
        // PlantEaters)
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.addAll(plants);
        creatures.addAll(plantEaters);

        // Test stillAlive method
        System.out.println("Is any creature alive? " + stillAlive(creatures));

        // Test totalMass method
        System.out.println("Total mass of all creatures: " + totalMass(creatures));

        System.out.println("\n----------Test1----------");

        Random rand = new Random();

        // Create a list of 2000 Plant objects with random sizes and fixed growth rate
        ArrayList<Plant> plants2 = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            double size = 250 + rand.nextDouble() * 100; // Random size between 250 and 350 grams
            double growthRate = 5; // Fixed growth rate of 5 grams/day
            plants2.add(new Plant(size, growthRate));
        }

        // Instantiate one PlantEater object with random size, fixed growth rate, and
        // food need
        double plantEaterSize = 900 + rand.nextDouble() * 200; // Random size between 900 and 1100 grams
        double plantEaterGrowthRate = 3; // Fixed growth rate of 3 grams/day
        // double plantEaterFoodNeed = 50; // Fixed food need of 50 grams/day
        PlantEater plantEater = new PlantEater(plantEaterSize, plantEaterGrowthRate, plants2);

        // Test A: Check initial values of the PlantEater
        System.out.println("Test A: Initial Values of PlantEater");
        System.out.println("PlantEater size: " + plantEater.getSize());
        System.out.println("PlantEater growth rate: " + plantEater.getGrowthRate());
        System.out.println("PlantEater food need: " + plantEater.getFoodNeed());
        System.out.println("PlantEater is alive: " + plantEater.isAlive());
        System.out.println();

        // Test B: Test the chew method
        // PlantEater should chew on plants and reduce their size. Let's test chewing on
        // a few plants.
        System.out.println("Test B: Testing chew method");
        plantEater.chew(plants2.get(0)); // Chew on the first plant
        System.out.println("After chewing the first plant:");
        System.out.println("Plant size: " + plants2.get(0).getSize());
        System.out.println(
                "PlantEater food eaten: " + String.format("%.2f", plantEater.getFoodNeed() - plantEater.stillNeed()));
        System.out.println();

        // Test C: Simulate one day of the PlantEater's life (growth, food consumption,
        // etc.)
        System.out.println("Test C: Simulating one day for PlantEater");
        plantEater.simulateDay();
        System.out.println("After one day of simulation:");
        System.out.println("PlantEater size: " + plantEater.getSize());
        System.out.println("PlantEater age: " + plantEater.getAge());
        System.out.println("PlantEater food need: " + plantEater.getFoodNeed());
        System.out.println("PlantEater food eaten: " + (plantEater.getFoodNeed() - plantEater.stillNeed()));
        System.out.println("Is PlantEater still alive? " + plantEater.isAlive());
        System.out.println();

        // Test D: Check if any plants are still alive
        System.out.println("Test D: Checking if any plants are alive");
        System.out.println("Is any plant alive? " + stillAlive(plants2));
        System.out.println();

        // Test E: Total mass of all plants and the PlantEater
        System.out.println("Test E: Total mass of all plants and PlantEater");
        double totalMass = totalMass(plants2) + plantEater.getSize();
        System.out.println("Total mass of all plants and PlantEater: " + totalMass);

        System.out.println("\n----------Test2----------");

        // Step 1: Instantiate 1500 PlantEater objects
        ArrayList<PlantEater> plantEaters2 = new ArrayList<>();
        for (int i = 0; i < 1500; i++) {
            double size = 1000 + (rand.nextDouble() * 200 - 100); // Random size between 900 and 1100 grams
            double growthRate = 3; // Fixed growth rate of 3 grams/day
            // double foodNeed = 50; // Fixed food need of 50 grams/day
            plantEaters2.add(new PlantEater(size, growthRate, new ArrayList<>()));

        }

        // Step 2: Instantiate 2000 Plant objects
        ArrayList<Plant> plants3 = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            double size = 300 + (rand.nextDouble() * 100 - 50); // Random size between 250 and 350 grams
            double growthRate = 5; // Fixed growth rate of 5 grams/day
            plants3.add(new Plant(size, growthRate));
        }

        // Step 2.5: Instantiate 25 MeatEater objects
        ArrayList<MeatEater> meatEatersL = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            double size = 1400 + (rand.nextDouble() * 300 - 150); // Random size between 1250 and 1550 grams
            double growthRate = 2; // Fixed growth rate of 2 grams/day
            meatEatersL.add(new MeatEater(size, growthRate, plantEaters2));
        }

        // Step 3: Run the simulation for 1000 days (or until all PlantEaters are dead)
        int day = 0;
        while (day < 1000 && (!plantEaters2.isEmpty() || !meatEatersL.isEmpty())) {
            // Simulate a day for all plants
            for (Plant plant : plants3) {
                plant.simulateDay();
            }

            // Simulate a day for all PlantEaters
            for (PlantEater pe : plantEaters2) {
                pe.simulateDay();
            }

            // Simulate a day for all MeatEaters
            for (MeatEater me : meatEatersL) {
                me.simulateDay();
            }

            // Step 4: Birth new cretures based on probabilities
            if (rand.nextDouble() < 0.05) { // 5% chance for a new plant
                double size = 300 + (rand.nextDouble() * 100 - 50); // Random size between 250 and 350 grams
                double growthRate = 5; // Fixed growth rate of 5 grams/day
                plants3.add(new Plant(size, growthRate));
            }

            if (rand.nextDouble() < 0.48) { // 48% chance for a new plant eater
                double size = 1000 + (rand.nextDouble() * 200 - 100); // Random size between 900 and 1100 grams
                double growthRate = 3; // Fixed growth rate of 3 grams/day
                // double foodNeed = 50; // Fixed food need of 50 grams/day
                plantEaters2.add(new PlantEater(size, growthRate, new ArrayList<>()));

            }

            if (rand.nextDouble() < 0.60) { // 60% chance for a new meat eater
                double size = 1400 + (rand.nextDouble() * 300 - 150); // Random size between 1250 and 1550 grams
                double growthRate = 2; // Fixed growth rate of 2 grams/day
                meatEatersL.add(new MeatEater(size, growthRate, plantEaters2));
            }

            // Step 5: Remove dead PlantEaters
            int i = 0;
            while (i < plantEaters2.size()) {
                if (!plantEaters2.get(i).isAlive()) {
                    plantEaters2.remove(i); // Remove dead PlantEater
                } else {
                    i++; // Move to the next PlantEater if the current one is still alive
                }
            }

            // Step 5.5: Remove dead MeatEaters
            int j = 0;
            while (j < meatEatersL.size()) {
                if (!meatEatersL.get(j).isAlive()) {
                    meatEatersL.remove(j); // Remove dead MeatEater
                } else {
                    j++; // Move to the next MeatEater if the current one is still alive
                }
            }

            // Step 6: Pint daily statistics (every 100 days)
            if (day % 100 == 0) {
                System.out.printf("Day %d: %d PlantEaters, %d MeatEaters, %d Plants, Total mass: %.2f%n",
                        day, plantEaters2.size(), meatEatersL.size(), plants3.size(),
                        totalMass(plants3) + totalMass(plantEaters2) + totalMass(meatEatersL));
            }

            day++; // Increment the day counter

        }

        // Step 7: Print final statistics
        System.out.println("\nSimulation ended.");
        System.out.printf("\nTotal days simulated: %d%n", day);
        System.out.printf("Final number of PlantEaters: %d%n", plantEaters2.size());
        System.out.printf("Final number of MeatEaters: %d%n", meatEatersL.size());
        System.out.printf("Final number of Plants: %d%n", plants3.size());
        System.out.printf("Total mass of PlantEaters: %.2f grams%n", totalMass(plantEaters2));
        System.out.printf("Total mass of MeatEaters: %.2f grams%n", totalMass(meatEatersL));
        System.out.printf("Total mass of Plants: %.2f grams%n", totalMass(plants3));

        System.out.println("\n----------TestMeatEaters----------");

        // Create a list of 1000 PlantEaters objects with random sizes and fixed growth
        // rate
        ArrayList<PlantEater> plantEaters3 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            double size = 1000 + rand.nextDouble() * 200; // Random size between 1000 and 1200 grams
            double growthRate = 3; // Fixed growth rate of 3 grams/day
            plantEaters3.add(new PlantEater(size, growthRate, plants2));
        }

        // Instantiate one MeatEater object with random size, fixed growth rate, and
        // prey list
        double meatEaterSize = 2000 + rand.nextDouble() * 500; // Random size between 2000 and 2500 grams
        double meatEaterGrowthRate = 2; // Fixed growth rate of 2 grams/day
        MeatEater meatEater = new MeatEater(meatEaterSize, meatEaterGrowthRate, plantEaters3);

        // Test A: Check initial values of the MeatEater
        System.out.println("Test A: Initial Values of MeatEater");
        System.out.println("MeatEater size: " + meatEater.getSize());
        System.out.println("MeatEater growth rate: " + meatEater.getGrowthRate());
        System.out.println("MeatEater food need: " + meatEater.getFoodNeed());
        System.out.println("MeatEater is alive: " + meatEater.isAlive());
        System.out.println();

        // Test B: Test the hunt method
        // MeatEater should hunt PlantEaters and eat them. Let's test hunting a few
        // PlantEaters.
        System.out.println("Test B: Testing hunt method");
        System.out.println("PlantEater size before hunt: " + plantEaters3.get(0).getSize()); // Initial size of the
                                                                                             // first PlantEater
        meatEater.hunt(plantEaters3.get(0)); // Hunt the first PlantEater
        System.out.println("After hunting the first PlantEater:");
        System.out.println("MeatEater size: " + meatEater.getSize());
        System.out.println("PlantEater size after hunt: " + plantEaters3.get(0).getSize()); // Size of the first
                                                                                            // PlantEater after hunting
        System.out.println();

        // Test C: Simulate one day
        System.out.println("Test C: Simulating one day for MeatEater");
        meatEater.simulateDay();
        System.out.println("After one day of simulation:");
        System.out.println("MeatEater size: " + meatEater.getSize());
        System.out.println("MeatEater age: " + meatEater.getAge());
        System.out.println("MeatEater food need: " + meatEater.getFoodNeed());
        System.out.println("Is MeatEater still alive? " + meatEater.isAlive());
        System.out.println();

        // Test D: Check if any PlantEaters are still alive
        System.out.println("Test D: Checking if any PlantEaters are alive");
        System.out.println("Is any PlantEater alive? " + stillAlive(plantEaters3));
        System.out.println();

        // Test E: Total mass of all PlantEaters and the MeatEater
        System.out.println("Test E: Total mass of all PlantEaters and MeatEater");
        double totalMass2 = totalMass(plantEaters3) + meatEater.getSize();
        System.out.println("Total mass of all PlantEaters and MeatEater: " + String.format("%.2f", totalMass2));

    }
}