package com.productlayer.rest.client.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.BrandOwner;
import com.productlayer.core.beans.Category;
import com.productlayer.core.beans.Count;
import com.productlayer.core.beans.Product;
import com.productlayer.core.beans.ResultMessageWithCount;
import com.productlayer.core.beans.ValuesForKey;
import com.productlayer.core.beans.ranking.RankingResults;
import com.productlayer.core.beans.reports.ProblemReport;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing products.
 */
public class ProductService {

    /**
     * Searches for a product by GTIN (more than one result may be returned).
     * The GTIN is unique for a product but a Product object will be returned
     * per locale.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            [Optional] The GTIN (barcode) of the product
     * @param suggestions
     *            [Optional] Make product suggestions if search returns no
     *            results. Product suggestions are all returned products without
     *            a pl-id parameter. Default: false
     * @return Any products matching the GTIN
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] searchProductsByGtin(PLYRestClient client, String gtin, boolean suggestions) {
        return searchProducts(client, null, null, null, gtin, null, null, null, suggestions, null, null,
                null, null);
    }

    /**
     * Searches for products by using the query string.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param query
     *            The query may contain the name, GTIN or brand of the product
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @param suggestions
     *            [Optional] Make product suggestions if search returns no
     *            results. Product suggestions are all returned products without
     *            a pl-id parameter. Default: false
     * @return Any products matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] searchProductsByQuery(PLYRestClient client, String query, String categoryKey,
            boolean suggestions) {
        return searchProducts(client, query, null, null, null, null, null, null, suggestions, null, null,
                categoryKey, null);
    }

    /**
     * Gets a product by GTIN and language.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            The preferred language (e.g.: 'en' or 'de')
     * @param searchAlsoOtherLocales
     *            Whether to also try different locales if the one specified is
     *            not found
     * @param suggestions
     *            [Optional] Make product suggestions if search returns no
     *            results. Product suggestions are all returned products without
     *            a pl-id parameter. Default: false
     * @param fetchOnly
     *            [Optional] Fetch only specific keys
     * @return The identified product or null if not found
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product getProductForGtinAndLocale(PLYRestClient client, String gtin, String language,
            boolean searchAlsoOtherLocales, boolean suggestions, String fetchOnly) {
        if (!searchAlsoOtherLocales) {
            return getProductForGtin(client, gtin, language, suggestions, fetchOnly);
        }

        Product[] products = searchProducts(client, null, null, null, gtin, null, null, null, suggestions,
                fetchOnly, null, null, null);
        if (products == null || products.length == 0) {
            return null;
        }
        if (products.length == 1) {
            return products[0];
        }

        Product defaultLocale = null;
        for (Product product : products) {
            if (product.getLanguage() != null) {
                // If requested locale has been found return the product.
                if (product.getLanguage().equalsIgnoreCase(language)) {
                    return product;
                } else if (product.getLanguage().startsWith("en") && defaultLocale == null) {
                    defaultLocale = product;
                }
            }
        }
        // return default locale product or the first product if requested and
        // default locale was not found.
        if (defaultLocale != null) {
            return defaultLocale;
        } else {
            return products[0];
        }
    }

    /**
     * Searches for products by brand.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param brand
     *            The brand of the product
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @return Any products matching the brand
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] searchProductsByBrand(PLYRestClient client, String brand, Integer page,
            Integer recordsPerPage) {
        return searchProducts(client, null, page, recordsPerPage, null, brand, null, null, false, null, null,
                null, null);
    }

    /**
     * Searches for products by brand owner.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param brandOwner
     *            The brand owner of the product
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @return Any products matching the brand owner
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] searchProductsByBrandOwner(PLYRestClient client, String brandOwner, Integer page,
            Integer recordsPerPage) {
        return searchProducts(client, null, page, recordsPerPage, null, null, brandOwner, null, false, null,
                null, null, null);
    }

    /**
     * Updates a specific product. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param product
     *            The product
     * @return The updated product
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product updateProduct(PLYRestClient client, Product product) {
        return updateProduct(client, product.getGtin(), product);
    }

    /**
     * Creates a new product. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param product
     *            The product
     * @return The newly created product
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product createProduct(PLYRestClient client, Product product) {
        String url = "/products";

        ResponseEntity<Product> response = client.exchangeWithObject(url, HttpMethod.POST, product,
                Product.class, null);
        return response.getBody();
    }

    /**
     * Downvotes a specific product. By using the product ID instead of the GTIN
     * it's possible to vote for a specific localized product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productID
     *            The identifier of the product (identifies a specific localized
     *            product, e.g.: Apple iPhone 5S (en))
     * @return The product with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product downVoteProduct(PLYRestClient client, String productID) {
        String url = "/product/" + productID + "/down_vote";

        ResponseEntity<Product> response = client.exchange(url, HttpMethod.POST, Product.class, null);
        return response.getBody();
    }

    /**
     * Gets suggestions of brand owners of a GTIN.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @return Any suggested brand owners for a GTIN
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static BrandOwner[] getBrandOwnerSuggestions(PLYRestClient client, String gtin) {
        String url = "/product/" + gtin + "/recommended_brand_owners";

        ResponseEntity<BrandOwner[]> response = client
                .exchange(url, HttpMethod.GET, BrandOwner[].class, null);
        return response.getBody();
    }

    /**
     * Gets known brand owner names.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return All brand owner names
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getBrandOwners(PLYRestClient client) {
        String url = "/products/brand_owners";

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, null);
        return response.getBody();
    }

    /**
     * Gets known brand names.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return All brand names
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getBrands(PLYRestClient client) {
        String url = "/products/brands";

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, null);
        return response.getBody();
    }

    /**
     * Gets suggestions of categories of a GTIN.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @return Any suggested categories for a GTIN
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Category[] getCategorySuggestions(PLYRestClient client, String gtin) {
        String url = "/product/" + gtin + "/recommended_categories";

        ResponseEntity<Category[]> response = client.exchange(url, HttpMethod.GET, Category[].class, null);
        return response.getBody();
    }

    /**
     * Gets the scoring history within a range from a product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param from_date
     *            [Optional] Start date, format: yyyy-MM-dd HH:mm:ss
     * @param to_date
     *            [Optional] End date, format: yyyy-MM-dd HH:mm:ss
     * @param count
     *            [Optional] The amount of results to be returned, default:
     *            '200'
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @return The hottest products contained within a ranking results object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static RankingResults<Product> getHottestProducts(PLYRestClient client, Date from_date,
            Date to_date, Integer count, String language, Boolean showOpines, Boolean showReviews,
            Boolean showPictures, Boolean showProducts) {
        String url = "/products/hottest";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(from_date)) {
            parameters.put("from_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from_date));
        }
        if (!StringUtils.isEmpty(to_date)) {
            parameters.put("to_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to_date));
        }
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(showOpines)) {
            parameters.put("opines", showOpines.toString());
        }
        if (!StringUtils.isEmpty(showReviews)) {
            parameters.put("reviews", showReviews.toString());
        }
        if (!StringUtils.isEmpty(showPictures)) {
            parameters.put("images", showPictures.toString());
        }
        if (!StringUtils.isEmpty(showProducts)) {
            parameters.put("products", showProducts.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<RankingResults<Product>> response = client.exchange(url, HttpMethod.GET,
                new ParameterizedTypeReference<RankingResults<Product>>() {
                }, parameters);
        return response.getBody();
    }

    /**
     * Gets localized category keys.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @return All category keys and as their value the translation to the
     *         preferred language
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Map<String, String> getLocalizedCategories(PLYRestClient client, String language) {
        String url = "/products/categories";

        Map<String, String> parameters = new HashMap<String, String>();
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
     * Gets localized characteristics keys.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @return All characteristics keys and as their value the translation to
     *         the preferred language
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Map<String, String> getLocalizedCharacteristics(PLYRestClient client, String language) {
        String url = "/products/characteristics";

        Map<String, String> parameters = new HashMap<String, String>();
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
     * Gets localized nutrition keys.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @return All nutrition keys and as their value the translation to the
     *         preferred language
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Map<String, String> getLocalizedNutrition(PLYRestClient client, String language) {
        String url = "/products/nutritious";

        Map<String, String> parameters = new HashMap<String, String>();
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
     * Gets the overall product count or the count for a specific timeframe.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param from_date
     *            [Optional] Start date, format: yyyy-MM-dd HH:mm:ss
     * @param to_date
     *            [Optional] End date, format: yyyy-MM-dd HH:mm:ss
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @return The product count
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Count getProductCount(PLYRestClient client, Date from_date, Date to_date, String categoryKey) {
        String url = "/products/count";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(from_date)) {
            parameters.put("from_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from_date));
        }
        if (!StringUtils.isEmpty(to_date)) {
            parameters.put("to_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to_date));
        }
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ResultMessageWithCount> response = client.exchange(url, HttpMethod.GET,
                ResultMessageWithCount.class, parameters);
        return (Count) response.getBody().getResult();
    }

    /**
     * Gets a product by GTIN and language. If language=auto the best language
     * match will be returned. Best language match using preferred language
     * header and predefined secondary languages.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @param suggestions
     *            [Optional] Make product suggestions if search returns no
     *            results. Product suggestions are all returned products without
     *            a pl-id parameter. Default: false
     * @param fetchOnly
     *            [Optional] Fetch only specific keys
     * @return The identified product
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product getProductForGtin(PLYRestClient client, String gtin, String language,
            Boolean suggestions, String fetchOnly) {
        String url = "/product/" + gtin;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(suggestions)) {
            parameters.put("suggestions", suggestions.toString());
        }
        if (!StringUtils.isEmpty(fetchOnly)) {
            parameters.put("fetch_only", fetchOnly);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Product> response = client.exchange(url, HttpMethod.GET, Product.class, parameters);
        return response.getBody();
    }

    /**
     * Gets the values of a specific key.<br>
     * <br>
     * e.g.: ?key=pl-brand-name&amp;language=en returns all brands which have
     * been entered for products with the locale en.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param key
     *            The key to query the values of.
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @return All values of the specified key
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ValuesForKey getValuesForKey(PLYRestClient client, String key, String language) {
        String url = "/products/values";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(key)) {
            parameters.put("key", key);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ValuesForKey> response = client.exchange(url, HttpMethod.GET, ValuesForKey.class,
                parameters);
        return response.getBody();
    }

    /**
     * Sends a report about copyright infringements or any other problems with
     * the product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productID
     *            The identifier of the product (identifies a specific localized
     *            product, e.g.: Apple iPhone 5S (en))
     * @param report
     *            The report
     * @return The problem report object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProblemReport reportProduct(PLYRestClient client, String productID, ProblemReport report) {
        String url = "/products/report_problem";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(productID)) {
            parameters.put("product_id", productID);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProblemReport> response = client.exchangeWithObject(url, HttpMethod.POST, report,
                ProblemReport.class, parameters);
        return response.getBody();
    }

    /**
     * Searches for products. If no search parameters are present the first 50
     * products will be presented.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param query
     *            [Optional] The query may contain the name, GTIN or brand of
     *            the product. <b>ATTENTION: If the query is set all other url
     *            parameters will be ignored!</b>
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @param gtin
     *            [Optional] The GTIN (barcode) of the product
     * @param brand
     *            [Optional] The brand of the product
     * @param brandOwner
     *            [Optional] The brand owner of the product
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @param suggestions
     *            [Optional] Make product suggestions if search returns no
     *            results. Product suggestions are all returned products without
     *            a pl-id parameter. Default: false
     * @param fetchOnly
     *            [Optional] Fetch only specific keys
     * @param name
     *            [Optional] The name of the product or a substring of it.
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @param order_by
     *            [Optional] Used to sort the result-set by one or more columns.
     *            The order by parameters are <strong>seperated by a
     *            semicolon</strong>. Also you need to provide a prefix
     *            <strong>asc for ascending</strong> or <strong>desc for
     *            descending order</strong><br>
     * <br>
     *            <strong>Default:</strong> pl-prod-name_asc (Product names
     *            ascending)
     * @return Any products matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] searchProducts(PLYRestClient client, String query, Integer page,
            Integer recordsPerPage, String gtin, String brand, String brandOwner, String language,
            Boolean suggestions, String fetchOnly, String name, String categoryKey, String order_by) {
        String url = "/products";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(query)) {
            parameters.put("query", query);
        }
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(gtin)) {
            parameters.put("gtin", gtin);
        }
        if (!StringUtils.isEmpty(brand)) {
            parameters.put("brand", brand);
        }
        if (!StringUtils.isEmpty(brandOwner)) {
            parameters.put("brand_owner", brandOwner);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(suggestions)) {
            parameters.put("suggestions", suggestions.toString());
        }
        if (!StringUtils.isEmpty(fetchOnly)) {
            parameters.put("fetch_only", fetchOnly);
        }
        if (!StringUtils.isEmpty(name)) {
            parameters.put("name", name);
        }
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        if (!StringUtils.isEmpty(order_by)) {
            parameters.put("order_by", order_by);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Product[]> response = client
                .exchange(url, HttpMethod.GET, Product[].class, parameters);
        return response.getBody();
    }

    /**
     * Upvotes a specific product. By using the product ID instead of the GTIN
     * it's possible to vote for a specific localized product.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param productID
     *            The identifier of the product (identifies a specific localized
     *            product, e.g.: Apple iPhone 5S (en))
     * @return The product with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product upVoteProduct(PLYRestClient client, String productID) {
        String url = "/product/" + productID + "/up_vote";

        ResponseEntity<Product> response = client.exchange(url, HttpMethod.POST, Product.class, null);
        return response.getBody();
    }

    /**
     * Updates a specific product. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param product
     *            The product
     * @return The updated product
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product updateProduct(PLYRestClient client, String gtin, Product product) {
        String url = "/product/" + gtin;

        ResponseEntity<Product> response = client.exchangeWithObject(url, HttpMethod.PUT, product,
                Product.class, null);
        return response.getBody();
    }

}
