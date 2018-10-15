package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Activity a session can be associated with
 */
public class HKActivity
        extends HKBase
{

    /**
     * Activity name
     */
    @SerializedName("name")
    public String   name;
}
