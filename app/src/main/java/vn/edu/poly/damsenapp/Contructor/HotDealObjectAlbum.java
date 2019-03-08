package vn.edu.poly.damsenapp.Contructor;

import android.net.Uri;

import java.io.Serializable;

public class HotDealObjectAlbum implements Serializable {

    Uri images;
    long id;

    public HotDealObjectAlbum() {

    }

    public HotDealObjectAlbum(long id, Uri images) {
        this.id = id;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getImage() {
        return images;
    }

    public void setImage(Uri images) {
        this.images = images;
    }
}
