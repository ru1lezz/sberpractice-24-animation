package com.example.android.animationpractice;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable mAnimationDrawable;
    private Animation mAnim;
    private ImageView mCodeAnimationImageView;
    private ImageView mXmlAnimationImageView;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.animation_drawable_image_view);
        imageView.setBackgroundResource(R.drawable.animation_drawable);
        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        mCodeAnimationImageView = findViewById(R.id.code_animation_image_view);
        mCodeAnimationImageView.setBackgroundResource(R.drawable.animation_drawable);

        mXmlAnimationImageView = findViewById(R.id.xml_animation_image_view);
        mXmlAnimationImageView.setBackgroundResource(R.drawable.animation_drawable);

        mAnim = new ScaleAnimation(4.0f, 1.0f, 1.0f, 4.0f);
        mAnim.setDuration(2500);
        mAnim.setInterpolator(new AccelerateInterpolator());

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener((v) -> {
            startActivity(SecondActivity.newInstance(MainActivity.this));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimationDrawable.start();
        mXmlAnimationImageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.base));
        mCodeAnimationImageView.startAnimation(mAnim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAnimationDrawable.stop();
    }
}
