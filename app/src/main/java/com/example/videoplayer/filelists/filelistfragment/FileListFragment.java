package com.example.videoplayer.filelists.filelistfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.videoplayer.common.BaseFragment;
import com.example.videoplayer.filelists.FetchFileFromStorageUseCase;
import com.example.videoplayer.filelists.FileItemModel;
import com.example.videoplayer.filelists.adapter.FileListItemMvcImpl;

import java.io.File;
import java.util.List;

public class FileListFragment extends BaseFragment implements FileListFragmentMvc.Listener, FetchFileFromStorageUseCase.Listener {
    FileListFragmentMvcImpl viewMvc;
    FetchFileFromStorageUseCase fetFileUsecase;
    FileListItemMvcImpl.Listener mliListener;
    String CURRENT_PATH;
    Handler mainHandler;
    public static final String EXTRA_PATH="currentpath";
    private boolean runningFetching;
    private boolean dataLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtraData(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(viewMvc==null) {
            viewMvc=getCompositionRoot().getViewMvcFactory().geFilelistFragmentViewMvc(container);
        }
        fetFileUsecase=getCompositionRoot().getFileUseCase();
        return viewMvc.getRootView();
    }

    private void getExtraData(Bundle arguments) {
        if(arguments==null)return;
        CURRENT_PATH=arguments.getString(EXTRA_PATH);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
        fetFileUsecase.registerListener(this);
        //fetFileUsecase.fetchFileListAndNotify();
        if(CURRENT_PATH!=null ) {
            loadFilesUsingThread();

        }
    }

    private void loadFilesUsingThread() {
        if(dataLoaded)return;
        viewMvc.showProgressbar(true);
        if(runningFetching)return;
        if(mainHandler==null){
            mainHandler=new Handler();
        }
        runningFetching=true;
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runningFetching=false;
                fetFileUsecase.fetchDirectoryAndNotify(getContext(),new File(CURRENT_PATH));
            }
        },0);



    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
        fetFileUsecase.unregisterListener(this);
    }

    @Override
    public void fetchOrRefreshFileList() {

    }

    @Override
    public void onFileClicked(FileItemModel model) {
        if(mliListener!=null){
            mliListener.onfileClicked(model);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }



    @Override
    public void notifyFailfetchingFiles(String errorMessage) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewMvc.showProgressbar(false);
                Log.d("debugging","fetching file failed "+errorMessage);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void notifySuccessfetchingFiles(List<FileItemModel> files) {
        dataLoaded=true;
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewMvc.showProgressbar(false);
                viewMvc.onFetchCompleted(files);
                Log.d("debugging","fetching file Succesful  "+files.size());
            }
        });
    }

    public void registerListener(FileListItemMvcImpl.Listener mliListener) {
        this.mliListener = mliListener;
    }
}
