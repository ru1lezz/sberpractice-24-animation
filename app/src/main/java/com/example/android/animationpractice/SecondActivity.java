package com.example.android.animationpractice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<ImageView> mImageList;
    private List<ValueAnimator> mValueAnimatorList;
    private List<ImageView> mSecondImageList;
    private Button mAnimatedButton;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
        initAnimator();
        initListeners();
    }

    private void initView() {
        mImageList = new ArrayList<>();
        mImageList.add(findViewById(R.id.image_view_1));
        mImageList.add(findViewById(R.id.image_view_2));
        mImageList.add(findViewById(R.id.image_view_3));
        mImageList.add(findViewById(R.id.image_view_4));
        mImageList.add(findViewById(R.id.image_view_5));

        mSecondImageList = new ArrayList<>();
        mSecondImageList.add(findViewById(R.id.image_view_6));
        mSecondImageList.add(findViewById(R.id.image_view_7));
        mSecondImageList.add(findViewById(R.id.image_view_8));
        mSecondImageList.add(findViewById(R.id.image_view_9));
        mSecondImageList.add(findViewById(R.id.image_view_10));

        mNextButton = findViewById(R.id.next_button);
        mAnimatedButton = findViewById(R.id.animated_button);
    }

    private void initAnimator() {
        mValueAnimatorList = new ArrayList<>();

        for(int i = 0; i < mImageList.size(); i++) {
            ValueAnimator animator = ValueAnimator
                    .ofFloat(0.0f, 3.0f)
                    .setDuration(2000);
            mValueAnimatorList.add(animator);
        }

        for(int i = 0; i < mValueAnimatorList.size(); i++) {
            int position = i;
            mValueAnimatorList.get(i).addUpdateListener(animation -> mImageList.get(position).setScaleY((float) animation.getAnimatedValue()));
        }

        mValueAnimatorList.get(0).setInterpolator(new FastOutLinearInInterpolator());
        mValueAnimatorList.get(1).setInterpolator(new LinearOutSlowInInterpolator());
        mValueAnimatorList.get(2).setInterpolator(new LinearInterpolator());
        mValueAnimatorList.get(3).setInterpolator(new OvershootInterpolator());
        mValueAnimatorList.get(4).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void initListeners() {
        mAnimatedButton.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatedButton, View.TRANSLATION_X, 0, 180f, -180f);
            animator.setDuration(3000);
            animator.setInterpolator(new AnticipateInterpolator());
            animator.start();
        });

        mNextButton.setOnClickListener((v) -> startActivity(ScenesActivity.newInstance(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(ValueAnimator animator : mValueAnimatorList) {
            animator.start();
        }
        for(ImageView imageView : mImageList) {
            startAnimation(imageView);
        }
    }

    private void startAnimation(ImageView imageVew) {
        imageVew.setVisibility(View.VISIBLE);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(showFadeInAnimation());
        animationSet.addAnimation(showFadeOutAnimation());
        imageVew.startAnimation(animationSet);
    }

    private Animation showFadeOutAnimation() {
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(2000);
        fadeOut.setDuration(2000);
        return fadeOut;
    }

    private Animation showFadeInAnimation() {
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(2000);
        return fadeIn;
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, SecondActivity.class);
    }
}
