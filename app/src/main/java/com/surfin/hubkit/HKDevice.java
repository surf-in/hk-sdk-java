package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Physical device containing a set of electronic components for measurements (Sensors)
 */
public class HKDevice
    extends HKBase
{

    /**
     * Device's UUID
     */
    @SerializedName("externalIdentifier")
    public String   externalUUID;

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

    private Map<String, String> asMap() {
        Map<String, String> map = new HashMap<>();
        map.put("identifier", identifier);
        map.put("externalIdentifier", externalUUID);
        map.put("name", name);
        map.put("factoryTest", factoryTest);
        map.put("macAddress", macAddress);
        map.put("hardwareVersion", hardwareVersion);
        map.put("firmwareVersion", firmwareVersion);
        map.put("manualMode", String.valueOf(manualMode));
        map.put("latitude", String.valueOf(latitude));
        map.put("longitude", String.valueOf(longitude));
        map.put("sensorType", sensorType);
        map.put("battery", String.valueOf(battery));
        map.put("activated", String.valueOf(activated));

        return map;
    }

    /**
     * Get the device for the given identifier
     */
    public static void  get(@NonNull String identifier, @NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.getDevice(identifier, onSuccess, onFailure);
    }

    /**
     * Create a new session
     */
    public static void  create(@NonNull HKDevice device, @NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.createDevice(device.asMap(), onSuccess, onFailure);
    }

    /**
     * Update this device
     */
    public void         update(@NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.updateDevice(identifier, asMap(), onSuccess, onFailure);
    }

    /**
     * Activate this device
     */
    public void         activate(@NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.activateDevice(identifier, onSuccess, onFailure);
    }
}
