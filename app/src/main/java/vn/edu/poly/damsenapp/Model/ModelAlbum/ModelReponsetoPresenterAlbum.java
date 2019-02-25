package vn.edu.poly.damsenapp.Model.ModelAlbum;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.damsenapp.Adapter.CustomGridViewActivity;
import vn.edu.poly.damsenapp.Adapter.CustomGridViewAlbumActivity;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.damsenapp.Adapter.CustomViewPagerAdapterAlbum;
import vn.edu.poly.damsenapp.Contructor.ContructorLibrary;
import vn.edu.poly.damsenapp.Contructor.ContructorLibraryAlbum;
import vn.edu.poly.damsenapp.Contructor.HotDealObject;
import vn.edu.poly.damsenapp.Contructor.HotDealObjectAlbum;

public interface ModelReponsetoPresenterAlbum {
    void onData(CustomGridViewAlbumActivity adpter,ArrayList<ContructorLibraryAlbum> arrayList);
    void onData2(CustomGridViewAlbumActivity adpter,ArrayList<ContructorLibraryAlbum> arrayList);
    void onImageViewPager(CustomViewPagerAdapterAlbum adpter, List<HotDealObjectAlbum> mTestData);
}
