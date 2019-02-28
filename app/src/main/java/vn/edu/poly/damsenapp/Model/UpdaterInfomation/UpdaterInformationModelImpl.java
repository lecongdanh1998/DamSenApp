package vn.edu.poly.damsenapp.Model.UpdaterInfomation;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitAPIs;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitUtils;

public class UpdaterInformationModelImpl implements UpdaterInformationModel {

    private static final String TAG = "UPDATE_ACCOUNT";
    private Context mContext;
    private Activity mActivity;
    private CompositeDisposable mDisposable;
    private RetrofitAPIs mRetrofit;

    public UpdaterInformationModelImpl(Context mContext) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public void updateUser(OnUpdateListener listener, String name, String email, String phone, String password, String confirmPassword) {
        mRetrofit = RetrofitUtils.user();
        mDisposable = new CompositeDisposable();
        int errorCode = 0;
        if (!password.equals(confirmPassword)) {
            listener.updateInfo(2, "Password does not match");
            errorCode = 1;
        } else if (name.isEmpty()) {
            listener.updateInfo(2, "Enter your name");
            errorCode = 1;
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            listener.updateInfo(2, "Enter your email");
            errorCode = 1;
        } else if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            listener.updateInfo(2, "Enter your phone");
            errorCode = 1;
        }
        if (errorCode == 0) {
            mDisposable.add(mRetrofit.registerAccount(name, email, phone, password)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(
                            data -> {
                                Log.d(TAG, "updateUser data: " + data.getMessage());
                                mActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        listener.updateInfo(0, data.getMessage());
                                    }
                                });
                            },
                            throwable -> {
                                Log.d(TAG, "updateUser: " + throwable.toString());
                            }
                    )
            );
        } else {
            Log.d(TAG, "updateUser error code: ");
        }
    }
}
