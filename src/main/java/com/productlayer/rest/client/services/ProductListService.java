package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.lists.ProductList;
import com.productlayer.core.beans.lists.ProductListItem;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing lists of products.
 */
public class ProductListService {

    /**
     * Adds the product to the list or, if it exists, replaces it.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @param listItem
     *            The listItem
     * @return The updated product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList addToProductList(PLYRestClient client, String productlistId, String gtin,
            String language, ProductListItem listItem) {
        String url = "/list/" + productlistId + "/product/" + gtin;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchangeWithObject(url, HttpMethod.PUT, listItem,
                ProductList.class, parameters);
        return response.getBody();
    }

    /**
     * Creates a new product list for the authenticated user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param list
     *            The list
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return The newly created product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList createNewProductList(PLYRestClient client, ProductList list, String language) {
        String url = "/lists";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchangeWithObject(url, HttpMethod.POST, list,
                ProductList.class, parameters);
        return response.getBody();
    }

    /**
     * Deletes a product from the list.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language for the loaded product
     *            objects.
     * @return The updated product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList deleteFromProductList(PLYRestClient client, String productlistId, String gtin,
            String language) {
        String url = "/list/" + productlistId + "/product/" + gtin;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchange(url, HttpMethod.DELETE, ProductList.class,
                parameters);
        return response.getBody();
    }

    /**
     * Deletes the product list matching the ID.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @return The deleted product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList deleteProductList(PLYRestClient client, String productlistId) {
        String url = "/list/" + productlistId;

        ResponseEntity<ProductList> response = client.exchange(url, HttpMethod.DELETE, ProductList.class,
                null);
        return response.getBody();
    }

    /**
     * Gets a product list by ID.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return The identified product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList getProductList(PLYRestClient client, String productlistId, String language) {
        String url = "/list/" + productlistId;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchange(url, HttpMethod.GET, ProductList.class,
                parameters);
        return response.getBody();
    }

    /**
     * Get the product list mosaic image.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @return the URL to the requested data
     */
    public static String getProductListImageURL(PLYRestClient client, Long productlistId) {
        String url = client.getUrlForMethod("/list/" + productlistId + "/image.jpg");

        return url;
    }
    
    public static byte[] getProductListImage(PLYRestClient client, Long productlistId) {
        String url = getProductListImageURL(client, productlistId);

        ResponseEntity<byte[]> response = client.exchange(url, HttpMethod.GET, byte[].class, null);
        return response.getBody();
    }

    /**
     * Gets product lists matching certain criteria.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param type
     *            [Optional] The type can be:
     *            <ul>
     *            <li>owned (Shows only product lists which are owned by the
     *            user.)</li>
     *            <li>shared (Shows only product lists which have been shared by
     *            the user.)</li>
     *            <li>public (Shows all public product lists.)</li>
     *            </ul>
     * @param user_id
     *            [Optional] The identifier of the user
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return Product lists matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList[] searchProductLists(PLYRestClient client, String type, String user_id,
            Integer page, Integer recordsPerPage, String language) {
        String url = "/lists";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(type)) {
            parameters.put("type", type);
        }
        if (!StringUtils.isEmpty(user_id)) {
            parameters.put("user_id", user_id);
        }
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList[]> response = client.exchange(url, HttpMethod.GET, ProductList[].class,
                parameters);
        return response.getBody();
    }

    /**
     * Gets a user's product lists matching certain criteria.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param listType
     *            [Optional] The type of the product list
     * @param userId
     *            The identifier of the user
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return Product lists of the user matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList[] searchUserProductLists(PLYRestClient client, String listType, String userId,
            Integer page, Integer recordsPerPage, String language) {
        String url = "/user/" + userId + "/lists";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(listType)) {
            parameters.put("list_type", listType);
        }
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList[]> response = client.exchange(url, HttpMethod.GET, ProductList[].class,
                parameters);
        return response.getBody();
    }

    /**
     * Shares a list with a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param userId
     *            The identifier of the user
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return The updated product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList shareProductList(PLYRestClient client, String productlistId, String userId,
            String language) {
        String url = "/list/" + productlistId + "/share/" + userId;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchange(url, HttpMethod.POST, ProductList.class,
                parameters);
        return response.getBody();
    }

    /**
     * Unshares a list with a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param userId
     *            The identifier of the user
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @return The updated product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList unshareProductList(PLYRestClient client, String productlistId, String userId,
            String language) {
        String url = "/list/" + productlistId + "/share/" + userId;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchange(url, HttpMethod.DELETE, ProductList.class,
                parameters);
        return response.getBody();
    }

    /**
     * Updates a product list.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productlistId
     *            The identifier of the product list
     * @param language
     *            [Optional] The preferred language of the product (e.g.: 'en'
     *            or 'de')
     * @param list
     *            The list
     * @return The updated product list
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProductList updateProductList(PLYRestClient client, String productlistId, String language,
            ProductList list) {
        String url = "/list/" + productlistId;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProductList> response = client.exchangeWithObject(url, HttpMethod.PUT, list,
                ProductList.class, parameters);
        return response.getBody();
    }

}
