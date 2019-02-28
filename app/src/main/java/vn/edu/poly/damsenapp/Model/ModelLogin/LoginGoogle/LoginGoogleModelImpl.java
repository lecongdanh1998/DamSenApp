package vn.edu.poly.damsenapp.Model.ModelLogin.LoginGoogle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import vn.edu.poly.damsenapp.View.Main.MainActivity;
import vn.edu.poly.damsenapp.View.UpdateInfomation.UpdaterInfomationActivity;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitAPIs;
import vn.edu.poly.damsenapp.retrofit.config.RetrofitUtils;

public class LoginGoogleModelImpl implements LoginGoogleModel{

    private static final String TAG = "GOOGLE_LOGIN";
    private Context mContext;
    private Activity mActivity;
    private RetrofitAPIs mRetrofit;
    private CompositeDisposable mDisposable;

    public LoginGoogleModelImpl(Context mContext) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;
    }

    @Override
    public void loginWithGoogle(onLoginGoogleListener listener, String email) {
        mRetrofit = RetrofitUtils.user();
        mDisposable = new CompositeDisposable();
        mRetrofit = RetrofitUtils.user();
        mDisposable = new CompositeDisposable();
        mDisposable.add(mRetrofit.loginGoogle(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            if (data.getErrorCode() == 0) {
                                listener.navigateScreen(UpdaterInfomationActivity.class);
                            } else if (data.getErrorCode() == -1) {
                                listener.loginGoogle(0, "");
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
}
