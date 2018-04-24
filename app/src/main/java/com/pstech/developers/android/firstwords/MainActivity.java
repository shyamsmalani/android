package com.pstech.developers.android.firstwords;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private MediaPlayer mMediaPlayer;

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find the button that shows numbers
        Button number = (Button) findViewById(R.id.button_numbers);
        //Set a listener on the button number
        number.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the number screen
                Toast.makeText(view.getContext(),"Open the list of numbers", Toast.LENGTH_SHORT).show();
                Intent numberIntent = new Intent(MainActivity.this,NumberActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                mMediaPlayer.start();
                startActivity(numberIntent);
             }
        });

        //Find the button that shows numbers
        Button colors = (Button) findViewById(R.id.button_colors);
        //Set a listener on the button color
        colors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the number screen
                Toast.makeText(view.getContext(),"Open the list of colors", Toast.LENGTH_SHORT).show();
                Intent colorIntent = new Intent(MainActivity.this,ColorsActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.black);
                mMediaPlayer.start();
                startActivity(colorIntent);
            }
        });

        //Find the button that shows vegetable
        Button vegetables = (Button) findViewById(R.id.button_vegetables);
        //Set a listener on the textview number
        vegetables.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the vegetable screen
                Toast.makeText(view.getContext(),"Open the list of vegetable", Toast.LENGTH_SHORT).show();
                Intent vegetableIntent = new Intent(MainActivity.this,VegetablesActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.brinjal);
                mMediaPlayer.start();
                startActivity(vegetableIntent);
            }
        });

        //Find the button that shows fruits
        Button fruits = (Button) findViewById(R.id.button_fruits);
        //Set a listener on the textview number
        fruits.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the fruits screen
                Toast.makeText(view.getContext(),"Open the list of fruits", Toast.LENGTH_SHORT).show();
                Intent fruitIntent = new Intent(MainActivity.this,FruitsActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.apple);
                mMediaPlayer.start();
                startActivity(fruitIntent);
            }
        });

        //Find the button that shows animals
        Button animal = (Button) findViewById(R.id.button_animals);
        //Set a listener on the button animal
        animal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the animal screen
                Toast.makeText(view.getContext(),"Open the list of animals", Toast.LENGTH_SHORT).show();
                Intent animalIntent = new Intent(MainActivity.this,AnimalsActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.cat);
                mMediaPlayer.start();
                startActivity(animalIntent);
            }
        });

        //Find the button that shows birds
        Button birds = (Button) findViewById(R.id.button_birds);
        //Set a listener on the button birds
        birds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Create a new intent to open the bird screen
                Toast.makeText(view.getContext(),"Open the list of birds", Toast.LENGTH_SHORT).show();
                Intent birdsIntent = new Intent(MainActivity.this,BirdsActivity.class);
                mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.crow);
                mMediaPlayer.start();
                startActivity(birdsIntent);
            }
        });
    }
}