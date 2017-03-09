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

    /**
     * error messages
     */
    protected static final String ROUND_TRIP = "remote api round trip for %s in %sms";

    /**
     * Has an Api
     */
    protected Api api;

    /**
     * Create as a wrapper around Api
     * @param api the Api
     */
    public TimingWrapperInterceptor(Api api) {
        this.api = api;
    }

    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri) {
        long before = now();
        JsonNode result = api.get(uri);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @param headers, a Map of HTTP header value pairs
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri, Map headers) {
        long before = now();
        JsonNode result = api.get(uri, headers);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP POST api endpoint
     * @param uri the Uri
     * @param headers, a Map of HTTP header key/value pairs
     * @return a JsonNode or Exception
     */
    public JsonNode post(String uri, Map headers) {
        long before = now();
        JsonNode result = api.post(uri, headers);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP POST api endpiont
     * @param uri the Uri
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri) {
        long before = now();
        JsonNode result = api.post(uri);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP POST api endpiont
     * @param uri the Uri
     * @param body the body payload
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri, JsonNode body) {
        long before = now();
        JsonNode result = api.post(uri, body);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP POST api endpiont
     * @param uri the Uri
     * @param headers a map of headers
     * @param body the body payload
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri, Map headers, JsonNode body) {
        long before = now();
        JsonNode result = api.post(uri, headers, body);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP PUT api endpoint
     * @param uri the Uri
     * @param body a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
    public JsonNode put(String uri, JsonNode body) {
        long before = now();
        JsonNode result = api.put(uri, body);
        return logAndReturn(uri, before, result);
    }

    /**
     * HTTP PUT api endpoint
     * @param uri the Uri
     * @param headers a map of headers
     * @param body a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
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


    /**
     * Delete the Uri
     * @param uri the Uri
     * @return parent node
     */
    public JsonNode delete(String uri) {
        long before = now();
        JsonNode result = api.delete(uri);
        return logAndReturn(uri, before, result);
    }

    /**
     * Delete the Uri
     * @param uri the Uri
     * @param headers a Map of headers.
     * @return parent node
     */
    public JsonNode delete(String uri, Map headers) {
        long before = now();
        JsonNode result = api.delete(uri, headers);
        return logAndReturn(uri, before, result);
    }

    /**
     * Time this execution and return
     * @param uri the uri
     * @param before the starting time
     * @param result the payload result
     * @return the result
     */
    protected JsonNode logAndReturn(String uri, long before, JsonNode result) {
        Logger.info(String.format(ROUND_TRIP, uri, now()-before));
        return result;
    }

    /**
     * Get the current time
     * @return as long millis
     */
    protected long now() {
        return System.currentTimeMillis();
    }
}
