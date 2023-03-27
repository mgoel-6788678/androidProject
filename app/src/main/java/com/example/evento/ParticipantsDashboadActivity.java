package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ParticipantsDashboadActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_dashboad);

        Intent intent = getIntent();
        String Email = intent.getStringExtra("Email").toString();

        TextView participant_email = findViewById(R.id.participant_email_textView);
        participant_email.setText("Welcome "+Email);

    }
}