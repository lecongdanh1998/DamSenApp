package vn.edu.poly.damsenapp.View.Register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Presenter.Register.PTVRegister;
import vn.edu.poly.damsenapp.Presenter.Register.Register;
import vn.edu.poly.damsenapp.R;

public class RegisterActivity extends BaseActivity implements PTVRegister,View.OnClickListener {
    Register register;
    ImageView img_back;
    Button btn_login;
    TextView txt_title_toobar;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        img_back = findViewById(R.id.img_btn_account);
        btn_login = findViewById(R.id.btn_login);
        txt_title_toobar = findViewById(R.id.txt_name_title);
    }

    @Override
    protected void initData() {
        register = new Register(this, this);
        initToobar();
    }

    private void initToobar() {
        txt_title_toobar.setText("Đăng ký");
        img_back.setImageResource(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    protected void initOnClick() {
        btn_login.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_account:
            case R.id.btn_login:
                onBackPressed();
                break;
        }
    }
}
