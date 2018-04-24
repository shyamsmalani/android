package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class AnimalsActivity extends AppCompatActivity
{

    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mAnimalWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(AnimalsActivity.this,MainActivity.class);
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
        if(fwdCounter < mAnimalWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.animal_image);
            nextImage.setImageResource(mAnimalWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(AnimalsActivity.this, mAnimalWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mAnimalWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.animal_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mAnimalWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(AnimalsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.animal_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.animal_image);
            previousImage.setImageResource(mAnimalWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(AnimalsActivity.this, mAnimalWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(AnimalsActivity.this,mAnimalWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.animal_image);
        spellImage.setImageResource(mAnimalWords.get(backCounter).getmWordSpelling());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        //TODO Add words for fruits/edit images/record sentences and pronounciation/spellings
        mAnimalWords.add(new Words(R.drawable.cat,R.drawable.cat_spell,R.raw.cat,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.dog,R.drawable.dog_spell,R.raw.dog,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.elephant,R.drawable.elephant_spell,R.raw.elephant,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.giraffe1,R.drawable.giraffe_spell,R.raw.giraffe,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.goat,R.drawable.goat_spell,R.raw.goat,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.horse,R.drawable.horse_spell,R.raw.horse,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.lion,R.drawable.lion_spell,R.raw.lion,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.monkey,R.drawable.monkey_spell,R.raw.m9nkey,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.rabbit,R.drawable.rabbit_spell,R.raw.rabbit,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.sheep,R.drawable.shee_spell,R.raw.sheep,R.raw.crowsays));
        mAnimalWords.add(new Words(R.drawable.tiger,R.drawable.tiger_spell,R.raw.tiger,R.raw.crowsays));

        //When clicked next, should load next image and play sound
        Button mAnimalNext = (Button)findViewById(R.id.animal_next);
        mAnimalNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mAnimalSentence = (Button) findViewById(R.id.animal_sentence);
        mAnimalSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mAnimalSpelling = (Button) findViewById(R.id.animal_spelling);
        mAnimalSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mAnimalBack = (Button) findViewById(R.id.animal_back);
        mAnimalBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mAnimalHome = (Button) findViewById(R.id.animal_home);
        mAnimalHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });
    }
}
