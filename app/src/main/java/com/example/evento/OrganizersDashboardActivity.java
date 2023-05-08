package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class OrganizersDashboardActivity extends AppCompatActivity {

    Button Logout, Add_event, Show_events, Mark_attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers_dashboard);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Logout = findViewById(R.id.organizers_logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent new_intent = new Intent(OrganizersDashboardActivity.this, MainActivity.class);
                startActivity(new_intent);
            }
        });

        Add_event = findViewById(R.id.organizers_add_event);
        Add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(OrganizersDashboardActivity.this, OrganizersAddEventActivity.class);
                startActivity(new_intent);
            }
        });

        Show_events = findViewById(R.id.organizers_show_events);
        Show_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(OrganizersDashboardActivity.this, ShowEventsActivity.class);
                new_intent.putExtra("Role","Participants");
                startActivity(new_intent);

            }
        });


        Mark_attendance = findViewById(R.id.organizers_mark_attendance);
        Mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(OrganizersDashboardActivity.this, QrScannerActivity.class);
                startActivity(new_intent);

            }
        });
    }
}