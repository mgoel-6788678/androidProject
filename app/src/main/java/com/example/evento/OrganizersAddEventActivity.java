package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrganizersAddEventActivity extends AppCompatActivity {

    EditText event_name, event_date, event_time, event_desc, event_loc;
    Button Add_event ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers_add_event);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String Email = mAuth.getCurrentUser().getEmail();

        event_name = findViewById(R.id.organizer_event_name);
        event_date = findViewById(R.id.organizer_event_date);
        event_time = findViewById(R.id.organizer_event_time );
        event_loc = findViewById(R.id.organizer_event_loc);
        event_desc = findViewById(R.id.organizer_event_desc);

        Add_event = findViewById(R.id.organizers_create);

        Add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Event_name = event_name.getText().toString();
                EventsClass new_event = new EventsClass();
                new_event.setEventDate(event_date.getText().toString());
                new_event.setEventTime(event_time.getText().toString());
                new_event.setEventLocation(event_loc.getText().toString());
                new_event.setEventDescription(event_desc.getText().toString());

                // Firebase db
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference node = db.getReference("Events");

                node.child(Event_name).setValue(new_event);
                Users me = new Users(Email);
                node = db.getReference("Events").child(Event_name);
                node.child("Organizers").setValue(me);

                Toast.makeText(OrganizersAddEventActivity.this,"Event Create Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OrganizersAddEventActivity.this, OrganizersDashboardActivity.class);
                startActivity(intent);
            }
        });


    }
}