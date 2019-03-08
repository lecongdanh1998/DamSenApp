package vn.edu.poly.damsenapp.Contructor;

import android.graphics.Bitmap;
import android.net.Uri;

public class ContructorLibraryAlbum {
    long id;
    String image;

    public ContructorLibraryAlbum() {

    }

    public ContructorLibraryAlbum(long id, String image) {
        this.id = id;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
