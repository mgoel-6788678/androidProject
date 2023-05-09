package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class VolunteersDashboardActivity extends AppCompatActivity {

    Button Logout, Show_events, Mark_attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers_dashboard);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String Email = mAuth.getCurrentUser().getEmail();

        Logout = findViewById(R.id.volunteer_logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent new_intent = new Intent(VolunteersDashboardActivity.this, MainActivity.class);
                startActivity(new_intent);
            }
        });

        Show_events = findViewById(R.id.volunteer_show_events);
        Show_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(VolunteersDashboardActivity.this, ShowEventsActivity.class);
                new_intent.putExtra("Role","Participants");
                startActivity(new_intent);

            }
        });


        Mark_attendance = findViewById(R.id.volunteer_mark_attendance);
        Mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(VolunteersDashboardActivity.this, QrScannerActivity.class);
                startActivity(new_intent);

            }
        });

    }
}
