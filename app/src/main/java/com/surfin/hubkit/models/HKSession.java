package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Measurement campaign over a given time interval with one or more Devices
 */
public class HKSession
    extends HKBase
{

    /**
     * State of the session
     */
    @SerializedName("state")
    public String   state;

    /**
     * Unique identifier of the project this session is linked to
     */
    @SerializedName("project")
    public String   project;

    /**
     * Unique identifier of the activity this session is associated with
     */
    @SerializedName("activity")
    public String   activity;

    /**
     * Session capture date
     */
    @SerializedName("capturedAt")
    public Date capturedAt;

    /**
     * Get all sessions
     */
    public static void  all(@NonNull Consumer<List<HKSession>> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("sessions", null, onSuccess, onFailure);
    }

    /**
     * Create new session
     */
    public static void  create(@NonNull HKProject project, @NonNull HKActivity activity, @NonNull Date capturedAt, @NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        Map<String, String> params = new HashMap<>();
        params.put("project", project.identifier);
        params.put("activity", activity.identifier);
        params.put("state", "NEW");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        params.put("capturedAt", f.format(capturedAt));

        HKManager.defaultInstance.post("sessions", params, null, onSuccess, onFailure);
    }

    /**
     * Get session for the given identifier
     */
    public static void  get(@NonNull String identifier, @NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("sessions/" + identifier, null, onSuccess, onFailure);
    }

    /**
     * Change a session state to ready
     */
    public void  ready(@NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.patch("sessions/" + identifier + "/ready", null, onSuccess, onFailure);
    }
}
