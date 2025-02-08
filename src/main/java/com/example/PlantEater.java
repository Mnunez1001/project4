package com.example;

import java.util.List;
import java.util.Random;

public class PlantEater extends Critter {

     // Attribute: List of plants it may chew on
    private List<Plant> plants;

    // Constructor
    public PlantEater(double size, double growthRate, double foodNeed, List<Plant> plants) {
        super(size, growthRate, foodNeed);
        this.plants = plants;
    }

    // Method for chewing on a plant
    public void chew(Plant plant) {
        Random rand = new Random();
        double maxAmount = Math.min(Math.min(plant.getSize() / 2, stillNeed()), getFoodNeed() * 0.1);
        
        if (maxAmount <= 0) {
            return; // Exit if the conditions aren't satisfied
        }

        // Randomly choose an amount to chew on, ensuring it doesn't exceed the maximum allowed
        double amountToChew = rand.nextDouble() * maxAmount;
        
        // Eat the food and reduce the plant's size
        eat(amountToChew);
        plant.chewedOn(amountToChew);
    }

    // Override simulateDay
    @Override
    public void simulateDay() {
        if (isAlive()) {
            Random rand = new Random();
            // Decide how many plants to chew on (between 0.5% and 2% of the total plants)
            int plantsToChewOn = rand.nextInt((int)(plants.size() * 0.02) - (int)(plants.size() * 0.005)) + (int)(plants.size() * 0.005);

            for (int i = 0; i < plantsToChewOn; i++) {
                // Randomly select a plant to chew on
                Plant plantToChew = plants.get(rand.nextInt(plants.size()));
                chew(plantToChew);
            }

            // Call the super class's simulateDay method
            super.simulateDay();
        }
    }

    // toString method
    @Override
    public String toString() {
        return "PlantEater [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", foodNeed=" + getFoodNeed() 
                + ", alive=" + isAlive() + ", age=" + getAge() + ", plants=" + plants.size() + "]";
    }

}
