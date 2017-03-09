/*
 * ApiFacade
 */
package com.bcgdv.play.services;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * Facade to access api, wraps execution in timer/logger.
 */
public class ApiFacade implements Api {
    protected Api api;

    public ApiFacade() {
        this.api = new TimingWrapperInterceptor(new SimpleApiAdapter());
    }


    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri) {
        return api.get(uri);
    }


    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @param headers, a Map of HTTP header value pairs
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri, Map headers) {
        return api.get(uri, headers);
    }

    /**
     * Delete the Uri
     * @param uri the Uri
     * @return parent node
     */
    public JsonNode delete(String uri) {
        return api.delete(uri);
    }

    /**
     * Delete the Uri
     * @param uri the Uri
     * @param headers a map of http headers
     * @return parent node
     */
    public JsonNode delete(String uri, Map headers) {
        return api.delete(uri, headers);
    }

    public JsonNode post(String uri) {
        return api.post(uri);
    }

    public JsonNode post(String uri, Map headers) {
        return api.post(uri, headers);
    }
    
    public JsonNode post(String uri, JsonNode body) {
        return api.post(uri, body);
    }

    public JsonNode post(String uri, Map headers, JsonNode body) {
        return api.post(uri, headers, body);
    }

    public JsonNode put(String uri, JsonNode body) {
        return api.put(uri, body);
    }

    public JsonNode put(String uri, Map headers, JsonNode body) {
        return api.put(uri, headers, body);
    }

    @Override
    public void putAndForget(String uri, JsonNode body) {
         api.putAndForget(uri,body);
    }

    @Override
    public void putAndForget(String uri, Map headers, JsonNode body) {
         api.putAndForget(uri,headers,body);
    }
}
