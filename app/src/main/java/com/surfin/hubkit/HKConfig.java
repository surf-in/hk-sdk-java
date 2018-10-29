package com.surfin.hubkit;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Allow to configure the HKManager default instance with a given API environment and version
 */
public class HKConfig {

    /**
     * Available api versions
     */
    public enum ApiVersion {
        V1("v1"),
        V2("v2");

        public final String version;

        ApiVersion(String version) {
            this.version = version;
        }
    }

    /**
     * Available api environment
     */
    public enum ApiEnvironment {
        DEV(BuildConfig.DEV_BASE_URL),
        SANDBOX(BuildConfig.SANDBOX_BASE_URL),
        PROD(BuildConfig.PROD_BASE_URL);

        public final String baseUrl;

        ApiEnvironment(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    private ApiEnvironment  environment;
    private ApiVersion      version;

    /**
     * Creates a new configuration with the given environment and version
     *
     * @param environment environment
     * @param version version
     */
    public HKConfig(ApiEnvironment environment, ApiVersion version) {
        this.environment = environment;
        this.version = version;
    }

    public ApiEnvironment getEnvironment() {
        return environment;
    }

    public ApiVersion getVersion() {
        return version;
    }

    /**
     * Get default (Accept and Content-Type) headers
     *
     * @return default headers as map
     */
    public static Map<String, String>   getHeaders() {
        return getHeaders(null);
    }

    /**
     * Get default (Accept and Content-Type) headers and the authentication header according to the provided token
     *
     * @param token authentication token
     * @return headers as map
     */
    public static Map<String, String>   getHeaders(@Nullable HKToken token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        if (token != null) {
            if (token.apiKey != null && !token.apiKey.isEmpty()) headers.put("apikey", token.apiKey);
            else if (token.jwt != null && !token.jwt.isEmpty()) headers.put("Authorization", "Bearer " + token.jwt);
        }

        return headers;
    }
}
