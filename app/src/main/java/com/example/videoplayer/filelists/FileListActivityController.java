package com.example.videoplayer.filelists;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.videoplayer.R;
import com.example.videoplayer.common.BaseActivity;
import com.example.videoplayer.filelists.adapter.FileListItemMvcImpl;
import com.example.videoplayer.filelists.filelistfragment.FileListFragment;
import com.example.videoplayer.screen.interfaces.ControllerCommonMethods;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;

public class FileListActivityController  implements ControllerCommonMethods, FileListItemMvcImpl.Listener {

    private FFmpeg fFmpeg;
    private boolean firstPress=true;
    BaseActivity mActivity;
    public FileListActivityController(){

    }
    public void bindView(FileListViewMvcImpl viewMvc) {
        this.viewMvc = viewMvc;
        mActivity= (BaseActivity) viewMvc.getRootView().getContext();
    }

    FileListViewMvcImpl viewMvc;

    public void onCreate(){
        //mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public void onStart(){

    }
    public void onStop(){

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
    public void onBackPressed() {
        //super.onBackPressed();
        if (mActivity.getSupportFragmentManager().getBackStackEntryCount() > 1) {
            mActivity.getSupportFragmentManager().popBackStackImmediate();
        }
        else if(canExitApp()) {
            mActivity.finish();
        }
        /*else {
            super.onBackPressed();
        }*/
    }

    boolean canExitApp(){
        if(firstPress){
            this.firstPress = false;
            Toast.makeText(geContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            return false;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstPress=true;
            }
        }, 1500);
        return !firstPress;
    }




    private Context geContext() {
        return viewMvc.getRootView().getContext();
    }


    @Override
    public void onfileClicked(FileItemModel model) {
        Log.d("debugging","file clicked "+model.getName());
        Log.d("debugging",
                "name "+model.getName()+"\n"+
                        "path "+model.getPath()+"\n"+
                        "isdirectory "+model.isDirectory()+"\n"
        );
        if(model.isDirectory()){
            addFragment(model.getPath());
            return;
        }
        mActivity.getCompositionRoot().getScreenNavigator().toVideoPlayer(model.getPath());
    }

    private void addFragment(String path) {
        FileListFragment fragment=mActivity.getCompositionRoot().getFilelistFragment();
        Bundle bundle=new Bundle();
        bundle.putString(FileListFragment.EXTRA_PATH,path);
        fragment.setArguments(bundle);
        fragment.registerListener(this);
        mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment)
                .addToBackStack(null)
                .commit();
    }

    public boolean onoptionItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                viewMvc.toggleDrawer();
                return true;
        }
        return false;
    }
}
