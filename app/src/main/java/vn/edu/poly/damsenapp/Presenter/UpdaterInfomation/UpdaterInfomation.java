package vn.edu.poly.damsenapp.Presenter.UpdaterInfomation;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.damsenapp.Model.UpdaterInfomation.MTPUpdaterInfomation;

public class UpdaterInfomation implements MTPUpdaterInfomation {
    Context context;
    Activity activity;
    PTVUpdaterInfomation callback;
    vn.edu.poly.damsenapp.Model.UpdaterInfomation.UpdaterInfomation updaterInfomation;

    public UpdaterInfomation(Context context, PTVUpdaterInfomation callback) {
        this.context = context;
        this.callback = callback;
        this.activity = (Activity) context;
        updaterInfomation = new vn.edu.poly.damsenapp.Model.UpdaterInfomation.UpdaterInfomation(this,context);
    }
}
