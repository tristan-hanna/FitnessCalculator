package com.example.fitnesscalculator;

//Class for exercises that each have a name and a set amount
//of calories that it burns
public class Exercises {
    private String name;
    private int calories;

    //Default constructor
    public Exercises()
    {
        super();
    }

    //Parameterized constructor
    public Exercises(String name, int calories)
    {
        super();
        this.name = name;
        this.calories = calories;
    }

    //Getter and setter methods for the exercise name and the amount
    //of calories it burns
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    //toString method that displays the number of calories for a given exercise
    @Override
    public String toString() {
        return this.name + " (" + this.calories + " calories)";
    }
}
