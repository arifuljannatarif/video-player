package com.example.videoplayer.filelists;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.videoplayer.R;
import com.example.videoplayer.ToolbarMvcView;
import com.example.videoplayer.screen.common.BaseObservable;
import com.example.videoplayer.screen.common.ViewMvcFactory;
import com.example.videoplayer.screen.common.navdrawer.BaseNavDrawerViewMvc;
import com.example.videoplayer.screen.common.navdrawer.DrawerItem;

import java.util.List;

import wseemann.media.FFmpegMediaPlayer;

public class FileListViewMvcImpl extends BaseNavDrawerViewMvc<FileListViewMvc.Listener> implements FileListViewMvc {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ViewMvcFactory viewMvcFactory;
    private ToolbarMvcView toolbarViewMvc;

    public FileListViewMvcImpl(LayoutInflater inflater,ViewGroup parent, ViewMvcFactory viewMvcFactory){
        super(inflater,parent);
        this.viewMvcFactory = viewMvcFactory;
        setmRootView(inflater.inflate(R.layout.activity_file_list,parent,false));
        initViews();

    }

    private void initViews() {
        drawerLayout =findViewById(R.id.drawerLayout);
        drawerToggle=new ActionBarDrawerToggle((Activity) getContext(),drawerLayout,R.string.open_drawer,R.string.close_Drawer);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbar =findViewById(R.id.toolbar);
        toolbarViewMvc=viewMvcFactory.geToolbarMvcView();
    }

    @Override
    public void onFilelistFetched(List<FileItemModel> files) {

    }

    @Override
    public void toggleDrawer() {
        if(drawerLayout==null)return;

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDrawerItemClicked(DrawerItem item) {

    }
}
