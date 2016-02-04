/**
 * 
 */
package com.productlayer.rest.client;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.productlayer.core.beans.errors.ErrorMessage;
import com.productlayer.core.beans.errors.ErrorResponse;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.Base64;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.config.PLYRestClientConfig;
import com.productlayer.rest.client.helper.ConversionTool;

/**
 * REST Client for the ProductLayer API. Services use this component to send
 * data to the API server. Any response is automatically deserialized.
 * 
 * Uses Spring RestTemplate. By default Apache's HttpClient is used for
 * communication with the server. Switch to Java's native URLConnection by using
 * the return value of {@link #getSimpleRestTemplate(PLYRestClientConfig)} for
 * {@link #setRestTemplate(RestTemplate)}.
 */
@SuppressWarnings("serial")
public class PLYRestClient implements Serializable {
    private PLYRestClientConfig config;
    private String username;
    private String password;
    private String session;
    private String token;

    private String clientIP;
    private String userAgent;

    private String preferredLanguage;
    private String[] additionalLanguages;

    private Map<String, String> additionalHeaders;

    private transient RestTemplate restTemplate;

    private HttpStatus lastHttpStatus;
    private HttpHeaders lastHttpHeaders;
    private Object lastResponseBody;

    public static final String COOKIE_AUTH_TOKEN = "X-ProductLayer-Auth-Token";
    public static final String COOKIE_CLIENT_IP = "X-ProductLayer-Client-IP";
    public static final String COOKIE_USER_AGENT = "X-ProductLayer-User-Agent";

    public static final String HEADER_USER_POINTS = "X-ProductLayer-User-Points";
    public static final String HEADER_USER_POINTS_CHANGE = "X-ProductLayer-User-Points-Changed";
    public static final String HEADER_USER_NEW_ACHIEVEMENTS = "X-ProductLayer-User-Unlocked-Achievements";

    /**
     * Build an HTTP client for Spring using Java's native URLConnection.
     * 
     * @param config
     *            any proxy configuration
     * @return a URLConnection HTTP client for Spring
     */
    public static RestTemplate getSimpleRestTemplate(PLYRestClientConfig config) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        if (config.proxyEnabled) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress(config.proxyHost, config.proxyPort));
            requestFactory.setProxy(proxy);
        }

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        enableConverters(restTemplate);
        return restTemplate;
    }

    /**
     * Build an HTTP client for Spring using Apache's HTTPComponents library.
     * 
     * @param config
     *            any proxy configuration
     * @return an Apache HTTP client for Spring
     */
    public static RestTemplate getHttpComponentsRestTemplate(PLYRestClientConfig config) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        if (config.proxyEnabled) {
            clientBuilder = clientBuilder.setProxy(new HttpHost(config.proxyHost, config.proxyPort));
        }

        final CloseableHttpClient client = clientBuilder.build();
        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        enableConverters(restTemplate);
        return restTemplate;
    }

    /**
     * @return the HTTP client used by Spring for communication (builds an
     *         Apache HTTPClient by default if none has been set)
     */
    public RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = getHttpComponentsRestTemplate(config);
        }
        return restTemplate;
    }

    /**
     * Sets the HTTP client to be used by Spring.
     * 
     * @param restTemplate
     *            the HTTP client to use
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Constructs a new REST client to be used for communication with the
     * ProductLayer API server.
     * 
     * @param config
     *            the configuration for the new REST client
     */
    public PLYRestClient(PLYRestClientConfig config) {
        this(config, null, null, null, null);
    }

    private PLYRestClient(PLYRestClientConfig config, String username, String password, String session,
            String token) {
        this.config = config;
        this.username = username;
        this.password = password;
        this.session = session;
        this.token = token;
    }

    /**
     * @param version
     *            the API version to target
     * @return the link to the versioned JSONDoc documentation of the API
     */
    public String getUrlForDocumentation(String version) {
        return config.apiSchema + "://" + config.apiHost
                + ((config.apiPort != 80) ? ":" + config.apiPort : "") + "/" + version + "/jsondoc";
    }

    /**
     * Adds the ProductLayer API server data (URL) to a service path.
     * 
     * @param method
     *            the path of the endpoint
     * @return the full URL to the specified method
     */
    public String getUrlForMethod(String method) {
        return config.apiSchema + "://" + config.apiHost
                + ((config.apiPort != 80) ? ":" + config.apiPort : "") + "/" + config.apiVersion
                + (method.charAt(0) == '/' ? "" : "/") + method;
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();

        if (StringUtils.hasText(token)) {
            String[] tokenSplit = token.split("=");
            headers.add(tokenSplit[0], tokenSplit[1]);
        } else if (StringUtils.hasText(session)) {
            headers.add("Cookie", session);
        } else if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            headers.add("Authorization", getBasicAuthCredentials());
        }

        String supportedLanguages = null;
        if (StringUtils.hasText(preferredLanguage)) {
            supportedLanguages = preferredLanguage;
        }
        if (additionalLanguages != null && additionalLanguages.length > 0) {
            for (String language : additionalLanguages) {
                if (supportedLanguages == null) {
                    supportedLanguages = language;
                } else {
                    supportedLanguages += "," + language;
                }
            }
        }

        if (supportedLanguages != null) {
            headers.set("Accept-Language", supportedLanguages);
        }

        if (additionalHeaders != null) {
            for (String key : additionalHeaders.keySet()) {
                headers.add(key, additionalHeaders.get(key));
            }
        }

        // Add API-Key
        headers.add("API-KEY", config.apiKey);

        if (StringUtils.hasText(clientIP)) {
            headers.add(COOKIE_CLIENT_IP, clientIP);
        }

        if (StringUtils.hasText(userAgent)) {
            headers.add(COOKIE_USER_AGENT, userAgent);
        }

        return headers;
    }

    /**
     * @return "Basic " + Base64 encoded username:password
     */
    public String getBasicAuthCredentials() {
        String plainCreds = username + ":" + password;
        String base64Creds = Base64.encodeString(plainCreds);

        return "Basic " + base64Creds;
    }

    /**
     * Execute the HTTP {@code method} to the given {@code url}, expanding any
     * given {@code uriVariables}, and returning the response as ResponseEntity.
     * 
     * @param url
     *            the URL to send the request to (may include placeholders for
     *            {@code uriVariables})
     * @param method
     *            the HTTP method to use for the request
     * @param responseType
     *            the class expected to be returned
     * @param uriVariables
     *            any variables to be inserted into the URL
     * @param <T>
     *            the type to deserialize the value returned from the server to
     * @return the response of type {@code responseType} wrapped in a
     *         ResponseEntity
     * @throws PLYHttpException
     *             on any HTTP status code indicating failure
     * @throws RestClientException
     *             on any client-side HTTP error
     */
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, Class<T> responseType,
            Map<String, ?> uriVariables) throws PLYHttpException, RestClientException {
        return exchange(url, method, responseType, null, null, null, uriVariables);
    }

    /**
     * Execute the HTTP {@code method} to the given {@code url}, expanding any
     * given {@code uriVariables}, and returning the parameterized response as
     * ResponseEntity.
     * 
     * @param url
     *            the URL to send the request to (may include placeholders for
     *            {@code uriVariables})
     * @param method
     *            the HTTP method to use for the request
     * @param parameterizedResponseType
     *            the parameterized class expected to be returned implemented as
     *            a {@link ParameterizedTypeReference}
     * @param uriVariables
     *            any variables to be inserted into the URL
     * @param <T>
     *            the type to deserialize the value returned from the server to
     * @return the response of type {@code responseType} wrapped in a
     *         ResponseEntity
     * @throws PLYHttpException
     *             on any HTTP status code indicating failure
     * @throws RestClientException
     *             on any client-side HTTP error
     */
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
            final ParameterizedTypeReference<T> parameterizedResponseType, Map<String, ?> uriVariables)
            throws PLYHttpException, RestClientException {
        return exchange(url, method, null, parameterizedResponseType, null, null, uriVariables);
    }

    /**
     * Execute the HTTP {@code method} to the given {@code url} with an
     * {@code object} serialized as {@code application/json} in the request's
     * body, expanding any given {@code uriVariables}, and returning the
     * response as ResponseEntity.
     * 
     * @param url
     *            the URL to send the request to (may include placeholders for
     *            {@code uriVariables})
     * @param method
     *            the HTTP method to use for the request
     * @param object
     *            the object to be sent as the request's HTTP body
     * @param responseType
     *            the class expected to be returned
     * @param uriVariables
     *            any variables to be inserted into the URL
     * @param <T>
     *            the type to deserialize the value returned from the server to
     * @return the response of type {@code responseType} wrapped in a
     *         ResponseEntity
     * @throws PLYHttpException
     *             on any HTTP status code indicating failure
     * @throws RestClientException
     *             on any client-side HTTP error
     */
    public <T> ResponseEntity<T> exchangeWithObject(String url, HttpMethod method, Object object,
            Class<T> responseType, Map<String, ?> uriVariables) throws PLYHttpException, RestClientException {
        return exchangeWithObjectAndContentType(url, method, object, MediaType.APPLICATION_JSON,
                responseType, uriVariables);
    }

    /**
     * Execute the HTTP {@code method} to the given {@code url} with an
     * {@code object} of {@code contentType} in the request's body, expanding
     * any given {@code uriVariables}, and returning the response as
     * ResponseEntity.
     * 
     * @param url
     *            the URL to send the request to (may include placeholders for
     *            {@code uriVariables})
     * @param method
     *            the HTTP method to use for the request
     * @param object
     *            the object to be sent as the request's HTTP body
     * @param contentType
     *            the content type of the request's HTTP body
     * @param responseType
     *            the class expected to be returned
     * @param uriVariables
     *            any variables to be inserted into the URL
     * @param <T>
     *            the type to deserialize the value returned from the server to
     * @return the response of type {@code responseType} wrapped in a
     *         ResponseEntity
     * @throws PLYHttpException
     *             on any HTTP status code indicating failure
     * @throws RestClientException
     *             on any client-side HTTP error
     */
    public <T> ResponseEntity<T> exchangeWithObjectAndContentType(String url, HttpMethod method,
            Object object, MediaType contentType, Class<T> responseType, Map<String, ?> uriVariables)
            throws PLYHttpException, RestClientException {
        return exchange(url, method, responseType, null, object, contentType, uriVariables);
    }

    private interface RestCall<T> {
        ResponseEntity<T> exchange(String fullUrl) throws HttpStatusCodeException, RestClientException;
    }

    private <T> ResponseEntity<T> exchange(String url, final HttpMethod method, final Class<T> responseType,
            final ParameterizedTypeReference<T> parameterizedResponseType, final Object object,
            final MediaType contentType, final Map<String, ?> uriVariablesMap) throws PLYHttpException,
            RestClientException {
        final boolean objectInBody = object != null;
        // prepare request body and headers
        Object entityBody;
        HttpHeaders entityHeaders = getDefaultHeaders();
        if (objectInBody) {
            entityBody = object;
            entityHeaders.setContentType(contentType != null ? contentType : MediaType.APPLICATION_JSON);
        } else {
            entityBody = method == HttpMethod.GET ? null : "parameters";
        }
        final HttpEntity<Object> entity = new HttpEntity<Object>(entityBody, entityHeaders);
        // prepare RestCall object
        RestCall<T> restCall = new RestCall<T>() {
            public ResponseEntity<T> exchange(String fullUrl) throws HttpStatusCodeException,
                    RestClientException {
                if (uriVariablesMap != null) {
                    if (parameterizedResponseType != null) {
                        return getRestTemplate().exchange(fullUrl, method, entity, parameterizedResponseType,
                                uriVariablesMap);
                    } else {
                        return getRestTemplate().exchange(fullUrl, method, entity, responseType,
                                uriVariablesMap);
                    }
                } else {
                    if (parameterizedResponseType != null) {
                        return getRestTemplate().exchange(fullUrl, method, entity, parameterizedResponseType);
                    } else {
                        return getRestTemplate().exchange(fullUrl, method, entity, responseType);
                    }
                }
            }
        };
        try {
            // send and receive
            ResponseEntity<T> response = restCall.exchange(url.startsWith("http") ? url
                    : getUrlForMethod(url));
            // follow redirects
            if (response.getStatusCode() == HttpStatus.MOVED_PERMANENTLY
                    || response.getStatusCode() == HttpStatus.FOUND) {
                response = restCall.exchange(response.getHeaders().getLocation().toString());
            }
            lastHttpStatus = response.getStatusCode();
            lastHttpHeaders = response.getHeaders();
            lastResponseBody = response.getBody();
            List<String> sessionCookies = response.getHeaders().get("Set-Cookie");
            if (sessionCookies != null && !sessionCookies.isEmpty()) {
                setSession(sessionCookies.get(0));
            }
            return response;
        } catch (HttpStatusCodeException e) {
            // attempt to parse response body into error response object
            // the parsing will fail and return null any time the server is not
            // responding with a JSON error response object (for example if a
            // non-existent URL is called)
            ErrorResponse errors = ConversionTool.parseObject(e.getResponseBodyAsString(),
                    ErrorResponse.class);
            lastHttpStatus = e.getStatusCode();
            lastHttpHeaders = e.getResponseHeaders();
            lastResponseBody = errors;
            if (errors != null && errors.getErrors() != null) {
                // throw PLYHttpException containing messages of the parsed
                // error response and the HTTP status code
                throw new PLYHttpException(errors.getErrors(), e.getStatusCode().value());
            } else {
                // throw PLYHttpException containing a new error message with
                // the HTTP status code and description (no error response
                // returned)
                throw new PLYHttpException(new ErrorMessage(e.getStatusCode().getReasonPhrase(), e
                        .getStatusCode().value()), e.getStatusCode().value());
            }
        }
    }

    /**
     * @return the user name used for authentication with the ProductLayer API
     *         server
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the user name to set for authentication with the ProductLayer
     *            API server
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param session
     *            the session to set
     */
    public void setSession(String session) {
        if (session != null && session.contains("path")) {
            session = session.substring(0, session.indexOf("path"));
        }
        this.session = session;
    }

    /**
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * @return the token used for authentication with the ProductLayer API
     *         server
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set for authentication with the ProductLayer API
     *            server
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the password used for authentication with the ProductLayer API
     *         server
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set for authentication with the ProductLayer
     *            API server
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return any additional headers that are sent with HTTP requests
     */
    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    /**
     * @param additionalHeaders
     *            any additional headers to be sent with HTTP requests
     */
    public void setAdditionalHeaders(Map<String, String> additionalHeaders) {
        this.additionalHeaders = additionalHeaders;
    }

    /**
     * @return a deep copy of this PLYRestClient
     */
    public PLYRestClient copy() {
        PLYRestClientConfig copiedConfig = config.copy();
        PLYRestClient client = new PLYRestClient(copiedConfig, this.username, this.password, this.session,
                this.token);

        ClientHttpRequestFactory oldRequestFactory = getRestTemplate().getRequestFactory();
        if (oldRequestFactory instanceof SimpleClientHttpRequestFactory) {
            // native URLConnection
            client.setRestTemplate(getSimpleRestTemplate(copiedConfig));
        } else {
            // Apache HTTPClient
            client.setRestTemplate(getHttpComponentsRestTemplate(copiedConfig));
        }

        client.setClientIP(clientIP);
        client.setUserAgent(userAgent);
        client.setPreferredLanguage(preferredLanguage);

        if (additionalLanguages != null) {
            client.setAdditionalLanguages(additionalLanguages.clone());
        }

        if (additionalHeaders != null) {
            client.setAdditionalHeaders(new HashMap<String, String>(additionalHeaders));
        } else {
            client.setAdditionalHeaders(new HashMap<String, String>());
        }

        return client;
    }

    /**
     * @return the preferred language for localizations
     */
    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    /**
     * @param preferredLanguage
     *            the preferred language to set for localizations
     */
    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    /**
     * @return any additional languages to be considered for localization
     */
    public String[] getAdditionalLanguages() {
        return additionalLanguages;
    }

    /**
     * @param additionalLanguages
     *            any additional languages to be considered for localization
     */
    public void setAdditionalLanguages(String[] additionalLanguages) {
        this.additionalLanguages = additionalLanguages;
    }

    /**
     * @return the client IP address in case the {@code PLYRestClient} is used
     *         as proxy
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param clientIP
     *            the client IP address to store in case the
     *            {@code PLYRestClient} is used as proxy
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * @return the user agent of the client in case the {@code PLYRestClient} is
     *         used as proxy
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            the user agent of the client to store in case the
     *            {@code PLYRestClient} is used as proxy
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the status code returned by the last HTTP request
     */
    public HttpStatus getLastHttpStatus() {
        return lastHttpStatus;
    }

    /**
     * @return the headers returned by the last HTTP request
     */
    public HttpHeaders getLastHttpHeaders() {
        return lastHttpHeaders;
    }

    /**
     * @return the body returned by the last HTTP request
     */
    public Object getLastResponseBody() {
        return lastResponseBody;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the schema (http/https)
     */
    public String getConfigApiSchema() {
        return config.apiSchema;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the host (domain/IP)
     */
    public String getConfigApiHost() {
        return config.apiHost;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the port number
     */
    public int getConfigApiPort() {
        return config.apiPort;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the version of the API
     */
    public String getConfigApiVersion() {
        return config.apiVersion;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the API key registered for this application
     */
    public String getConfigApiKey() {
        return config.apiKey;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return whether a proxy is used
     */
    public boolean isConfigProxyEnabled() {
        return config.proxyEnabled;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the proxy IP address
     */
    public String getConfigProxyHost() {
        return config.proxyHost;
    }

    /**
     * Read access to the configuration used by this {@code PLYRestClient}.
     * 
     * @return the proxy port number
     */
    public int getConfigProxyPort() {
        return config.proxyPort;
    }

    /**
     * Enables the JSON and byte array (de)serializers.
     * 
     * @param restTemplate
     *            the {@code RestTemplate} to enable the (de)serializers for
     */
    public static void enableConverters(RestTemplate restTemplate) {
        restTemplate.getMessageConverters().add(0, ConversionTool.getJacksonConverter());
        restTemplate.getMessageConverters().add(1, ConversionTool.getByteArrayConverter());
    }

    /**
     * Disables the JSON and byte array (de)serializers.
     * 
     * @param restTemplate
     *            the {@code RestTemplate} to disable the (de)serializers for
     */
    public static void disableConverters(RestTemplate restTemplate) {
        restTemplate.getMessageConverters().remove(0);
        restTemplate.getMessageConverters().remove(1);
    }
}
