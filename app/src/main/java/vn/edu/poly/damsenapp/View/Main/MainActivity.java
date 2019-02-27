package vn.edu.poly.damsenapp.View.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.damsenapp.Adapter.MenuAdapter;
import vn.edu.poly.damsenapp.Component.BaseActivity.BaseActivity;
import vn.edu.poly.damsenapp.Contructor.MenuModel;
import vn.edu.poly.damsenapp.Presenter.PresenterMain.PresenterMain;
import vn.edu.poly.damsenapp.Presenter.PresenterMain.PresenterReponsetoViewMain;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Album.Album;
import vn.edu.poly.damsenapp.View.HomePage.HomePage;

import static android.support.constraint.ConstraintSet.VISIBLE;

public class MainActivity extends BaseActivity implements PresenterReponsetoViewMain, View.OnClickListener, AdapterView.OnItemClickListener {
    private DrawerLayout drawer_layout;
    PresenterMain presenterMain;
    private ListView listview_menu;
    private TextView toolbar_title;
    private ImageView img_btn_account;
    private View.OnClickListener clickNe;
    LinearLayout accept;
    ImageView img_album;
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        img_btn_account = findViewById(R.id.img_btn_account);
        toolbar_title = findViewById(R.id.txt_name_title);
        listview_menu = findViewById(R.id.listview_menu);
        drawer_layout = findViewById(R.id.drawer_layout);
    }

    @Override
    protected void initData() {
        clickNe = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Album();
                transactionFrangment(fragment, "Album");
                accept.setVisibility(ImageView.GONE);
                img_album.setRotation(0f);
            }
        };
        presenterMain = new PresenterMain(this, this);
        presenterMain.initTabLayOut(clickNe);


    }

    @Override
    protected void initOnClick() {
        img_btn_account.setOnClickListener(this);
        listview_menu.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_account:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
    }

    public void transactionFrangment(Fragment f, String s) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                f, s).commit();
        toolbar_title.setText(s);
        drawer_layout.closeDrawers();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomePage();
                transactionFrangment(fragment, "Trang Chủ");
                break;
            case 1:
                accept = view.findViewById(R.id.linearLayout_album);
                img_album = view.findViewById(R.id.img_album);
                if (accept.getVisibility() == View.GONE) {
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.topbottom);
                    accept.startAnimation(animation);
                    accept.setVisibility(View.VISIBLE);
                    img_album.setRotation(180f);
                } else {
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.bottomtop);
                    accept.startAnimation(animation);
                    accept.setVisibility(View.GONE);
                    img_album.setRotation(0f);
                }
//                fragment = new Album();
//                transactionFrangment(fragment, "Album");
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabLayOut(MenuAdapter menuAdapter) {
        listview_menu.setAdapter(menuAdapter);
        try {
            transactionFrangment(new HomePage(), "Trang Chủ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
