package com.example.evento;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ParticipantsSignupFragment extends Fragment {

    TextInputLayout p_email, p_pass;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_participants_signup, container, false);

        p_email = view.findViewById(R.id.participants_signup_email);
        p_pass = view.findViewById(R.id.participants_signup_password);
        // Getting ARGUMENTS -
        Bundle mBundle = getArguments();
        String Role = mBundle.getString("Role");

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

                                    String uid = mAuth.getCurrentUser().getUid();
                                    Users user = new Users(email2);

                                    // Firebase db
                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    DatabaseReference node = db.getReference("Users").child(Role);

                                    node.child(uid).setValue(user);

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

        TextView already_account = view.findViewById(R.id.already_registered);
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating initial ListFragment -
                ParticipantsLoginFragment mParticipantsLoginFragment = new ParticipantsLoginFragment();

                // For Passing data from ShowActivty to ListFragment - using Bundle
                Bundle mBundle = new Bundle();
                mBundle.putString("Role", Role);
                // Setting arguments -
                mParticipantsLoginFragment.setArguments(mBundle);

                // Doing Fragment Transaction
                FragmentTransaction new_transaction = getFragmentManager().beginTransaction();
                new_transaction.replace(R.id.participants_login_fragment_container, mParticipantsLoginFragment);
                new_transaction.addToBackStack(null);
                new_transaction.commit();
            }
        });


        return view;
    }
}