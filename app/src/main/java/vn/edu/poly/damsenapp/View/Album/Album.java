package vn.edu.poly.damsenapp.View.Album;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomGridViewAlbumActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapterAlbum;
import vn.edu.poly.damsenapp.Component.BaseFragment.BaseFragment;
import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;
import vn.edu.poly.damsenapp.Presenter.PresenterAlbum.PresenterAlbum;
import vn.edu.poly.damsenapp.Presenter.PresenterAlbum.PresenterReponsetoViewAlbum;
import vn.edu.poly.damsenapp.Presenter.PresenterHomePage.PresenterHomePage;
import vn.edu.poly.damsenapp.R;

public class Album extends BaseFragment implements PresenterReponsetoViewAlbum, View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    GridView gridView;
    ViewPager viewPager;
    RelativeLayout relativeLayoutShowHide, relativeLayoutToobar;
    ImageView btn_exit,img_btn_account;
    PresenterAlbum presenterAlbum;
    private Uri imageCaptureUri;
    private int CAMERA_REQUEST = 1;
    private int CAMERA_REQUEST_MAX = 1999;
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
    public static final String IMAGE_DIRECTORY = "ImageScalling";
    private static final int CAMERA_REQUEST_CODE = 1;
    private File file;
    private File destFile;
    private SimpleDateFormat dateFormatter;
    int size;

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fragment_album, parent, false);
    }

    @Override
    public void initView() {
        img_btn_account = getActivity().findViewById(R.id.img_btn_account);
        relativeLayoutToobar = getActivity().findViewById(R.id.relativeLayoutToobar);
        gridView = view.findViewById(R.id.grid_view_image_text);
        relativeLayoutShowHide = view.findViewById(R.id.relativeLayoutShowHide);
        viewPager = view.findViewById(R.id.pager);
        btn_exit = view.findViewById(R.id.btn_exit);
    }

    @Override
    public void initData() {
        presenterAlbum = new PresenterAlbum(this, getContext());
        presenterAlbum.initData();
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
                relativeLayoutShowHide.setVisibility(View.GONE);
                relativeLayoutToobar.setVisibility(View.VISIBLE);
                img_btn_account.setEnabled(true);
                img_btn_account.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onData2(CustomGridViewAlbumActivity adpter, ArrayList<ContructorLibraryAlbum> arrayList) {
        gridView.setAdapter(adpter);
        this.size = arrayList.size();
        adpter.notifyDataSetChanged();
        presenterAlbum.initImageViewPager();

    }

    @Override
    public void onData(CustomGridViewAlbumActivity adpter, ArrayList<ContructorLibraryAlbum> arrayList) {
        gridView.setAdapter(adpter);
        this.size = arrayList.size();
        adpter.notifyDataSetChanged();
        presenterAlbum.initImageViewPager();
    }

    @Override
    public void onImageViewPager(CustomViewPagerAdapterAlbum adpter, List<HotDealObjectAlbum> mTestData) {
        viewPager.setAdapter(adpter);
        adpter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == size - 1) {
            IntentMediaStore();

        } else if (position < size - 1) {
            gridView.getItemAtPosition(position);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            relativeLayoutShowHide.startAnimation(animation);
            Animation animationToobar = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
            relativeLayoutToobar.startAnimation(animationToobar);
            relativeLayoutToobar.setVisibility(View.GONE);
            relativeLayoutShowHide.setVisibility(View.VISIBLE);
            img_btn_account.setEnabled(false);
            img_btn_account.startAnimation(animationToobar);
            img_btn_account.setVisibility(View.GONE);
            viewPager.setCurrentItem(position, false);
        }
    }


    public void IntentMediaStore() {
        file = new File(Environment.getExternalStorageDirectory()
                + "/" + IMAGE_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
        dateFormatter = new SimpleDateFormat(
                DATE_FORMAT, Locale.US);
        destFile = new File(file, "img_"
                + dateFormatter.format(new Date()).toString() + ".png");
        imageCaptureUri = Uri.fromFile(destFile);
        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageCaptureUri);
        startActivityForResult(intent1, CAMERA_REQUEST_CODE);
//        intent1.setType("image/*");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == getActivity().RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
//            thePic = extras.getParcelable("data");
//            image1.setImageBitmap(thePic);
        }
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap bitmap = decodeFile(destFile);
                presenterAlbum.initAddData(bitmap);
            }


        }
    }

    private Bitmap decodeFile(File f) {
        Bitmap b = null;

        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int IMAGE_MAX_SIZE = 1024;
        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        try {
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        destFile = new File(file, "img_"
//                + dateFormatter.format(new Date()).toString() + ".png");
        try {
            FileOutputStream out = new FileOutputStream(destFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }



}
