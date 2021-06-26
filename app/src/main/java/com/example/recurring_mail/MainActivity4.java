package com.example.recurring_mail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {


    EditText title;
    EditText location;
    EditText description;
    Button addEvent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main4);

        title=findViewById(R.id.etitle);
        location=findViewById(R.id.elocation);
        description=findViewById(R.id.edescription);
        addEvent= findViewById(R.id.btnAdd);

        addEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description
                        .getText().toString().isEmpty()) {

                    Intent intent=new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY,true);
                    intent.putExtra(Intent.EXTRA_EMAIL,"tejasnazre12@gmail.com");


                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity4.this, "There is no app that can support this action",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MainActivity4.this, "please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}