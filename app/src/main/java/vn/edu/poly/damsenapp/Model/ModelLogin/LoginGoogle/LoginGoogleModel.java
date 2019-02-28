package vn.edu.poly.damsenapp.Model.ModelLogin.LoginGoogle;

public interface LoginGoogleModel {

    interface onLoginGoogleListener{
        void loginGoogle(int success, String message);

        void navigateScreen(Class mClass);
    }

    void loginWithGoogle(onLoginGoogleListener listener, String email);
}
