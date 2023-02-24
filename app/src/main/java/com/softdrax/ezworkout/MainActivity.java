package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softdrax.ezworkout.adapter.MainActivityAdapter;
import com.softdrax.ezworkout.databinding.ActivityMainBinding;
import com.softdrax.ezworkout.model.ExerciseModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView recyclerViewMain;
    DatabaseReference databaseReference;
    MainActivityAdapter mainActivityAdapter;
    ArrayList<ExerciseModel> exerciseModels;
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


        recyclerViewMain=findViewById(R.id.rvMain);

        databaseReference= FirebaseDatabase.getInstance().getReference("exercises");
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        exerciseModels=new ArrayList<>();
        mainActivityAdapter=new MainActivityAdapter(MainActivity.this,exerciseModels);
        recyclerViewMain.setAdapter(mainActivityAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ExerciseModel exerciseModel=dataSnapshot.getValue(ExerciseModel.class);
                    exerciseModels.add(exerciseModel);
                }
                mainActivityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DATABASE_ERROR","error");
                System.out.println("error is"+error);
            }
        });

    }



}