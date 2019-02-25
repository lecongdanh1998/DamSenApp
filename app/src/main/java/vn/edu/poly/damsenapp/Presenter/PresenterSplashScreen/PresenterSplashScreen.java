package vn.edu.poly.damsenapp.Presenter.PresenterSplashScreen;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import vn.edu.poly.damsenapp.Model.ModelSplashScreen.ModelReponsetoPresenterSplashScreen;
import vn.edu.poly.damsenapp.Model.ModelSplashScreen.ModelSplashScreen;

public class PresenterSplashScreen implements ModelReponsetoPresenterSplashScreen {
    PresenterReponsetoViewSplashScreen callback;
    Context context;
    Activity activity;
    ModelSplashScreen modelSplashScreen;

    public PresenterSplashScreen(PresenterReponsetoViewSplashScreen callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelSplashScreen = new ModelSplashScreen(this, context);
    }

    public void initFlasScreen(ImageView imagesLogo) {
        modelSplashScreen.initFlasScreen(imagesLogo);
    }
}
