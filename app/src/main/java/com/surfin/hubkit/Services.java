package com.surfin.hubkit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

class Services {

    public interface auth {
        @FormUrlEncoded
        @POST("/api/{v}/login_check")
        Call<HKToken> load(
                @Path("v") String version,
                @FieldMap Map<String, String> params
        );
    }

    public interface getMe {
        @GET("/api/{v}/me")
        Call<HKAccount> load(
                @Path("v") String version
        );
    }

    public interface getActivity {
        @GET("/api/{v}/activities/{id}")
        Call<HKActivity> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface getActivityList {
        @GET("/api/{v}/activities")
        Call<List<HKActivity>> load(
                @Path("v") String version
        );
    }

    public interface getDevice {
        @GET("/api/{v}/devices/{id}")
        Call<HKDevice> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface createDevice {
        @FormUrlEncoded
        @POST("/api/{v}/devices")
        Call<HKDevice> load(
                @Path("v") String version,
                @FieldMap Map<String, String> params
        );
    }

    public interface updateDevice {
        @FormUrlEncoded
        @PATCH("/api/{v}/devices/{id}")
        Call<HKDevice> load(
                @Path("v") String version,
                @Path("id") String id,
                @FieldMap Map<String, String> params
        );
    }

    public interface activateDevice {
        @PATCH("/api/{v}/devices/{id}/activate")
        Call<HKDevice> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface getProject {
        @GET("/api/{v}/projects/{id}")
        Call<HKProject> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface getRawData {
        @GET("/api/{v}/raw_datas/{id}")
        Call<HKRawData> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface uploadRawData {
        @Multipart
        @FormUrlEncoded
        @POST("/api/{v}/raw_datas")
        Call<HKRawData> load(
                @Path("v") String version,
                @Part MultipartBody.Part filePart,
                @FieldMap Map<String, String> params
        );
    }

    public interface getSession {
        @GET("/api/{v}/sessions/{id}")
        Call<HKSession> load(
                @Path("v") String version,
                @Path("id") String id
        );
    }

    public interface postSession {
        @FormUrlEncoded
        @POST("/api/{v}/sessions")
        Call<HKSession> load(
                @Path("v") String version,
                @FieldMap Map<String, String> params
        );
    }

    public interface patchSession {
        @PATCH("/api/{v}/sessions/{id}/ready")
        Call<HKSession> load(
                @Path("v") String version,
                @Url String url
        );
    }
}
