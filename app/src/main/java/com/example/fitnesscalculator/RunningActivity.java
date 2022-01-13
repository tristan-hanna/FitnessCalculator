package com.example.fitnesscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RunningActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_running);

        ListView listView = (ListView) findViewById(R.id.runninglist);

        //Initializes an array list that will be used to store
        //the preset exercises
        final ArrayList<Exercises> runningex = new ArrayList<>();

        //Creates exercise objects
        Exercises e1 = new Exercises("150m Sprint", 550);
        Exercises e2 = new Exercises("100m Sprint", 366);
        Exercises e3 = new Exercises("General Jogging", 134);
        Exercises e4 = new Exercises("Jogging in Place", 156);
        Exercises e5 = new Exercises("Running at 5 mph", 156);
        Exercises e6 = new Exercises("Running at 5.2 mph", 178);
        Exercises e7 = new Exercises("Running at 6 mph", 200);
        Exercises e8 = new Exercises("Running at 6.7 mph",223);
        Exercises e9 = new Exercises("Running at 7 mph", 234);
        Exercises e10= new Exercises("Running at 7.5 mph",256);
        Exercises e11= new Exercises("Running at 8 mph",278);
        Exercises e12= new Exercises("Running at 8.6 mph", 289);
        Exercises e13= new Exercises("Running at 9 mph", 312);
        Exercises e14= new Exercises("Running at 10 mph", 334);
        Exercises e15= new Exercises("Running at 10.9 mph",378);
        Exercises e16= new Exercises("Treadmill, Light Effort", 149);
        Exercises e17= new Exercises("Treadmill, High Effort", 200);
        Exercises e18= new Exercises("Treadmill, Vigorous Effort", 312);
        Exercises e19= new Exercises("Water Jogging", 156);

        //Populates the runningex array list with the previously created
        //exercises
        runningex.add(e1);
        runningex.add(e2);
        runningex.add(e3);
        runningex.add(e4);
        runningex.add(e5);
        runningex.add(e6);
        runningex.add(e7);
        runningex.add(e8);
        runningex.add(e9);
        runningex.add(e10);
        runningex.add(e11);
        runningex.add(e12);
        runningex.add(e13);
        runningex.add(e14);
        runningex.add(e15);
        runningex.add(e16);
        runningex.add(e17);
        runningex.add(e18);
        runningex.add(e19);

        selectedExArrayList = new ArrayList<>();

        ArrayAdapter<Exercises> adapter = new ArrayAdapter<Exercises>(this,android.R.layout.simple_list_item_1, runningex );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Stores the exercise name and calories of the selected exercise
                Exercises e = runningex.get(position);
                String name = e.getName();
                int calories = e.getCalories();

                //Message that alerts the user that they have added an exercise to the workout
                Toast.makeText(getApplicationContext(), "You added the " + name + " exercise to the workout", Toast.LENGTH_LONG).show();

                //Saves list of selected exercises and sends it to the RunningWorkout Activity
                selectedExArrayList.add(name);  //Adds exercise name to the array list
                selectedExArray = new String[selectedExArrayList.size()];   //Initializes array with the size of the array list
                selectedExArray = selectedExArrayList.toArray(selectedExArray);   //Converts from array to arrayList
                saveArray(selectedExArray, selectedEx, RunningActivity.this);   //Sends array to RunningWorkout Activity

                //Saves calories counted from the selected exercises so that they can be sent to the viewCaloriesActivity
                currentCalorieCount += calories;
                saveRunningCalories(currentCalorieCount, selectedExCals, RunningActivity.this);

                //Test to check if current calorie count works
                //Log.d("testCals", "The current calories are: " + currentCalorieCount);
            }
        });
    }

    //Function used to save the array of selected exercises
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("runningex", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(arrayName + "_" + i, array[i]);    //Adds selected items to array before being sent to other activity
        return editor.commit();
    }

    //Function used to save accumulated amount of calories
    public boolean saveRunningCalories(int cur_cals, String intName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("runningcals", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(intName, cur_cals);
        return editor.commit();
    }
}