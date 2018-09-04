package com.example.powjh.weatherreport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity{

    private final int REQUEST_SPEECH_RECOGNIZER = 3000;
    private TextView mTextView;
    private ArrayList<String> mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.speechResult);
    }

    public void startVoice (View view){

        // rng for different weather questions
        int n = (int) (Math.random() * 4 );
        String[] questions = new String[4];
        questions[0] = "Where are you located at?";
        questions[1] = "Tell me your location";
        questions[2] = "Weather report at?";
        questions[3] = "Where do you want your weather report at?";

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // get default langauge of android device
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, "Your Device Does Not Support Speech Input", Toast.LENGTH_SHORT).show();

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, questions[n]);
        startActivityForResult(intent, REQUEST_SPEECH_RECOGNIZER);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data!= null){
            mAnswer = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mTextView.setText(mAnswer.toString());
        }
    }
}
