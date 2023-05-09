package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class ShowEventsActivity extends AppCompatActivity implements Clickable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        Intent intent = getIntent();
        String Role = intent.getStringExtra("Role");


        if(savedInstanceState == null) {
            // Creating initial ListFragment -
            ShowEventsFragment mShowEventsFragment = new ShowEventsFragment();

            // For Passing data from ShowActivty to ListFragment - using Bundle
            Bundle mBundle = new Bundle();
            mBundle.putString("Role", Role);
            // Setting arguments -
            mShowEventsFragment.setArguments(mBundle);

            // Doing Fragment Transaction
            FragmentTransaction new_transaction = getSupportFragmentManager().beginTransaction();
            new_transaction.add(R.id.show_events_container, mShowEventsFragment);
            new_transaction.commit();
        }
    }

    @Override
    public void openEventDetails() {
        Intent intent = new Intent(this, EventsDetailPage.class);
        startActivity(intent);
    }
}