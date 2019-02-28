package vn.edu.poly.damsenapp.View.Login;

import com.facebook.CallbackManager;

public interface LoginView {

    void showDialog();

    void hideDialog();

    void loginFacebook(CallbackManager callbackManager);

    void loginFacebookSuccess(int success, String name);

    void logoutFacebookSuccess(int success, String message);

    void navigateScreen(Class mClass);
}
