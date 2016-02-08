package com.example.horopter.gallery;

/**
 * Created by Horopter on 2/4/2016.
 */
public class folder_icon {
    private Integer imageId;
    private String title;

    public folder_icon(Integer imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }
    public Integer getImageId() {
        return imageId;
    }
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n";
    }
}
