package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView tvUsername;
    private long pressedTime;
    ShimmerFrameLayout shimmerFrameLayout;
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
                } else if (id == R.id.menu_item_plans) {
                    startActivity(new Intent(MainActivity.this, WorkoutPlansActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                } else if (id == R.id.menu_item_advice) {
                    startActivity(new Intent(MainActivity.this, MyAccountActivity.class));
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

        //        shimmer effect
        shimmerFrameLayout=findViewById(R.id.rvShimmer);
        shimmerFrameLayout.startShimmer();


        recyclerViewMain = findViewById(R.id.rvMain);

        databaseReference = FirebaseDatabase.getInstance().getReference("exercises");
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        exerciseModels = new ArrayList<>();
        mainActivityAdapter = new MainActivityAdapter(MainActivity.this, exerciseModels);
        recyclerViewMain.setAdapter(mainActivityAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ExerciseModel exerciseModel = dataSnapshot.getValue(ExerciseModel.class);
                    exerciseModels.add(exerciseModel);
                }
                mainActivityAdapter.notifyDataSetChanged();
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerViewMain.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DATABASE_ERROR", "error");
                System.out.println("error is" + error);
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(MainActivity.this, gso);

        tvUsername = findViewById(R.id.tvUsername);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
        if (account != null) {
            String userName = account.getDisplayName();
            tvUsername.setText(userName);
        }

        findViewById(R.id.cvPowerlifting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("category", "powerlifting");
                startActivity(intent);
            }
        });

        findViewById(R.id.cvStrength).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("category","strength");
                startActivity(intent);
            }
        });

        findViewById(R.id.cvStretching).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("category","stretching");
                startActivity(intent);
            }
        });

        findViewById(R.id.cvStrongman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra("category","strongman");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();




    }
}