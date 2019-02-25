package vn.edu.poly.damsenapp.Model.ModelSplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Login.LoginActivity;


public class ModelSplashScreen {
    ModelReponsetoPresenterSplashScreen callback;
    Context context;
    Activity activity;
    private int SPLASH_DISPLAY_LENGTH = 3;

    public ModelSplashScreen(ModelReponsetoPresenterSplashScreen callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
    }

    public void initFlasScreen(ImageView imagesLogo) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.mytransition);
        imagesLogo.startAnimation(animation);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread {
        public void run() {
            try {
                sleep(1000 * SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initIntentView(LoginActivity.class);

        }
    }

    private void initIntentView(Class c) {
        Intent mainIntent = new Intent(activity, c);
        activity.startActivity(mainIntent);
        activity.finish();
    }


}
