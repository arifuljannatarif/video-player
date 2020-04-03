package com.example.videoplayer.screen;

import android.content.Context;
import android.content.Intent;

import com.example.videoplayer.activity.VideoPlayerActivity;

public class ScreenNavigator {

    private final Context mContext;

    public ScreenNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toVideoPlayer(String fileUri){
        mContext.startActivity(new Intent(mContext, VideoPlayerActivity.class)
                .putExtra(VideoPlayerActivity.EXTRA_PATH,fileUri));
    }
}
