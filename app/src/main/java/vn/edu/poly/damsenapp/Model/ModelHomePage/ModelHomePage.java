package vn.edu.poly.damsenapp.Model.ModelHomePage;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;

public class ModelHomePage {
    ModelReponsentoPresenterHomePage callback;
    Context context;
    Activity activity;
    ArrayList<ContructorLibrary> arrayList;
    CustomGridViewActivity adapter;
    CustomViewPagerAdapter mAdapter;
    List<HotDealObject> mTestData;


    public ModelHomePage(ModelReponsentoPresenterHomePage callback, Context context) {
        this.callback = callback;
        this.context = context;
        this.activity = (Activity) context;
        arrayList = new ArrayList<>();
        mTestData = new ArrayList<HotDealObject>();
    }


    public void initData() {
        arrayList.clear();
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-05.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-01.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-03.png"));
        arrayList.add(new ContructorLibrary("http://moments.damsenpark.vn/site/images/sieutet/10-04.png"));

//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-04-17-40-WuTa_2019-02-05_11-11-51.jpg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-06-22-21-AE0D81D5-58E9-47B4-B83D-FEC82D26E971.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-06-37-04-E67B0C59-C069-442F-A93F-8BDE74549208.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-06-52-49-20190205_135129.jpg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-06-56-19-IMG20190205135427.jpg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-06-59-49-beauty_20190205135759.jpg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-05-09-10-45-IMG_8112.JPG"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-06-02-48-03-29EC2D71-1816-4479-997E-281A93F5484B.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-08-05-59-01-FE75F482-BA09-4F43-9241-8C4D4195191C.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-08-06-02-37-4ADCF3C5-C074-49EB-89D5-48F9E569D7B9.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-08-05-53-44-BE1A28AA-BCF7-47A4-A479-6114017F2BDB.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-07-16-02-26-A4A6FDAB-8A9E-47C3-922E-02A3D21B485E.jpeg"));
//        arrayList.add(new ContructorLibrary("https://moments.damsenpark.vn/storage/app/public/albums/February2019/2019-02-07-16-03-32-23172D56-9EAB-414A-9159-94ABEFFDA1D8.jpeg"));
        adapter = new CustomGridViewActivity(context, arrayList);
        callback.onData(adapter);
    }



    public void initImageViewPager(){
        mTestData.clear();
        for (int i = 0; i <  arrayList.size(); i++) {
            mTestData.add(new HotDealObject(arrayList.get(i).getImage()));
        }
        mAdapter = new CustomViewPagerAdapter(context, mTestData);
        callback.onImageViewPager(mAdapter,mTestData);
    }

}
