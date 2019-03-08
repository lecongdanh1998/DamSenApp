package vn.edu.poly.damsenapp.Presenter.PresenterAlbum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomGridViewAlbumActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapterAlbum;
import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;
import vn.edu.poly.damsenapp.Model.ModelAlbum.ModelAlbum;
import vn.edu.poly.damsenapp.Model.ModelAlbum.ModelReponsetoPresenterAlbum;

public class PresenterAlbum implements ModelReponsetoPresenterAlbum {
    PresenterReponsetoViewAlbum callback;
    ModelAlbum modelAlbum;
    Context context;
    Activity activity;

    public PresenterAlbum(PresenterReponsetoViewAlbum callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        modelAlbum = new ModelAlbum(this, context);
    }

    public void initData() {
        modelAlbum.initData();
    }

    public void initAddData(Uri resultUri) {
        modelAlbum.initAddData(resultUri);
    }

    public void initImageViewPager() {
        modelAlbum.initImageViewPager();
    }

    public void initAddDataEditCrop(Uri resultUri){
        modelAlbum.initAddDataPosition(resultUri);
    }

    @Override
    public void onData(CustomGridViewAlbumActivity adpter, ArrayList<ContructorLibraryAlbum> arrayList) {
        callback.onData(adpter, arrayList);
    }

    @Override
    public void onData2(CustomGridViewAlbumActivity adpter, ArrayList<ContructorLibraryAlbum> arrayList) {
        callback.onData2(adpter, arrayList);
    }

    @Override
    public void onImageViewPager(CustomViewPagerAdapterAlbum adpter, List<HotDealObjectAlbum> mTestData) {
        callback.onImageViewPager(adpter, mTestData);
    }

    @Override
    public void onFileUri(Uri uri) {
        callback.onFileUri(uri);
    }


}
