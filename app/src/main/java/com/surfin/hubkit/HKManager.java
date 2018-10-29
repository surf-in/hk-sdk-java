package com.surfin.hubkit;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HKManager {

    /**
     * Default HKManager instance, used by top-level HubKit request methods, and suitable for use directly for any ad hoc requests
     */
    public static final HKManager defaultInstance = new HKManager();

    private HKConfig    config = new HKConfig(HKConfig.ApiEnvironment.DEV, HKConfig.ApiVersion.V1);
    private HKToken     tokens = null;
    private HKProject   project = null;

    public HKManager() {
        buildRetrofitInstance();
    }

    /**
     * base URL defined by the current HKConfig
     *
     * @return base URL
     */
    public String   getBaseUrl() {
        if (config == null) return "";
        return String.format("%s/api/%s/", config.getEnvironment().baseUrl, config.getVersion().version);
    }

    /**
     * Update manager's configuration
     *
     * @param config new configuration
     */
    public void     setConfig(@NonNull HKConfig config) {
        this.config = config;
        buildRetrofitInstance();
    }

    /**
     * Update the authentication token used in API calls made to HubKit
     *
     * @param token new token
     */
    public void     setToken(HKToken token) {
        this.tokens = token;
        buildRetrofitInstance();
    }

    /**
     * Fetch project from ID and set it as currently active project
     *
     * @param identifier project ID
     */
    public void     setProject(String identifier, @NonNull Consumer<HKProject> onSuccess, @NonNull Consumer<Error> onFailure) {
        getProject(identifier, new Consumer<HKProject>() {
            @Override
            public void accept(HKProject hkProject) {
                project = hkProject;
                onSuccess.accept(hkProject);
            }
        }, onFailure);
    }

    /**
     * Current active project
     *
     * @return HKProject object
     */
    HKProject       getProject() {
        return project;
    }

    /*

    Retrofit stuff

     */

    void auth(Map<String, String> params, Consumer<HKToken> onSuccess, Consumer<Error> onError) {
        Call<HKToken> call = buildService(Services.auth.class).load(config.getVersion().version, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getMe(Consumer<HKAccount> onSuccess, Consumer<Error> onError) {
        Call<HKAccount> call = buildService(Services.getMe.class).load(config.getVersion().version);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getActivity(String identifier, Consumer<HKActivity> onSuccess, Consumer<Error> onError) {
        Call<HKActivity> call = buildService(Services.getActivity.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getActivityList(Consumer<List<HKActivity>> onSuccess, Consumer<Error> onError) {
        Call<List<HKActivity>> call = buildService(Services.getActivityList.class).load(config.getVersion().version);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getDevice(String identifier, Consumer<HKDevice> onSuccess, Consumer<Error> onError) {
        Call<HKDevice> call = buildService(Services.getDevice.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void createDevice(Map<String, String> params, Consumer<HKDevice> onSuccess, Consumer<Error> onError) {
        Call<HKDevice> call = buildService(Services.createDevice.class).load(config.getVersion().version, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void updateDevice(String identifier, Map<String, String> params, Consumer<HKDevice> onSuccess, Consumer<Error> onError) {
        Call<HKDevice> call = buildService(Services.updateDevice.class).load(config.getVersion().version, identifier, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void activateDevice(String identifier, Consumer<HKDevice> onSuccess, Consumer<Error> onError) {
        Call<HKDevice> call = buildService(Services.activateDevice.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getProject(String identifier, Consumer<HKProject> onSuccess, Consumer<Error> onError) {
        Call<HKProject> call = buildService(Services.getProject.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getRawData(String identifier, Consumer<HKRawData> onSuccess, Consumer<Error> onError) {
        Call<HKRawData> call = buildService(Services.getRawData.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void uploadRawDatas(Map<String, String> params, HKFile file, Consumer<Double> onProgress, Consumer<HKRawData> onSuccess, Consumer<Error> onError) {
        ProgressRequestBody.UploadCallbacks callback = onProgress::accept;

        ProgressRequestBody fileBody = new ProgressRequestBody(file, "application/octet-stream", callback);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        RequestBody session = RequestBody.create(MediaType.parse("text/plain"), params.get("session"));
        RequestBody device = RequestBody.create(MediaType.parse("text/plain"), params.get("device"));

//        Call<HKRawData> call = buildService(Services.uploadRawData.class).load(config.getVersion().version, filePart, params);
        Call<HKRawData> call = buildService(Services.uploadRawData.class).load(config.getVersion().version, filePart, session, device);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void getSession(String identifier, Consumer<HKSession> onSuccess, Consumer<Error> onError) {
        Call<HKSession> call = buildService(Services.getSession.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void postSession(Map<String, String> params, Consumer<HKSession> onSuccess, Consumer<Error> onError) {
        Call<HKSession> call = buildService(Services.postSession.class).load(config.getVersion().version, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    void updateSession(String identifier, Consumer<HKSession> onSuccess, Consumer<Error> onError) {
        Call<HKSession> call = buildService(Services.patchSession.class).load(config.getVersion().version, identifier);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    private Retrofit    retrofit;

    private void buildRetrofitInstance() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(buildHeadersInterceptor(HKConfig.getHeaders(tokens)))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(getGSONConverter())
                .client(client)
                .build();
    }

    /**
     * Create a service from Services interfaces with default URL
     *
     * @param serviceClass interface class defining the  service
     * @return created Service
     */
    private <S> S buildService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    /**
     * Create a Retrofit callback from provided listener
     *
     * @param onSuccess consumer executed if the call is successful
     * @param onError consumer executed if the call fails
     * @return created callback
     */
    private <S> Callback<S> createAPICallback(Consumer<S> onSuccess, Consumer<Error> onError) {

        return new Callback<S>() {
            @Override
            public void onResponse(@NonNull Call<S> call, @NonNull Response<S> response) {
                if (response.isSuccessful()) {
                    onSuccess.accept(response.body());
                } else {
                    onError.accept(new Error(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<S> call, @NonNull Throwable t) {
                onError.accept(new Error(t.getMessage()));
            }
        };
    }

    private static Interceptor  buildHeadersInterceptor(@NonNull Map<String, String> headers) {
        return chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();

            for (String key :
                    headers.keySet()) {
                builder = builder.header(key, headers.get(key));
            }
            return chain.proceed(builder.method(original.method(), original.body()).build());
        };
    }

    /**
     * @return GSON converter converting dates into ISO8601
     */
    private static GsonConverterFactory getGSONConverter() {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") // Allow timestamps to be converted to Date object directly when necessary
                .create();

        return GsonConverterFactory.create(gson);
    }
}
