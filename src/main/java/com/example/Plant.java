package com.example;

/**
 * Represents a plant in the ecosystem.
 * A plant has a size and a growth rate and can be chewed on by other creatures.
 * Plants have a predefined lifespan, after which they have a probability of dying each day.
 * It extends the Creature abstract class.
 * 
 * @Author Miguel A. Nunez Palomares
 * @Version 1.0
 */
public class Plant extends Creature {

    /**
     * Constructor for a Plant.
     * @param size Initial size of the plant.
     * @param growthRate Growth rate of the plant per day.
     */
    public Plant(double size, double growthRate) {
        super(size, growthRate, 890);
    }

    /**
     * Reduces the size of the plant when it is chewed on.
     * If the amount chewed is greater than the plant's current size,
     * the plant's size is set to zero.
     * @param amount The amount by which the plant's size is reduced.
     */
    public void chewedOn(double amount) {
        if (amount > getSize()) {
            amount = getSize();
        }
        changeSize(-amount);
    }

     /**
     * Returns a string representation of the plant.
     * @return A string describing the plant's attributes.
     */
    @Override
    public String toString() {
        return "Plant [size=" + getSize() + ", growthRate=" + getGrowthRate() + ", alive=" + isAlive() + ", age="
                + getAge() + "]";
    }

    /**
     * Gets the probability of death for a plant once it surpasses its lifespan.
     * @return The probability of death per day past its lifespan.
     */
    @Override
    protected double getDeathProbability() {
        return 0.10; // 10% chance of dying each day
    }
}
