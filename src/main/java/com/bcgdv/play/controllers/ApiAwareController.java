/*
 * ApiAwareController
 */
package com.bcgdv.play.controllers;

import com.bcgdv.play.services.Api;
import com.bcgdv.play.util.ApiHeaderHelper;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;

import javax.inject.Inject;
import java.util.Map;


/**
 * Parent controller, injected with api convenience object, that allows
 * a controller to access other services within the same domain. Provides wrapper
 * methods for get, post, put access to JSON based APIs
 */
public class ApiAwareController extends Controller implements Api {

    protected Api api;

    protected ApiAwareController() {
        //use Guice injection via play instead
    }

    @Inject
    public ApiAwareController(Api api) {
        this.api = api;
    }

    public JsonNode get(String uri) {
        return api.get(uri, requestHeaders());
    }

    public JsonNode get(String uri, String token) {
        return api.get(uri, headers(token));
    }

    public JsonNode get(String uri, Map headers) {
        return api.get(uri, headers);
    }

    public JsonNode post(String uri) {
        return api.post(uri);
    }

    public JsonNode post(String uri, String token) {
        return api.post(uri, headers(token));
    }

    public JsonNode post(String uri, Map headers) {
        return api.post(uri, headers);
    }

    public JsonNode post(String uri, JsonNode body) {
        return api.post(uri, requestHeaders(), body);
    }

    public JsonNode post(String uri, JsonNode body, String token) {
        return api.post(uri, headers(token), body);
    }

    public JsonNode post(String uri, Map headers, JsonNode body) {
        return api.post(uri, headers, body);
    }

    @Override
    public void putAndForget(String uri, Map header, JsonNode body) {
        api.putAndForget(uri, header, body);
    }

    @Override
    public void putAndForget(String uri, JsonNode body) {
        api.putAndForget(uri, body);
    }

    public void putAndForget(String uri, JsonNode body, String token) {
        api.putAndForget(uri, headers(token), body);
    }


    public JsonNode put(String uri, JsonNode body) {
        return api.put(uri, requestHeaders(), body);
    }

    public JsonNode put(String uri, JsonNode body, String token) {
        return api.put(uri, headers(token), body);
    }

    public JsonNode put(String uri, Map headers, JsonNode body) {
        return api.put(uri, headers, body);
    }

    public JsonNode delete(String uri) {
        return api.delete(uri, requestHeaders());
    }

    public JsonNode delete(String uri, String token) {
        return api.delete(uri, headers(token));
    }

    public JsonNode delete(String uri, Map headers) {
        return api.delete(uri, headers);
    }

    protected Map<String, String> headers(String token) {
        return ApiHeaderHelper.headers(token, request());
    }

    protected Map requestHeaders() {
        return ApiHeaderHelper.requestHeaders(request());

    }
}
