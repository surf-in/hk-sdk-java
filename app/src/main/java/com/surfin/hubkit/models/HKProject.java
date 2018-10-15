package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

import java.util.List;
import java.util.function.Consumer;

/**
 * Container grouping a set of Session representing measurement campaigns
 */
public class HKProject
    extends HKBase
{

    /**
     * Project name
     */
    @SerializedName("name")
    public String   name;

    /**
     * Project attached devices
     */
    @SerializedName("devices")
    public List<HKDevice>   devices;

    /**
     * Get project for the given identifier
     */
    public static void  get(@NonNull String identifier, @NonNull Consumer<HKProject> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("projects/" + identifier, null, onSuccess, onFailure);
    }
}
