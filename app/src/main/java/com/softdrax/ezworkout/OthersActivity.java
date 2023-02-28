package com.softdrax.ezworkout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.softdrax.ezworkout.databinding.ActivityOthersBinding;

public class OthersActivity extends AppCompatActivity {
    ActivityOthersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOthersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Other services");
        }

        findViewById(R.id.BMICalculator).setOnClickListener(v -> {
            Bundle b = ActivityOptions.makeSceneTransitionAnimation(OthersActivity.this).toBundle();

            startActivity(new Intent(OthersActivity.this, BMICalculatorActivity.class), b);
        });

    }


}