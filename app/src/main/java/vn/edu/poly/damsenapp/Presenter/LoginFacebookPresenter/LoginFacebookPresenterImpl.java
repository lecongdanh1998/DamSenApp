package vn.edu.poly.damsenapp.Presenter.LoginFacebookPresenter;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import vn.edu.poly.damsenapp.Model.ModelLogin.LogigFacebook.LoginFacebookModel;
import vn.edu.poly.damsenapp.View.Login.LoginView;

public class LoginFacebookPresenterImpl implements LoginFacebookPresenter, LoginFacebookModel.onLoginFacebookListener {

    private LoginFacebookModel mLoginFacebookModel;
    private LoginView mLoginView;

    public LoginFacebookPresenterImpl(LoginFacebookModel mLoginFacebookModel, LoginView mLoginView) {
        this.mLoginFacebookModel = mLoginFacebookModel;
        this.mLoginView = mLoginView;
    }

    @Override
    public void loginFacebook(CallbackManager callbackManager) {
        if (mLoginView != null){
            mLoginView.loginFacebook(callbackManager);
        }
    }

    @Override
    public void loginFacebookSuccess(int success, String name) {
        if (mLoginView != null){
            mLoginView.loginFacebookSuccess(success, name);
        }
    }

    @Override
    public void logoutFacebookSuccess(int success, String message) {
        if (mLoginView != null){
            mLoginView.logoutFacebookSuccess(success, message);
        }
    }

    @Override
    public void navigateScreen(Class mClass) {
        if (mLoginView != null){
            mLoginView.navigateScreen(mClass);
        }
    }

    @Override
    public void loginFacebook(LoginButton mLoginButton) {
        if (mLoginFacebookModel != null){
            mLoginFacebookModel.loginWithFacebook(this, mLoginButton);
        }
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }
}
