package com.example.myapplication.Activties;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.myapplication.ApiManager.ApiManager;
import com.example.myapplication.DataHandling.MovieAdapter;
import com.example.myapplication.DataHandling.MovieModel;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private RecyclerView movieRecyclerView;
    private ApiManager apiManager;
    private MovieAdapter movieAdapter;
    private List<MovieModel> movieList;
    private final  String url="https://api.themoviedb.org/3/movie/popular?api_key=99e3b7c68c146700906f0a70133ba340";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        makeApiCall();

    }

    private void makeApiCall() {
        apiManager=new ApiManager(this);
        apiManager.fetchData(url, new ApiManager.ApiListener() {
            @Override
            public void onSuccess(JSONObject response) {
                if(response!=null)
                {
                    manageJsonObj(response);
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
    private void manageJsonObj(JSONObject response) {
        try {
            JSONArray results = response.getJSONArray("results");
            manageResult(results.toString());
        } catch (JSONException e) {
            Log.d("sarvin", "error " + Objects.requireNonNull(e.getMessage()));
        }

    }
    private void getReferences() {
        movieRecyclerView=findViewById(R.id.recyclerView);
    }
    private void manageResult(String response) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<MovieModel>>(){}.getType();
            movieList = gson.fromJson(response, listType);
            setAdapter();
        } catch (JsonSyntaxException e) {
            Log.e("sarvin", "Error parsing JSON: " + e.getMessage());
        }

    }

    private void setAdapter() {
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this, movieList, this);
        movieRecyclerView.setAdapter(movieAdapter);
    }


    @Override
    public void onMovieClick(MovieModel movie) {
//        item view on click can be performed using interface !
    }






}