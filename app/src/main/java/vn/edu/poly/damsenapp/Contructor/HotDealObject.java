package vn.edu.poly.damsenapp.Contructor;

import java.io.Serializable;

public class HotDealObject implements Serializable {

    String images;

    public HotDealObject(String images) {
        this.images = images;
    }

    public HotDealObject() {
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
