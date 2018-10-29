package com.surfin.hubkit;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HKManagerTests {

    private Gson        gson;
    private HKManager   apiManager;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
        apiManager = new HKManager();
    }

    @Test
    public void testDefaultBaseURL() {
        assertEquals(apiManager.getBaseUrl(), "http://localhost:8080/api/v1/");
    }

    @Test
    public void testDefaultHeaders() {
        Map<String, String> headers = HKConfig.getHeaders();

        assertNotNull(headers);
        assertFalse(headers.isEmpty());
        assertEquals(headers.get("Accept"), "application/json");
        assertEquals(headers.get("Content-Type"), "application/json");
        assertNull(headers.get("apikey"));
        assertNull(headers.get("Authorization"));
    }

    @Test
    public void testBaseURL() {
        HKConfig config = new HKConfig(HKConfig.ApiEnvironment.SANDBOX, HKConfig.ApiVersion.V2);
        apiManager.setConfig(config);

        assertEquals(apiManager.getBaseUrl(), "https://app-staging.hubkit.cloud/api/v2/");
    }

    @Test
    public void testAuthenticationHeaders() {
        HKToken token = gson.fromJson(HKFixtures.jwt(), HKToken.class);
        Map<String, String> headers = HKConfig.getHeaders(token);

        assertNotNull(headers);
        assertFalse(headers.isEmpty());
        assertEquals(headers.get("Accept"), "application/json");
        assertEquals(headers.get("Content-Type"), "application/json");
        assertEquals(headers.get("Authorization"), "Bearer MyJwtToken");
        assertNull(headers.get("apikey"));
    }

    @Test
    public void testApiKeyHeaders() {
        HKToken token = new HKToken();
        token.apiKey = HKFixtures.apiKey();
        Map<String, String> headers = HKConfig.getHeaders(token);

        assertNotNull(headers);
        assertFalse(headers.isEmpty());
        assertEquals("application/json", headers.get("Accept"));
        assertEquals("application/json", headers.get("Content-Type"));
        assertNull(headers.get("Authorization"));
        assertEquals("a49686ae-6501-4c37-a3cd-e22bc205bd8f", headers.get("apikey"));
    }
}
