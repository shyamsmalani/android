package com.pstech.developers.android.firstwords;

/**
 * Created by SHYAM on 12/03/2018.
 */

public class Words
{
    private int mWordImage;
    private int mWordSpelling;
    private int mWordAudio;
    private int mWordSentence;

    //@parameter wordImage : id for the word
    //@parameter wordSpelling : id for spelling
    //@parameter wordAudio : id for word audio
    //@parameter wordSentence : id for sentence
    public Words( int wordImage, int wordSpelling, int wordAudio, int wordSentence)
    {
        mWordImage = wordImage;//image file of word
        mWordSpelling = wordSpelling;//spelling file of words
        mWordAudio = wordAudio;//audio file with only words
        mWordSentence = wordSentence;//audio file of sentence
    }

    public int getmWordSentence()
    {
        return mWordSentence;
    }

    public int getmWordImage()
    {
        return mWordImage;
    }

    public int getmWordSpelling()
    {
        return mWordSpelling;
    }

    public int getmWordAudio()
    {
        return mWordAudio;
    }
}
