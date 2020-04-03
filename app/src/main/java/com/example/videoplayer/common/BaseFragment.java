package com.example.videoplayer.common;

import androidx.fragment.app.Fragment;

import com.example.videoplayer.CustomApplication;
import com.example.videoplayer.screen.common.dependencyinjection.ControllerCompositionRoot;

public class BaseFragment  extends Fragment {
    private ControllerCompositionRoot controllerCompositionRoot;

    public ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot==null){
            controllerCompositionRoot=new ControllerCompositionRoot(
                    ((CustomApplication)requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return controllerCompositionRoot;
    }
}
