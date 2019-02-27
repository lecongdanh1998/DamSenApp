package vn.edu.poly.damsenapp.Model.Register;

import android.app.Activity;
import android.content.Context;

public class Register {
    MTPRegister callback;
    Context context;
    Activity activity;

    public Register(MTPRegister callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }
}
