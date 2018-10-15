package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surfin.hubkit.callbacks.ProgressRequestBody;
import com.surfin.hubkit.models.HKFile;
import com.surfin.hubkit.models.HKToken;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.function.Consumer;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    public HKManager() {
        buildRetrofitInstance(null);
    }

    /**
     * base URL defined by the current HKConfig
     *
     * @return base URL
     */
    public String   getBaseUrl() {
        if (config == null) return "";
        return String.format("%s/api/%s", config.getEnvironment().baseUrl, config.getVersion().version);
    }

    /**
     * Update manager's configuration
     *
     * @param config new configuration
     */
    public void     setConfig(@NonNull HKConfig config) {
        this.config = config;
    }

    /**
     * Update the authentication token used in API calls made to HubKit
     *
     * @param token new token
     */
    public void     setToken(HKToken token) {
        if (token != null) buildRetrofitInstance(token);
    }

    /*

    Retrofit stuff

     */

    public <T> void upload(String action, Map<String, String> params, HKFile file, Consumer<Double> onProgress, Consumer<T> onSuccess, Consumer<Error> onError) {
        ProgressRequestBody.UploadCallbacks callback = onProgress::accept;
        ProgressRequestBody fileBody = new ProgressRequestBody(file.file, file.mimetype, callback);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.filename, fileBody);

        Call<T> call = buildService(Services.upload.class).load("/" + action, filePart, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }


    public <T> void post(String action, Map<String, String> params, Object encoding, Consumer<T> onSuccess, Consumer<Error> onError) { //TODO encoding
        Call<T> call = buildService(Services.post.class).load("/" + action, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    public <T> void get(String action, Map<String, String> params, Consumer<T> onSuccess, Consumer<Error> onError) {
        Call<T> call = buildService(Services.get.class).load("/" + action, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    public <T> void patch(String action, Map<String, String> params, Consumer<T> onSuccess, Consumer<Error> onError) {
        Call<T> call = buildService(Services.patch.class).load("/" + action, params);

        call.enqueue(createAPICallback(onSuccess, onError));
    }

    private Retrofit    retrofit;

    private void buildRetrofitInstance(HKToken token) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(buildHeadersInterceptor(HKConfig.getHeaders(token)))
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

//    private(set) var manager: SessionManager = SessionManager.default //TODO ?

}
