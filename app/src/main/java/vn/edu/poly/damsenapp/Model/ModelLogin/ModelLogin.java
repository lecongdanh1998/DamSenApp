package vn.edu.poly.damsenapp.Model.ModelLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import vn.edu.poly.damsenapp.View.Main.MainActivity;

public class ModelLogin {
    ModelReponsetoPresenterLogin callback;
    Context context;
    Activity activity;

    public ModelLogin(ModelReponsetoPresenterLogin callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void initLogin() {
        initIntentView(MainActivity.class);
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(activity, c);
        activity.startActivity(mainIntent);
        activity.finish();
    }

}
