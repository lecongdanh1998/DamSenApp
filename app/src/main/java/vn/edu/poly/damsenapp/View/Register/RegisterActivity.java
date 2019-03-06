package vn.edu.poly.damsenapp.View.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Model.UpdaterInfomation.UpdaterInformationModelImpl;
import vn.edu.poly.damsenapp.Presenter.Register.PTVRegister;
import vn.edu.poly.damsenapp.Presenter.Register.Register;
import vn.edu.poly.damsenapp.Presenter.UpdaterInfomation.UpdaterInformationPresenter;
import vn.edu.poly.damsenapp.Presenter.UpdaterInfomation.UpdaterInformationPresenterImpl;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Login.LoginActivity;
import vn.edu.poly.damsenapp.View.UpdateInfomation.UpdateInformationView;

public class RegisterActivity extends BaseActivity implements UpdateInformationView, View.OnClickListener {
    UpdaterInformationPresenter mPresenter;
    ImageView img_back;
    Button btn_login;
    TextView txt_title_toobar;
    EditText mTextViewName, mTextViewEmail, mTextViewPass, mTextViewCPass, mTextViewPhone;
    Button mButtonRegister;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        img_back = findViewById(R.id.img_btn_account);
        btn_login = findViewById(R.id.btn_login);
        txt_title_toobar = findViewById(R.id.txt_name_title);
        mTextViewName = findViewById(R.id.edt_name_register);
        mTextViewEmail = findViewById(R.id.edt_email_register);
        mTextViewPass = findViewById(R.id.edt_password_register);
        mTextViewCPass = findViewById(R.id.edt_confirm_password_register);
        mButtonRegister = findViewById(R.id.btn_register);
        mTextViewPhone = findViewById(R.id.edt_phone_register);
    }

    @Override
    protected void initData() {
        initToobar();
        mPresenter = new UpdaterInformationPresenterImpl(new UpdaterInformationModelImpl(this), this);
    }

    private void initToobar() {
        txt_title_toobar.setText("Đăng ký");
        img_back.setImageResource(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    protected void initOnClick() {
        btn_login.setOnClickListener(this);
        img_back.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_account:
            case R.id.btn_login:
                onBackPressed();
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        String name = mTextViewName.getText().toString().trim();
        String email = mTextViewEmail.getText().toString().trim();
        String phone = mTextViewPhone.getText().toString().trim();
        String password = mTextViewPass.getText().toString().trim();
        String confirmPassword = mTextViewCPass.getText().toString().trim();
        mPresenter.updateInfo(name, email, phone, password, confirmPassword);
    }

    @Override
    public void updateInformationSucess(int success, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
    }
}
