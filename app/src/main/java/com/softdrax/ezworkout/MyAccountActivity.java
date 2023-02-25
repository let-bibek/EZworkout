package com.softdrax.ezworkout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MyAccountActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(MyAccountActivity.this, gso);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(MyAccountActivity.this);

        if (account!=null){
            String userName=account.getDisplayName();
            String userEmail= account.getEmail();
            Uri imageUri=account.getPhotoUrl();
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
}