package com.example.mr_kajol.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

       private  Button showscrbtn;
        private  EditText Loginname;
        private  TextView showans;

        private  String Name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Score");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showscrbtn = findViewById(R.id.showscrbtn);
        Loginname = findViewById(R.id.loginname);
        showans = findViewById(R.id.showans);

        showscrbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.showscrbtn:
                Name = Loginname.getText().toString();
                Intent intent = getIntent();
                int score ;
                score = intent.getIntExtra("score",0);

                    showans.setText(Name + "'s Score is: "+score);

                myRef.push().setValue(Name + "'s Score is  "+score);

                break;
        }

    }
}
