package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mNumberWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(NumberActivity.this,MainActivity.class);
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
        if(fwdCounter < mNumberWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.number_image);
            nextImage.setImageResource(mNumberWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(NumberActivity.this, mNumberWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mNumberWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.number_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mNumberWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(NumberActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.number_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.number_image);
            previousImage.setImageResource(mNumberWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(NumberActivity.this, mNumberWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(NumberActivity.this,mNumberWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.number_image);
        spellImage.setImageResource(mNumberWords.get(backCounter).getmWordSpelling());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        mNumberWords.add(new Words(R.drawable.one,R.drawable.one_spell,R.raw.one,R.raw.one));
        mNumberWords.add(new Words(R.drawable.two,R.drawable.two_spell,R.raw.two,R.raw.two));
        mNumberWords.add(new Words(R.drawable.three,R.drawable.three_spell,R.raw.three,R.raw.three));
        mNumberWords.add(new Words(R.drawable.four,R.drawable.four_spell,R.raw.four,R.raw.four));
        mNumberWords.add(new Words(R.drawable.five,R.drawable.five_spell,R.raw.five,R.raw.five));
        mNumberWords.add(new Words(R.drawable.six,R.drawable.six_spell,R.raw.six,R.raw.six));
        mNumberWords.add(new Words(R.drawable.seven,R.drawable.seven_spell,R.raw.seven,R.raw.seven));
        mNumberWords.add(new Words(R.drawable.eight,R.drawable.eight_spell,R.raw.eight,R.raw.eight));
        mNumberWords.add(new Words(R.drawable.nine,R.drawable.nine_spell,R.raw.nine,R.raw.nine));
        mNumberWords.add(new Words(R.drawable.zero,R.drawable.ten_spell,R.raw.ten,R.raw.ten));



        //When clicked next, should load next image and play sound
        Button mNumberNext = (Button)findViewById(R.id.number_next);
        mNumberNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mNumberSentence = (Button) findViewById(R.id.number_sentence);
        mNumberSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mNumberSpelling = (Button) findViewById(R.id.number_spelling);
        mNumberSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mNumberBack = (Button) findViewById(R.id.number_back);
        mNumberBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mNumberHome = (Button) findViewById(R.id.number_home);
        mNumberHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });

        ImageView mNumberImage = (ImageView) findViewById(R.id.number_image);
        mNumberImage.setOnTouchListener(new OnSwipeTouchListener(NumberActivity.this)
        {
            public void onSwipeRight()
            {
                onClickBackButton();
            }
            public void onSwipeLeft()
            {
                onClickNextButton();
            }

        });
    }
}