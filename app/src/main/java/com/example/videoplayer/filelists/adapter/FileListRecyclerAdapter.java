package com.example.videoplayer.filelists.adapter;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoplayer.filelists.FileItemModel;
import com.example.videoplayer.filelists.FileListViewMvc;
import com.example.videoplayer.screen.common.ViewMvcFactory;

import java.util.ArrayList;
import java.util.List;

public class FileListRecyclerAdapter extends RecyclerView.Adapter<FileListRecyclerAdapter.ViewHolder> implements FileListItemMvcImpl.Listener {
    private ViewMvcFactory mViewFactory;
    List<FileItemModel> mItems;
    FileListItemMvcImpl.Listener mListener;

    public FileListRecyclerAdapter(ViewMvcFactory factory,FileListItemMvcImpl.Listener listener){
        mViewFactory = factory;
        mListener=listener;
        mItems=new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FileListItemMvcImpl itemMvc = mViewFactory.getFilelistItemMvc(parent);
        itemMvc.registerListener(this);
        return new ViewHolder(itemMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemMvc.bindView(getItem(position));
        holder.itemMvc.registerListener(this);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onfileClicked(FileItemModel model) {
        Log.d("debugging","item clicked recycler adapter 47");
        if(mListener!=null)
            mListener.onfileClicked(model);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final FileListItemMvcImpl itemMvc;
        ViewHolder(@NonNull FileListItemMvcImpl itemMvc) {
            super(itemMvc.getRootView());
            this.itemMvc=itemMvc;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private FileItemModel getItem(int pos){
        return mItems.get(pos);
    }
    public void addFile(FileItemModel itemModel){
        mItems.add(itemModel);
    }
    public void addFiles(List<FileItemModel> models){
        mItems.clear();
        mItems.addAll(models);
        notifyDataSetChanged();
    }
    public void clear(){
        mItems.clear();
        notifyDataSetChanged();
    }

}
