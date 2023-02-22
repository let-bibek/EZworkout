package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private static final String ROOT_FRAGMENT_TAG = "root_fragment";
    BottomNavigationView bnvBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnvBottomNav = findViewById(R.id.bnvBottomNav);
//        default fragment
        loadFragment(new WorkoutsFragment(), 0);

        bnvBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                get id of current selected item
                int id = item.getItemId();
                if (id == R.id.menu_item_workouts) {
                    Fragment fragment = new WorkoutsFragment();
                    loadFragment(fragment, 0);
                } else if (id == R.id.menu_item_diet) {
                    Fragment fragment = new DietsFragment();
                    loadFragment(fragment, 1);
                } else if (id == R.id.menu_item_advice) {
                    Fragment fragment = new AdviceFragment();
                    loadFragment(fragment, 1);
                } else if (id == R.id.menu_item_workout_routine) {
                    Fragment fragment = new RoutineFragment();
                    loadFragment(fragment, 1);
                } else {
                    loadFragment(new OthersFragment(), 1);
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment, int flag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //        data passing in fragment

//        Bundle bundle=new Bundle();
//        bundle.putString("fragment","This is data passed using fragment");
//        bundle.putInt("arg1",52);
//        fragment.setArguments(bundle);

        if (flag == 0) {
            ft.add(R.id.flBottomNav, fragment);
            fm.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        } else {
            ft.replace(R.id.flBottomNav, fragment);
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}