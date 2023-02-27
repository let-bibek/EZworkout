package com.softdrax.ezworkout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
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
        getSupportActionBar().setTitle("");

        recyclerViewMain = findViewById(R.id.rvMain);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_with_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search your favourite exercise...");
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
        return super.onCreateOptionsMenu(menu);
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}