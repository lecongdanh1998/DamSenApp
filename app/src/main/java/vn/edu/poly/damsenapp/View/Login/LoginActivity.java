package vn.edu.poly.damsenapp.View.Login;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterLogin;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterReponsetoViewLogin;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Main.MainActivity;
import vn.edu.poly.damsenapp.View.Register.RegisterActivity;

public class LoginActivity extends BaseActivity implements PresenterReponsetoViewLogin, View.OnClickListener {

    PresenterLogin presenterLogin;
    EditText edtEmail, edtPassword;
    Button btnLogin, btn_signup;
    CheckBox checkbox_password;
    String email = "";
    String password = "";
    int codeCheck;

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
}
