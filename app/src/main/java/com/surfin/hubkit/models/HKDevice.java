package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Physical device containing a set of electronic components for measurements (Sensors)
 */
public class HKDevice
    extends HKBase
{

    /**
     * Name of the device
     */
    @SerializedName("name")
    public String   name;

    /**
     * Factory test results
     */
    @SerializedName("factoryTest")
    public String   factoryTest;

    /**
     * MAC address of the device
     */
    @SerializedName("macAddress")
    public String   macAddress;

    /**
     * Hardware version of the device
     */
    @SerializedName("hardwareVersion")
    public String   hardwareVersion;

    /**
     * Firmware version of the device
     */
    @SerializedName("firmwareVersion")
    public String   firmwareVersion;

    /**
     * Is the device start recording data manually
     */
    @SerializedName("manualMode")
    public boolean  manualMode;

    /**
     * Latitude of the device position
     */
    @SerializedName("latitude")
    public double   latitude;

    /**
     * Longitude of the device position
     */
    @SerializedName("longitude")
    public double   longitude;

    /**
     * Device type
     */
    @SerializedName("sensorType")
    public String   sensorType;

    /**
     * Device battery level in percentile
     */
    @SerializedName("battery")
    public int  battery;

    /**
     * Is the device activated
     */
    @SerializedName("activated")
    public boolean  activated;
}
