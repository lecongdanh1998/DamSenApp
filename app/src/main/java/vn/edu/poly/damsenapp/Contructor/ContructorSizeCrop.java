package vn.edu.poly.damsenapp.Contructor;

import java.io.Serializable;

public class ContructorSizeCrop implements Serializable {
    String size;

    public ContructorSizeCrop() {
    }

    public ContructorSizeCrop(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
