package com.example.android.api_tute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView TitleTextView;
    TextView YearTextView;
    RequestQueue requestqueue;
    ImageView imageView;
    Gson gson = new Gson(); //This was the issue with the null before.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TitleTextView = findViewById(R.id.Title);
        YearTextView = findViewById(R.id.Year);
        button = findViewById(R.id.submit);
        editText = findViewById(R.id.search);
        

        requestqueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
    }
        protected void makeRequest() {


            String url = String.format("https://www.omdbapi.com/?apikey=4d8e549d&t=%s",
                    editText.getText().toString().replaceAll(" ", "+"));

            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.d("JSON", response); //For debugging

                    MovieInfo Movieobject = gson.fromJson(response.toString(), MovieInfo.class);
                    //Creates an object that stores the values

                   // System.out.println(Movieobject);

                    TitleTextView.setText(Movieobject.getTitle());
                    YearTextView.setText(Movieobject.getYear());

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    TitleTextView.setText("That didn't work!");
                }
            });

            requestqueue.add(stringRequest);
        }
    }

