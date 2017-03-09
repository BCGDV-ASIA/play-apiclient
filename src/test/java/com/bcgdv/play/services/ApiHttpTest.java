/*
 * ApiTest
 */
package com.bcgdv.play.services;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Test cases
 */
public class ApiHttpTest {

    @Before
    public void setUp() {
        System.setProperty("API_HOST_NAME", "mightyjabba.io");
        System.setProperty("API_PORT", "81");
    }

    @Test
    public void testFacadeCreatesValidApiUrl() throws MalformedURLException {
        SimpleApiAdapter api = new SimpleApiAdapter();
        URL url = new URL(api.buildUri("/blah"));
        System.out.println("ok i built the URL");
    }

    @Test
    public void testHostAndPortAndScheme() throws MalformedURLException {
        SimpleApiAdapter api = new SimpleApiAdapter();

        assertEquals(81, new URL(api.buildUri("/blah")).getPort());
        assertEquals("/blah", new URL(api.buildUri("/blah")).getPath());
        assertEquals("mightyjabba.io", new URL(api.buildUri("/blah")).getHost());
        assertEquals("http", new URL(api.buildUri("/blah")).getProtocol());
    }
}
