package com.softdrax.ezworkout;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softdrax.ezworkout.adapter.MainActivityAdapter;
import com.softdrax.ezworkout.databinding.ActivityWorkoutsBinding;
import com.softdrax.ezworkout.model.ExerciseModel;

import java.util.ArrayList;

public class WorkoutsActivity extends AppCompatActivity {

    RecyclerView recyclerViewMain;
    DatabaseReference databaseReference;
    MainActivityAdapter mainActivityAdapter;
    ArrayList<ExerciseModel> exerciseModels;
ActivityWorkoutsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewMain=findViewById(R.id.rvMain);

        databaseReference= FirebaseDatabase.getInstance().getReference("exercises");
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(WorkoutsActivity.this));

        exerciseModels=new ArrayList<>();
        mainActivityAdapter=new MainActivityAdapter(WorkoutsActivity.this,exerciseModels);
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