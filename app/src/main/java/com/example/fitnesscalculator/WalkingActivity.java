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

public class WalkingActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_walking);

        ListView listView = (ListView) findViewById(R.id.walkinglist);

        //Initializes an array list that will be used to store
        //the preset exercises
        final ArrayList<Exercises> walkingex = new ArrayList<>();

        //Creates exercise objects
        Exercises e1 = new Exercises("Backpacking", 134);
        Exercises e2 = new Exercises("Climbing Hills with 0 - 9 lbs", 134);
        Exercises e3 = new Exercises("Climbing Hills with 10 to 20 lbs", 145);
        Exercises e4 = new Exercises("Climbing Hills with 21 to 42 lbs", 156);
        Exercises e5 = new Exercises("Climbing Hills with 42 lbs and heavier", 178);
        Exercises e6 = new Exercises("Walking less than 2 mph", 22);
        Exercises e7 = new Exercises("Walking at 2.5 mph", 45);
        Exercises e8 = new Exercises("Walking at 2.5 mph, Downhill",20);
        Exercises e9 = new Exercises("Walking at 3 mph", 51);
        Exercises e10 = new Exercises("Walking at 3.5 mph",62);
        Exercises e11 = new Exercises("Walking at 3.5 mph, Uphill", 111);
        Exercises e12 = new Exercises("Walking at 4 mph",89);
        Exercises e13 = new Exercises("Walking at 4.5 mph", 118);
        Exercises e14 = new Exercises("Walking at 5 mph",156);

        //Populates the walkingex array list with the previously created
        //exercises
        walkingex.add(e1);
        walkingex.add(e2);
        walkingex.add(e3);
        walkingex.add(e4);
        walkingex.add(e5);
        walkingex.add(e6);
        walkingex.add(e7);
        walkingex.add(e8);
        walkingex.add(e9);
        walkingex.add(e10);
        walkingex.add(e11);
        walkingex.add(e12);
        walkingex.add(e13);
        walkingex.add(e14);

        selectedExArrayList = new ArrayList<>();

        ArrayAdapter<Exercises> adapter = new ArrayAdapter<Exercises>(this, android.R.layout.simple_list_item_1, walkingex);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Stores the exercise name and calories of the selected exercise
                Exercises e = walkingex.get(position);
                String name = e.getName();
                int calories = e.getCalories();

                //Message that alerts the user that they have added an exercise to the workout
                Toast.makeText(getApplicationContext(), "You added the " + name + " exercise to the workout", Toast.LENGTH_LONG).show();

                //Saves list of selected exercises and sends it to the WalkingWorkout Activity
                selectedExArrayList.add(name);  //Adds exercise name to the array list
                selectedExArray = new String[selectedExArrayList.size()];   //Initializes array with the size of the array list
                selectedExArray = selectedExArrayList.toArray(selectedExArray);   //Converts from array to arrayList
                saveArray(selectedExArray, selectedEx, WalkingActivity.this);   //Sends array to WalkingWorkout Activity

                //Saves calories counted from the selected exercises so that they can be sent to the viewCaloriesActivity
                currentCalorieCount += calories;
                saveWalkingCalories(currentCalorieCount, selectedExCals, WalkingActivity.this);
            }
        });
    }

    //Function used to save the array of selected exercises
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("walkingex", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);    //Adds selected items to array before being sent to other activity
        return editor.commit();
    }

    //Function used to save accumulated amount of calories
    public boolean saveWalkingCalories(int cur_cals, String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("walkingcals", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(intName, cur_cals);
        return editor.commit();
    }
}