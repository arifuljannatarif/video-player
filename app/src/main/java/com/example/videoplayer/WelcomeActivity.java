package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.videoplayer.filelists.FileListActivity;


public class WelcomeActivity extends AppCompatActivity {
    ImageView appIcon;
    public static final int SPLASH_DURATION=2000;
    public static final int ANIMATION_DURATION=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        appIcon=findViewById(R.id.imageView);
        scaleUp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, FileListActivity.class));
                finish();
            }
        },SPLASH_DURATION);
    }
    private void scaleUp(){
        Animation anim = new ScaleAnimation(
                0.3f, 1.5f,
                0.3f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                scaleDown();
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationStart(Animation animation) {
                // footer.layout(footer.getLeft(), (footer.getTop() - 100), footer.getRight(), footer.getBottom());
            }
        });
        appIcon.startAnimation(anim);
    }
    private void scaleDown(){
        Animation anim = new ScaleAnimation(
                1.5f, 0.3f,
                1.5f, 0.3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                scaleUp();
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationStart(Animation animation) {
                // footer.layout(footer.getLeft(), (footer.getTop() - 100), footer.getRight(), footer.getBottom());
            }
        });
        appIcon.startAnimation(anim);
    }
}
