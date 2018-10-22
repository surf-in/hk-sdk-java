package com.surfin.hubkit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

class Services {

    public interface auth {
        @POST("/login_check")
        Call<HKToken> load(
                @FieldMap Map<String, String> params
        );
    }

    public interface getMe {
        @GET("/me")
        Call<HKAccount> load();
    }

    public interface getActivity {
        @GET("/activities/{id}")
        Call<HKActivity> load(
                @Path("id") String id
        );
    }

    public interface getActivityList {
        @GET("/activities")
        Call<List<HKActivity>> load();
    }

    public interface getDevice {
        @GET("/devices/{id}")
        Call<HKDevice> load(
                @Path("id") String id
        );
    }

    public interface postDevice {
        @POST("/devices")
        Call<HKDevice> load(
                @FieldMap Map<String, String> params
        );
    }

    public interface updateDevice {
        @POST("/devices/{id}")
        Call<HKDevice> load(
                @Path("id") String id,
                @FieldMap Map<String, String> params
        );
    }

    public interface activateDevice {
        @POST("/devices/{id}/activate")
        Call<HKDevice> load(
                @Path("id") String id
        );
    }

    public interface patchDevice {
        @PATCH("/devices/{id}/activate")
        Call<HKDevice> load(
                @Path("id") String id
        );
    }

    public interface getProject {
        @GET("/projects/{id}")
        Call<HKProject> load(
                @Path("id") String id
        );
    }

    public interface getRawDataList {
        @GET("/raw_datas")
        Call<List<HKRawData>> load();
    }

    public interface getSession {
        @GET("/sessions/{id}")
        Call<HKSession> load(
                @Path("id") String id
        );
    }

    public interface getSessionList {
        @GET("/sessions")
        Call<List<HKSession>> load();
    }

    public interface postSession {
        @POST("/sessions")
        Call<HKSession> load(
                @FieldMap Map<String, String> params
        );
    }

    public interface patchSession {
        @PATCH("/sessions/{id}/ready")
        Call<HKSession> load(
                @Url String url
        );
    }

    public interface uploadRawData {
        @Multipart
        @POST("/raw_datas")
        Call<HKRawData> load(
                @Part MultipartBody.Part filePart,
                @FieldMap Map<String, String> params
        );
    }
}
