package com.surfin.hubkit.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.surfin.hubkit.HKManager;

import java.util.List;
import java.util.function.Consumer;

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

    /**
     * get currently authenticated user
     */
    public static void  me(@NonNull Consumer<HKAccount> onSuccess, @NonNull Consumer<Error> onFailure) {
        HKManager.defaultInstance.get("me", null, onSuccess, onFailure);
    }
}
