package com.example.videoplayer.videoplayerfragment;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.videoplayer.R;
import com.example.videoplayer.screen.common.BaseObservable;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoPlayerFragmentMvcImpl extends BaseObservable<VideoPlayerFragmentMvc.Listener> implements VideoPlayerFragmentMvc {
    private SimpleExoPlayer player;
    private PlayerView playerView;
    ProgressBar progressBar,listProgress;
    private DefaultDataSourceFactory dataSourceFactory;
    public VideoPlayerFragmentMvcImpl(LayoutInflater inflater, ViewGroup parent){
        setmRootView(inflater.inflate(R.layout.video_player_fragment,parent,false));
        initView();
    }

    @Override
    public void initView() {
        playerView=findViewById(R.id.exoPlayerVIew);
        initExoPlayer();

    }

    private void initExoPlayer() {
        player= ExoPlayerFactory.newSimpleInstance(getContext(),new DefaultTrackSelector());
        playerView.setPlayer(player);
        dataSourceFactory=new DefaultDataSourceFactory(getContext(),"exo_demo");
        ConcatenatingMediaSource mediaSources=new ConcatenatingMediaSource();
        player.prepare(mediaSources);
        player.setPlayWhenReady(true);
        player.addListener(new Player.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {
                Log.d("debugging","calling method "+isLoading);
                progressBar.setVisibility(isLoading? View.VISIBLE:View.GONE);

            }
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                player.next();
                Log.d("debugging","error occured "+error.getMessage());
            }

        });
    }
    public void onStart(){

    }
    public void onStop(){
        playerView.setPlayer(null);
        player.release();
        player=null;
    }
    public void startStreaming(String url){
        if(player!=null && dataSourceFactory!=null){
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(url));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
           /* if(fFmpeg!=null){
                executeCommand(adapter.getItem(position).getVideoUri());
            }*/
        }
    }
}
