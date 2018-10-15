package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

import java.util.List;
import java.util.function.Consumer;

/**
 * Activity a session can be associated with
 */
public class HKActivity
        extends HKBase
{

    /**
     * Activity name
     */
    @SerializedName("name")
    public String   name;

    /**
     * Get the activity for the given identifier
     */
    public static void  get(@NonNull String indentifier, @NonNull Consumer<HKActivity> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("activities/" + indentifier, null, onSuccess, onFailure); //TODO params
    }

    /**
     * Get all activities from HubKit
     */
    public static void  all(@NonNull Consumer<List<HKActivity>> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("activities", null, onSuccess, onFailure); //TODO params
    }
}
