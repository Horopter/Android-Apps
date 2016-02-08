package com.example.horopter.listgallery;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Horopter on 2/7/2016.
 */
public class ImageFetch {
    public static ArrayList<String> getFilePaths(MainActivity act)
    {
        Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA};
        Cursor c = null;
        SortedSet<String> dirList = new TreeSet<>();
        ArrayList<String> resultIAV = new ArrayList<>();
        String[] directories = null;
        if(u!=null)
        {
            c = act.getContentResolver().query(u, projection, null, null, null);
        }
        if((c!=null)&&(c.moveToFirst()))
        {
            do
            {
                String tempDir = c.getString(0);
                tempDir = tempDir.substring(0,tempDir.lastIndexOf("/"));
                try
                {
                    dirList.add(tempDir);
                }
                catch(Exception e)
                {

                }
            }while(c.moveToNext());
            directories = new String[dirList.size()];
            dirList.toArray(directories);
        }
        for(int i=0;i< dirList.size();i++)
        {
            File imageDir = new File(directories[i]);
            File[] imageList = imageDir.listFiles();
            if(imageList==null)
                continue;
            for(File imagePath : imageList)
            {
                try
                {
                    if(imagePath.isDirectory())
                    {
                        imageList = imagePath.listFiles();
                    }
                    if(imagePath.getName().endsWith(".jpg"))
                    {
                        String path = imagePath.getAbsolutePath();
                        resultIAV.add(path);
                    }
                }
                catch(Exception e) {}
            }
        }
        return resultIAV;
    }
}
