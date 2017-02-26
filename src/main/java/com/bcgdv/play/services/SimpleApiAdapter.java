package com.bcgdv.play.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import play.Configuration;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.bcgdv.RequestIdGenerator.X_REQUEST_ID;

/**
 * Simple API Adapter connects to internal APIs using the environment variable $API_HOST_NAME,
 * sets a timeout, and sets up the client to support HTTP redirects, i.e. from HTTP
 * to HTTPS URLs.
 */
public class SimpleApiAdapter implements Api {
    protected static final String API_HOST_NAME = "API_HOST_NAME";

    protected static final int CLIENT_ERROR_400 = 400;

    protected static final String HTTP_AUTHORIZATION_HEADER = "Authorization";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String HTTP_CLIENT_TIMEOUT = "HTTP_CLIENT_TIMEOUT";
    protected static final long HTTP_CLIENT_TIMEOUT_DEFAULT_MS = 90000l;

    protected static final String HTTPS = "https";
    protected static final String SSL = "ssl";
    protected static final String SCHEME_SEPARATOR = "://";
    protected static final String HTTP = "http";
    protected static String scheme = "http://";

    /**
     * One of "https" "ssl" or "http"
     * @param scheme the url scheme
     */
    public SimpleApiAdapter(String scheme) {
        initScheme(scheme);
    }

    protected void initScheme(String scheme) {
        if(HTTPS.equalsIgnoreCase(scheme) || SSL.equalsIgnoreCase(scheme)) {
            SimpleApiAdapter.scheme = HTTPS + SCHEME_SEPARATOR;
        } else if(HTTP.equalsIgnoreCase(scheme)) {
            SimpleApiAdapter.scheme = HTTP + SCHEME_SEPARATOR;
        } else {
            throw new IllegalArgumentException("unable to recognise scheme");
        }
    }

    public JsonNode get(String uri) {
        return handleResponse(apiRequest(uri, new HashMap()).get().toCompletableFuture().join());
    }

    public JsonNode get(String uri, Map headers) {
        return handleResponse(apiRequest(uri, headers).get().toCompletableFuture().join());
    }

    public JsonNode post(String uri, JsonNode body) {
        return handleResponse(apiRequest(uri, new HashMap()).post(body).toCompletableFuture().join());
    }

    public JsonNode post(String uri) {
        return handleResponse(apiRequest(uri, new HashMap()).post(Json.newObject()).toCompletableFuture().join());
    }

    public JsonNode post(String uri, Map headers) {
        return handleResponse(apiRequest(uri, headers).post(Json.newObject()).toCompletableFuture().join());
    }
    
    public JsonNode post(String uri, Map headers, JsonNode body) {
        return handleResponse(apiRequest(uri, headers).post(body).toCompletableFuture().join());
    }

    public JsonNode put(String uri, JsonNode body) {
        return handleResponse(apiRequest(uri, new HashMap()).put(body).toCompletableFuture().join());
    }

    public JsonNode put(String uri, Map headers, JsonNode body) {
        return handleResponse(apiRequest(uri, headers).put(body).toCompletableFuture().join());
    }

    @Override
    public void putAndForget(String uri, Map headers, JsonNode body) {
        Logger.info("Trying to make  put request for url {}",uri);
         CompletableFuture.runAsync(() -> {
            try {
                WSResponse response = apiRequest(uri, headers).put(body).toCompletableFuture().get();
                Logger.info("Remote put response  for uri " + uri + "is " + response.getStatus());
            } catch (Exception e) {
                Logger.warn("Remote put response  for uri  failed :" + uri + e.getMessage());
            }
        });
    }

    @Override
    public void putAndForget(String uri, JsonNode body) {
         putAndForget(uri,new HashMap(),body);
    }

    public JsonNode delete(String uri) {
        return handleResponse(apiRequest(uri, new HashMap()).delete().toCompletableFuture().join());
    }

    public JsonNode delete(String uri, Map headers) {
        return handleResponse(apiRequest(uri, headers).delete().toCompletableFuture().join());
    }

    /**
     * Make api request to relative path inside domain
     * @param pathSegment the path to call
     * @param headers HTTP headers to pass
     * @return WSRequest
     */
    protected WSRequest apiRequest(String pathSegment, Map headers) {
        String host = getEnvPropertyConfiguration(API_HOST_NAME);
        WSRequest request = WS.url(scheme+host+pathSegment);
        request.setFollowRedirects(true);
        request.setRequestTimeout(timeout());
        request.setContentType(APPLICATION_JSON);
        if(headers!=null&&headers.containsKey(HTTP_AUTHORIZATION_HEADER)) {
            request.setHeader(HTTP_AUTHORIZATION_HEADER, (String) headers.get(HTTP_AUTHORIZATION_HEADER));
        }
        if(headers!=null&&headers.containsKey(X_REQUEST_ID)) {
            request.setHeader(X_REQUEST_ID, (String) headers.get(X_REQUEST_ID));
        }
        return request;
    }

    /**
     * Handle the JSON response
     * @param response WSResponse
     * @return a JSON node
     */
    protected JsonNode handleResponse(WSResponse response) {
        if(response.getStatus() < CLIENT_ERROR_400) {
            return Json.parse(jsonSafeResponse(response));
        } else {
            return handleRemoteError(response.getUri().getPath(), response);
        }
    }

    /**
     * Parses JSON response and returns empty JSON body on failure
     * @param response WSResponse
     * @return decoded JSON body as STring
     */
    protected String jsonSafeResponse(WSResponse response) {
        return "".equals(response.getBody()) ? "{}" : response.getBody();
    }

    /**
     * Check the remote response HTTP status
     * @param uri the URI
     * @param response the WSResponse
     * @return the JSONNode
     */
    protected JsonNode handleRemoteError(String uri, WSResponse response) {
        String message = "remote response for uri: " + uri +", status: " + response.getStatus();
        Logger.warn(message);
        throw new RuntimeJsonMappingException(message);
    }

    /**
     * Parse HTTP timeout from config
     * @return long value
     */
    protected long timeout() {
        String timeout = getEnvPropertyConfiguration(HTTP_CLIENT_TIMEOUT);
        if (timeout==null) {
            return HTTP_CLIENT_TIMEOUT_DEFAULT_MS;
        } else {
            return Long.parseLong(timeout);
        }
    }

    /**
     * read parameter as system environment variable or Java property value
     * @param param the param
     * @return a String decoded value
     */
    protected String getEnvPropertyConfiguration(String param) {
        String value = System.getenv(param);
        if(value==null) {
            value = System.getProperty(param);
        }
        if(value==null) {
            value = Configuration.root().getString(param);
        }
        if(value==null) {
            value = Configuration.root().getString(param.toLowerCase());
        }
        return value;
    }
}
