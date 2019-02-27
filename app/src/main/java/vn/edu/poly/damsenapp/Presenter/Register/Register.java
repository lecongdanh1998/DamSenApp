package vn.edu.poly.damsenapp.Presenter.Register;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.damsenapp.Model.Register.MTPRegister;

public class Register implements MTPRegister {
    PTVRegister callback;
    Context context;
    vn.edu.poly.damsenapp.Model.Register.Register register;
    Activity activity;

    public Register(PTVRegister callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        register = new vn.edu.poly.damsenapp.Model.Register.Register(this, context);
    }
}
