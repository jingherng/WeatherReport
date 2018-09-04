package com.example.powjh.weatherreport;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powjh.weatherreport.Model.OpenWeatherMapAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity{

    private final int REQUEST_SPEECH_RECOGNIZER = 100;

    // Variables for layout
    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTime, txtCelsius;
    ImageView imageView;
    ProgressBar progressBar;

    // WeatherAPI variables
    private ArrayList<String> mAnswer;
    static final String API_KEY = "13b407014e49c57f1de0a5aabb7e7a8b";
    static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Control
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        imageView = (ImageView) findViewById(R.id.weatherImage);

    }

    public void startVoice (View view){

        // rng for different Weather questions
        int n = (int) (Math.random() * 4 );
        String[] questions = new String[4];
        questions[0] = "Where are you located at?";
        questions[1] = "Tell me your location";
        questions[2] = "Weather report at?";
        questions[3] = "Where do you want your Weather report at?";

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

            new callWeather().execute();
        }
    }

    class callWeather extends AsyncTask<Void, Void, String>{

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls){
            try{
                // URL for Weather
                URL weatherURL = new URL(API_URL + mAnswer.get(0) + "&APPID=" + API_KEY);
                Log.i("Full URL:", weatherURL.toString());

                // Establish Web Connection
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

                // If city not found
                txtLastUpdate.setText("City not found. Try again");
                progressBar.setVisibility(View.GONE);

            }
            else {
                progressBar.setVisibility(View.GONE);
                Log.i("INFO", response);
                Type type = new TypeToken<OpenWeatherMapAPI>() {
                }.getType();

                OpenWeatherMapAPI api = new Gson().fromJson(response, type);


                // Set layout if city found
                txtCity.setText(String.format("%s, %s", api.getName(), api.getSys().getCountry()));
                txtLastUpdate.setText(String.format("Last Updated: %s", Calendar.getInstance().getTime()));
                txtDescription.setText(String.format("%s", api.getWeather().get(0).getDescription()));
                txtHumidity.setText(String.format("%d%%", api.getMain().getHumidity()));
                txtCelsius.setText(String.format("%.1f °C", api.getMain().getTemp() - 273.15));


                String icon = api.getWeather().get(0).getIcon();
                switch (icon) {
                    case "01d":
                        imageView.setImageResource(R.drawable.d01);
                        break;
                    case "02d":
                        imageView.setImageResource(R.drawable.d02);
                        break;
                    case "03d":
                        imageView.setImageResource(R.drawable.d03);
                        break;
                    case "04d":
                        imageView.setImageResource(R.drawable.d04);
                        break;
                    case "09d":
                        imageView.setImageResource(R.drawable.d09);
                        break;
                    case "10d":
                        imageView.setImageResource(R.drawable.d10);
                        break;
                    case "11d":
                        imageView.setImageResource(R.drawable.d11);
                        break;
                    case "13d":
                        imageView.setImageResource(R.drawable.d13);
                        break;
                    case "50d":
                        imageView.setImageResource(R.drawable.d50);
                        break;
                    case "01n":
                        imageView.setImageResource(R.drawable.n01);
                        break;
                    case "02n":
                        imageView.setImageResource(R.drawable.n02);
                        break;
                    case "03n":
                        imageView.setImageResource(R.drawable.n03);
                        break;
                    case "04n":
                        imageView.setImageResource(R.drawable.n04);
                        break;
                    case "09n":
                        imageView.setImageResource(R.drawable.n09);
                        break;
                    case "10n":
                        imageView.setImageResource(R.drawable.n10);
                        break;
                    case "11n":
                        imageView.setImageResource(R.drawable.n11);
                        break;
                    case "13n":
                        imageView.setImageResource(R.drawable.n13);
                        break;
                    case "50n":
                        imageView.setImageResource(R.drawable.d50);
                        break;
                }
            }
        }
    }
}
