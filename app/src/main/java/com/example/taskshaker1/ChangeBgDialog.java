package com.example.taskshaker1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ChangeBgDialog extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Activity mainActivity;
    private MainInterface interface1;
    RadioGroup radioGroup;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interface1 = (MainInterface) context;
    }

    public ChangeBgDialog(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.dialog, null);

        radioGroup = (RadioGroup) v.findViewById(R.id.rgGravity);
        radioGroup.setOnCheckedChangeListener(this);


//        RadioGroup radioGroup = findViewById(R.id.rgGravity);
        return v;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb1:
                    interface1.changeBg(R.drawable.bg_1);
                    break;
                case R.id.rb2:
                    interface1.changeBg(R.drawable.bg_2);
                    break;
                case R.id.rb3:
                    interface1.changeBg(R.drawable.bg_3);
                    break;
                default:
                    break;
            }
    }

    @Override
    public void onClick(View v) {

    }

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