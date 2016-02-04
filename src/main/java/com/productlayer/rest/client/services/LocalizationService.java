package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.localization.LocalizedKey;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing localizations.
 */
public class LocalizationService {

    /**
     * Gets localizable strings property file for Java applications.
     * 
     * @param client
     *            the REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            The language (e.g.: 'en' or 'de')
     * @return The content of the localizable property file
     */
    public static String getLocalizedPropertiesFile(PLYRestClient client, String language) {
        String url = "/localization/file/Localizable." + language + ".properties";

        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, String.class, null);
        return response.getBody();
    }

    /**
     * Gets localizable strings file for iOS applications.
     * 
     * @param client
     *            the REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            The language (e.g.: 'en' or 'de')
     * @return The content of the localizable strings file
     */
    public static String getLocalizedStringsFile(PLYRestClient client, String language) {
        String url = "/localization/file/Localizable." + language + ".strings";

        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, String.class, null);
        return response.getBody();
    }

    /**
     * Gets localized keys for a domain.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param domain
     *            [Optional] The root domain, e.g.: pl-prod
     * @param fetchChilds
     *            [Optional] If true all localizations containing the domain are
     *            returned, otherwise only the specific key will be returned.
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @return Any keys belonging to the specific base domain and as their value
     *         the translation to the preferred language
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Map<String, String> getLocalizedKeys(PLYRestClient client, String domain,
            boolean fetchChilds, String language) {
        String url = "/localization";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(domain)) {
            parameters.put("domain", domain);
        }
        if (!StringUtils.isEmpty(fetchChilds)) {
            parameters.put("fetchChilds", String.valueOf(fetchChilds));
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Map<String, String>> response = client.exchange(url, HttpMethod.GET,
                new ParameterizedTypeReference<Map<String, String>>() {
                }, parameters);
        return response.getBody();
    }

    /**
     * Creates a new localization.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param item
     *            The item
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static void insertLocalizedKey(PLYRestClient client, LocalizedKey item) {
        String url = "/localization";

        client.exchangeWithObject(url, HttpMethod.POST, item, Void.class, null);
    }

    /**
     * Updates a localization.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param item
     *            The item
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static void updateLocalizedKey(PLYRestClient client, LocalizedKey item) {
        String url = "/localization";

        client.exchangeWithObject(url, HttpMethod.PUT, item, Void.class, null);
    }

}
