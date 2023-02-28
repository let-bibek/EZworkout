package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softdrax.ezworkout.adapter.MainActivityAdapter;
import com.softdrax.ezworkout.model.ExerciseModel;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    String categoryName;
    TextView tvCategoryName;
    RecyclerView recyclerViewMain;
    DatabaseReference databaseReference;
    MainActivityAdapter mainActivityAdapter;
    ArrayList<ExerciseModel> exerciseModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent getIntent = getIntent();
        categoryName = getIntent.getStringExtra("category").toUpperCase();
        tvCategoryName = findViewById(R.id.tvCategoryName);
        tvCategoryName.setText(categoryName);

        recyclerViewMain = findViewById(R.id.rvMain);
        databaseReference = FirebaseDatabase.getInstance().getReference(getIntent.getStringExtra("category"));
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

        exerciseModels = new ArrayList<>();
        mainActivityAdapter = new MainActivityAdapter(CategoryActivity.this, exerciseModels);
        recyclerViewMain.setAdapter(mainActivityAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ExerciseModel exerciseModel = dataSnapshot.getValue(ExerciseModel.class);
                    exerciseModels.add(exerciseModel);
                }
                mainActivityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DATABASE_ERROR", "error");
                System.out.println("error is" + error);
            }
        });

    }
}