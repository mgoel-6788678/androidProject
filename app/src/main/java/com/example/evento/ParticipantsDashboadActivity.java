package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ParticipantsDashboadActivity extends AppCompatActivity {

    Button Logout, Show_events, Show_qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_dashboad);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String Email = mAuth.getCurrentUser().getEmail();

        TextView participant_email = findViewById(R.id.participant_email_textView);
        participant_email.setText("Welcome "+Email);

        Logout = findViewById(R.id.participants_logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent new_intent = new Intent(ParticipantsDashboadActivity.this, MainActivity.class);
                startActivity(new_intent);
            }
        });

        Show_events = findViewById(R.id.participants_show_events);
        Show_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(ParticipantsDashboadActivity.this, ShowEventsActivity.class);
                new_intent.putExtra("Role","Participants");
                startActivity(new_intent);

            }
        });

        Show_qr = findViewById(R.id.participants_qr_code);
        Show_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(ParticipantsDashboadActivity.this, ParticipantShowQrActivity.class);
                startActivity(new_intent);

            }
        });
    }
}