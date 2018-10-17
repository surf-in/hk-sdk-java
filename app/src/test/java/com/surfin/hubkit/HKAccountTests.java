package com.surfin.hubkit;

import com.google.gson.Gson;
import com.surfin.hubkit.models.HKAccount;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HKAccountTests {

    private Gson    gson;

    @Before
    public void setUp() {
        gson = HKFixtures.gson();
    }

    @Test
    public void testAccountMapping() {
        HKAccount hkAccount = gson.fromJson(HKFixtures.account(), HKAccount.class);

        assertNotNull(hkAccount);
        assertEquals(hkAccount.identifier, "d039c7a3-c514-43a9-b502-59bd02c57e38");
        assertEquals(hkAccount.email, "loic@hubkit.io");
        assertNotNull(hkAccount.project);
        assertTrue(hkAccount.project.isEmpty());
    }

    @Test
    public void testAccountProjectsMapping() {
        HKAccount hkAccount = gson.fromJson(HKFixtures.accountWithProjects(), HKAccount.class);

        assertNotNull(hkAccount);
        assertEquals(hkAccount.identifier, "d039c7a3-c514-43a9-b502-59bd02c57e38");
        assertEquals(hkAccount.email, "loic@hubkit.io");
        assertNotNull(hkAccount.project);
        assertFalse(hkAccount.project.isEmpty());
        assertEquals(hkAccount.project.size(), 1);
    }
}
