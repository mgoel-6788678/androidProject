package com.example.evento;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ParticipantsLoginFragment extends Fragment {

    TextInputLayout p_email, p_pass;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_participants_login, container, false);

        p_email = (TextInputLayout) view.findViewById(R.id.participants_login_email);
        p_pass = (TextInputLayout) view.findViewById(R.id.participants_login_password);
        // Getting ARGUMENTS -
        Bundle mBundle = getArguments();
        String Role = mBundle.getString("Role");

        TextView login_role = view.findViewById(R.id.Login_Role);
        login_role.setText("Login as "+Role);

        Button login_participants = view.findViewById(R.id.login_participants_button);

        login_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = p_email.getEditText().getText().toString();
                String pass2 = p_pass.getEditText().getText().toString();
                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email2, pass2)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getActivity(), ParticipantsDashboadActivity.class);
                                    intent.putExtra("Email",mAuth.getCurrentUser().getEmail());
                                    startActivity(intent);
                                } else {
                                    p_email.getEditText().setText("");
                                    p_pass.getEditText().setText("");
                                    Toast.makeText(getActivity(),"Invalid Credentials", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        return view;
    }
}