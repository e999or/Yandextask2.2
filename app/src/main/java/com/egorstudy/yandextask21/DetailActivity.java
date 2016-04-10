package com.egorstudy.yandextask21;

/**
 * Created by Lenovo on 10.04.2016.
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hisham.jsonparsingdemo.models.MovieModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class DetailActivity extends ActionBarActivity {

    private ImageView ivSingerIcon;
    private TextView tvSinger;
    private TextView tvTracks;
    private TextView tvAlbums;
    private TextView tvGenres;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // setting up text views and stuff
        setUpUIViews();

        // recovering data from MainActivity, sent via intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String json = bundle.getString("pozishon");
            Pozishon pozishon = new Gson().fromJson(json, Pozishon.class);

            // Then later, when you want to display image
            ImageLoader.getInstance().displayImage(pozishon.getImage(), ivSingerIcon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            tvSinger.setText(pozishon.getSinger());
            tvTracks.setText("Tracks: " + pozishon.getTracks());
            tvAlbums.setText("Albums:" + pozishon.getAlbums());
            tvGenres.setText("Genres:" + stringBuffer);
            tvDescription.setText(pozishon.getDescription());

        }

    }

    private void setUpUIViews() {
        ivSingerIcon = (ImageView)findViewById(R.id.ivIcon);
        tvSinger = (TextView)findViewById(R.id.tvSinger);
        tvTracks = (TextView)findViewById(R.id.tvTracks);
        tvAlbums = (TextView)findViewById(R.id.tvAlbums);
        tvGenres = (TextView)findViewById(R.id.tvGenres);
        tvDescription = (TextView)findViewById(R.id.tvDescription);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
