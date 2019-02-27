package vn.edu.poly.damsenapp.Model.ModelMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import vn.edu.poly.damsenapp.Adapter.MenuAdapter;
import vn.edu.poly.damsenapp.Contructor.MenuModel;
import vn.edu.poly.damsenapp.R;


public class ModelMain {
    ModelReponsetoPresenterMain callback;
    Context context;
    Activity activity;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuModel> menuModelArrayList;

    public ModelMain(ModelReponsetoPresenterMain callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        menuModelArrayList = new ArrayList<>();
    }

    public void initBarCode() {
//        initIntentView(BarcodeActivity.class);
    }

    public void initTabLayOut(View.OnClickListener clickNe) {
        menuModelArrayList = new ArrayList<>();
        menuModelArrayList.add(new MenuModel("Trang chủ"));
        menuModelArrayList.add(new MenuModel("Album"));
        menuModelArrayList.add(new MenuModel("Bảng xếp hạng"));
        menuAdapter = new MenuAdapter(context, menuModelArrayList, "3",clickNe);
        callback.onTabLayOut(menuAdapter);
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(activity, c);
        activity.startActivity(mainIntent);
    }






}
