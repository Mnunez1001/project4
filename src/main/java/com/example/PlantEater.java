package com.example;

import java.util.List;
import java.util.Random;

/**
 * Represents a plant-eating creature in the ecosystem.
 * A PlantEater consumes plants to sustain itself and grows over time.
 * It extends the Critter abstract class.
 * 
 * @Author Miguel A. Nunez Palomares
 * @Version 1.0
 * @see java.util.List, java.util.Random
 */

public class PlantEater extends Critter {

    /** List of plants available for consumption. */
    private List<Plant> plants;

    /**
     * Constructs a PlantEater with the specified size, growth rate, and a list of
     * plants it can eat.
     * 
     * @param size       Initial size of the PlantEater.
     * @param growthRate Rate at which the PlantEater grows daily.
     * @param plants     List of plants available for consumption.
     */
    public PlantEater(double size, double growthRate, List<Plant> plants) {
        super(size, growthRate, size * 0.01, 750); // Food need is 1% of size
        this.plants = plants;
    }

    /**
     * Allows the PlantEater to chew on a given plant.
     * The amount chewed is randomly determined within certain constraints.
     * 
     * @param plant The plant to be chewed on.
     */
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

    /**
     * Simulates a day in the life of a PlantEater.
     * The PlantEater selects a number of plants to chew on and consumes food
     * accordingly.
     * It then ages, grows, and updates its food need based on its new size.
     */
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

            // update foodNeed as 1% of the new size
            setFoodNeed(getSize() * 0.01);
        }
    }

    /**
     * Returns a string representation of the PlantEater, displaying its attributes.
     * 
     * @return A string representation of the PlantEater.
     */

    @Override
    public String toString() {
        return "PlantEater [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", foodNeed=" + getFoodNeed()
                + ", alive=" + isAlive() + ", age=" + getAge() + ", plants=" + plants.size() + "]";
    }

    /**
     * Returns the probability of death for the PlantEater if it surpasses its
     * lifespan.
     * 
     * @return A 15% probability (0.15) of death each day past its lifespan.
     */
    @Override
    protected double getDeathProbability() {
        return 0.15; // 15% chance of dying each day
    }

}
