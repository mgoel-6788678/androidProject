package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class ParticipantsLoginActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_login_activiy);

        Intent intent = getIntent();
        String Role = intent.getStringExtra("Role");

        if(savedInstanceState == null) {
            // Creating initial ListFragment -
            ParticipantsSignupFragment mParticipantsSignupFragment = new ParticipantsSignupFragment();

            // For Passing data from ShowActivty to ListFragment - using Bundle
            Bundle mBundle = new Bundle();
            mBundle.putString("Role", Role);
            // Setting arguments -
            mParticipantsSignupFragment.setArguments(mBundle);

            // Doing Fragment Transaction
            FragmentTransaction new_transaction = getSupportFragmentManager().beginTransaction();
            new_transaction.add(R.id.participants_login_fragment_container, mParticipantsSignupFragment);
            new_transaction.commit();
        }

    }
}