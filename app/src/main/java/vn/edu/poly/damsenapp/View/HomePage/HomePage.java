package vn.edu.poly.damsenapp.View.HomePage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Component.BaseFragment.BaseFragment;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;
import vn.edu.poly.damsenapp.Presenter.PresenterHomePage.PresenterHomePage;
import vn.edu.poly.damsenapp.Presenter.PresenterHomePage.PresenterReponsetoViewHomePage;
import vn.edu.poly.damsenapp.R;

public class HomePage extends BaseFragment implements PresenterReponsetoViewHomePage, View.OnClickListener, AdapterView.OnItemClickListener {
    GridView gridView;
    PresenterHomePage presenterHomePage;
    private View view;
    ViewPager viewPager;
    RelativeLayout relativeLayoutShowHide, relativeLayoutToobar;
    ImageView btn_exit;

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.home_page, parent, false);
    }

    @Override
    public void initView() {
        gridView = view.findViewById(R.id.grid_view_image_text);
        relativeLayoutShowHide = view.findViewById(R.id.relativeLayoutShowHide);
        viewPager = view.findViewById(R.id.pager);
        btn_exit = view.findViewById(R.id.btn_exit);
        relativeLayoutToobar = getActivity().findViewById(R.id.relativeLayoutToobar);
    }

    @Override
    public void initData() {
        presenterHomePage = new PresenterHomePage(this, getContext());
        presenterHomePage.initData();
    }

    @Override
    public void initOnClick() {
        gridView.setOnItemClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);
                relativeLayoutShowHide.startAnimation(animation);
                relativeLayoutToobar.setVisibility(View.VISIBLE);
                relativeLayoutShowHide.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onData(CustomGridViewActivity adpter) {
        gridView.setAdapter(adpter);
        adpter.notifyDataSetChanged();
        presenterHomePage.initImageViewPager();
    }

    @Override
    public void onImageViewPager(CustomViewPagerAdapter adpter, List<HotDealObject> mTestData) {
        viewPager.setAdapter(adpter);
        adpter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gridView.getItemAtPosition(position);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        relativeLayoutShowHide.startAnimation(animation);
        Animation animationToobar = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        relativeLayoutToobar.startAnimation(animationToobar);
        relativeLayoutToobar.setVisibility(View.GONE);
        relativeLayoutShowHide.setVisibility(View.VISIBLE);
        viewPager.setCurrentItem(position, false);


    }

}
