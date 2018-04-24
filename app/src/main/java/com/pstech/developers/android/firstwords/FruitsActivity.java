package com.pstech.developers.android.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class FruitsActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;
    private int fwdCounter = 1;
    private int backCounter=0;
    private ArrayList<Words> mFruitWords = new ArrayList<Words>();
    private boolean restartedFlag;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent mainIntent = new Intent(FruitsActivity.this,MainActivity.class);
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
        if(fwdCounter < mFruitWords.size())
        {
            ImageView nextImage = (ImageView) findViewById(R.id.fruit_image);
            nextImage.setImageResource(mFruitWords.get(fwdCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(FruitsActivity.this, mFruitWords.get(fwdCounter).getmWordAudio());
            mMediaPlayer.start();
            fwdCounter++;
            backCounter++;
        }
        if(fwdCounter >= mFruitWords.size())
        {
            Button hideNextButton = (Button)findViewById(R.id.fruit_next);
            hideNextButton.setVisibility(View.INVISIBLE);
            fwdCounter=mFruitWords.size();
        }

    }

    private void onClickHomeButton()
    {
        Intent mainIntent = new Intent(FruitsActivity.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
    }

    private void onClickBackButton()
    {
        Button hideNextButton = (Button)findViewById(R.id.fruit_next);
        hideNextButton.setVisibility(View.VISIBLE);
        if(backCounter <= 0)
        {
            onClickHomeButton();
        }
        else
        {
            backCounter--;
            fwdCounter--;
            ImageView previousImage = (ImageView) findViewById(R.id.fruit_image);
            previousImage.setImageResource(mFruitWords.get(backCounter).getmWordImage());
            releaseMediaPlayerObject();
            mMediaPlayer = MediaPlayer.create(FruitsActivity.this, mFruitWords.get(backCounter).getmWordAudio());
            mMediaPlayer.start();
        }

    }

    private void onClickSentenceButton()
    {
        releaseMediaPlayerObject();
        mMediaPlayer = MediaPlayer.create(FruitsActivity.this,mFruitWords.get(backCounter).getmWordSentence());
        mMediaPlayer.start();
    }

    private void onClickSpellingButton()
    {
        ImageView spellImage = (ImageView) findViewById(R.id.fruit_image);
        spellImage.setImageResource(mFruitWords.get(backCounter).getmWordSpelling());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        //TODO Add words for fruits/edit images/record sentences and pronounciation/spellings
        mFruitWords.add(new Words(R.drawable.apples,R.drawable.apple_spell,R.raw.apple,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.bananas2,R.drawable.banana_spell,R.raw.banana,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.blueberries,R.drawable.bluelerry_spell,R.raw.blueberry,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.grapes,R.drawable.grapes_spell,R.raw.grapes,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.figs,R.drawable.fig_spell,R.raw.fig,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.kiwi,R.drawable.kiwi_spell,R.raw.kiwi,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.orange,R.drawable.orange_spell,R.raw.orange,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.pineapple,R.drawable.pineapple_spell,R.raw.pineapple,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.pomogranate,R.drawable.pomegranate_spell,R.raw.pomegranate,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.raspberries,R.drawable.raspberry_spell,R.raw.raspberry,R.raw.crowsays));
        mFruitWords.add(new Words(R.drawable.srawberries,R.drawable.strawberry_spell,R.raw.strawberry,R.raw.crowsays));

        //When clicked next, should load next image and play sound
        Button mFruitNext = (Button)findViewById(R.id.fruit_next);
        mFruitNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickNextButton();
            }
        });

        //When clicked sentence, should play sentence using word
        Button mFruitSentence = (Button) findViewById(R.id.fruit_sentence);
        mFruitSentence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSentenceButton();
            }
        });

        //When clicked spelling, image of spelling should be load
        Button mFruitSpelling = (Button) findViewById(R.id.fruit_spelling);
        mFruitSpelling.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickSpellingButton();
            }
        });

        //When clicked back, should load previous image and play sound
        Button mFruitBack = (Button) findViewById(R.id.fruit_back);
        mFruitBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickBackButton();
            }
        });

        //When clicked home, should load the main activity
        Button mFruitHome = (Button) findViewById(R.id.fruit_home);
        mFruitHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickHomeButton();
            }
        });
    }
}
