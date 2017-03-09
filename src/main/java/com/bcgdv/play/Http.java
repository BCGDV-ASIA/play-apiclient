/*
 * Http
 */
package com.bcgdv.play;

/**
 * Http Constants
 */
public class Http {


    /**
     * Bad Request
     */
    public static final int BAD_REQUEST = 400;


    /**
     * Authorization Header
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";


    /**
     * Mime Type
     */
    public static final String MIME_TYPE = "application/json";

    /**
     * Client timeout for outgoing HTTP requests
     */
    public static final long CLIENT_TIMEOUT_DEFAULT_MS = 90000l;
}
