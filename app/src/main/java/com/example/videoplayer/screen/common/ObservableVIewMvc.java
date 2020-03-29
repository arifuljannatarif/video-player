package com.example.videoplayer.screen.common;

public interface ObservableVIewMvc<ListenerType> {
    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);
}
