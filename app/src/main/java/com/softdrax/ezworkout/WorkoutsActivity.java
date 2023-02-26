package com.softdrax.ezworkout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewMain = findViewById(R.id.rvMain);
        searchView = findViewById(R.id.svExercise);
        searchView.clearFocus();
        databaseReference = FirebaseDatabase.getInstance().getReference("exercises");
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(WorkoutsActivity.this));

        exerciseModels = new ArrayList<>();
        mainActivityAdapter = new MainActivityAdapter(WorkoutsActivity.this, exerciseModels);
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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


    }

    private void filterList(String newText) {
        ArrayList<ExerciseModel> filteredText = new ArrayList<>();
        for (ExerciseModel itemList : exerciseModels) {
            if (itemList.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredText.add(itemList);
            }
        }

        if (filteredText.isEmpty()) {
            Toast toast = new Toast(getApplicationContext());
            View getToastView = getLayoutInflater()
                    .inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toastViewGroup));
            toast.setView(getToastView);
            TextView tvMessage = getToastView.findViewById(R.id.tvToastWarning);
            tvMessage.setText("No ecercise found");
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -100);
            toast.show();
        } else {
            mainActivityAdapter.setFilteredList(filteredText);
        }
    }
}