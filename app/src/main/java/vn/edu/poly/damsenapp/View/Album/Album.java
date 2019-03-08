package vn.edu.poly.damsenapp.View.Album;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
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

import vn.edu.poly.damsenapp.Adapter.CustomGridViewAlbumActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapterAlbum;
import vn.edu.poly.damsenapp.Component.BaseFragment.BaseFragment;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;
import vn.edu.poly.damsenapp.Presenter.PresenterAlbum.PresenterAlbum;
import vn.edu.poly.damsenapp.Presenter.PresenterAlbum.PresenterReponsetoViewAlbum;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Album.CropImage.CropImage;

public class Album extends BaseFragment implements PresenterReponsetoViewAlbum, View.OnClickListener, AdapterView.OnItemClickListener {
    private View view;
    GridView gridView;
    ViewPager viewPager;
    RelativeLayout relativeLayoutShowHide, relativeLayoutToobar;
    ImageView btn_exit, img_btn_account;
    PresenterAlbum presenterAlbum;
    int setPlay;
    private ArrayList<String> permissionToRequest;
    private static final int ALL_PERMISSION_RESULT = 912;
    int size, postionbitmap;
    private ArrayList<String> permissions;
    private DrawerLayout drawer_layout;

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fragment_album, parent, false);
    }

    @Override
    public void initView() {
        drawer_layout = getActivity().findViewById(R.id.drawer_layout);
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
        permissionToRequest = new ArrayList<>();
        permissions = new ArrayList<>();
        getPermission();
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
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
        }
    }


    @Override
    public void onData2(CustomGridViewAlbumActivity adapter, ArrayList<ContructorLibraryAlbum> arrayList) {
        gridView.setAdapter(adapter);
        this.size = arrayList.size();
        adapter.notifyDataSetChanged();
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
    public void onImageViewPager(CustomViewPagerAdapterAlbum adapter, List<HotDealObjectAlbum> mTestData) {
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        postionbitmap = dataBitMap.getInt("Position", 0);
        viewPager.setCurrentItem(postionbitmap);
    }

    @Override
    public void onFileUri(Uri uri) {
        setPlay = 1;
        CropImage.activity(uri)
                .start(getActivity(), this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == size - 1) {
            setPlay = 2;
            CropImage.activity()
                    .start(getContext(), this);

        } else if (position < size - 1) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            gridView.getItemAtPosition(position);
//            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
//            relativeLayoutShowHide.startAnimation(animation);
//            Animation animationToobar = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
//            relativeLayoutToobar.startAnimation(animationToobar);
            relativeLayoutToobar.setVisibility(View.GONE);
            relativeLayoutShowHide.setVisibility(View.VISIBLE);
            img_btn_account.setEnabled(false);
//            img_btn_account.startAnimation(animationToobar);
            img_btn_account.setVisibility(View.GONE);
            viewPager.setCurrentItem(position, false);
        }
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (Build.VERSION.SDK_INT > 24) {
//            if (requestCode == CAMERA_REQUEST_CODE) {
//                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
////                presenterAlbum.initAddData(imageBitmap);
//
//            }
//        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == getActivity().RESULT_OK) {

                Uri resultUri = result.getUri();
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), resultUri);
                if (setPlay == 1) {
                    presenterAlbum.initAddDataEditCrop(resultUri);
                } else if (setPlay == 2) {
                    presenterAlbum.initAddData(resultUri);
                }
                Log.d("LinkUri",""+resultUri.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(getContext(), "Exception " + error, Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void getPermission() {
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionToRequest = permissionToRequest(permissions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionToRequest.size() > 0) {
                requestPermissions(permissionToRequest.toArray(new
                        String[permissionToRequest.size()]), ALL_PERMISSION_RESULT);
            }
        }
    }

    private ArrayList<String> permissionToRequest(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<>();
        for (String perm : permissions) {
            if (!hashPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hashPermission(String perm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getActivity().checkSelfPermission(perm) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }


}
