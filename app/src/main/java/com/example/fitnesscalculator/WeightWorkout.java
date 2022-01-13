package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightWorkout extends AppCompatActivity {

    String userEx;  //Key for size of retrieved array list
    String[] userExArray;   //Array for the user's selected exercises

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_workout);

        ListView listView = (ListView) findViewById(R.id.weightWorkoutView);

        //Loads selected exercises from WeightActivity into an array
        userExArray = loadArray(userEx, this);

        //Converts the array into an array list
        ArrayList<String> currentEx = new ArrayList<String>(Arrays.asList(userExArray));

        //Sets the adapter to display the array list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, currentEx);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    //Function used to retrieve the array of selected exercises from the WeightActivity
    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("weightex", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }
}