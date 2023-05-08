package com.example.evento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button Participants, Volunteers, Organizers;
    private FloatingActionButton chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

        Participants = findViewById(R.id.Participants);
        Volunteers = findViewById(R.id.Volunteers);
        Organizers = findViewById(R.id.Organizers);
        chatButton = findViewById(R.id.chatBox);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatBoxFragment chatboxFragment = new ChatBoxFragment();
                chatboxFragment.show(getSupportFragmentManager(), "chatbox");
            }
        });

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
    public static class ChatBoxFragment extends DialogFragment {
        private EditText chatInput;
        private TextView chatHistory;

//        @NonNull
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            Dialog dialog = super.onCreateDialog(savedInstanceState);
//
//            // Set the dialog window size
//            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//            lp.copyFrom(dialog.getWindow().getAttributes());
//
//            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            int dialogWidth = (int) (displayMetrics.widthPixels * 0.75); // Set the width to 75% of the screen width
//            int dialogHeight = (int) (displayMetrics.heightPixels * 0.25); // Set the height to 25% of the screen height
//
//            lp.width = 200;
//            lp.height = 200;
//            dialog.getWindow().setAttributes(lp);
//
//            return dialog;
//        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_chat_box, container, false);
            // Add logic for the chatbox UI elements here
            // Set the layout parameters of the root view to fill the available space
            Window window = Objects.requireNonNull(getDialog()).getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // Get references to the UI elements
            chatInput = view.findViewById(R.id.chat_input);
            chatHistory = view.findViewById(R.id.chat_history);

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

                        // Clear the chat input field
                        chatInput.setText("");

                        // Send the message to the chat server (or handle it locally)
                        // TODO: Implement chat server logic here
                    }
                }
            });

            return view;
        }
    }
}