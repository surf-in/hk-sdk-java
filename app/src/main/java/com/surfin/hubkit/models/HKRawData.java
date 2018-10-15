package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

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
    public static void  all(@NonNull Consumer<List<HKRawData>> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("raw_datas", null, onSuccess, onFailure);
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

        HKManager.defaultInstance.upload("raw_datas", params, file, onProgress, onSuccess, onFailure);
    }
}
