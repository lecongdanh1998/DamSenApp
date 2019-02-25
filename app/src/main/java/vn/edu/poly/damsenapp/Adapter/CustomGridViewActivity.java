package vn.edu.poly.damsenapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.R;

public class CustomGridViewActivity extends BaseAdapter {

    private Context mContext;
    ArrayList<ContructorLibrary> arrayList;
    LayoutInflater inflater;

    public CustomGridViewActivity(Context mContext, ArrayList<ContructorLibrary> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.gridview_layout, null);
            viewHolder.android_gridview_image = (ImageView) convertView.findViewById(R.id.android_gridview_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContructorLibrary library = arrayList.get(i);
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.android_gridview_image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {

                int width = finalViewHolder.android_gridview_image.getWidth();
                int height = finalViewHolder.android_gridview_image.getHeight();
                Log.d("PositionImage",""+i+": "+width+"(Width) \n"+height+"(Height)");
//                Picasso.with(getActivity())
//                        .load(imageUrl)
//                        .placeholder(R.drawable.placeholder)
//                        .error(R.drawable.error)
//                        .resize(screenWidth, imageHeight)
//                        .fit
//                        .centerInside()
//                        .into(imageView);
                finalViewHolder.android_gridview_image.getViewTreeObserver().removeGlobalOnLayoutListener(this);



            }
        });
        Picasso.get()
                .load(library.getImage())
                .into(viewHolder.android_gridview_image);
        return convertView;
    }

    class ViewHolder {
        ImageView android_gridview_image;
    }

}
