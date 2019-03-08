package vn.edu.poly.damsenapp.Model.ModelAlbum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewAlbumActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapterAlbum;
import vn.edu.poly.damsenapp.Component.BaseFragment.BaseFragment;
import vn.edu.poly.damsenapp.Component.DB.Dao.MemberDB;
import vn.edu.poly.damsenapp.Component.DB.Database.DBHelper;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;

public class ModelAlbum {
    ModelReponsetoPresenterAlbum callback1;
    Context context;
    Activity activity;
    ArrayList<ContructorLibraryAlbum> arrayList;
    CustomGridViewAlbumActivity adapter;
    CustomViewPagerAdapterAlbum mAdapter;
    List<HotDealObjectAlbum> mTestData;
    Uri resultUri = null;
    int potisition;
    long PositionUpdate;
    String name = "";
    String nameLogo = "";
    private static final String TAG = "Camera2Fragment";
    View.OnClickListener onClickCrop;
    DBHelper dbHelper;
    MemberDB memberDB;

    public ModelAlbum(ModelReponsetoPresenterAlbum callback, Context context) {
        this.callback1 = callback;
        this.context = context;
        this.activity = (Activity) context;
        arrayList = new ArrayList<>();
        mTestData = new ArrayList<HotDealObjectAlbum>();
    }

    public void initData() {
        memberDB = new MemberDB(context);
        memberDB.open();
        dbHelper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
        String query = "SELECT * FROM " + DBHelper._TABLE_NAME;
        Cursor cursor = dbHelper.getData(query);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
            } else {
                memberDB.insert("null");
            }
        }
        initView();
    }

    private void initView() {
        arrayList.clear();
        String query = "SELECT * FROM " + DBHelper._TABLE_NAME;
        Cursor cursor = dbHelper.getData(query);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            arrayList.add(new ContructorLibraryAlbum(id, name));
        }
        Collections.sort(arrayList, new Comparator<ContructorLibraryAlbum>() {
            @Override
            public int compare(ContructorLibraryAlbum lhs, ContructorLibraryAlbum rhs) {
                return Double.compare(rhs.getId(), lhs.getId());
            }
        });
        adapter = new CustomGridViewAlbumActivity(context, arrayList);
        callback1.onData(adapter, arrayList);
    }


    public void initImageViewPager() {
        onClickCrop = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potisition = BaseFragment.dataBitMap.getInt("Position", 0);
                PositionUpdate = BaseFragment.dataBitMap.getLong("PositionUpdate", 0);
                name = BaseFragment.dataBitMap.getString("Name", "");
                Uri uri = Uri.parse(name);
                callback1.onFileUri(uri);
            }
        };
        mTestData.clear();
        for (int i = 0; i < arrayList.size() - 1; i++) {
            mTestData.add(new HotDealObjectAlbum(arrayList.get(i).getId(),Uri.parse(arrayList.get(i).getImage())));
        }
        mAdapter = new CustomViewPagerAdapterAlbum(context, mTestData, onClickCrop);
        callback1.onImageViewPager(mAdapter, mTestData);
    }


    public void initAddDataPosition(Uri resultUri1) {
        memberDB.update(PositionUpdate,String.valueOf(resultUri1));
        initView();
    }


    public void initAddData(Uri resultUri1) {
        memberDB.insert(String.valueOf(resultUri1));
        initView();
    }


}
