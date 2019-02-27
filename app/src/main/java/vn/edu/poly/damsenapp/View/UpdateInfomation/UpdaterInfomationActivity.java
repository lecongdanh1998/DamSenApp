package vn.edu.poly.damsenapp.View.UpdateInfomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Presenter.UpdaterInfomation.PTVUpdaterInfomation;
import vn.edu.poly.damsenapp.Presenter.UpdaterInfomation.UpdaterInfomation;
import vn.edu.poly.damsenapp.R;

public class UpdaterInfomationActivity extends BaseActivity implements PTVUpdaterInfomation, View.OnClickListener {
    UpdaterInfomation updaterInfomation;
    ImageView img_back;
    TextView txt_title_toobar;

    @Override
    protected int initLayout() {
        return R.layout.activity_updater_infomation;
    }

    @Override
    protected void initView() {
        img_back = findViewById(R.id.img_btn_account);
        txt_title_toobar = findViewById(R.id.txt_name_title);
    }

    @Override
    protected void initData() {
        updaterInfomation = new UpdaterInfomation(this, this);
        initToobar();
    }

    private void initToobar() {
        txt_title_toobar.setText("Cập nhật thông tin");
        img_back.setImageResource(R.drawable.ic_arrow_back_white_24dp);
    }

    @Override
    protected void initOnClick() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_account:
                onBackPressed();
                break;
        }
    }
}
