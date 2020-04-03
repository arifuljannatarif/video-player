package com.example.videoplayer.filelists;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.videoplayer.R;
import com.example.videoplayer.activity.VideoPlayerActivity;
import com.example.videoplayer.common.BaseActivity;
import com.example.videoplayer.filelists.adapter.FileListItemMvcImpl;
import com.example.videoplayer.filelists.filelistfragment.FileListFragment;
import com.example.videoplayer.services.FFmpegService;
import com.example.videoplayer.videoplayerfragment.VideoPlayerFragment;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;

public class FileListActivity extends BaseActivity {
    FileListActivityController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileListViewMvcImpl viewMvc = getCompositionRoot().getViewMvcFactory()
                .geFilelistViewMvc(null);
        mController=getCompositionRoot().getFileListActivityController();
        mController.bindView(viewMvc);
        setContentView(viewMvc.getRootView());
        mController.onCreate();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(!mController.onoptionItemSelected(item))
            super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        mController.onBackPressed();
    }





}
