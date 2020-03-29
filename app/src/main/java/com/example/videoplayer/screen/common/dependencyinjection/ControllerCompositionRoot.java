package com.example.videoplayer.screen.common.dependencyinjection;

import android.app.Activity;

public class ControllerCompositionRoot {

    private CompositionRoot compositionRoot;
    private Activity activity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }
}
