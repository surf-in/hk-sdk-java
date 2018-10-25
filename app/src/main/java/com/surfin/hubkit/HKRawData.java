package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents the data captured by a Sensor
 */
public class HKRawData
    extends HKBase
{

    /**
     * Unique identifier of the device this data came from
     */
    @SerializedName("device")
    public String   device;

    /**
     * Unique identifier of the session this data is associated with
     */
    @SerializedName("session")
    public String   session;

    /**
     * Get all raw data
     */
    public static void     get(@NonNull String identifier,@NonNull Consumer<HKRawData> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.getRawData(identifier, onSuccess, onFailure);
    }

    /**
     * Create a new session
     */
    public static void  create(@NonNull HKSession session,
                               @NonNull HKDevice device,
                               @NonNull HKFile file,
                               @NonNull Consumer<Double> onProgress,
                               @NonNull Consumer<HKRawData> onSuccess,
                               @NonNull Consumer<Error> onFailure) {
        Map<String, String> params = new HashMap<>();
        params.put("session", session.identifier);
        params.put("device", device.identifier);

        HKManager.defaultInstance.uploadRawDatas(params, file, onProgress, onSuccess, onFailure);
    }
}
