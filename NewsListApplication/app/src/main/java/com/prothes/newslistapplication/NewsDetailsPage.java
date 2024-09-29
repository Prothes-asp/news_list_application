package com.prothes.newslistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewsDetailsPage extends AppCompatActivity {
    public static String titles = "";
    public static String detailss = "";
    public static String catagorys = "";
    public static Bitmap bitmaps = null;
    private ImageView newsDetailsImageView;
    private TextView newsDetailsTitle,newsDetailsdetails,newsDetailsCatagory;
    private FloatingActionButton floatingReadBtn;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        setContentView(R.layout.news_details_page);

        floatingReadBtn = findViewById(R.id.floatingReadBtn);
        newsDetailsCatagory = findViewById(R.id.newsDetailsCatagory);
        newsDetailsdetails = findViewById(R.id.newsDetailsdetails);
        newsDetailsTitle = findViewById(R.id.newsDetailsTitle);
        newsDetailsImageView = findViewById(R.id.newsDetailsImageView);

        newsDetailsCatagory.setText(""+catagorys);
        newsDetailsdetails.setText(""+detailss);
        newsDetailsTitle.setText(""+titles);

        if (bitmaps != null){
            newsDetailsImageView.setImageBitmap(bitmaps);
        }

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        floatingReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getData = newsDetailsdetails.getText().toString();
                textToSpeech.speak(getData,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop();
    }
}