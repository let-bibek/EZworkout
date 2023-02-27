package com.softdrax.ezworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button btnSignOut;
    TextView tvMyAccountUserName, tvUserEmail;
    ListView lvSettings;
    String[] settings={"Subscribe","Message us","Terms and conditions","Check for updates","About us"};
    int[] settingsIcons={R.drawable.ic_baseline_receipt_24,R.drawable.ic_baseline_message_24,R.drawable.ic_baseline_book_24,R.drawable.ic_baseline_system_update_24,R.drawable.ic_baseline_info_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        setting
        lvSettings=findViewById(R.id.lvSettings);
        AccountSettingAdapter accountSettingAdapter=new AccountSettingAdapter();

        lvSettings.setAdapter(accountSettingAdapter);




//        google authentication
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(MyAccountActivity.this, gso);
        btnSignOut = findViewById(R.id.btnSignOut);
        getSupportActionBar().setTitle("Account");
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MyAccountActivity.this);
        tvMyAccountUserName = findViewById(R.id.tvMyAccountUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        ImageView ivUserImage = (ImageView) findViewById(R.id.ivUserImage);
        if (account != null) {
            String userName = account.getDisplayName();
            String userEmail = account.getEmail();
            Uri imageUri = account.getPhotoUrl();
            if (imageUri != null) {
                Glide.with(this).load(imageUri).into(ivUserImage);
            } else {
                Glide.with(this).load("https://th.bing.com/th/id/OIP.w-L3HP_7QYalYXw7apT2tAHaHx?pid=ImgDet&rs=1").into(ivUserImage);
            }
            tvMyAccountUserName.setText(userName);
            tvUserEmail.setText(userEmail);
        }


    }

    private void signOutUser() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(MyAccountActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private class AccountSettingAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return settingsIcons.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.settings_list,null);
            TextView textView=view.findViewById(R.id.tvSetting);
            ImageView imageView=view.findViewById(R.id.ivSetting);
            textView.setText(settings[position]);
            imageView.setImageResource(settingsIcons[position]);
            return view;
        }
    }
}