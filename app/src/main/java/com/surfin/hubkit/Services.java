package com.surfin.hubkit;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public class Services {

    public interface get {
        @GET
        <T> Call<T> load(
                @Url String url,
                @FieldMap Map<String, String> params
        );
    }

    public interface post {
        @POST
        <T> Call<T> load(
                @Url String url,
                @FieldMap Map<String, String> params
        );
    }

    public interface patch {
        @PATCH
        <T> Call<T> load(
                @Url String url,
                @FieldMap Map<String, String> params
        );
    }

    public interface upload {
        @Multipart
        @POST
        <T> Call<T> load(
                @Url String url,
                @Part MultipartBody.Part filePart,
                @FieldMap Map<String, String> params
        );
    }
}
