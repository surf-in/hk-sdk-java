package com.surfin.hubkit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Authenticated user account representation
 */
public class HKAccount
        extends HKBase
{

    /**
     * Authenticated user email address
     */
    @SerializedName("email")
    public String   email;

    /**
     * Authenticated user projects
     */
    @SerializedName("projects")
    public List<HKProject>  project;
}
