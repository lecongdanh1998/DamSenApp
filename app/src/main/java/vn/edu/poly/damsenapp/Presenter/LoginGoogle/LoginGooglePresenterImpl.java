package vn.edu.poly.damsenapp.Presenter.LoginGoogle;

import com.facebook.login.Login;

import vn.edu.poly.damsenapp.Model.ModelLogin.LoginGoogle.LoginGoogleModel;
import vn.edu.poly.damsenapp.View.Login.LoginView;

public class LoginGooglePresenterImpl implements LoginGoogleModel.onLoginGoogleListener, LoginGooglePresenter {

    private LoginGoogleModel mLoginGoogleModel;
    private LoginView mLoginView;

    public LoginGooglePresenterImpl(LoginGoogleModel mLoginGoogleModel, LoginView mLoginView) {
        this.mLoginGoogleModel = mLoginGoogleModel;
        this.mLoginView = mLoginView;
    }

    @Override
    public void loginGoogle(int success, String message) {
        if (mLoginView != null){
            mLoginView.loginFacebookSuccess(success, message);
        }
    }

    @Override
    public void navigateScreen(Class mClass) {
        if (mLoginView != null){
            mLoginView.navigateScreen(mClass);
        }
    }

    @Override
    public void loginWithGoogle(String email) {
        if (mLoginGoogleModel != null){
            mLoginGoogleModel.loginWithGoogle(this, email);
        }
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }
}
