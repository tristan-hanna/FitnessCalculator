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

public class SwimmingActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_swimming);

        ListView listView = (ListView) findViewById(R.id.swimminglist);

        //Initializes an array list that will be used to store
        //the preset exercises
        final ArrayList<Exercises> swimmingex = new ArrayList<>();

        //Creates exercise objects
        Exercises e6 = new Exercises("Backstroke", 134);
        Exercises e5 = new Exercises("Breaststroke", 200);
        Exercises e4 = new Exercises("Butterfly Stroke", 223);
        Exercises e10 = new Exercises("Canoeing at 2 - 3.9 mph",45);
        Exercises e11 = new Exercises("Canoeing at 4 - 5.9 mph", 134);
        Exercises e12 = new Exercises("Canoeing at greater than 6 mph", 245);
        Exercises e8 = new Exercises("Freestyle, Vigorous Effort", 200);
        Exercises e7 = new Exercises("Freestyle, Moderate Effort", 134);
        Exercises e9 = new Exercises("Scuba Diving", 134);
        Exercises e3 = new Exercises("Sidestroke", 156);
        Exercises e1 = new Exercises("Treading Water, Moderate Effort", 67);
        Exercises e2 = new Exercises("Treading water, Vigorous Effort", 200);

        //Populates the swimmingex array list with the previously created
        //exercises
        swimmingex.add(e1);
        swimmingex.add(e2);
        swimmingex.add(e3);
        swimmingex.add(e4);
        swimmingex.add(e5);
        swimmingex.add(e6);
        swimmingex.add(e7);
        swimmingex.add(e8);
        swimmingex.add(e9);
        swimmingex.add(e10);
        swimmingex.add(e11);
        swimmingex.add(e12);

        selectedExArrayList = new ArrayList<>();

        ArrayAdapter<Exercises> adapter = new ArrayAdapter<Exercises>(this,android.R.layout.simple_list_item_1, swimmingex );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Stores the exercise name and calories of the selected exercise
                Exercises e = swimmingex.get(position);
                String name = e.getName();
                int calories = e.getCalories();

                //Message that alerts the user that they have added an exercise to the workout
                Toast.makeText(getApplicationContext(), "You added the " + name + " exercise to the workout", Toast.LENGTH_LONG).show();

                //Saves list of selected exercises and sends it to the SwimmingWorkout Activity
                selectedExArrayList.add(name);  //Adds exercise name to the array list
                selectedExArray = new String[selectedExArrayList.size()];   //Initializes array with the size of the array list
                selectedExArray = selectedExArrayList.toArray(selectedExArray);   //Converts from array to arrayList
                saveArray(selectedExArray, selectedEx, SwimmingActivity.this);  //Sends array to SwimmingWorkout Activity

                //Saves calories counted from the selected exercises so that they can be sent to the viewCaloriesActivity
                currentCalorieCount += calories;
                saveSwimmingCalories(currentCalorieCount, selectedExCals, SwimmingActivity.this);
            }
        });
    }

    //Function used to save the array of selected exercises
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("swimmingex", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);    //Adds selected items to array before being sent to other activity
        return editor.commit();
    }

    //Function used to save accumulated amount of calories
    public boolean saveSwimmingCalories(int cur_cals, String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("swimmingcals", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(intName, cur_cals);
        return editor.commit();
    }
}