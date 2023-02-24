package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;
import com.softdrax.ezworkout.api.AppUtilities;
import com.softdrax.ezworkout.databinding.ActivityMainBinding;
import com.softdrax.ezworkout.model.Exercise;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    TextView tvMainListExerciseData;

    ArrayList<Exercise> exerciseArrayList;
    private RecyclerView recyclerViewOfMain;
    MainActivityAdapter adapter;

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
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.menu_item_diet) {
                    startActivity(new Intent(MainActivity.this, DietsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                } else if (id == R.id.menu_item_advice) {
                    startActivity(new Intent(MainActivity.this, AdviceActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                } else if (id == R.id.menu_item_workout_routine) {
                    startActivity(new Intent(MainActivity.this, RoutineActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                } else if (id == R.id.menu_item_other_services) {
                    startActivity(new Intent(MainActivity.this, OthersActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                }
                return false;
            }
        });

        recyclerViewOfMain=findViewById(R.id.rvMain);
        exerciseArrayList=new ArrayList<>();
        recyclerViewOfMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter =new MainActivityAdapter(MainActivity.this,exerciseArrayList);
        recyclerViewOfMain.setAdapter(adapter);
        getExercise();

    }

    private void getExercise() {
        AppUtilities.getApiInterface().getExercise().enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
               if (response.isSuccessful()){
                   exerciseArrayList.addAll(response.body().getArrayExercise());
                   adapter.notifyDataSetChanged();
                   for (int i=0;i<exerciseArrayList.size();i++){

                   }
               }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {

            }
        });
    }


}