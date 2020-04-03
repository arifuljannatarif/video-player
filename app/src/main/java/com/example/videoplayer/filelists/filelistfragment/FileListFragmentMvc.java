package com.example.videoplayer.filelists.filelistfragment;

import com.example.videoplayer.filelists.FileItemModel;

import java.util.List;

public interface FileListFragmentMvc {
    interface Listener{
        void fetchOrRefreshFileList();
        void onFileClicked(FileItemModel model);

    }
    void onFileClicked(FileItemModel model);
    void onFetchCompleted( List<FileItemModel> list);

}
