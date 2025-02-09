package com.example;

import java.util.List;
import java.util.Random;

public class MeatEater extends Critter {

    // Attribute: List of PlantEaters it may chase
    private List<PlantEater> preyList;
    private static final double CATCH_PROBABILITY = 0.35; // Adjusted to 35%

    // Constructor
    public MeatEater(double size, double growthRate, List<PlantEater> preyList) {
        super(size, growthRate, size * 0.05); // Food need is 5% of size
        this.preyList = preyList;
    }

    // Method to hunt a PlantEater
    public void hunt(PlantEater prey) {
        Random rand = new Random();
        if (rand.nextDouble() < CATCH_PROBABILITY) { // Determines if the hunt is successful
            eat(prey.getSize()); // Eat the prey's entire size
            prey.die(); // The prey perishes
        }
    }

    // Override simulateDay
    @Override
    public void simulateDay() {
        if (isAlive() && !preyList.isEmpty()) { // Ensure there is prey available
            Random rand = new Random();

            // Determine how many prey to chase (1 or 2)
            int preyToChase = 1 + rand.nextInt(2);

            for (int i = 0; i < preyToChase; i++) {
                if (preyList.isEmpty())
                    break; // Safety check

                // Select a random prey from the list
                PlantEater prey = preyList.get(rand.nextInt(preyList.size()));
                hunt(prey);
            }

            // Call the superclass's simulateDay method
            super.simulateDay();

            // Update foodNeed as 5% of the new size
            setFoodNeed(getSize() * 0.05);
        }
    }

    // toString method
    @Override
    public String toString() {
        return "MeatEater [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", foodNeed=" + getFoodNeed()
                + ", alive=" + isAlive() + ", age=" + getAge() + ", preyListSize=" + preyList.size() + "]";
    }
}
