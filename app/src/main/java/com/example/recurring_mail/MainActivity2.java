package com.example.recurring_mail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button signUpBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main2);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity5.class));
           /* if you want to finish the first activity then just call
            finish(); */
            }
        });


        mEmail = findViewById(R.id.signin_btn);
        mPass = findViewById(R.id.passreg);
        mTextView = findViewById(R.id.textView1);
        signUpBtn = findViewById(R.id.registration_btn1);

        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();

            }
        });


    }

    public void createUser() {
        String email = mEmail.getText().toString();
        String pass = mEmail.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivity2.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, "Reg Error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                mPass.setError("Empty Fields Not Allowed");
            }
        } else if (email.isEmpty()) {
            mEmail.setError("Empty Fields Not Allowed");
        } else {
            mEmail.setError("Please Enter Correct Email");
        }
    }
}