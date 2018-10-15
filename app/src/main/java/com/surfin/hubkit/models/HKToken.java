package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

/**
 * Authentication token used to sign API communication
 */
public class HKToken
    extends HKBase
{

    /**
     * Authentication jwt token
     */
    @SerializedName("token")
    public String   jwt;

    /**
     * Authentication api key
     */
    public String   apiKey;
    //TODO where does this come from ?
}
