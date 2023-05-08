package com.example.evento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ParticipantsDashboadActivity extends AppCompatActivity {

    Button Logout, Show_events, Show_qr;
    private FloatingActionButton chatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_dashboad);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String Email = mAuth.getCurrentUser().getEmail();

        TextView participant_email = findViewById(R.id.participant_email_textView);
        participant_email.setText("Welcome "+Email);

        Logout = findViewById(R.id.participants_logout);
        chatButton = findViewById(R.id.chatBox);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatBoxFragment chatboxFragment = new ChatBoxFragment();
                chatboxFragment.show(getSupportFragmentManager(), "chatbox");
            }
        });
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


    public static class ChatBoxFragment extends DialogFragment {
        private EditText chatInput;
        private TextView chatHistory;

        private FirebaseUser fUser;
        private DatabaseReference dbRef;

        ChatClass chat = null;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_chat_box, container, false);

            chatInput = view.findViewById(R.id.chat_input);
            chatHistory = view.findViewById(R.id.chat_history);
            fUser = FirebaseAuth.getInstance().getCurrentUser();
            dbRef = FirebaseDatabase.getInstance().getReference("Chats");

            // fetch data from chat and put it in the editText chathistory

            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    chat = snapshot.getValue(ChatClass.class);
                    if (chat != null) {
                        Log.d("Chat", chat.toString());
                        chatHistory.setText(chat.toString());
                    }else {
                        Log.d("ChatError", "Failed");
                        chat = new ChatClass();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("ChatError", "Error retrieving chat");
                    chat = new ChatClass();
                }
            });

            // Add logic for the chatbox UI elements here
            // Set the layout parameters of the root view to fill the available space
            Window window = Objects.requireNonNull(getDialog()).getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // Get references to the UI elements

            // set chatHistory

            // Set up the send button
            Button sendButton = view.findViewById(R.id.send_button);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the text from the chat input field
                    String message = chatInput.getText().toString().trim();

                    if (!message.isEmpty()) {
                        // Append the message to the chat history
                        chatHistory.append("You: " + message + "\n");
                        chat.appendChat(fUser.getEmail(), message);

                        // add data to the database

                        dbRef.setValue(chat);

                        // Clear the chat input field
                        chatInput.setText("");
                    }
                }
            });

            return view;
        }
    }
}