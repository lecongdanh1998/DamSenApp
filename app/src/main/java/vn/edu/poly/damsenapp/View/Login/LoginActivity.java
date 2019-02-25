package vn.edu.poly.damsenapp.View.Login;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterLogin;
import vn.edu.poly.damsenapp.Presenter.PresenterLogin.PresenterReponsetoViewLogin;
import vn.edu.poly.damsenapp.R;

public class LoginActivity extends BaseActivity implements PresenterReponsetoViewLogin,View.OnClickListener {

    PresenterLogin presenterLogin;
    EditText edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {
        presenterLogin = new PresenterLogin(this, this);
        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    presenterLogin.initLogin();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initOnClick() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                presenterLogin.initLogin();
                break;
        }
    }
}
