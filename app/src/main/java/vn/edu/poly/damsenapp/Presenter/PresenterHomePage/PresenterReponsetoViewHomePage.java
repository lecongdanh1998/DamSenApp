package vn.edu.poly.damsenapp.Presenter.PresenterHomePage;

import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;

public interface PresenterReponsetoViewHomePage {
    void onData(CustomGridViewActivity adpter);
    void onImageViewPager(CustomViewPagerAdapter adpter, List<HotDealObject> mTestData);
}
