package com.example.taskshaker1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.DialogFragment;

import com.squareup.seismic.ShakeDetector;

import static android.content.Context.SENSOR_SERVICE;

public class SettingsDialog extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ShakeDetector.Listener {

    public static int SHAKE = 0;
    public static int SWIPE = 1;

    private Activity mainActivity;
    private MainInterface interface1;
    RadioGroup rg;
    SensorManager sm;
    ShakeDetector shakeDetector;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interface1 = (MainInterface) context;
    }

    public SettingsDialog(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.settings, null);
        v.findViewById(R.id.btn_share).setOnClickListener(this);
        v.findViewById(R.id.change_bg).setOnClickListener(this);
        rg = (RadioGroup) v.findViewById(R.id.rg_settings);
        rg.setOnCheckedChangeListener(this);
        sm = (SensorManager) mainActivity.getSystemService(SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(this);



        return v;

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_1:
                interface1.setFlag(SHAKE);
//                shakeDetector.start(sm);
                break;
            case R.id.btn_2:
                interface1.setFlag(SWIPE);
                break;
            default:
                break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                ShareCompat.IntentBuilder.from(mainActivity)
                        .setType("text/plain")
                        .setChooserTitle("Share URL")
                        .setText("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                        .startChooser();
                break;
            case R.id.change_bg:
                interface1.callDialogChangeBg();
                break;
            default:
                break;
        }
//        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
//        dismiss();
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void hearShake() {

    }


//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), R.style.NewDialog);
//
//        return builder
//                .setView(R.layout.settings)
//                .create();




//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        getDialog().setTitle("Title!");
//        View v = inflater.inflate(R.layout.dialog, null);
//
//        return v;
//    }

//    public void onClick(View v) {
//        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
//        dismiss();
//    }
//
//    public void onDismiss(DialogInterface dialog) {
//        super.onDismiss(dialog);
//        Log.d(LOG_TAG, "Dialog 1: onDismiss");
//    }
//
//    public void onCancel(DialogInterface dialog) {
//        super.onCancel(dialog);
//        Log.d(LOG_TAG, "Dialog 1: onCancel");
//    }
}