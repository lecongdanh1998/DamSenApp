package vn.edu.poly.damsenapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.damsenapp.Component.ItemClick;
import vn.edu.poly.damsenapp.Contructor.ContructorSizeCrop;
import vn.edu.poly.damsenapp.R;
import vn.edu.poly.damsenapp.View.Album.CropImage.CropImageView;

public class AdapterSizeCrop extends RecyclerView.Adapter<RecyclerViewHolderSizeCrop> {
    ArrayList<ContructorSizeCrop> arrayList;
    Context context;
    LayoutInflater inflater;
    private CropImageView mCropImageView;

    public AdapterSizeCrop(ArrayList<ContructorSizeCrop> arrayList, Context context, CropImageView mCropImageView) {
        this.arrayList = arrayList;
        this.context = context;
        this.mCropImageView = mCropImageView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewHolderSizeCrop onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_size_crop, viewGroup, false);
        RecyclerViewHolderSizeCrop vh = new RecyclerViewHolderSizeCrop(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderSizeCrop viewHolder, int i) {
        final ContructorSizeCrop sizeCrop = arrayList.get(i);
        viewHolder.size.setText(sizeCrop.getSize());
        viewHolder.setItemClickListener(new ItemClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (i == mSelectedPosition) {
                } else {
                    if (isLongClick) {
                    } else {
                        switch (position) {
                            case 0:
                                setSelectedItem(position);
                                mCropImageView.setCropShape(CropImageView.CropShape.RECTANGLE);
                                mCropImageView.setFixedAspectRatio(false);
                                CropImageView();
                                break;
                            case 1:
                                setSelectedItem(position);
                                mCropImageView.setAspectRatio(1, 1);
                                mCropImageView.setFixedAspectRatio(true);
                                CropImageView();
                                break;
                            case 2:
                                setSelectedItem(position);
                                mCropImageView.setAspectRatio(16, 9);
                                mCropImageView.setFixedAspectRatio(true);
                                CropImageView();
                                break;
                            case 3:
                                setSelectedItem(position);
                                mCropImageView.setAspectRatio(2, 4);
                                mCropImageView.setFixedAspectRatio(true);
                                CropImageView();
                                break;
                            case 4:
                                setSelectedItem(position);
                                mCropImageView.setAspectRatio(4, 6);
                                mCropImageView.setFixedAspectRatio(true);
                                CropImageView();
                                break;
                        }
                    }
                }


            }
        });
        if (i == mSelectedPosition) {
            viewHolder.size.setBackground(context.getResources().getDrawable(R.drawable.background_custom_white_select));
            viewHolder.size.setTextColor(Color.parseColor("#000000"));
        } else {
            viewHolder.size.setBackground(context.getResources().getDrawable(R.drawable.background_custom_white));
            viewHolder.size.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    private int mSelectedPosition = 0;

    public void setSelectedItem(int itemPosition) {
        mSelectedPosition = itemPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void CropImageView() {
        mCropImageView.setScaleType(CropImageView.ScaleType.FIT_CENTER);
        mCropImageView.setAutoZoomEnabled(true);
        mCropImageView.setShowProgressBar(true);
        mCropImageView.setCropRect(new Rect(0, 0, 800, 500));
    }


}

class RecyclerViewHolderSizeCrop extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener // Implement 2 sự kiện onClick và onLongClick
{
    TextView size;
    private ItemClick itemClickListener; // Khai báo interface

    public RecyclerViewHolderSizeCrop(View itemView) {
        super(itemView);
        size = (TextView) itemView.findViewById(R.id.txt_size_crop);
        itemView.setOnClickListener(this); // Mấu chốt ở đây , set sự kiên onClick cho View
        itemView.setOnLongClickListener(this); // Mấu chốt ở đây , set sự kiên onLongClick cho View
    }

    //Tạo setter cho biến itemClickListenenr
    public void setItemClickListener(ItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false); // Gọi interface , false là vì đây là onClick
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true); // Gọi interface , true là vì đây là onLongClick
        return true;
    }
}
