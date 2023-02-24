package com.softdrax.ezworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;
import com.softdrax.ezworkout.databinding.ActivityDietsBinding;
import com.softdrax.ezworkout.databinding.ActivityMainBinding;

public class DietsActivity extends AppCompatActivity {
ActivityDietsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDietsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //        activity switching

        binding.bnvBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_item_workouts) {
                    startActivity(new Intent(DietsActivity.this, WorkoutsActivity.class));
                } else if (id == R.id.menu_item_diet) {
                    startActivity(new Intent(DietsActivity.this, DietsActivity.class));

                } else if (id == R.id.menu_item_advice) {
                    startActivity(new Intent(DietsActivity.this, AdviceActivity.class));

                } else if (id == R.id.menu_item_workout_routine) {
                    startActivity(new Intent(DietsActivity.this, RoutineActivity.class));

                } else {
                    startActivity(new Intent(DietsActivity.this, OthersActivity.class));

                }
                return false;
            }
        });
    }
}