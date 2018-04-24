package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mColorsWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(ColorsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayerObject();
    }

    private void releaseMediaPlayerObject()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void onClickNextButton()
    {
        if(fwdCounter < mColorsWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.color_image);
            nextImage.setImageResource(mColorsWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(ColorsActivity.this, mColorsWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mColorsWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.color_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mColorsWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(ColorsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.color_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.color_image);
            previousImage.setImageResource(mColorsWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(ColorsActivity.this, mColorsWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(ColorsActivity.this,mColorsWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.color_image);
        spellImage.setImageResource(mColorsWords.get(backCounter).getmWordSpelling());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        //TODO Add words for fruits/edit images/record sentences and pronounciation/spellings
        mColorsWords.add(new Words(R.drawable.black,R.drawable.black_spell,R.raw.black,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.blue,R.drawable.blue_spell,R.raw.blue,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.brown,R.drawable.brown_spell,R.raw.brown,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.orangecolo,R.drawable.orangecolo_spell,R.raw.orange,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.pink,R.drawable.pink_spell,R.raw.pink,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.red,R.drawable.red_spell,R.raw.red,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.skyblue,R.drawable.skyblue_spell,R.raw.skyblue,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.white,R.drawable.white_spell,R.raw.white,R.raw.crowsays));
        mColorsWords.add(new Words(R.drawable.yellow,R.drawable.yellow_spell,R.raw.yellow,R.raw.crowsays));


        //When clicked next, should load next image and play sound
        Button mColorNext = (Button)findViewById(R.id.color_next);
        mColorNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mColorSentence = (Button) findViewById(R.id.color_sentence);
        mColorSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mColorSpelling = (Button) findViewById(R.id.color_spelling);
        mColorSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mColorBack = (Button) findViewById(R.id.color_back);
        mColorBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mColorHome = (Button) findViewById(R.id.color_home);
        mColorHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });
    }
}
