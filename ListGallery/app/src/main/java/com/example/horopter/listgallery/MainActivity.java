package com.example.horopter.listgallery;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView lv;
    Context context;
    public static ArrayList<String> image;
    public static ArrayList<String> name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        lv = (GridView) findViewById(R.id.lv1);
        image = new ArrayList<>();
        name = new ArrayList<>();
        /*for(int i=0;i<10;i++)
        {
            image.add(R.drawable.ts1); // if image were integer, we could have drawn from resource through resource id.
            name.add("Taylor Swift");
        }
        */
        image = ImageFetch.getFilePaths(this);
        //name = ImageFetch.getFilePaths(this);
        /*for ( String s : image)
        {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }
        */
        lv.setAdapter(new GridAdapter(this, name, image));
    }
}
