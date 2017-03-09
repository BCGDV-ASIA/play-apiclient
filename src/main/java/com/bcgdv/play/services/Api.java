/**
 * Api
 */
package com.bcgdv.play.services;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * Api Interface for remote JSON/HTTP calls.
 */
public interface Api {


    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri);


    /**
     * HTTP GET api endpoint
     * @param uri the Uri
     * @param headers, a Map of HTTP header value pairs
     * @return a JsonNode or Exception
     */
    public JsonNode get(String uri, Map headers);


    /**
     * HTTP POST api endpoint
     * @param uri the Uri
     * @return a JsonNode or Exception
     */
    public JsonNode post(String uri);


    /**
     * HTTP POST api endpiont
     * @param uri the Uri
     * @param headers, a Map of HTTP header key/value pairs
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri, Map headers);


    /**
     * HTTP POST api endpoint
     * @param uri the Uri
     * @param body, a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri, JsonNode body);


    /**
     * HTTP POST api endpoint
     * @param uri the Uri
     * @param headers a Map of HTTP header value pairs
     * @param body a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
    public JsonNode post(String uri, Map headers, JsonNode body);


    /**
     * HTTP PUT api endpoint
     * @param uri the Uri
     * @param body a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
    public JsonNode put(String uri, JsonNode body);


    /**
     * HTTP POST api endpoint
     * @param uri the Uri
     * @param headers a Map of HTTP header value pairs
     * @param body a JsonNode body to be posted
     * @return a JsonNode received from remote
     */
    public JsonNode put(String uri, Map headers, JsonNode body);


    /**
     * HTTP PUT api endpoint
     * Fire and forget pattern. Fires a put request for given uri, headers
     * and body asynchronously.
     * @param uri the Uri
     * @param headers a Map of HTTP header value pairs
     * @param body a JsonNode body to be posted
     */
    public void putAndForget(String uri,Map headers,JsonNode body);


    /**
     * HTTP PUT api endpoint
     * Fire and forget pattern. Fires a put request for given uri, headers
     * and body asynchronously.
     * @param uri the Uri
     * @param body a JsonNode body to be posted
     */
    public void putAndForget(String uri,JsonNode body);


    /**
     * Delete the Uri
     * @param uri the Uri
     * @return parent node
     */
    public JsonNode delete(String uri);


    /**
     * Delete the URi
     * @param uri the Uri
     * @param headers a Map of HTTP header value pairs
     * @return parent node
     */
    public JsonNode delete(String uri, Map headers);
}
