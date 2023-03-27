package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ParticipantsDashboadActivity extends AppCompatActivity {

    Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_dashboad);

        Intent intent = getIntent();
        String Email = intent.getStringExtra("Email").toString();

        TextView participant_email = findViewById(R.id.participant_email_textView);
        participant_email.setText("Welcome "+Email);

        Logout = findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent new_intent = new Intent(ParticipantsDashboadActivity.this, MainActivity.class);
                startActivity(new_intent);
            }
        });

    }
}