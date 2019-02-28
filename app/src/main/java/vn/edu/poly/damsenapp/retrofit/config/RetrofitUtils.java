package vn.edu.poly.damsenapp.retrofit.config;

public class RetrofitUtils {
    public static RetrofitAPIs user(){
        return RetrofitUserInstance.getInstance().create(RetrofitAPIs.class);
    }
}
