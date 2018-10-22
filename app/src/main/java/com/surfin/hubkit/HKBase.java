package com.surfin.hubkit;

import com.google.gson.annotations.SerializedName;

/**
 * Base object
 */
abstract class HKBase {

    /**
     * Unique identifier
     */
    @SerializedName("identifier")
    public String   identifier;
}
