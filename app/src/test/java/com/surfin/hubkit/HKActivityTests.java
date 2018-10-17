package com.surfin.hubkit;

import com.google.gson.Gson;
import com.surfin.hubkit.models.HKActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HKActivityTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testActivityMapping() {
        HKActivity hkActivity = gson.fromJson(HKFixtures.activity(), HKActivity.class);

        assertNotNull(hkActivity);
        assertEquals(hkActivity.identifier, "d039c7a3-c514-43a9-b502-59bd02c57e38");
        assertEquals(hkActivity.name, "Football");
    }
}
