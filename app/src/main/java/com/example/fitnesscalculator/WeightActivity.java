package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WeightActivity extends AppCompatActivity {

    //Variable for accumulated calorie count after each exercise is added to
    //the workout list
    int currentCalorieCount;

    //Keys that will be used for Shared Preferences
    String selectedEx;
    String selectedExCals;

    //Array of Strings and array list used to store the selected exercises
    String[] selectedExArray;
    ArrayList<String> selectedExArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        ListView listView = (ListView) findViewById(R.id.weightlist);

        //Initializes an array list that will be used to store
        //the preset exercises
        final ArrayList<Exercises> weightex = new ArrayList<>();

        //Creates exercise objects
        Exercises e1 = new Exercises("Push-ups", 156);
        Exercises e2 = new Exercises("Sit-ups", 156);
        Exercises e3 = new Exercises("Jumping Jacks", 156);
        Exercises e4 = new Exercises("Pull-ups", 222);
        Exercises e5 = new Exercises("Lunges", 294);

        //Populates the weightex array list with the previously created
        //exercises
        weightex.add(e1);
        weightex.add(e2);
        weightex.add(e3);
        weightex.add(e4);
        weightex.add(e5);

        selectedExArrayList = new ArrayList<>();

        ArrayAdapter<Exercises> adapter = new ArrayAdapter<Exercises>(this, android.R.layout.simple_list_item_1, weightex);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Stores the exercise name and calories of the selected exercise
                Exercises e = weightex.get(position);
                String name = e.getName();
                int calories = e.getCalories();

                //Message that alerts the user that they have added an exercise to the workout
                Toast.makeText(getApplicationContext(), "You added the " + name + " exercise to the workout", Toast.LENGTH_LONG).show();

                //Saves list of selected exercises and sends it to the WeightWorkout Activity
                selectedExArrayList.add(name);  //Adds exercise name to the array list
                selectedExArray = new String[selectedExArrayList.size()];   //Initializes array with the size of the array list
                selectedExArray = selectedExArrayList.toArray(selectedExArray);   //Converts from array to arrayList
                saveArray(selectedExArray, selectedEx, WeightActivity.this);    //Sends array to WeightWorkout Activity

                //Saves calories counted from the selected exercises so that they can be sent to the viewCaloriesActivity
                currentCalorieCount += calories;
                saveCalisthenicsCalories(currentCalorieCount, selectedExCals, WeightActivity.this);
            }
        });
    }

    //Function used to save the array of selected exercises
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("weightex", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);    //Adds selected items to array before being sent to other activity
        return editor.commit();
    }

    //Function used to save accumulated amount of calories
    public boolean saveCalisthenicsCalories(int cur_cals, String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("calisthenicscals", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(intName, cur_cals);
        return editor.commit();
    }
}