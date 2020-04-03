package com.example.videoplayer.screen.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.videoplayer.ToolbarMvcView;
import com.example.videoplayer.filelists.FileListViewMvcImpl;
import com.example.videoplayer.filelists.adapter.FileListItemMvcImpl;
import com.example.videoplayer.filelists.filelistfragment.FileListFragmentMvcImpl;
import com.example.videoplayer.videoplayerfragment.VideoPlayerFragmentMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public FileListViewMvcImpl geFilelistViewMvc(ViewGroup parent) {
        return new FileListViewMvcImpl(mLayoutInflater,parent,this);
    }

    public FileListFragmentMvcImpl geFilelistFragmentViewMvc(ViewGroup parent) {
        return new FileListFragmentMvcImpl(mLayoutInflater,parent,this);
    }

    public FileListItemMvcImpl getFilelistItemMvc(ViewGroup parent) {
        return new FileListItemMvcImpl(mLayoutInflater,parent);
    }

    public VideoPlayerFragmentMvcImpl getVideoPlayerFragmentMvc(ViewGroup container) {
        return new VideoPlayerFragmentMvcImpl(mLayoutInflater,container);
    }

    public ToolbarMvcView geToolbarMvcView() {
        return new ToolbarMvcView();
    }
}
