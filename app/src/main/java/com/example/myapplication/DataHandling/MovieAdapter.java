package com.example.myapplication.DataHandling;

import static com.example.myapplication.ApiManager.ApiManager.API_KEY;
import static com.example.myapplication.ApiManager.ApiManager.DOMAIN_URL;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activties.MovieDescription;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<MovieModel> movieList;
    private OnMovieClickListener onMovieClickListener;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w500"; // You can choose any available size


    String fullUrl = BASE_IMAGE_URL + IMAGE_SIZE;

    public MovieAdapter(Context context, List<MovieModel> movieList, OnMovieClickListener onMovieClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
       MovieModel movie = movieList.get(position);
       setViews(holder, movie, position);
        // Set click listener
        holder.itemView.setOnClickListener(v ->
                new MovieDescription(context,movieList.get(position),fullUrl+movieList.get(position).getBackdropPath())
                        .showCustomDialog(movieList.get(position)));
    }

    private void setViews(MovieViewHolder holder, MovieModel movie, int position) {
        holder.MovieTitle.setText(movie.getTitle());
        holder.count.setText(String.valueOf(position + 1));
        String imagePath = movie.getBackdropPath();
        Picasso.get().load(fullUrl+imagePath).into(holder.MoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView count;
        public TextView MovieTitle;
        public ImageView MoviePoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.movieCountTxt);
            MovieTitle = itemView.findViewById(R.id.movieTitleTxt);
            MoviePoster = itemView.findViewById(R.id.moviePosterImg);
        }
    }

    // Interface for handling item click events
    public interface OnMovieClickListener {
        void onMovieClick(MovieModel movie);
    }
}
