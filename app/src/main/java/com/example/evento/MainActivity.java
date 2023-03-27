package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Participants, Volunteers, Organizers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Participants = (Button) findViewById(R.id.Participants);
        Volunteers = (Button) findViewById(R.id.Volunteers);
        Organizers = (Button) findViewById(R.id.Organizers);

        Participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipantsLoginActiviy.class);
                intent.putExtra("Role", "Participants");
                startActivity(intent);
            }
        });

        Volunteers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipantsLoginActiviy.class);
                intent.putExtra("Role", "Volunteers");
                startActivity(intent);
            }
        });

        Organizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipantsLoginActiviy.class);
                intent.putExtra("Role", "Organizers");
                startActivity(intent);
            }
        });
    }
}