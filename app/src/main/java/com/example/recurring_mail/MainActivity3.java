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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button signInBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);


        mEmail = findViewById(R.id.signin_btn);
        mPass = findViewById(R.id.passsignin);
        mTextView = findViewById(R.id.textView1);
        signInBtn = findViewById(R.id.registration_btn1);

        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
    }
    public void loginUser() {
        String email = mEmail.getText().toString();
        String pass = mEmail.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {

              mAuth.signInWithEmailAndPassword(email,pass)
                      .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                          @Override
                          public void onSuccess(AuthResult authResult) {
                              Toast.makeText(MainActivity3.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(MainActivity3.this,MainActivity4.class));
                              finish();
                          }
                      }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(MainActivity3.this, "Login Failed", Toast.LENGTH_SHORT).show();

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