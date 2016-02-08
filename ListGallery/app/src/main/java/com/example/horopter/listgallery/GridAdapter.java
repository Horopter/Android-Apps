package com.example.horopter.listgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Horopter on 2/7/2016.
 */
public class GridAdapter extends BaseAdapter {
    ArrayList<String> result;
    Context context;
    ArrayList<String> imageId;
    private static LayoutInflater inflater = null;
    public GridAdapter(MainActivity mainActivity, ArrayList<String> imagesList, ArrayList<String> images) {
        result = imagesList;
        context = mainActivity;
        imageId = images;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder h = new Holder();
        View v;
        v = inflater.inflate(R.layout.program_list,null);
        h.tv = (TextView) v.findViewById(R.id.tv1);
        h.img = (ImageView) v.findViewById(R.id.iv1);
        h.tv.setText(result.get(position));
        //File fileObj = new  File(“/sdcard/Images/test_image.jpg”);
        File fileObj = new  File(imageId.get(position));
        if(fileObj.exists())
        {
            Bitmap bitMapObj= BitmapFactory.decodeFile(fileObj.getAbsolutePath());
            //ImageView imgView= (ImageView) findViewById(R.id.imageviewTest);
            //imgView.setImageBitmap(bitMapObj);
            h.img.setImageBitmap(bitMapObj);
        }
        //else
            //Toast.makeText(this.context,"Couldn't find required file",Toast.LENGTH_SHORT).show();
        //h.img.setImageResource(imageId.get(position));
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,result.get(position),Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
