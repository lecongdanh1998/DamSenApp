package vn.edu.poly.damsenapp.Presenter.PresenterHomePage;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;
import vn.edu.poly.damsenapp.Model.ModelHomePage.ModelHomePage;
import vn.edu.poly.damsenapp.Model.ModelHomePage.ModelReponsentoPresenterHomePage;

public class PresenterHomePage implements ModelReponsentoPresenterHomePage {
    PresenterReponsetoViewHomePage callback;
    Context context;
    Activity activity;
    ModelHomePage modelHomePage;

    public PresenterHomePage(PresenterReponsetoViewHomePage callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelHomePage = new ModelHomePage(this, context);
    }

    public void initData() {
        modelHomePage.initData();
    }

    public void initImageViewPager() {
        modelHomePage.initImageViewPager();
    }

    @Override
    public void onData(CustomGridViewActivity adpter) {
        callback.onData(adpter);
    }

    @Override
    public void onImageViewPager(CustomViewPagerAdapter adpter, List<HotDealObject> mTestData) {
        callback.onImageViewPager(adpter, mTestData);
    }
}
