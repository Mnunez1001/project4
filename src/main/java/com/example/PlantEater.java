package com.example;

import java.util.List;
import java.util.Random;

public class PlantEater extends Critter {

    // Attribute: List of plants it may chew on
    private List<Plant> plants;

    // Constructor
    public PlantEater(double size, double growthRate, List<Plant> plants) {
        super(size, growthRate, size * 0.01); // Food need is 1% of size
        this.plants = plants;
    }

    // Method for chewing on a plant
    public void chew(Plant plant) {
        Random rand = new Random();
        double maxAmount = Math.min(Math.min(plant.getSize() / 2, stillNeed()), getFoodNeed() * 0.1);

        if (maxAmount <= 0) {
            return; // Exit if the conditions aren't satisfied
        }

        // Randomly choose an amount to chew on, ensuring it doesn't exceed the maximum
        // allowed
        double amountToChew = rand.nextDouble() * maxAmount;

        // Eat the food and reduce the plant's size
        eat(amountToChew);
        plant.chewedOn(amountToChew);
    }

    // Override simulateDay
    @Override
    public void simulateDay() {
        if (isAlive() && !plants.isEmpty()) { // Ensure there are plants to eat
            Random rand = new Random();

            // Decide how many plants to chew on (between 0.5% and 2% of the total plants)
            // rand.nextInt(max - min) + min generates a random number between min and max
            // (in this case, between 0.5% and 2% of the plant list size).
            // Calculate min and max safely
            int min = Math.max(1, (int) (plants.size() * 0.005)); // Ensure at least 1
            int max = Math.max(min, (int) (plants.size() * 0.02)); // Ensure max is at least min

            // Handle edge cases where there's only one valid choice
            int plantsToChewOn = (min == max) ? min : rand.nextInt(max - min + 1) + min;

            for (int i = 0; i < plantsToChewOn; i++) {
                if (plants.isEmpty())
                    break; // Extra safety check

                // Randomly select a plant to chew on
                Plant plantToChew = plants.get(rand.nextInt(plants.size()));
                chew(plantToChew);
            }

            // Call the super class's simulateDay method
            super.simulateDay();

            //update foodNeed as 1% of the new size
            setFoodNeed(getSize() * 0.01);
        }
    }

   

    // toString method
    @Override
    public String toString() {
        return "PlantEater [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", foodNeed=" + getFoodNeed()
                + ", alive=" + isAlive() + ", age=" + getAge() + ", plants=" + plants.size() + "]";
    }

}
