package com.softdrax.ezworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.softdrax.ezworkout.databinding.ActivityAdviceBinding;
import com.softdrax.ezworkout.databinding.ActivityDietsBinding;

public class AdviceActivity extends AppCompatActivity {
ActivityAdviceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());    }
}