package vn.edu.poly.damsenapp.Model.ModelLogin.LogigFacebook;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public interface LoginFacebookModel {

    interface onLoginFacebookListener {
        void loginFacebook(CallbackManager callbackManager);

        void loginFacebookSuccess(int success, String name);

        void logoutFacebookSuccess(int success, String message);

        void navigateScreen(Class mClass);
    }

    void loginWithFacebook(onLoginFacebookListener listener, LoginButton mLoginFacebook);

    void logoutFacebook(onLoginFacebookListener listener);
}
