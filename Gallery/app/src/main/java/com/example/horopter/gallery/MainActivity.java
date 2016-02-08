package com.example.horopter.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<folder_icon> list;
    GridView grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        grid = (GridView) findViewById(R.id.gridView);
        IconAdapter adapter = new IconAdapter(this,
                R.layout.folder_icon, list);
        grid.setAdapter(adapter);
        for(int i=0;i<10;i++)
        {
            folder_icon f = new folder_icon(R.drawable.ts1,"Taylor Swift");
            list.add(f);
        }

        ArrayAdapter<folder_icon> adp = new ArrayAdapter<> (this,
                R.layout.folder_icon,list);
        grid.setAdapter(adp);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), list.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + list.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}

