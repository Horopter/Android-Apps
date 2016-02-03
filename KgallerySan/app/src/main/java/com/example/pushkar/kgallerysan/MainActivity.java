package com.example.pushkar.kgallerysan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        setContentView(gridView);
        gridView.setAdapter(new GridViewAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

            }
        });
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getFile(root);

        for(File i :  fileList) {
            if (i.isDirectory()) {
                ImageView imv = new ImageView(this);

            }
        }

    }

    public void getFile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File i : listFile) {

                if (i.isDirectory()) {
                    fileList.add(i);
                    getFile(i);
                }
                else {
                    if (i.getName().endsWith(".png")
                            || i.getName().endsWith(".jpg")
                            || i.getName().endsWith(".jpeg")
                            || i.getName().endsWith(".gif"))

                    {
                        fileList.add(i);
                    }
                }

            }
        }
        return;
    }

}