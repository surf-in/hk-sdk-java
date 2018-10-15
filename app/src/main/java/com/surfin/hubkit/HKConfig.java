package com.surfin.hubkit;

import android.support.annotation.Nullable;
import android.util.Pair;

import com.surfin.hubkit.models.HKToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Allow to configure the HKManager default instance with a given API environment and version
 */
public class HKConfig {

    private static final Pair<String, String>   HEADER_ACCEPT       = new Pair<>("Accept", "application/json");
    private static final Pair<String, String>   HEADER_CONTENT_TYPE = new Pair<>("Content-Type", "application/json");
    private static final Pair<String, String>   HEADER_API_KEY      = new Pair<>("apikey", "");
    private static final Pair<String, String>   HEADER_AUTH         = new Pair<>("Authorization", "Bearer ");

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
        headers.put(HEADER_ACCEPT.first, HEADER_ACCEPT.second);
        headers.put(HEADER_CONTENT_TYPE.first, HEADER_CONTENT_TYPE.second);
        if (token != null) {
            if (token.apiKey != null && !token.apiKey.isEmpty()) headers.put(HEADER_API_KEY.first, HEADER_API_KEY.second + token.apiKey);
            else if (token.jwt != null && !token.jwt.isEmpty()) headers.put(HEADER_AUTH.first, HEADER_AUTH.second + token.jwt);
        }

        return headers;
    }
}
