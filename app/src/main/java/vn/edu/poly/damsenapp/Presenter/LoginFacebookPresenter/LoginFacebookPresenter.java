package vn.edu.poly.damsenapp.Presenter.LoginFacebookPresenter;

import com.facebook.login.widget.LoginButton;

public interface LoginFacebookPresenter {
    void loginFacebook(LoginButton mLoginButton);

    void onDestroy();
}
