package com.example;

/**
 * Abstract class representing a critter, which is a type of creature that requires food.
 * Critters have a daily food requirement and must consume enough food to survive.
 * it extends the Creature abstract class.
 * 
 * @Author: Miguel A. Nunez Palomares
 * @Version: 1.0 
 */
public abstract class Critter extends Creature {
    /**
     * Daily food requirement of the critter.
     */
    private double foodNeed;

    /**
     * Amount of food eaten by the critter in a day.
     */
    private double foodEaten;

     /**
     * Constructor for a Critter.
     * @param size Initial size of the critter.
     * @param growthRate Rate at which the critter grows daily.
     * @param foodNeed Daily food requirement of the critter.
     * @param lifeSpan Maximum lifespan of the critter in days.
     */
    public Critter(double size, double growthRate, double foodNeed, int lifeSpan) {
        super(size, growthRate, lifeSpan);
        this.foodNeed = foodNeed;
        this.foodEaten = 0;

    }

    /**
     * Gets the daily food requirement of the critter.
     * @return Rounded food requirement.
     */
    public double getFoodNeed() {
        return Math.round(foodNeed * 100.0) / 100.0;
    }

     /**
     * Sets the daily food requirement of the critter.
     * @param foodNeed New food requirement.
     */
    public void setFoodNeed(double foodNeed) {
        this.foodNeed = foodNeed;
    }

     /**
     * Simulates one day in the critter's life.
     * The critter must eat enough food to survive. If it does not meet its food requirement, it dies.
     */
    @Override
    public void simulateDay() {
        if (isAlive()) {
            if (foodEaten < foodNeed) {
                die();
            } else {
                foodEaten = 0;
                super.simulateDay();
            }
        }
    }

      /**
     * Allows the critter to eat a certain amount of food.
     * @param amount The amount of food eaten.
     */
    public void eat(double amount) {
        foodEaten += amount;
    }

      /**
     * Calculates the remaining food needed for the critter to meet its daily requirement.
     * @return Rounded amount of food still needed.
     */
    public double stillNeed() {
        return Math.round((foodNeed - foodEaten) * 100.0) / 100.0;
    }


}
