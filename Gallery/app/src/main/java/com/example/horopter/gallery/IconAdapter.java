package com.example.horopter.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Horopter on 2/4/2016.
 */
public class IconAdapter extends ArrayAdapter<folder_icon>{
    Context context;

    public IconAdapter(Context context, int resourceId,
                                 List<folder_icon> items) {
        super(context, resourceId, items);
        this.context = context;
    }
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View v = convertView;
        folder_icon f = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            v = mInflater.inflate(R.layout.folder_icon, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) v.findViewById(R.id.text);
            holder.imageView = (ImageView) v.findViewById(R.id.icon);
            v.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(f.getTitle());
        holder.imageView.setImageResource(f.getImageId());

        return v;
    }

}
