package com.example.videoplayer.videoplayerfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.videoplayer.common.BaseFragment;

public class VideoPlayerFragment extends BaseFragment implements VideoPlayerFragmentMvc.Listener {
    VideoPlayerFragmentMvcImpl viewMvc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc=getCompositionRoot().getViewMvcFactory().getVideoPlayerFragmentMvc(container);
        viewMvc.registerListener(this);
        return viewMvc.getRootView();
    }

    @Override
    public void onPauseClicked() {

    }
}
