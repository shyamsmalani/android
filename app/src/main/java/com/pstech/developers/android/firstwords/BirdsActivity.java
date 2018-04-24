package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class BirdsActivity extends AppCompatActivity
{

    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mBirdsWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(BirdsActivity.this,MainActivity.class);
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
        if(fwdCounter < mBirdsWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.bird_image);
            nextImage.setImageResource(mBirdsWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(BirdsActivity.this, mBirdsWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mBirdsWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.bird_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mBirdsWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(BirdsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.bird_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.bird_image);
            previousImage.setImageResource(mBirdsWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(BirdsActivity.this, mBirdsWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(BirdsActivity.this,mBirdsWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.bird_image);
        spellImage.setImageResource(mBirdsWords.get(backCounter).getmWordSpelling());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds);
        mBirdsWords.add(new Words(R.drawable.crow,R.drawable.crow_spell,R.raw.crow,R.raw.crowsays));
        mBirdsWords.add(new Words(R.drawable.duck,R.drawable.duck_spell,R.raw.duck,R.raw.ducksays));
        mBirdsWords.add(new Words(R.drawable.eagle1,R.drawable.eagle_spell,R.raw.eagle,R.raw.eaglesays));
        mBirdsWords.add(new Words(R.drawable.flamingo,R.drawable.flamingo_spell,R.raw.flamingo,R.raw.flamingosays));
        mBirdsWords.add(new Words(R.drawable.owl,R.drawable.owl_spell,R.raw.owl,R.raw.owldays));
        mBirdsWords.add(new Words(R.drawable.parrot,R.drawable.parrot_spell,R.raw.parrot,R.raw.six));
        mBirdsWords.add(new Words(R.drawable.sparrow1,R.drawable.sparrow_spell,R.raw.sparrow,R.raw.seven));
        mBirdsWords.add(new Words(R.drawable.pigeon,R.drawable.pigeon_spell,R.raw.pigeon,R.raw.eight));
        mBirdsWords.add(new Words(R.drawable.peacock,R.drawable.peacock_spell,R.raw.peacock,R.raw.nine));
        //TODO Add words for fruits/edit images/record sentences and pronounciation/spellings

        //When clicked next, should load next image and play sound
        Button mBirdNext = (Button)findViewById(R.id.bird_next);
        mBirdNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mBirdSentence = (Button) findViewById(R.id.bird_sentence);
        mBirdSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mBirdSpelling = (Button) findViewById(R.id.bird_spelling);
        mBirdSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mBirdBack = (Button) findViewById(R.id.bird_back);
        mBirdBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mBirdHome = (Button) findViewById(R.id.bird_home);
        mBirdHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });
    }
}
