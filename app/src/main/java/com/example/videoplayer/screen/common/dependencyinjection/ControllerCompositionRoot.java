package com.example.videoplayer.screen.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.example.videoplayer.filelists.FetchFileFromStorageUseCase;
import com.example.videoplayer.filelists.FileListActivityController;
import com.example.videoplayer.filelists.FileListViewMvcImpl;
import com.example.videoplayer.filelists.filelistfragment.FileListFragment;
import com.example.videoplayer.screen.ScreenNavigator;
import com.example.videoplayer.screen.common.ViewMvcFactory;

public class ControllerCompositionRoot {

    private CompositionRoot compositionRoot;
    private Activity activity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }
    protected LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(activity);
    }

    public FileListFragment getFilelistFragment() {
        return new FileListFragment();
    }

    public FetchFileFromStorageUseCase getFileUseCase() {
        return new FetchFileFromStorageUseCase(activity);
    }

    public FileListActivityController getFileListActivityController() {
        return new FileListActivityController();
    }

    public ScreenNavigator getScreenNavigator() {
        return new ScreenNavigator(activity);
    }
}
