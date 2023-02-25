package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.softdrax.ezworkout.model.ExerciseModel;

import java.util.ArrayList;

public class ExerciseDetails extends AppCompatActivity {
    String instructions, equipment, muscle, difficulty, type, name;
    Toolbar toolbar;
    TextView tvExerciseTitle, tvExerciseType, tvExerciseDifficulty, tvExerciseMuscle, tvExerciseEquipment, tvExerciseInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        tvExerciseTitle = findViewById(R.id.tvExerciseTitle);
        tvExerciseType = findViewById(R.id.tvExerciseType);
        tvExerciseDifficulty = findViewById(R.id.tvExerciseDifficulty);
        tvExerciseMuscle = findViewById(R.id.tvExerciseMuscle);
        tvExerciseEquipment = findViewById(R.id.tvExerciseEquipment);
        tvExerciseInstruction = findViewById(R.id.tvExerciseInstruction);

        //step one
        toolbar = findViewById(R.id.tbApp);
        setSupportActionBar(toolbar);

        //step two

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        Intent getIntent = getIntent();
        name = getIntent.getStringExtra("name");
        type = getIntent.getStringExtra("type").toUpperCase();
        difficulty = getIntent.getStringExtra("difficulty").toUpperCase();
        muscle = getIntent.getStringExtra("muscle").toUpperCase();
        equipment = getIntent.getStringExtra("equipment").toUpperCase();
        instructions = getIntent.getStringExtra("instructions");
        showInstruction();

    }

    private void showInstruction() {
        tvExerciseTitle.setText(name);
        tvExerciseType.setText(type);
        tvExerciseMuscle.setText(muscle);
        tvExerciseDifficulty.setText(difficulty);
        tvExerciseEquipment.setText(equipment);
        tvExerciseInstruction.setText(instructions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_item_favourites) {
            Toast.makeText(this, "No favourites", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_item_myAccount) {
            Toast.makeText(this, "Please login", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}