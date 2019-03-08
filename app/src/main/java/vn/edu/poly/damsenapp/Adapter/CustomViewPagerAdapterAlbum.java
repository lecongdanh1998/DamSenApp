package vn.edu.poly.damsenapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.util.List;

import vn.edu.poly.damsenapp.Component.BaseFragment.BaseFragment;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Album.CropImage.TouchImageView;


public class CustomViewPagerAdapterAlbum extends PagerAdapter {
    private Context context;
    private List<HotDealObjectAlbum> hotDealList;
    private LayoutInflater layoutInflater;
    View.OnClickListener onClickCrop;

    public CustomViewPagerAdapterAlbum(Context context, List<HotDealObjectAlbum> hotDealLis
            , View.OnClickListener onClickCrop) {
        this.context = context;
        this.hotDealList = hotDealLis;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.onClickCrop = onClickCrop;
    }


    @Override
    public int getCount() {
        return hotDealList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View) o);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.hot_deal_layout, container, false);
        HotDealObjectAlbum mHotDealObject = hotDealList.get(position);
        TouchImageView favoriteIcon = view.findViewById(R.id.hot_deal_food_image);
        ImageView btn_ratio = (ImageView) view.findViewById(R.id.btn_ratio);
        favoriteIcon.setImageURI(mHotDealObject.getImage());
        btn_ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.valueOf(mHotDealObject.getImage());
                BaseFragment.editorBitMap = BaseFragment.dataBitMap.edit();
                BaseFragment.editorBitMap.putString("Name", uri);
                BaseFragment.editorBitMap.putInt("Position", position);
                BaseFragment.editorBitMap.putLong("PositionUpdate", mHotDealObject.getId());
                BaseFragment.editorBitMap.commit();
                v.setTag(mHotDealObject);
                onClickCrop.onClick(v);
            }
        });
        container.addView(view);
        return view;
    }

}
