package com.example;

import java.util.List;
import java.util.Random;

/**
 * Represents a meat-eating creature in the ecosystem.
 * A MeatEater hunts and consumes PlantEaters to sustain itself and grow.
 * It extends the Critter abstract class.
 * 
 * @Author Miguel A. Nunez Palomares
 * @Version 1.0
 * @see java.util.List, java.util.Random
 */
public class MeatEater extends Critter {

    /** List of PlantEaters available as prey. */
    private List<PlantEater> preyList;

    /** Probability of successfully catching prey, set to 55%. */
    private static final double CATCH_PROBABILITY = 0.55; // Adjusted to 55%

    /**
     * Constructs a MeatEater with the specified size, growth rate, and a list of
     * prey.
     * 
     * @param size       Initial size of the MeatEater.
     * @param growthRate Rate at which the MeatEater grows daily.
     * @param preyList   List of PlantEaters available for hunting.
     */
    public MeatEater(double size, double growthRate, List<PlantEater> preyList) {
        super(size, growthRate, size * 0.015, 600); // Food need is 1.5% of size
        this.preyList = preyList;
    }

    /**
     * Hunts a given PlantEater. If the hunt is successful, the prey is eaten and
     * removed from the ecosystem.
     * 
     * @param prey The PlantEater to hunt.
     */
    public void hunt(PlantEater prey) {
        Random rand = new Random();
        if (rand.nextDouble() < CATCH_PROBABILITY) { // Determines if the hunt is successful
            eat(prey.getSize()); // Eat the prey's entire size
            prey.die(); // The prey perishes
        }
    }

    /**
     * Simulates a day in the life of the MeatEater.
     * The MeatEater attempts to hunt and consume prey before continuing its daily
     * cycle.
     */
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

    /**
     * Returns a string representation of the MeatEater, displaying its attributes.
     * 
     * @return A string representation of the MeatEater.
     */
    @Override
    public String toString() {
        return "MeatEater [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", foodNeed=" + getFoodNeed()
                + ", alive=" + isAlive() + ", age=" + getAge() + ", preyListSize=" + preyList.size() + "]";
    }

    /**
     * Returns the probability of death for the MeatEater if it surpasses its
     * lifespan.
     * 
     * @return A 20% probability (0.20) of death each day past its lifespan.
     */
    @Override
    protected double getDeathProbability() {
        return 0.20; // 20% chance of dying each day
    }

}
