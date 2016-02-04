package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.Category;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for retrieving product categories.
 */
public class CategoryService {

    /**
     * Gets the category identified by the specified key.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param categoryKey
     *            The category key starting with 'pl-prod-cat-', e.g.:
     *            pl-prod-cat-books
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return The identified category
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Category getCategoryForKey(PLYRestClient client, String categoryKey, String language) {
        String url = "/category/" + categoryKey;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Category> response = client.exchange(url, HttpMethod.GET, Category.class, parameters);
        return response.getBody();
    }

    /**
     * Gets all category keys.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return All category keys
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getCategoryKeys(PLYRestClient client) {
        String url = "/categories/keys";

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, null);
        return response.getBody();
    }

    /**
     * Gets the main categories with product counts and sub categories.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return All main categories
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Category[] getMainCategories(PLYRestClient client, String language) {
        String url = "/categories";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Category[]> response = client.exchange(url, HttpMethod.GET, Category[].class,
                parameters);
        return response.getBody();
    }

}
