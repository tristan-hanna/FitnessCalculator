package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class viewCaloriesActivity extends AppCompatActivity {

    //Creates variables that store the amount of calories and fat burned
    //by each workout
    int runningCalories;
    double runningFat;

    int calisthenicsCalories;
    double calisthenicsFat;

    int swimmingCalories;
    double swimmingFat;

    int walkingCalories;
    double walkingFat;

    //Declares the TextViews that display the calories and fat burned
    TextView runningCalorieCounter;
    TextView runningFatCounter;

    TextView calisthenicsCalorieCounter;
    TextView calisthenicsFatCounter;

    TextView swimmingCalorieCounter;
    TextView swimmingFatCounter;

    TextView walkingCalorieCounter;
    TextView walkingFatCounter;

    //Key used for Shared Preferences
    String addedCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calories);

        //Initializes the previously declared TextViews
        runningCalorieCounter = findViewById(R.id.runningCalorieCounter);
        runningFatCounter = findViewById(R.id.runningFatCounter);

        calisthenicsCalorieCounter = findViewById(R.id.calisthenicsCalorieCounter);
        calisthenicsFatCounter = findViewById(R.id.calisthenicsFatCounter);

        swimmingCalorieCounter = findViewById(R.id.swimmingCalorieCounter);
        swimmingFatCounter = findViewById(R.id.swimmingFatCounter);

        walkingCalorieCounter = findViewById(R.id.walkingCalorieCounter);
        walkingFatCounter = findViewById(R.id.walkingFatCounter);

        //Retrieves the calories burned from each workout and then calculates
        //the amount of fat that will be lost
        runningCalories = loadRunningCals(addedCals, this);
        runningFat = (double)runningCalories/3500;
        double runningRoundedfat = Math.round(runningFat * 100)/100.0;

        calisthenicsCalories = loadCalisthenicsCals(addedCals, this);
        calisthenicsFat = (double)calisthenicsCalories/3500;
        double calisthenicsRoundedfat = Math.round(calisthenicsFat * 100)/100.0;

        swimmingCalories = loadSwimmingCals(addedCals, this);
        swimmingFat = (double)swimmingCalories/3500;
        double swimmingRoundedfat = Math.round(swimmingFat * 100)/100.0;

        walkingCalories = loadWalkingCals(addedCals, this);
        walkingFat = (double)walkingCalories/3500;
        double walkingRoundedfat = Math.round(walkingFat * 100)/100.0;

        //Displays the burnt calories and lost fat
        runningCalorieCounter.setText("Your current running workout burns "+ runningCalories + " calories");
        runningFatCounter.setText("You'll burn " + runningRoundedfat +" pounds of fat with this workout");

        calisthenicsCalorieCounter.setText("Your current calisthenics workout burns "+ calisthenicsCalories + " calories");
        calisthenicsFatCounter.setText("You'll burn " + calisthenicsRoundedfat +" pounds of fat with this workout");

        swimmingCalorieCounter.setText("Your current swimming workout burns "+ swimmingCalories + " calories");
        swimmingFatCounter.setText("You'll burn " + swimmingRoundedfat +" pounds of fat with this workout");

        walkingCalorieCounter.setText("Your current walking workout burns "+ walkingCalories + " calories");
        walkingFatCounter.setText("You'll burn " + walkingRoundedfat +" pounds of fat with this workout");
    }

    //Functions that retrieve the calories burned for each workout using Shared Preferences
    public int loadRunningCals(String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("runningcals", 0);
        int totalCals = prefs.getInt(intName, 0);
        return totalCals;
    }

    public int loadCalisthenicsCals(String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("calisthenicscals", 0);
        int totalCals = prefs.getInt(intName, 0);
        return totalCals;
    }

    public int loadSwimmingCals(String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("swimmingcals", 0);
        int totalCals = prefs.getInt(intName, 0);
        return totalCals;
    }

    public int loadWalkingCals(String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("walkingcals", 0);
        int totalCals = prefs.getInt(intName, 0);
        return totalCals;
    }
}