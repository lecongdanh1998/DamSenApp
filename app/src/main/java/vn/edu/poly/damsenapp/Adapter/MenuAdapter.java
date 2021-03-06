package vn.edu.poly.damsenapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.damsenapp.Contructor.MenuModel;
import vn.edu.poly.damsenapp.R;

public class MenuAdapter extends BaseAdapter {

    Context context;
    List<MenuModel> modelList;
    LayoutInflater layoutInflater;
    String notificationsCount;
    String ALBUM = "ALBUM";
    private View.OnClickListener clickNe;
    public MenuAdapter(Context context, List<MenuModel> modelList, String notificationsCount, View.OnClickListener clickNe) {
        this.context = context;
        this.modelList = modelList;
        this.notificationsCount = notificationsCount;
        this.clickNe = clickNe;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_title_menu, txt_badge_menu,txt_album_sieurangro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        MenuModel menuModel = modelList.get(position);
        Log.d("MENUADAPTER", menuModel.getTitle());
        if (convertView == null){
            viewHolder = new ViewHolder();
            if (!menuModel.getTitle().equalsIgnoreCase(ALBUM)){
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.row_menu, null);
            } else {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.item_album_menu, null);
                viewHolder.txt_album_sieurangro = convertView.findViewById(R.id.txt_title1_menu);
            }
            viewHolder.txt_title_menu = convertView.findViewById(R.id.txt_title_menu);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (menuModel.getTitle().equalsIgnoreCase(ALBUM)){
            viewHolder.txt_album_sieurangro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setTag(menuModel);
                    clickNe.onClick(view);
                }
            });
//            if (!notificationsCount.equalsIgnoreCase("0")){
//                viewHolder.txt_badge_menu.setText(notificationsCount);
//            } else {
//                viewHolder.txt_badge_menu.setVisibility(View.GONE);
//            }
        }

        viewHolder.txt_title_menu.setText(menuModel.getTitle());
        return convertView;
    }
}
