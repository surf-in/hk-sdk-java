package com.surfin.hubkit;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HKRawDataTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testRawDataMapping() {
        HKRawData object = gson.fromJson(HKFixtures.rawData(), HKRawData.class);

        assertNotNull(object);
        assertEquals(object.identifier, "7a19070c-a7ad-43a2-b851-59656328e6b7");
        assertEquals(object.device, "2a6c50ef-2b6f-48fe-89c9-77044cd2d584");
        assertEquals(object.session, "5f743e5a-9969-4cd4-8769-81fa71734578");
    }
}
