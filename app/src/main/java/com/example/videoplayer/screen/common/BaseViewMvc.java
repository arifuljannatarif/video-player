package com.example.videoplayer.screen.common;

import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc {
    View mRootView;

    public void setmRootView(View mRootView) {
        this.mRootView = mRootView;
    }

    public View getmRootView() {
        return mRootView;
    }
    private Context getContext(){
        return getmRootView().getContext();
    }
    private <T extends View>T  findViewById(int resID){
        return getmRootView().findViewById(resID);
    }
}
