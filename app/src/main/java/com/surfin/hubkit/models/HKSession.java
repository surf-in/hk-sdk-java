package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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
    public Date capturedAt; //TODO check it's converted to ISO860
}
