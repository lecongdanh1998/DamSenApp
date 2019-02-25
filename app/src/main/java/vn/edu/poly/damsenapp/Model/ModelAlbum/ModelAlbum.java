package vn.edu.poly.damsenapp.Model.ModelAlbum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

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

public class ModelAlbum {
    ModelReponsetoPresenterAlbum callback;
    Context context;
    Activity activity;
    ArrayList<ContructorLibraryAlbum> arrayList;
    CustomGridViewAlbumActivity adapter;
    CustomViewPagerAdapterAlbum mAdapter;
    List<HotDealObjectAlbum> mTestData;
    Bitmap bitmap = null;
    public ModelAlbum(ModelReponsetoPresenterAlbum callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        arrayList = new ArrayList<>();
        mTestData = new ArrayList<HotDealObjectAlbum>();
    }

    public void initData() {
        arrayList.clear();
        arrayList.add(new ContructorLibraryAlbum(bitmap,"http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibraryAlbum());
        adapter = new CustomGridViewAlbumActivity(context, arrayList);
        callback.onData(adapter, arrayList);
    }


    public void initImageViewPager() {
        mTestData.clear();
        for (int i = 0; i < arrayList.size() - 1; i++) {
            mTestData.add(new HotDealObjectAlbum(arrayList.get(i).getImage()));
        }
        mAdapter = new CustomViewPagerAdapterAlbum(context, mTestData);
        callback.onImageViewPager(mAdapter, mTestData);
    }

    public void initAddData(Bitmap bitmap1) {
        Log.d("Bitmap",""+bitmap1);
        arrayList.remove(arrayList.size()-1);
//        arrayList.clear();
//        arrayList.add(new ContructorLibraryAlbum(bitmap,"http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibraryAlbum(bitmap1,""));
        arrayList.add(new ContructorLibraryAlbum(bitmap,""));
        callback.onData2(adapter, arrayList);
    }

}
