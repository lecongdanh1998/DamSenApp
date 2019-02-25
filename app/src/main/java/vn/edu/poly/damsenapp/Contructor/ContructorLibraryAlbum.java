package vn.edu.poly.damsenapp.Contructor;

import android.graphics.Bitmap;

public class ContructorLibraryAlbum {
    Bitmap image;
    String imageLink;
    public ContructorLibraryAlbum() {
    }

    public ContructorLibraryAlbum(Bitmap image,String imageLink) {
        this.image = image;
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
