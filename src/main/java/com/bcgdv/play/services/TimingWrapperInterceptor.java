/*
 * TimingWrapperInterceptor
 */
package com.bcgdv.play.services;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;

import java.util.Map;

/**
 * TimerWrapper logs execution times of remote calls
 */
public class TimingWrapperInterceptor implements Api {
    protected static final String ROUND_TRIP = "remote api round trip for %s in %sms";

    protected Api api;

    public TimingWrapperInterceptor(Api api) {
        this.api = api;
    }

    public JsonNode get(String uri) {
        long before = now();
        JsonNode result = api.get(uri);
        return logAndReturn(uri, before, result);
    }

    public JsonNode get(String uri, Map headers) {
        long before = now();
        JsonNode result = api.get(uri, headers);
        return logAndReturn(uri, before, result);
    }

    public JsonNode post(String uri, Map headers) {
        long before = now();
        JsonNode result = api.post(uri, headers);
        return logAndReturn(uri, before, result);
    }

    public JsonNode post(String uri) {
        long before = now();
        JsonNode result = api.post(uri);
        return logAndReturn(uri, before, result);
    }
    
    public JsonNode post(String uri, JsonNode body) {
        long before = now();
        JsonNode result = api.post(uri, body);
        return logAndReturn(uri, before, result);
    }

    public JsonNode post(String uri, Map headers, JsonNode body) {
        long before = now();
        JsonNode result = api.post(uri, headers, body);
        return logAndReturn(uri, before, result);
    }

    public JsonNode put(String uri, JsonNode body) {
        long before = now();
        JsonNode result = api.put(uri, body);
        return logAndReturn(uri, before, result);
    }

    public JsonNode put(String uri, Map headers, JsonNode body) {
        long before = now();
        JsonNode result = api.put(uri, headers, body);
        return logAndReturn(uri, before, result);
    }

    /**
     * Async, no need to time this.
     * @param uri the Uri
     * @param header the http header arguments as a map
     * @param body a JsonNode body to be posted
     */
    @Override
    public void putAndForget(String uri, Map header, JsonNode body) {
        api.putAndForget(uri,header,body);
    }

    /**
     * Async, no need to time this.
     * @param uri the Uri
     * @param body a JsonNode body to be posted
     */
    @Override
    public void putAndForget(String uri, JsonNode body) {
        api.putAndForget(uri,body);
    }

    public JsonNode delete(String uri) {
        long before = now();
        JsonNode result = api.delete(uri);
        return logAndReturn(uri, before, result);
    }

    public JsonNode delete(String uri, Map headers) {
        long before = now();
        JsonNode result = api.delete(uri, headers);
        return logAndReturn(uri, before, result);
    }

    protected JsonNode logAndReturn(String uri, long before, JsonNode result) {
        Logger.info(String.format(ROUND_TRIP, uri, now()-before));
        return result;
    }

    protected long now() {
        return System.currentTimeMillis();
    }
}
