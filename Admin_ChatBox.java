package com.android.chattingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chattingapp.Adapters.FragmentsAdapter;
import com.android.chattingapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_ChatBox extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.viewpager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();

        binding.imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin_ChatBox.this, SettingsActivity.class);
                i.putExtra("uid",auth.getUid());
                startActivity(i);

            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(Admin_ChatBox.this,R.style.Dailoge);

                dialog.setContentView(R.layout.logoutdailog_layout);
                TextView yesbtn,nobtn;
                yesbtn=dialog.findViewById(R.id.yesbtn);
                nobtn=dialog.findViewById(R.id.nobtn);

                yesbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();//logout
                        startActivity(new Intent(getApplicationContext(),Welcome.class));
                        finish();
                    }
                });
                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        Toast.makeText(this,"Click back again to exit",Toast.LENGTH_SHORT).show();
        doubleBackToExitPressedOnce=true;
    }
}