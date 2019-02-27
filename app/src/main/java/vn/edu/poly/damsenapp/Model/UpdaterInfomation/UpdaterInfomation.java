package vn.edu.poly.damsenapp.Model.UpdaterInfomation;

import android.app.Activity;
import android.content.Context;

public class UpdaterInfomation {
    MTPUpdaterInfomation callback;
    Context context;
    Activity activity;

    public UpdaterInfomation(MTPUpdaterInfomation callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }
}
