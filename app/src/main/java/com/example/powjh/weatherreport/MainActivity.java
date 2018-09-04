package com.example.powjh.weatherreport;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends Activity{

    private final int REQUEST_SPEECH_RECOGNIZER = 3000;
    private TextView mTextView;
    private ArrayList<String> mAnswer;
    ProgressBar progressBar;
    static final String API_KEY = "46f8c31769f0088cba2b5db358ae29c4";
    static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static ArrayList<HashMap<String, String>> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.speechResult);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button voiceButton = (Button) findViewById(R.id.voiceInput);
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
            //mTextView.setText(mAnswer.get(0));

            new callWeather().execute();
        }
    }

    class callWeather extends AsyncTask<Void, Void, String>{

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            mTextView.setText("");
        }

        protected String doInBackground(Void... urls){
            try{
                // URL for weather
                URL weatherURL = new URL(API_URL + mAnswer.get(0) + "&APPID=" + API_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) weatherURL.openConnection();

                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally {
                    urlConnection.disconnect();
                }
            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response){
            if (response == null){
                response = "Error found";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO",response);
            mTextView.setText(response);
        }
    }
}
