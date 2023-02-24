package com.softdrax.ezworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;
import com.softdrax.ezworkout.databinding.ActivityDietsBinding;
import com.softdrax.ezworkout.databinding.ActivityOthersBinding;

public class OthersActivity extends AppCompatActivity {
ActivityOthersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOthersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}