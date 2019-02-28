package vn.edu.poly.damsenapp.retrofit.config;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.edu.poly.damsenapp.retrofit.response.UserResult;

public interface RetrofitAPIs {

    @FormUrlEncoded
    @POST("login")
    Observable<UserResult> loginEmail(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("loginfacebook")
    Observable<UserResult> loginFacebook(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("loginfacebook")
    Observable<UserResult> loginGoogle(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("register")
    Observable<UserResult> registerAccount(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );
}
