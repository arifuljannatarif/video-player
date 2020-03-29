package com.example.videoplayer.common;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videoplayer.CustomApplication;
import com.example.videoplayer.screen.common.dependencyinjection.CompositionRoot;
import com.example.videoplayer.screen.common.dependencyinjection.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {
    CompositionRoot mCompositionRoot;
    ControllerCompositionRoot controllerCompositionRoot;

    public ControllerCompositionRoot getmCompositionRoot() {
        if(controllerCompositionRoot==null){
            controllerCompositionRoot=new ControllerCompositionRoot(
                    ((CustomApplication)getApplication()).getCompositionRoot(),
                    this
            );
        }
        return controllerCompositionRoot;
    }
}
