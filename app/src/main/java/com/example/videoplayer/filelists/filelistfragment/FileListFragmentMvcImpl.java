package com.example.videoplayer.filelists.filelistfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoplayer.R;
import com.example.videoplayer.filelists.FileItemModel;
import com.example.videoplayer.filelists.adapter.FileListItemMvcImpl;
import com.example.videoplayer.filelists.adapter.FileListRecyclerAdapter;
import com.example.videoplayer.screen.common.BaseObservable;
import com.example.videoplayer.screen.common.ViewMvcFactory;

import java.util.List;

public class FileListFragmentMvcImpl extends BaseObservable<FileListFragmentMvc.Listener>
        implements FileListFragmentMvc, FileListItemMvcImpl.Listener {
    private final ViewMvcFactory viewFactory;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    FileListRecyclerAdapter adapter;
    public FileListFragmentMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewFactory){
        setmRootView(inflater.inflate(R.layout.fragmetn_file_list,parent,false));
        this.viewFactory=viewFactory;
        initView();
    }


    @Override
    public void onFileClicked(FileItemModel model) {

    }

    @Override
    public void onFetchCompleted(List<FileItemModel> files) {
        adapter.addFiles(files);
    }

    @Override
    public void initView() {
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        progressBar=findViewById(R.id.progressbar);
        adapter=new FileListRecyclerAdapter(viewFactory,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void showProgressbar(boolean b) {
        progressBar.setVisibility(b? View.VISIBLE:View.GONE);
    }

    @Override
    public void onfileClicked(FileItemModel model) {
        Log.d("debugging","item clicked Filelist fragment impl 61");
        for(Listener listener:getListeners()){
            listener.onFileClicked(model);
        }
    }
}
