package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.softdrax.ezworkout.model.ExerciseModel;

import java.util.ArrayList;

public class ExerciseDetails extends AppCompatActivity {
    String instructions, equipment, muscle, difficulty, type, name, gifExercise;
    Toolbar toolbar;
    TextView tvExerciseTitle, tvExerciseType, tvExerciseDifficulty, tvExerciseMuscle, tvExerciseEquipment, tvExerciseInstruction;
    ImageView gifView;
    ShimmerFrameLayout gifViewPlaceholder;

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
        gifView = findViewById(R.id.exerciseGif);
        gifViewPlaceholder = findViewById(R.id.gifViewPlaceholder);

        //step one
        toolbar = findViewById(R.id.tbApp);
        setSupportActionBar(toolbar);


        //step two

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

//        shimmer
        gifViewPlaceholder = findViewById(R.id.gifViewPlaceholder);
        gifViewPlaceholder.startShimmer();


        Intent getIntent = getIntent();
        name = getIntent.getStringExtra("name");
        type = getIntent.getStringExtra("type").toUpperCase();
        difficulty = getIntent.getStringExtra("difficulty").toUpperCase();
        muscle = getIntent.getStringExtra("muscle").toUpperCase();
        equipment = getIntent.getStringExtra("equipment").toUpperCase();
        instructions = getIntent.getStringExtra("instructions");
        gifExercise = getIntent.getStringExtra("gif");
        showInstruction();

    }

    private void showInstruction() {
        tvExerciseTitle.setText(name);
        tvExerciseType.setText(type);
        tvExerciseMuscle.setText(muscle);
        tvExerciseDifficulty.setText(difficulty);
        tvExerciseEquipment.setText(equipment);
        tvExerciseInstruction.setText(instructions);
        Glide.with(ExerciseDetails.this)
                .load(gifExercise)
                .into(gifView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gifViewPlaceholder.stopShimmer();
                gifViewPlaceholder.setVisibility(View.GONE);
                gifView.setVisibility(View.VISIBLE);
            }
        }, 3000);

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
        }
        if (itemId == R.id.menu_item_myAccount) {
            startActivity(new Intent(ExerciseDetails.this, MyAccountActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
}