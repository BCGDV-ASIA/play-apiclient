/*
 * ApiTest
 */
package com.bcgdv.play.services;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

/**
 * Test cases
 */
public class ApiHttpsTest {

    @Before
    public void setUp() {
        
    }

    @Test
    public void testHttps() throws MalformedURLException {
        SimpleApiAdapter api = new SimpleApiAdapter("https");
        assertEquals(443, new URL(api.buildUri("/blah")).getPort());
        assertEquals("https", new URL(api.buildUri("/blah")).getProtocol());
    }
}
