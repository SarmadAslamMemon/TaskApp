package com.example.myapplication.Activties;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DataHandling.MovieModel;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class MovieDescription {

    Context context;
    MovieModel movieModel;
    String posterUrl;
    ImageView imageView;
    TextView titleTextView;
    TextView descriptionTextView;
    TextView ratingTextView;
    TextView dateTextView;

    public MovieDescription(Context context, MovieModel movieModel, String posterUrl) {
        this.context = context;
        this.movieModel = movieModel;
        this.posterUrl = posterUrl;
    }

    public void showCustomDialog(MovieModel movie) {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R
                .layout.movie_description_layout, null);


        setViews(dialogView);
        populateData(movie);
        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.TransparentAlertDialogStyle)
                .setView(dialogView);



        AlertDialog dialog = builder.create();
        dialogView.setBackground(null);
        dialog.setCancelable(true);

        dialog.show();
    }

    private void populateData(MovieModel movie) {
        Picasso.get().load(posterUrl).into(imageView);
        titleTextView.setText(movie.getTitle());
        descriptionTextView.setText(movie.getOverview());
        ratingTextView.setText(String.valueOf(movie.getPopularity()));
        dateTextView.setText(movie.getReleaseDate());
    }

    private void setViews(View dialogView) {
        // Set dialog contents
         imageView = dialogView.findViewById(R.id.dialog_image);
         titleTextView = dialogView.findViewById(R.id.dialog_title);
         descriptionTextView = dialogView.findViewById(R.id.dialog_description);
         ratingTextView = dialogView.findViewById(R.id.dialog_rating);
         dateTextView = dialogView.findViewById(R.id.dialog_date);
    }

}
