package com.example.taskshaker1;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static int MIN_SWIPE_DISTANCE_X = 100;
    private static int MIN_SWIPE_DISTANCE_Y = 100;

    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;
    private MainInterface interface1;

    private MainActivity activity = null;

    public MainActivity getActivity() {
        return activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
        interface1 = activity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float deltaX = e1.getX() - e2.getX();

        float deltaY = e1.getY() - e2.getY();

        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X) || (deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y))
        {
            Random ran = new Random();
            int randomNum = ran.nextInt(6);

            for (int i = 0; i <= randomNum; i++) {

                interface1.addImageButton().startAnimation(AnimationUtils.loadAnimation(activity,
                        R.anim.falling));

            }

        }

        return true;
    }


}