package com.example;

import java.util.Random;

/**
 * Abstract class representing a generic creature in the ecosystem.
 * Each creature has a size, growth rate, lifespan, and an age.
 * Once a creature's age exceeds its lifespan, there is a probability of death.
 * 
 * @Author: Miguel A. Nunez Palomares
 * @Version: 1.0 
 * @see: java.util.Random
 */

public abstract class Creature {

    /**
     * Size of the creature.
     */
    private double size;

    /**
     * Rate at which the creature grows daily.
     */
    private double growthRate;

    /**
     * Boolean flag to indicate if the creature is alive.
     */
    private boolean alive;

    /**
     * Age of the creature in days.
     */
    private int age;

    /**
     * Maximum lifespan of the creature in days.
     */
    private int lifeSpan;

    /**
     * Constructor for a Creature.
     * 
     * @param size       Initial size of the creature.
     * @param growthRate Rate at which the creature grows daily.
     * @param lifespan   Maximum lifespan of the creature in days.
     */
    public Creature(double size, double growthRate, int lifeSpan) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
        this.lifeSpan = lifeSpan;
    }

    /**
     * Gets the current size of the creature.
     * 
     * @return Rounded size of the creature up to 2 decimal places.
     */
    public double getSize() {
        return Math.round(size * 100.0) / 100.0; // Round to 2 decimal places
    }

    /**
     * Gets the growth rate of the creature.
     * 
     * @return Rounded growth rate of the creature up to 2 decimal places.
     */
    public double getGrowthRate() {
        return Math.round(growthRate * 100.0) / 100.0; // Round to 2 decimal places
    }

    /**
     * Checks if the creature is alive.
     * 
     * @return true if the creature is alive, false otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Gets the current age of the creature in days.
     * 
     * @return Age of the creature.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the lifespan of the creature.
     * 
     * @return Lifespan of the creature in days.
     */
    public int getLifeSpan() {
        return lifeSpan;
    }

    /**
     * Sets the growth rate of the creature.
     * 
     * @param growthRate New growth rate.
     */
    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    /**
     * Changes the size of the creature.
     * If size falls to or below zero, the creature dies.
     * 
     * @param amount The amount to change the size by.
     */
    public void changeSize(double amount) {
        this.size += amount;
        if (this.size <= 0) {
            die();
        }
    }

    /**
     * Simulates one day in the creature's life.
     * The creature grows and ages.
     * If it exceeds its lifespan, it has a chance of dying.
     */
    public void simulateDay() {
        if (alive) {
            changeSize(growthRate);
            age++;
            checkLifespan();
        }
    }

    /**
     * Handles the creature's death by setting attributes accordingly.
     */
    public void die() {
        this.size = 0;
        this.growthRate = 0;
        this.alive = false;
    }

    /**
     * Checks if the creature has reached its lifespan and if it should die.
     */
    private void checkLifespan() {
        if (age >= lifeSpan) {
            Random rand = new Random();
            double probability = getDeathProbability();
            if (rand.nextDouble() <= probability) {
                die();
            }
        }
    }

    /**
     * Abstract method to get the death probability of the creature.
     * 
     * @return The probability of the creature dying each day.
     */
    protected abstract double getDeathProbability();

}
