/**
 * 
 */
package com.productlayer.rest.client.config;

import java.io.Serializable;

/**
 * Configuration data to generate the {@code PLYRestClient} with.
 */
public class PLYRestClientConfig implements Serializable {

    private static final long serialVersionUID = -7886597827050884718L;

    private static String API_SCHEMA = "https";
    private static String API_HOST = "api.productlayer.com";
    private static int API_PORT = 80;

    private static String API_VERSION = "0.5";
    private static String API_KEY = "ENTER_APPLICATION_KEY";

    private static boolean PROXY_ENABLED = false;
    private static String PROXY_HOST = "localhost";
    private static int PROXY_PORT = 8888;

    public String apiSchema = API_SCHEMA;
    public String apiHost = API_HOST;
    public int apiPort = API_PORT;

    public String apiVersion = API_VERSION;
    public String apiKey = API_KEY;

    public boolean proxyEnabled = PROXY_ENABLED;
    public String proxyHost = PROXY_HOST;
    public int proxyPort = PROXY_PORT;

    /**
     * Initializes the configuration with default values.
     * 
     * Make sure to set a valid {@code apiKey}. If you are developing a new
     * application, please register a developer account at <a
     * href="http://api.productlayer.com/">api.productlayer.com</a>.
     */
    public PLYRestClientConfig() {

    }

    /**
     * Initializes the configuration with the specified values.
     * 
     * @param apiSchema
     *            the schema (http/https) of the API server
     * @param apiHost
     *            the host (domain/IP) of the API server
     * @param apiPort
     *            the port number of the API server
     * @param apiVersion
     *            the version of the API
     * @param apiKey
     *            the API key of your application (register at <a
     *            href="http://api.productlayer.com/">api.productlayer.com</a>)
     * @param proxyEnabled
     *            whether to use a proxy
     * @param proxyHost
     *            the proxy's IP address
     * @param proxyPort
     *            the proxy's port number
     */
    public PLYRestClientConfig(String apiSchema, String apiHost, int apiPort, String apiVersion,
            String apiKey, boolean proxyEnabled, String proxyHost, int proxyPort) {
        this.apiSchema = apiSchema;
        this.apiHost = apiHost;
        this.apiPort = apiPort;
        this.apiVersion = apiVersion;
        this.apiKey = apiKey;
        this.proxyEnabled = proxyEnabled;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    /**
     * @return a clone of this configuration object
     */
    public PLYRestClientConfig copy() {
        return new PLYRestClientConfig(apiSchema, apiHost, apiPort, apiVersion, apiKey, proxyEnabled,
                proxyHost, proxyPort);
    }

}
