package com.example.videoplayer.screen.interfaces;

import android.content.Context;
import android.view.View;

/**
 * this is not required
 * @param <MvcType>
 */
public abstract class BaseController<MvcType extends View> implements ControllerCommonMethods {
    MvcType viewMvc;
    public void bindView(MvcType viewMvc){
        this.viewMvc=viewMvc;
    }
    public void getContext(){

    }
    public MvcType getViewMvc(){
        return viewMvc;
    }
    public Context geContext(){
        if(viewMvc==null)
            return null;
        return viewMvc.getContext();
    }

}
