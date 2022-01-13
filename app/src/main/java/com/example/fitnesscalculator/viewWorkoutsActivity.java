package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class viewWorkoutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workouts);
    }

    //Intents that take the user to different screens that show their workout routines
    public void gotoweight (View view)
    {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity (intent);
    }

    public void gotorunning (View view)
    {
        Intent intent = new Intent(this, RunningActivity.class);
        startActivity(intent);
    }

    public void gotoswimming (View view)
    {
        Intent intent = new Intent(this, SwimmingActivity.class);
        startActivity (intent);
    }

    public void gotowalking (View view)
    {
        Intent intent = new Intent(this, WalkingActivity.class);
        startActivity (intent);
    }
}