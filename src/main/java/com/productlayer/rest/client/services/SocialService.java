package com.productlayer.rest.client.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.Opine;
import com.productlayer.core.beans.User;
import com.productlayer.core.beans.social.SocialOpine;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing social network connections.
 */
public class SocialService {

    /**
     * Gets the provider connection URL.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param provider
     *            The social service provider, currently valid are only facebook
     *            and twitter
     * @param redirectUrl
     *            The URL for redirecting after provider connect
     * @param token
     *            Any tokens for the connection to the API server
     * @return The provider connection URL
     */
    public static String getProviderConnectionURL(PLYRestClient client, String provider, String redirectUrl,
            String token) {
        String url;
        try {
            url = "connect/" + provider + "?callback=" + URLEncoder.encode(redirectUrl, "UTF-8") + "&"
                    + token;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String fullUrl = (url.startsWith("http")) ? url : client.getUrlForMethod(url);

        return fullUrl;
    }

    /**
     * Gets the provider sign in URL.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param provider
     *            The social service provider, currently valid are only facebook
     *            and twitter
     * @param redirectUrl
     *            The URL for redirecting after provider sign in
     * @return The provider sign in URL
     */
    public static String getProviderSigninURL(PLYRestClient client, String provider, String redirectUrl) {
        String url;
        try {
            url = "signin/" + provider + "?callback=" + URLEncoder.encode(redirectUrl, "UTF-8") + "&API-KEY="
                    + client.getConfigApiKey();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String fullUrl = (url.startsWith("http")) ? url : client.getUrlForMethod(url);

        return fullUrl;
    }

    /**
     * Connects the user to a social network provider like Twitter or Facebook.
     * After establishing a connection to the provider on behalf of the member
     * the user will be redirected to the provided URL.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param provider
     *            The social service provider, currently valid are only facebook
     *            and twitter
     * @param redirectUrl
     *            The URL for redirecting after provider connect
     * @return The URL to redirect to
     */
    public static URI connectProvider(PLYRestClient client, String provider, String redirectUrl) {
        String url = "connect/" + provider;

        Map<String, String> parameters = new HashMap<String, String>();
        try {
            parameters.put("callback", URLEncoder.encode(redirectUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, String.class, parameters);
        return response.getHeaders().getLocation();
    }

    /**
     * Removes the social network connection from the logged in user.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param provider
     *            The social service provider, currently valid are only facebook
     *            and twitter
     * @return The updated user object
     */
    public static User disconnectProvider(PLYRestClient client, String provider) {
        String url = "connect/" + provider;

        ResponseEntity<User> response = client.exchange(url, HttpMethod.DELETE, User.class, null);
        return response.getBody();
    }

    /**
     * Get the social response for the specific post.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @return A list of social responses
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static SocialOpine[] getSocialReplies(PLYRestClient client, String opineID) {
        String url = "/opine/" + opineID + "/social_response";

        ResponseEntity<SocialOpine[]> response = client.exchange(url, HttpMethod.GET, SocialOpine[].class,
                null);
        return response.getBody();
    }

    /**
     * Check if the token for this provider is expired.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param providerId
     *            The social service provider (currently only facebook and
     *            twitter are valid)
     * @return True if the token is expired, otherwise false.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Boolean isTokenValid(PLYRestClient client, String providerId) {
        String url = "/" + providerId + "/token_expired";

        ResponseEntity<Boolean> response = client.exchange(url, HttpMethod.GET, Boolean.class, null);
        return response.getBody();
    }

    /**
     * Get tweets about a product
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param providerId
     *            The social service provider (currently only facebook and
     *            twitter are valid)
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return The updated user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine[] searchForProductPosts(PLYRestClient client, String providerId, String gtin,
            String language) {
        String url = "/" + providerId + "/search/" + gtin;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Opine[]> response = client.exchange(url, HttpMethod.GET, Opine[].class, parameters);
        return response.getBody();
    }

}
