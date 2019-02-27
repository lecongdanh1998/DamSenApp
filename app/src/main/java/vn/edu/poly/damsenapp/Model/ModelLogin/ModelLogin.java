package vn.edu.poly.damsenapp.Model.ModelLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Util.ValidateForm;
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

    public void initLogin(int requestcode, String email, String password) {
        switch (requestcode) {
            case 0:
                BaseActivity.editorResultUserName = BaseActivity.dataResultUserName.edit();
                BaseActivity.editorResultUserName.putInt("codePassword", requestcode);
                BaseActivity.editorResultUserName.putString("textEmail", email);
                BaseActivity.editorResultUserName.putString("textPassword", "");
                BaseActivity.editorResultUserName.commit();
                break;
            case 1:
                BaseActivity.editorResultUserName = BaseActivity.dataResultUserName.edit();
                BaseActivity.editorResultUserName.putInt("codePassword", requestcode);
                BaseActivity.editorResultUserName.putString("textEmail", email);
                BaseActivity.editorResultUserName.putString("textPassword", password);
                BaseActivity.editorResultUserName.commit();
                break;
        }


        if (new ValidateForm().validateTextEmpty(email) == false && new ValidateForm().validateTextEmpty(password) == false) {
            callback.onDataIntent(1);
        } else if (new ValidateForm().validateTextEmpty(email) == true && new ValidateForm().validateTextEmpty(password) == false) {
            callback.onDataIntent(2);
        } else if (new ValidateForm().validateTextEmpty(email) == false && new ValidateForm().validateTextEmpty(password) == true) {
            callback.onDataIntent(3);
        } else {
            callback.onDataIntent(4);
        }
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(activity, c);
        activity.startActivity(mainIntent);
        activity.finish();
    }

}
