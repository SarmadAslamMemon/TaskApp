package com.example.myapplication.ApiManager;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class ApiManager {

    private Context context;
    private RequestQueue requestQueue;
    public static String DOMAIN_URL = "https://api.themoviedb.org/3/movie/popular";
    public static String API_KEY = "?api_key=99e3b7c68c146700906f0a70133ba340";


    // Interface for success and error handling
    public interface ApiListener {
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }

    public ApiManager(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void fetchData(String url, final ApiListener listener) {
        // Create a request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                });

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}
