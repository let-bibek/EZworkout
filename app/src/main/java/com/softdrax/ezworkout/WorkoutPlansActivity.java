package com.softdrax.ezworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WorkoutPlansActivity extends AppCompatActivity {
    ImageView planA, planB, planC, planD, planE, planH, planG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans);
        planA = findViewById(R.id.planA);
        planB = findViewById(R.id.planB);
        planC = findViewById(R.id.planC);
        planD = findViewById(R.id.planD);
        planE = findViewById(R.id.planE);
        planG = findViewById(R.id.planG);
        planH = findViewById(R.id.planH);

        getSupportActionBar().setTitle("Workouts plans");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/09/advanced-workout-plan.png")
                .into(planA);

        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/09/5x5-workout-plan.png")
                .into(planB);

        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/08/6-DAY-PUSH-PULL-LEG-WORKOUT.png")
                .into(planC);
        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/06/20-min-tabata-workout.png")
                .into(planD);
        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/06/STRENGTH-AND-MUSCLE-BUILDING-WORKOUT-for-Women.png")
                .into(planE);
        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/06/8-Week-Muscle-Builder-Workout.png")
                .into(planG);
        Glide.with(this)
                .load("https://fitnessprogramer.com/wp-content/uploads/2021/06/Power-And-Hypertrophy-Workout.png")
                .into(planH);
    }
}