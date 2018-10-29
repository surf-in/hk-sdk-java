package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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

    /**
     * Authenticate user and get a HKToken with a JWT token
     */
    public static void         authenticate(@NonNull String username, @NonNull String password, @NonNull Consumer<HKToken> onSuccess, @NonNull Consumer<Error> onFailure) {
        Map<String, String> params = new HashMap<>();
        params.put("_username", username);
        params.put("_password", password);

        HKManager.defaultInstance.auth(params, onSuccess, onFailure);
    }

    /**
     * Logout current user logged in with JWT token authentication method
     */
    public static void         logout() {
        HKManager.defaultInstance.setToken(null);
    }
}
