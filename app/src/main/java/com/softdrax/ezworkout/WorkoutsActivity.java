package com.softdrax.ezworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.softdrax.ezworkout.databinding.ActivityDietsBinding;
import com.softdrax.ezworkout.databinding.ActivityWorkoutsBinding;

public class WorkoutsActivity extends AppCompatActivity {
ActivityWorkoutsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}