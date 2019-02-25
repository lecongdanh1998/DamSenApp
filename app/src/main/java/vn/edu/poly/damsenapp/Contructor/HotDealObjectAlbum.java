package vn.edu.poly.damsenapp.Contructor;

import android.graphics.Bitmap;

import java.io.Serializable;

public class HotDealObjectAlbum implements Serializable {

    Bitmap images;

    public HotDealObjectAlbum(Bitmap images) {
        this.images = images;
    }

    public HotDealObjectAlbum() {
    }

    public Bitmap getImages() {
        return images;
    }

    public void setImages(Bitmap images) {
        this.images = images;
    }
}
