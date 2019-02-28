package vn.edu.poly.damsenapp.View.SplashScreen;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Presenter.PresenterSplashScreen.PresenterReponsetoViewSplashScreen;
import vn.edu.poly.damsenapp.Presenter.PresenterSplashScreen.PresenterSplashScreen;
import vn.edu.poly.damsenapp.R;


public class SplashScreenActivity extends BaseActivity implements PresenterReponsetoViewSplashScreen {


    PresenterSplashScreen presenterSplashScreen;
    ImageView imageViewLogo;
    public static String TAG = "SPLASH_SCREEN";


    @Override
    protected int initLayout() {
        initTitle();
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initView() {
        imageViewLogo = findViewById(R.id.imagesLogo);
    }

    @Override
    protected void initData() {
        presenterSplashScreen = new PresenterSplashScreen(this, this);
        presenterSplashScreen.initFlasScreen(imageViewLogo);
    }

    @Override
    protected void initOnClick() {

    }

    private void initTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
