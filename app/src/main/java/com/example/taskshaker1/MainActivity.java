package com.example.taskshaker1;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.DialogFragment;

import com.squareup.seismic.ShakeDetector;

import java.util.Random;

import static com.example.taskshaker1.SettingsDialog.SHAKE;
import static com.example.taskshaker1.SettingsDialog.SWIPE;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener, View.OnClickListener, MainInterface {
    private Animation fallingAnimation, upAnimation;
    private ImageView mImageView1, mImageView2, mImageView3, mImageView4, mImageView5;
    private ViewGroup containerView;
    private RelativeLayout.LayoutParams params;
    private ImageView curFallingImageView;
    DialogFragment dlg1, settings;
    RelativeLayout relativeLayout;
    private GestureDetectorCompat gestureDetectorCompat = null;
    private int flag = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.container1);



        mImageView1 = findViewById(R.id.coin_1);
        mImageView2 = findViewById(R.id.coin_2);
        mImageView3 = findViewById(R.id.coin_3);
        mImageView4 = findViewById(R.id.coin_4);
        mImageView5 = findViewById(R.id.coin_5);
        ImageView Settings = findViewById(R.id.btn_settings);
        dlg1 = new ChangeBgDialog(this);
        settings = new SettingsDialog(this);



        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector shakeDetector = new ShakeDetector(this);
        shakeDetector.start(sm);
        containerView = findViewById(R.id.container);
        fallingAnimation = AnimationUtils.loadAnimation(this,
                R.anim.falling);
        upAnimation = AnimationUtils.loadAnimation(this, R.anim.falling_up);

        Settings.setOnClickListener(this);

        // Create a common gesture listener object.
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

    }

    @Override
    public void hearShake() {
        if (flag == SHAKE) {

            Random ran = new Random();
            int randomNum = ran.nextInt(6);

            for (int i = 0; i <= randomNum; i++) {

                addImageButton().startAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.falling));

            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (flag == SWIPE) {
            // Pass activity on touch event to the gesture detector.
            gestureDetectorCompat.onTouchEvent(event);
            // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
            return true;
        }
        return false;
    }

    public ImageView addImageButton() {
        ImageView v = (ImageView)LayoutInflater.from(this).inflate(R.layout.image_view, null);
        Random ran = new Random();
        int randomNum = ran.nextInt(6);

        if (randomNum == 1) {
            params = (RelativeLayout.LayoutParams) mImageView1.getLayoutParams();
            v.setLayoutParams(params);
        } else if (randomNum == 2) {
            params = (RelativeLayout.LayoutParams) mImageView2.getLayoutParams();
            v.setLayoutParams(params);
        } else if (randomNum == 3) {
            params = (RelativeLayout.LayoutParams) mImageView3.getLayoutParams();
            v.setLayoutParams(params);
        } else if (randomNum == 4) {
            params = (RelativeLayout.LayoutParams) mImageView4.getLayoutParams();
            v.setLayoutParams(params);
        } else {
            params = (RelativeLayout.LayoutParams) mImageView5.getLayoutParams();
            v.setLayoutParams(params);
        }
//
        containerView.addView(v);
        return v;
    }

    @Override
    public int getFlag() {
        return flag;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;

    }


    @Override
    protected void onPause() {
        super.onPause();
        mImageView1.clearAnimation();
        mImageView2.clearAnimation();
        mImageView3.clearAnimation();
        mImageView4.clearAnimation();
        mImageView5.clearAnimation();
    }

//    Animation.AnimationListener animationFallingListener = new Animation.AnimationListener() {
//
//        @Override
//        public void onAnimationEnd(Animation arg0) {
//
//            if (curFallingImageView == mImageView1) {
//                mImageView1.startAnimation(upAnimation);
//            } else if (curFallingImageView == mImageView2) {
//                mImageView2.startAnimation(upAnimation);
//            } else if (curFallingImageView == mImageView3) {
//                mImageView3.startAnimation(upAnimation);
//            } else if (curFallingImageView == mImageView4) {
//                mImageView3.startAnimation(upAnimation);
//            } else if (curFallingImageView == mImageView5) {
//                mImageView3.startAnimation(upAnimation);
//            }
//        }
//
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//        }
//
//        @Override
//        public void onAnimationStart(Animation animation) {
//        }
//    };

//    Animation.AnimationListener animationUpListener = new Animation.AnimationListener() {
//
//        @Override
//        public void onAnimationEnd(Animation animation) {
//            if (curFallingImageView == mImageView1) {
//                curFallingImageView = mImageView2;
//                mImageView2.startAnimation(fallingAnimation);
//                mImageView1.setVisibility(View.INVISIBLE);
//                mImageView2.setVisibility(View.VISIBLE);
//                mImageView3.setVisibility(View.INVISIBLE);
//            } else if (curFallingImageView == mImageView2) {
//                curFallingImageView = mImageView3;
//                mImageView3.startAnimation(fallingAnimation);
//                mImageView1.setVisibility(View.INVISIBLE);
//                mImageView2.setVisibility(View.INVISIBLE);
//                mImageView3.setVisibility(View.VISIBLE);
//            } else if (curFallingImageView == mImageView3) {
//                curFallingImageView = mImageView1;
//                mImageView1.startAnimation(fallingAnimation);
//                mImageView1.setVisibility(View.VISIBLE);
//                mImageView2.setVisibility(View.INVISIBLE);
//                mImageView3.setVisibility(View.INVISIBLE);
//            }
//        }
//
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//        }
//
//        @Override
//        public void onAnimationStart(Animation animation) {
//        }
//    };

    @Override
    public void callDialogChangeBg() {
        settings.dismiss();
        dlg1.show(getSupportFragmentManager(),"dlg1");
    }

    @Override
    public void changeBg(int bg) {
        relativeLayout.setBackgroundResource(bg);


    }

    @Override
    public void onClick(View v) {
        settings.show(getSupportFragmentManager(),"settings");

    }



}
