package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

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
    public static void  get(@NonNull String indentifier, @NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("devices/" + indentifier, null, onSuccess, onFailure);
    }

    /**
     * Create a new session
     */
    public static void  create(@NonNull HKDevice device, @NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        Map<String, String> params = device.asMap();
        params.remove("identifier");
        params.remove("activated");
        params.put("manualMode", String.valueOf(true));

        HKManager.defaultInstance.post("devices", device.asMap(), null, onSuccess, onFailure); //TODO encoding
    }

    /**
     * Update this device
     */
    public void         update(@NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.patch("devices/" + identifier, asMap(), onSuccess, onFailure);
    }

    /**
     * Activate this device
     */
    public void         activate(@NonNull Consumer<HKDevice> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.patch("devices/" + identifier + "/activate", null, onSuccess, onFailure);
    }
}
