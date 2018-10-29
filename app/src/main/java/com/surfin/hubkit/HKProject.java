package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

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
     * The activity this session is associated with
     */
    @SerializedName("activity")
    public HKActivity   activity;

    /**
     * Project attached devices
     */
    @SerializedName("devices")
    public List<HKDevice>   devices;
}
