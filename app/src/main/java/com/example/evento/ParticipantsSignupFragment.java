package com.example.evento;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class ParticipantsSignupFragment extends Fragment {

    TextInputLayout p_email, p_pass;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_participants_signup, container, false);

        p_email = (TextInputLayout) view.findViewById(R.id.participants_signup_email);
        p_pass = (TextInputLayout) view.findViewById(R.id.participants_signup_password);

        Button signup = view.findViewById(R.id.signup_participants_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = p_email.getEditText().getText().toString();
                String pass2 = p_pass.getEditText().getText().toString();

                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email2, pass2)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    p_email.getEditText().setText("");
                                    p_pass.getEditText().setText("");
                                    Toast.makeText(getActivity(),"Registered Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    p_email.getEditText().setText("");
                                    p_pass.getEditText().setText("");
                                    Toast.makeText(getActivity(),"Registration Failed, Please Enter Valid Input. Passwords should be atleast 8 characters long.", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        return view;
    }
}