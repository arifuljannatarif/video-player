package com.example.videoplayer.filelists;

import java.util.List;

public interface FileListViewMvc {
    interface Listener{
        void refreshFileList();
    }
    void onFilelistFetched( List<FileItemModel> files);
    void toggleDrawer();
}
