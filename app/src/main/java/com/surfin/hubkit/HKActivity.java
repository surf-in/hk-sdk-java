package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

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
    public static void  get(@NonNull String identifier, @NonNull Consumer<HKActivity> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.getActivity(identifier, onSuccess, onFailure);
    }

    /**
     * Get all activities from HubKit
     */
    public static void  all(@NonNull Consumer<List<HKActivity>> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.getActivityList(onSuccess, onFailure);
    }
}
