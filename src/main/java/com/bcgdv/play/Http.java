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

    /**
     * Scheme constants
     */
    public static class Scheme {

        /**
         * HTTPS Scheme
         */
        public static final String HTTPS = "https";

        /**
         * Scheme Separator
         */
        public static final String SCHEME_SEPARATOR = "://";

        /**
         * Http Scheme
         */
        public static final String HTTP = "http";
    }

    /**
     * Host constants
     */
    public static class Host {

        /**
         * Default
         */
        public static final String DEFAULT = "localhost";
    }
}
