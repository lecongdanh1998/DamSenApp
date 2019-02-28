package vn.edu.poly.damsenapp.Model.ModelLogin.LogigFacebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import vn.edu.poly.damsenapp.View.Main.MainActivity;
import vn.edu.poly.damsenapp.View.UpdateInfomation.UpdaterInfomationActivity;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitAPIs;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitUtils;

public class LoginFacebookModelImpl implements LoginFacebookModel {

    private static final String TAG = "LOGIN_FACEBOOK";
    private Context mContext;
    private Activity mActivity;
    private CallbackManager callbackManager;
    private String name = "", phone = "", email = "";
    private RetrofitAPIs mRetrofit;
    private CompositeDisposable mDisposable;

    public LoginFacebookModelImpl(Context mContext) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;
    }

    @Override
    public void loginWithFacebook(onLoginFacebookListener listener, LoginButton mLoginFacebook) {
        callbackManager = CallbackManager.Factory.create();
        mLoginFacebook.performClick();
        mLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest mGraphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            name = object.getString("name");
                            email = object.getString("email");
                            Log.d(TAG, "onCompleted: " + response.toString());
                            if (!email.isEmpty()) {
                                loginDatabase(listener, email, name);
                            } else {
                                listener.loginFacebookSuccess(1, "No email");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, ": " + e.getMessage());
                        }
                        Log.d(TAG, "onCompleted: " + response.toString());
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender");
                mGraphRequest.setParameters(parameters);
                mGraphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: " + error.toString());
            }
        });
        listener.loginFacebook(callbackManager);
    }

    private void loginDatabase(onLoginFacebookListener listener, String email, String name) {
        mRetrofit = RetrofitUtils.user();
        mDisposable = new CompositeDisposable();
        mDisposable.add(mRetrofit.loginFacebook(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            if (data.getErrorCode() == 0) {
                                listener.navigateScreen(UpdaterInfomationActivity.class);
                            } else if (data.getErrorCode() == -1) {
                                listener.loginFacebookSuccess(0, name);
                                listener.navigateScreen(MainActivity.class);
                            }
                            Log.d(TAG, "loginDatabase: " + data.getErrorCode());
                        },
                        throwable -> {
                            Log.d(TAG, "loginDatabase: " + throwable.getMessage());
                        }
                )
        );
    }

    @Override
    public void logoutFacebook(onLoginFacebookListener listener) {

    }
}
