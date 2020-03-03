package com.example.catchtherigby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;

    int score;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;


    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        score = 0;
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImage();




        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("time: " + l / 1000);

            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(image.INVISIBLE);
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Play Again?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //restart
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        }
                    });
                    alert.show();

                }
            }


        }.start();
    }

        public void increaseScore (View view){

            score++;
            scoreText.setText("score : " + score);
        }


        public void hideImage () {

            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    for (ImageView image : imageArray) {
                        image.setVisibility(image.INVISIBLE);
                    }
                    Random random = new Random();
                    int temp = random.nextInt(9);
                    imageArray[temp].setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable, 500);

                }


            };
            handler.post(runnable);

        }



}
