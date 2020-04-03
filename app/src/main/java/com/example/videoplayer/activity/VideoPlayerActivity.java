package com.example.videoplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.example.videoplayer.R;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.IOException;

import wseemann.media.FFmpegMediaPlayer;

public class VideoPlayerActivity extends AppCompatActivity {
    public static final String EXTRA_PATH = "filepath";
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private DefaultDataSourceFactory dataSourceFactory;
    public String FILE_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        hideSystemUI();
        initView();
        initExoPlayer();
        getextra();
        if (FILE_PATH != null) { startPlaying(FILE_PATH);
        }
    }


    private void getextra() {
        FILE_PATH = getIntent().getExtras().getString(EXTRA_PATH);
    }

    public void initView() {
        playerView = findViewById(R.id.exoPlayerVIew);
        initExoPlayer();

    }

    private void initExoPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);
        dataSourceFactory = new DefaultDataSourceFactory(this, "exo_demo");
        ConcatenatingMediaSource mediaSources = new ConcatenatingMediaSource();
        player.prepare(mediaSources);
        player.setPlayWhenReady(true);
        player.addListener(new Player.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {
                Log.d("debugging", "calling method " + isLoading);

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                player.next();
                Log.d("debugging", "error occured " + error.getMessage());
            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        playerView.setPlayer(null);
        if(player!=null) {
            player.release();
            player = null;
        }
    }

    public void startPlaying(String url) {
        if (player != null && dataSourceFactory != null) {
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(url));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
           /* if(fFmpeg!=null){
                executeCommand(adapter.getItem(position).getVideoUri());
            }*/
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @SuppressLint("SourceLockedOrientationActivity")
    private void rotateScreen(Uri uri) {
        try {
            //Create a new instance of MediaMetadataRetriever
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            //Declare the Bitmap
            Bitmap bmp;
            //Set the video Uri as data source for MediaMetadataRetriever
            retriever.setDataSource(this, uri);
            //Get one "frame"/bitmap - * NOTE - no time was set, so the first available frame will be used
            bmp = retriever.getFrameAtTime();

            //Get the bitmap width and height
            long videoWidth = bmp.getWidth();
            long videoHeight = bmp.getHeight();

            //If the width is bigger then the height then it means that the video was taken in landscape mode and we should set the orientation to landscape
            if (videoWidth > videoHeight) {
                //Set orientation to landscape
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            //If the width is smaller then the height then it means that the video was taken in portrait mode and we should set the orientation to portrait
            if (videoWidth < videoHeight) {
                //Set orientation to portrait
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        } catch (RuntimeException ex) {
            //error occurred
            Log.e("MediaMetadataRetriever", "- Failed to rotate the video");

        }
    }
}
