package com.example.videoplayer.filelists.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.videoplayer.R;
import com.example.videoplayer.filelists.FileItemModel;
import com.example.videoplayer.screen.common.BaseObservable;
import com.squareup.picasso.Picasso;


public class FileListItemMvcImpl extends BaseObservable<FileListItemMvcImpl.Listener> {
    public interface Listener {

        void onfileClicked(FileItemModel model);
    }
    FileItemModel fileItemModel;
    ImageView avatar;
    TextView title;
    TextView size;
    private TextView duration;
    public void bindView(FileItemModel item) {
        fileItemModel=item;
        if(item.isDirectory()){
            bindDirectoryItem(item);
        }
        else {
            bindFileItem(item);
        }
    }

    private void bindFileItem(FileItemModel item) {

        title.setGravity(Gravity.START);
        size.setText(String.format("%.2f", item.getLength()/(1e+6))+"mb");
        avatar.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(getContext())
                .load(item.getPath())
                .placeholder(R.color.Black)
                //.thumbnail(0.1f)
                .into(avatar);
       /* Picasso.get()
                .load(item.getPath())
                .placeholder(R.color.Black)
                .into(avatar);*/
        title.setText(item.getName());
        if(item.getDuration()!=null) {
            duration.setText(item.getDuration());
        }
        else {
            duration.setVisibility(View.GONE);
        }
    }

    private void bindDirectoryItem(FileItemModel item) {
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        avatar.setScaleType(ImageView.ScaleType.FIT_CENTER);
        avatar.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        Glide.with(getContext())
                .load(R.drawable.ic_folder_black_24dp)
                .into(avatar);
        title.setText(item.getName());
        duration.setVisibility(View.GONE);
        size.setVisibility(View.GONE);

    }


    public FileListItemMvcImpl(LayoutInflater inflater, ViewGroup parent){
        setmRootView(inflater.inflate(R.layout.file_list_single_item_grid,parent,false));
        initView();
    }
    @Override
    public void initView() {
        avatar=findViewById(R.id.file_avatar);
        title=findViewById(R.id.title);
        duration=findViewById(R.id.duration);
        size=findViewById(R.id.fileSize);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:getListeners()){
                    listener.onfileClicked(fileItemModel);
                }
            }
        });
    }


}
