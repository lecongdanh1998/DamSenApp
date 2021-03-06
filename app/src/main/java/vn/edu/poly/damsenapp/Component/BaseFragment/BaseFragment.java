package vn.edu.poly.damsenapp.Component.BaseFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseFragment extends Fragment {

    private View mViewBase;
    public static SharedPreferences dataBitMap;
    public static SharedPreferences.Editor editorBitMap;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBase = provideYourFragmentView(inflater, container, savedInstanceState);
        initEditor();
        initView();
        initData();
        initOnClick();
        return mViewBase;
    }

    private void initEditor() {
        dataBitMap = getActivity().getSharedPreferences("data_BitMap",getActivity().MODE_PRIVATE);
    }


    public abstract View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState);

    public abstract void initView();

    public abstract void initData();

    public abstract void initOnClick();




}
