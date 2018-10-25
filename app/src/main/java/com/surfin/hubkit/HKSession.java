package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

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
     * Session metadatas
     */
    @SerializedName("metas")
    public String   metas;

    /**
     * Session capture date
     */
    @SerializedName("capturedAt")
    public Date capturedAt;

    /**
     * Create new session
     */
    public static void  create(@NonNull HKProject project, @NonNull String metas, @NonNull Date capturedAt, @NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        Map<String, String> params = new HashMap<>();
        params.put("project", project.identifier);
        params.put("metas", metas);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        params.put("capturedAt", f.format(capturedAt));

        HKManager.defaultInstance.postSession(params, onSuccess, onFailure);
    }

    /**
     * Get session for the given identifier
     */
    public static void  get(@NonNull String identifier, @NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.getSession(identifier, onSuccess, onFailure);
    }

    /**
     * Change a session state to ready
     */
    public void  ready(@NonNull Consumer<HKSession> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.updateSession(identifier, onSuccess, onFailure);
    }
}
