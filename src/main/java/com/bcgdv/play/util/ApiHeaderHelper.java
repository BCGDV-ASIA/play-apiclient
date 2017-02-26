package com.bcgdv.play.util;

import com.google.common.collect.Maps;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static com.bcgdv.RequestIdGenerator.X_REQUEST_ID;

/**
 * Convenience methods for accessing HTTP request headers.
 * Created by mohanambalavanan on 24/11/2016.
 */
public class ApiHeaderHelper {
    protected static final String AUTHORIZATION = "Authorization";

    protected ApiHeaderHelper(){
        //don't instantiate directly
    }

    public static Map<String,String> headers(String token,Http.Request request){
        Map<String,String> headers =  Maps.newHashMap();
        headers.put(AUTHORIZATION,token);

        addXRequestHeader(headers,request);
        return headers;
    }

    public static Map requestHeaders(Http.Request request) {
        Map headers = new HashMap();
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && !"".equals(authorizationHeader)) {
            headers.put(AUTHORIZATION, authorizationHeader);
        }
        addXRequestHeader(headers,request);
        return headers;
    }

    public static void addXRequestHeader(Map<String, String> headers,Http.Request request) {
        String requestIdHeader = request.getHeader(X_REQUEST_ID);
        if(requestIdHeader != null && !"".equals(requestIdHeader)) {
            headers.put(X_REQUEST_ID, requestIdHeader);
        }
    }
}
