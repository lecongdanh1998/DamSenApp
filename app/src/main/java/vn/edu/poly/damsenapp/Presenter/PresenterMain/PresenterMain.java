package vn.edu.poly.damsenapp.Presenter.PresenterMain;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import vn.edu.poly.damsenapp.Adapter.MenuAdapter;
import vn.edu.poly.damsenapp.Model.ModelMain.ModelMain;
import vn.edu.poly.damsenapp.Model.ModelMain.ModelReponsetoPresenterMain;


public class PresenterMain  implements ModelReponsetoPresenterMain {
    PresenterReponsetoViewMain callback;
    Context context;
    Activity activity;
    ModelMain modelMain;

    public PresenterMain(PresenterReponsetoViewMain callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelMain = new ModelMain(this, context);
    }

    public void initBarCode(){
        modelMain.initBarCode();
    }


    public void initTabLayOut(View.OnClickListener clickNe) {
        modelMain.initTabLayOut(clickNe);
    }
    @Override
    public void onTabLayOut(MenuAdapter menuAdapter) {
        callback.onTabLayOut(menuAdapter);
    }
}
