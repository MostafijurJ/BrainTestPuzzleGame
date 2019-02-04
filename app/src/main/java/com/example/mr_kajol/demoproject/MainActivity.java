package com.example.mr_kajol.demoproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.floorDiv;
import static java.util.Objects.compare;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private  TextView Number1;
    private  TextView Number;
    private TextView Answer;
   private EditText Attempt;
    private  Button scorebtn, submitbtn, skipbtn, ratebtn;
    int score =0;
    int value1;
    int value2;

    private  String temp;

    DatabaseReference databaseReference;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Stored Data");

    DecimalFormat df2 = new DecimalFormat(".##");
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



             Number1 = findViewById(R.id.Number1);
            Number1.setText(""+value1);
            Number = findViewById(R.id.Number);
            Number.setText(""+value2);

           //tv's
            Answer = findViewById(R.id.Answer);
            Attempt = findViewById(R.id.Attempt);

            // buttons
            submitbtn = findViewById(R.id.Submit);
            ratebtn = findViewById(R.id.ratebtn);
            skipbtn = findViewById(R.id.skipbtn);
             scorebtn = findViewById(R.id.scorebtn);



            submitbtn .setOnClickListener(this);
            scorebtn.setOnClickListener(this);
            skipbtn.setOnClickListener(this);
            ratebtn.setOnClickListener(this);

            setNewNumbers();
        }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Submit:{

                try{
                    float userAnswer = Integer.parseInt(Attempt.getText().toString());
                    int add = value1+value2;
                    int sub = abs(value1-value2);
                    int mul = value1 * value2;
                    float div = (float)value1/(float)value2;
                    float pk = div;

                    String ck =  df2.format(div);
                    if(userAnswer == add || userAnswer == sub || userAnswer == mul || userAnswer == pk) {
                        Answer.setText("Correct Answer!\n\n");
                        setNewNumbers ();
                        score++;
                    }
                    else {
                        Answer.setText("Wrong!! \nCorrect Answers: " + add +" or "+sub+" or "+mul+" or "+ck);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Put Answer on the box..",Toast.LENGTH_LONG).show();
                }


                break;
            }

            case  R.id.skipbtn:{
                setNewNumbers();
                break;
            }

            case R.id.scorebtn:{
                Intent intent = new Intent(MainActivity.this,Login.class);

                intent.putExtra("score",score);
                startActivity(intent);

               // Answer.setText("Your Score is : "+score);
                myRef.push().setValue("Data gone, Score is : "+score);
                break;
            }

            case R.id.ratebtn:{
                try {
                    Uri uri = Uri.parse("market://details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (ActivityNotFoundException e){

                    Uri uri = Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                break;
            }

        }

    }

    private void setNewNumbers () {
        Random r = new Random();
        value1 = r.nextInt(20);
        value2 = r.nextInt(20);
        TextView Number1 = findViewById(R.id.Number1);
        Number1.setText(""+value1);
        TextView Number2 = findViewById(R.id.Number);
        Number2.setText(""+value2);
        EditText Attempt = findViewById(R.id.Attempt);
        Attempt.setText("");
    }

}