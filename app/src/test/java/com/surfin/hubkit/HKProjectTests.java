package com.surfin.hubkit;

import com.google.gson.Gson;
import com.surfin.hubkit.models.HKProject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HKProjectTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testProjectMapping() {
        HKProject object = gson.fromJson(HKFixtures.project(), HKProject.class);

        assertNotNull(object);
        assertEquals(object.identifier, "320c1df2-4488-400a-81c7-042e8805b695");
        assertEquals(object.name, "Tibtop");
        assertNull(object.activity);
        assertNull(object.devices);
    }

    @Test
    public void testProjectEmptyDevicesMapping() {
        HKProject object = gson.fromJson(HKFixtures.projectWithEmptyDevices(), HKProject.class);

        assertNotNull(object);
        assertEquals(object.identifier, "320c1df2-4488-400a-81c7-042e8805b695");
        assertEquals(object.name, "Tibtop");
        assertNull(object.activity);
        assertNotNull(object.devices);
        assertTrue(object.devices.isEmpty());
    }

    @Test
    public void testProjectWithDevicesMapping() {
        HKProject object = gson.fromJson(HKFixtures.projectWithDevices(), HKProject.class);

        assertNotNull(object);
        assertEquals(object.identifier, "320c1df2-4488-400a-81c7-042e8805b695");
        assertEquals(object.name, "Tibtop");
        assertNull(object.activity);
        assertNotNull(object.devices);
        assertEquals(object.devices.size(), 1);
    }

    @Test
    public void testProjectWithActivityMapping() {
        HKProject object = gson.fromJson(HKFixtures.projectWithActivity(), HKProject.class);

        assertNotNull(object);
        assertEquals(object.identifier, "320c1df2-4488-400a-81c7-042e8805b695");
        assertEquals(object.name, "Tibtop");
        assertNotNull(object.activity);
        assertNull(object.devices);
    }
}
