package com.example.videoplayer.screen.common;

import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc implements ViewMvc {
    View mRootView;

    protected void setmRootView(View mRootView) {
        this.mRootView = mRootView;
    }

    public View getRootView() {
        return mRootView;
    }
    protected Context getContext(){
        return getRootView().getContext();
    }
    protected  <T extends View>T  findViewById(int resID){
        return getRootView().findViewById(resID);
    }
}
