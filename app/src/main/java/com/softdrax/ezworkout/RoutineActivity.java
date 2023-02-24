package com.softdrax.ezworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.softdrax.ezworkout.databinding.ActivityDietsBinding;
import com.softdrax.ezworkout.databinding.ActivityRoutineBinding;

public class RoutineActivity extends AppCompatActivity {
ActivityRoutineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoutineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}