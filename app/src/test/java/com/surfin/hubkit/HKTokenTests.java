package com.surfin.hubkit;

import com.google.gson.Gson;
import com.surfin.hubkit.models.HKSession;
import com.surfin.hubkit.models.HKToken;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HKTokenTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testJWTTokenMapping() {
        HKToken object = gson.fromJson(HKFixtures.jwt(), HKToken.class);

        assertNotNull(object);
        assertEquals(object.jwt, "MyJwtToken");
    }

    @Test
    public void testApiKeyTokenMapping() {
        HKToken object = new HKToken();
        object.apiKey = HKFixtures.apiKey();

        assertNotNull(object);
        assertEquals(object.apiKey, "a49686ae-6501-4c37-a3cd-e22bc205bd8f");
    }
}
