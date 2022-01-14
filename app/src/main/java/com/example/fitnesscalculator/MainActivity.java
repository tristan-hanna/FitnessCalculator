package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Creates intents that take the user to the different screens
    //that show the user's current workout list
    public void gotorunningworkout (View view)
    {
        Intent intent = new Intent(this, RunningWorkout.class);
        startActivity(intent);
    }

    public void gotoweightworkout (View view)
    {
        Intent intent = new Intent(this, WeightWorkout.class);
        startActivity (intent);
    }

    public void gotoswimmingworkout (View view)
    {
        Intent intent = new Intent(this, SwimmingWorkout.class);
        startActivity (intent);
    }

    public void gotowalkingworkout (View view)
    {
        Intent intent = new Intent(this, WalkingWorkout.class);
        startActivity (intent);
    }

    //Takes the user to the screen that shows the exercises that
    //they can add to their workouts
    public void viewExercises (View view)
    {
        Intent intent = new Intent(this, viewWorkoutsActivity.class);
        startActivity(intent);
    }

    //Takes the user to the screen that shows the amount
    //of calories that they are burning with their different workouts
    public void viewCalories (View view)
    {
        Intent intent = new Intent(this, viewCaloriesActivity.class);
        startActivity(intent);
    }
}