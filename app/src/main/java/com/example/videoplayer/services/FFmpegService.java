package com.example.videoplayer.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

public class FFmpegService extends Service {
    private FFmpeg fFmpeg;

    @Override
    public void onCreate() {
        super.onCreate();
        loadFFFMPEG();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fFmpeg=null;
    }
    private void executeCommand(String file){
        try {
            fFmpeg.execute(new String[]{"-hide_banner","-i", file},new ExecuteBinaryResponseHandler(){
                @Override
                public void onFailure(String message) {
                    super.onFailure(message);
                    Log.d("debugging",message);
                }

                @Override
                public void onSuccess(String message) {
                    super.onSuccess(message);
                    Log.d("debugging",message);
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    void loadFFFMPEG(){
        fFmpeg= FFmpeg.getInstance(this);
        try {
            fFmpeg.loadBinary(new LoadBinaryResponseHandler(){

                @Override
                public void onStart() {
                    super.onStart();
                    Log.d("debugging","loading ffmpeg library");
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    Log.d("debugging","library loading finish");
                }


                @Override
                public void onSuccess() {
                    super.onSuccess();
                    Log.d("debugging","library loaded succesfully");
                }

                @Override
                public void onFailure() {
                    super.onFailure();
                    Log.d("debugging","library loading Failed");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
