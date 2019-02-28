package vn.edu.poly.damsenapp.View.Login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Model.ModelLogin.LogigFacebook.LoginFacebookModelImpl;
import vn.edu.poly.damsenapp.Model.ModelLogin.LoginGoogle.LoginGoogleModelImpl;
import vn.edu.poly.damsenapp.Presenter.LoginFacebookPresenter.LoginFacebookPresenter;
import vn.edu.poly.damsenapp.Presenter.LoginFacebookPresenter.LoginFacebookPresenterImpl;
import vn.edu.poly.damsenapp.Presenter.LoginGoogle.LoginGooglePresenter;
import vn.edu.poly.damsenapp.Presenter.LoginGoogle.LoginGooglePresenterImpl;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterLogin;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterReponsetoViewLogin;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Main.MainActivity;
import vn.edu.poly.damsenapp.View.Register.RegisterActivity;

public class LoginActivity extends BaseActivity implements PresenterReponsetoViewLogin, View.OnClickListener, LoginView {

    private static final String TAG = "LOGIN_ACTIVITY";
    private static final int RC_SIGN_IN = 2211;
    private static final int RC_SIGN_IN_FACE = 1122;
    PresenterLogin presenterLogin;
    EditText edtEmail, edtPassword;
    Button btnLogin, btn_signup;
    CheckBox checkbox_password;
    String email = "";
    String password = "";
    int codeCheck;
    private SignInButton mSignInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private RelativeLayout mButtonLoginFCustom;
    private ImageView mImageViewLoginFacebook;
    private TextView mTextViewLoginFaceBook;
    private LoginButton mLoginFacebook;
    private CallbackManager callbackManager;
    private LoginFacebookPresenter mLoginFacebookPresenter;
    private String name;
    private LoginGooglePresenter mLoginGooglePresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        checkbox_password = findViewById(R.id.checkbox_password);
        btn_signup = findViewById(R.id.btn_signup);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        mLoginFacebook = findViewById(R.id.login_button_facebook);
        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setSize(SignInButton.SIZE_STANDARD);
        mButtonLoginFCustom = findViewById(R.id.layout_login_facebook);
        mImageViewLoginFacebook = findViewById(R.id.img_login_facebook_custom);
        mTextViewLoginFaceBook = findViewById(R.id.txt_login_facebook_custom);
    }

    @Override
    protected void initData() {
        presenterLogin = new PresenterLogin(this, this);
        initEditTor();
        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    checkLogin();
                    return true;
                }
                return false;
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        mLoginFacebookPresenter = new LoginFacebookPresenterImpl(new LoginFacebookModelImpl(this), this);
        mLoginGooglePresenter = new LoginGooglePresenterImpl(new LoginGoogleModelImpl(this), this);
    }

    private void initEditTor() {
        codeCheck = dataResultUserName.getInt("codePassword", 0);
        email = dataResultUserName.getString("textEmail", "");
        password = dataResultUserName.getString("textPassword", "");
        if (!email.toString().equals("")) {
            edtEmail.setText(email);
        }
        if (!password.toString().equals("")) {
            edtPassword.setText(password);
        }
        if (codeCheck == 0) {
            checkbox_password.setChecked(false);
        } else if (codeCheck == 1) {
            checkbox_password.setChecked(true);
        }
    }


    private void checkLogin() {
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        if (checkbox_password.isChecked()) {
            presenterLogin.initLogin(1, email, password);
        } else {
            presenterLogin.initLogin(0, email, password);
        }
    }

    @Override
    protected void initOnClick() {
        btn_signup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);
        mButtonLoginFCustom.setOnClickListener(this);
        mImageViewLoginFacebook.setOnClickListener(this);
        mTextViewLoginFaceBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                checkLogin();
                break;
            case R.id.btn_signup:
                initIntentView(RegisterActivity.class);
                break;
            case R.id.layout_login_facebook:
            case R.id.img_login_facebook_custom:
            case R.id.txt_login_facebook_custom:
                faceIn();
                break;
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(this, c);
        startActivity(mainIntent);
    }

    @Override
    public void onDataIntent(int requestcode) {
        switch (requestcode) {
            case 1:
                initIntentView(MainActivity.class);
                finish();
                break;
            case 2:
                edtEmail.setError("Please enter your email!");
                break;
            case 3:
                edtPassword.setError("Please enter your password!");
                break;
            case 4:
                edtEmail.setError("Please enter your email!");
                edtPassword.setError("Please enter your password!");
                break;
        }
    }

    private void faceIn() {
        mLoginFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_gender"));
        mLoginFacebookPresenter.loginFacebook(mLoginFacebook);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.d(TAG, "handleSignInResult: " + account.getEmail());
            Log.d(TAG, "handleSignInResult: " + account.getDisplayName());
            mLoginGooglePresenter.loginWithGoogle(account.getEmail());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void loginFacebook(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
        callbackManager.onActivityResult(RC_SIGN_IN_FACE, RESULT_OK, getIntent());
    }

    @Override
    public void loginFacebookSuccess(int success, String name) {
        Toast.makeText(this, "Welcome back " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutFacebookSuccess(int success, String message) {
        Toast.makeText(this, "Welcome " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateScreen(Class mClass) {
        Intent mIntent = new Intent(this, mClass);
        startActivity(mIntent);
    }
}
