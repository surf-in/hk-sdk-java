package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

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
}
