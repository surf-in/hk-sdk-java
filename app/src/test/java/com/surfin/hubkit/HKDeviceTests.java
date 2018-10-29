package com.surfin.hubkit;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HKDeviceTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testDeviceMapping() {
        HKDevice hkDevice = gson.fromJson(HKFixtures.device(), HKDevice.class);

        assertNotNull(hkDevice);
        assertEquals(hkDevice.identifier, "15ddf330-c6f7-4d3e-89b7-84174b578074");
        assertEquals(hkDevice.externalUUID, "My external identifier");
        assertEquals(hkDevice.name, "TIBTOP (54)");
        assertEquals(hkDevice.factoryTest, "OK");
        assertEquals(hkDevice.macAddress, "19:6e:36:59:0c:d9");
        assertEquals(hkDevice.hardwareVersion, "1.0");
        assertEquals(hkDevice.firmwareVersion, "0.26");
        assertEquals(hkDevice.manualMode, true);
        assertEquals(hkDevice.latitude, 44.84454710000001, 0);
        assertEquals(hkDevice.longitude, -0.5794340999999577, 0);
        assertEquals(hkDevice.sensorType, "TIBTOP GPS");
        assertEquals(hkDevice.battery, 98);
        assertEquals(hkDevice.activated, false);
    }
}
