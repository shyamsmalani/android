package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class VegetablesActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mVeggieWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(VegetablesActivity.this,MainActivity.class);
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
        if(fwdCounter < mVeggieWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.veggie_image);
            nextImage.setImageResource(mVeggieWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(VegetablesActivity.this, mVeggieWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mVeggieWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.veggie_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mVeggieWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(VegetablesActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.veggie_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.veggie_image);
            previousImage.setImageResource(mVeggieWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(VegetablesActivity.this, mVeggieWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(VegetablesActivity.this,mVeggieWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.veggie_image);
        spellImage.setImageResource(mVeggieWords.get(backCounter).getmWordSpelling());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);

        //TODO Add words for vegetable/edit images/record sentences and pronounciation/spellings
        mVeggieWords.add(new Words(R.drawable.brinjal2,R.drawable.brinjal_spell,R.raw.brinjal,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.brocolii,R.drawable.broccoli_spell,R.raw.broccoli,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.capsicum2,R.drawable.bell_pepper_spell,R.raw.bellpepper,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.carrots,R.drawable.carrot_spell,R.raw.carrot,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.corn,R.drawable.corn_spell,R.raw.corn,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.cucumber,R.drawable.cucumber_spell,R.raw.cuxumber,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.garlic,R.drawable.garlic_spell,R.raw.garlic,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.ginger,R.drawable.ginger_spell,R.raw.ginger,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.lemons,R.drawable.lemon_spell,R.raw.lemon,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.onions,R.drawable.onion_spell,R.raw.onion,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.peas,R.drawable.peas_spell,R.raw.peas,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.potatoes,R.drawable.potato_spell,R.raw.potato,R.raw.crowsays));
        mVeggieWords.add(new Words(R.drawable.pumpkin,R.drawable.pumpkin_spell,R.raw.pumpkin,R.raw.crowsays));

        //When clicked next, should load next image and play sound
        Button mVeggieNext = (Button)findViewById(R.id.veggie_next);
        mVeggieNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mVeggieSentence = (Button) findViewById(R.id.veggie_sentence);
        mVeggieSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mVeggieSpelling = (Button) findViewById(R.id.veggie_spelling);
        mVeggieSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mVeggieBack = (Button) findViewById(R.id.veggie_back);
        mVeggieBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mVeggieHome = (Button) findViewById(R.id.veggie_home);
        mVeggieHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });
    }
}
