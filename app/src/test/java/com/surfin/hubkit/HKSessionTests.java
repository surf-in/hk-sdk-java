package com.surfin.hubkit;

import com.google.gson.Gson;
import com.surfin.hubkit.models.HKSession;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HKSessionTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testSessionMapping() {
        HKSession object = gson.fromJson(HKFixtures.session(), HKSession.class);

        assertNotNull(object);
        assertEquals(object.identifier, "5f743e5a-9969-4cd4-8769-81fa71734578");
        assertEquals(object.state, "NEW");
        assertEquals(object.project, "a48edeb7-a44d-437f-8f11-c83942d4d4eb");
        assertEquals(object.capturedAt, gson.fromJson("\"2018-09-24T20:04:30+02:00\"", Date.class));
    }
}
