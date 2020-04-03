package com.example.videoplayer.screen.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservable<ListenerType> extends BaseViewMvc
        implements ObservableVIewMvc<ListenerType>{
    Set<ListenerType> mlisteners=new HashSet<>();

    @Override
    public void initView() {

    }

    @Override
    public void registerListener(ListenerType listener) {
        mlisteners.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        mlisteners.remove(listener);
    }
    protected Set<ListenerType> getListeners(){
        return  Collections.unmodifiableSet(mlisteners);
    }
}
