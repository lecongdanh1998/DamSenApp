package vn.edu.poly.damsenapp.Component.BaseActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    public static SharedPreferences dataResultUserName;
    public static SharedPreferences.Editor editorResultUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initEditor();
        initView();
        initData();
        initOnClick();

    }
    private void initEditor() {
        dataResultUserName = getSharedPreferences("data_ResultCodePassword", MODE_PRIVATE);

    }
    @LayoutRes
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initOnClick();


}
