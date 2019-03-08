package vn.edu.poly.damsenapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.R;

public class CustomGridViewAlbumActivity extends BaseAdapter {

    private Context mContext;
    ArrayList<ContructorLibraryAlbum> arrayList;
    LayoutInflater inflater;

    public CustomGridViewAlbumActivity(Context mContext, ArrayList<ContructorLibraryAlbum> arrayList) {
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
            viewHolder.android_gridview_image = convertView.findViewById(R.id.android_gridview_image);
            viewHolder.android_gridview_image_add = convertView.findViewById(R.id.android_gridview_image_add);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContructorLibraryAlbum library = arrayList.get(i);
        if(arrayList.size() == 1){
            viewHolder.android_gridview_image_add.setVisibility(View.VISIBLE);
            viewHolder.android_gridview_image_add.setImageResource(R.drawable.ic_add_circle_blue_48dp);
        }else if(arrayList.size() > 1) {
            if (i == arrayList.size() - 1) {
                viewHolder.android_gridview_image_add.setVisibility(View.VISIBLE);
                viewHolder.android_gridview_image_add.setImageResource(R.drawable.ic_add_circle_blue_48dp);
            } else if (i < arrayList.size() - 1) {
            viewHolder.android_gridview_image.setImageURI(Uri.parse(library.getImage()));
            }
        }


        return convertView;
    }

    class ViewHolder {
        ImageView android_gridview_image, android_gridview_image_add;
    }

}
