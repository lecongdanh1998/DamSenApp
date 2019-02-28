package vn.edu.poly.damsenapp.View.UpdateInfomation;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Model.UpdaterInfomation.UpdaterInformationModelImpl;
import vn.edu.poly.damsenapp.Presenter.UpdaterInfomation.UpdaterInformationPresenterImpl;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Main.MainActivity;

public class UpdaterInfomationActivity extends BaseActivity implements UpdateInformationView, View.OnClickListener {

    private UpdaterInformationPresenterImpl mUpdatePresenter;
    private ImageView img_back;
    private TextView txt_title_toobar;
    private EditText mEditTextName, mEditTextEmail, mEditTextPhone, mEditTextPassword, mEditTextConfirmPass;
    private Button mButtonUpdate;

    @Override
    protected int initLayout() {
        return R.layout.activity_updater_infomation;
    }

    @Override
    protected void initView() {
        img_back = findViewById(R.id.img_btn_account);
        txt_title_toobar = findViewById(R.id.txt_name_title);
        mEditTextName = findViewById(R.id.txt_name_update);
        mEditTextEmail = findViewById(R.id.txt_email_update);
        mEditTextPhone = findViewById(R.id.txt_phone_update);
        mEditTextPassword = findViewById(R.id.txt_password_update);
        mEditTextConfirmPass = findViewById(R.id.txt_confirm_password_update);
        mButtonUpdate = findViewById(R.id.btn_update);
    }

    @Override
    protected void initData() {
        initToobar();
        mUpdatePresenter = new UpdaterInformationPresenterImpl(new UpdaterInformationModelImpl(this), this);
        String email = dataResultUserName.getString("textEmail", "");
        mEditTextEmail.setText(email);
        mEditTextEmail.setFocusable(false);
    }

    private void initToobar() {
        txt_title_toobar.setText("Cập nhật thông tin");
        img_back.setImageResource(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    protected void initOnClick() {
        img_back.setOnClickListener(this);
        mButtonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_account:
                onBackPressed();
                break;
            case R.id.btn_update:
                updateUser();
                break;
        }
    }

    private void updateUser() {
        String name = mEditTextName.getText().toString().trim();
        String email = mEditTextEmail.getText().toString().trim();
        String phone = mEditTextPhone.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        String confirmPass = mEditTextConfirmPass.getText().toString().trim();
        mUpdatePresenter.updateInfo(name, email, phone, password, confirmPass);
    }

    @Override
    public void updateInformationSucess(int success, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mIntent);
    }
}
