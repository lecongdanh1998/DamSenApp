package vn.edu.poly.damsenapp.Presenter.PresenterLogin;

import android.app.Activity;
import android.content.Context;

import vn.edu.poly.damsenapp.Model.ModelLogin.ModelLogin;
import vn.edu.poly.damsenapp.Model.ModelLogin.ModelReponsetoPresenterLogin;


public class PresenterLogin implements ModelReponsetoPresenterLogin {
    PresenterReponsetoViewLogin callback;
    Context context;
    Activity activity;
    ModelLogin modelLogin;

    public PresenterLogin(PresenterReponsetoViewLogin callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelLogin = new ModelLogin(this, context);
    }


    public void initLogin(int requestcode, String email, String password) {
        modelLogin.initLogin(requestcode, email, password);
    }

    @Override
    public void onDataIntent(int requestcode) {
        callback.onDataIntent(requestcode);
    }
}
