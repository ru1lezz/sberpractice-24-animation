package com.example.android.animationpractice;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.Button;

public class ScenesActivity extends AppCompatActivity {

    private Button mNextButton;
    private int currentScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);

        currentScene = 0;
        mNextButton = findViewById(R.id.next_button);
        ViewGroup root = findViewById(R.id.frame_layout);
        LayoutTransition transition = root.getLayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        Scene sceneA = Scene.getSceneForLayout(root, R.layout.scene_a, this);
        Scene sceneB = Scene.getSceneForLayout(root, R.layout.scene_b, this);
        Scene sceneC = Scene.getSceneForLayout(root, R.layout.scene_c, this);

        mNextButton.setOnClickListener((v) -> {
            switch (currentScene) {
                case 0:
                    TransitionManager.go(sceneB, new Slide());
                    ++currentScene;
                    break;
                case 1:
                    TransitionManager.go(sceneC, new Slide());
                    ++currentScene;
                    break;
                case 2:
                    TransitionManager.go(sceneA, new Slide());
                    currentScene = 0;
                    break;
            }
        });
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, ScenesActivity.class);
    }
}
