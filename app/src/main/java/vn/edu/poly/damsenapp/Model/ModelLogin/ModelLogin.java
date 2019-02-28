package vn.edu.poly.damsenapp.Model.ModelLogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Util.ValidateForm;
import vn.edu.poly.damsenapp.View.Main.MainActivity;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitAPIs;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitUserInstance;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitUtils;
import vn.edu.poly.damsenapp.retrofit.response.UserResult;

public class ModelLogin {
    ModelReponsetoPresenterLogin callback;
    Context context;
    Activity activity;
    private ProgressDialog progressDialog;
    public ModelLogin(ModelReponsetoPresenterLogin callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        progressDialog = new ProgressDialog(context);
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
            SignIn(email,password);
        } else if (new ValidateForm().validateTextEmpty(email) == true && new ValidateForm().validateTextEmpty(password) == false) {
            callback.onDataIntent(2);
        } else if (new ValidateForm().validateTextEmpty(email) == false && new ValidateForm().validateTextEmpty(password) == true) {
            callback.onDataIntent(3);
        } else {
            callback.onDataIntent(4);
        }
    }

    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(activity, c);
        activity.startActivity(mainIntent);
        activity.finish();
    }

    public void SignIn(final String email, String password) {
        setContentDialog("Đăng nhập", "Vui lòng chờ");
        progressDialog.show();
        progressDialog.setCancelable(false);
        RetrofitAPIs dataClient = RetrofitUtils.user();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("password", password);
        Call<UserResult> callback1 = dataClient.loginEmail(hashMap);
        callback1.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.body() == null) {
                    progressDialog.hide();
                } else {
//                    initInfoDataUser(response.body().getAccess_token());
                    if(response.body().getErrorCode() == 0){
                        callback.onDataIntent(1);
                    }else {
                        Toast.makeText(context, "Email hoặc password không đúng", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
    }

}
