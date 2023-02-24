package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.softdrax.ezworkout.databinding.ActivityMainBinding;
import com.softdrax.ezworkout.AdviceActivity;
import com.softdrax.ezworkout.DietsActivity;
import com.softdrax.ezworkout.OthersActivity;
import com.softdrax.ezworkout.RoutineActivity;
import com.softdrax.ezworkout.WorkoutsActivity;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnvBottomNav;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        activity switching

        binding.bnvBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_item_workouts) {
                    startActivity(new Intent(MainActivity.this, WorkoutsActivity.class));
                } else if (id == R.id.menu_item_diet) {
                    startActivity(new Intent(MainActivity.this, DietsActivity.class));

                } else if (id == R.id.menu_item_advice) {
                    startActivity(new Intent(MainActivity.this, AdviceActivity.class));

                } else if (id == R.id.menu_item_workout_routine) {
                    startActivity(new Intent(MainActivity.this, RoutineActivity.class));

                } else {
                    startActivity(new Intent(MainActivity.this, OthersActivity.class));

                }
                return false;
            }
        });

    }


}