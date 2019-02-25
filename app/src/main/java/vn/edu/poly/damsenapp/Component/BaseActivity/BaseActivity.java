package vn.edu.poly.damsenapp.Component.BaseActivity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
        initOnClick();

    }

    @LayoutRes
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initOnClick();

}
