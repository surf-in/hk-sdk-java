package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
}
