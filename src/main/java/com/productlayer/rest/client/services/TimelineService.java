package com.productlayer.rest.client.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.BaseObject;
import com.productlayer.core.beans.ResultSetWithCursor;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for accessing timelines.
 */
public class TimelineService {

    /**
     * Gets the most recent social content posted using parameters stored in the
     * URL. The timeline is always sorted by date.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param url
     *            The URL containing the path to the endpoint and any filter
     *            parameters
     * @return The timeline and URLs to load the timeline since or until the
     *         provided timeline
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ResultSetWithCursor getTimelineFromURL(PLYRestClient client, String url) {
        ResponseEntity<BaseObject[]> response = client
                .exchange(url, HttpMethod.GET, BaseObject[].class, null);
        List<BaseObject> results = new ArrayList<BaseObject>(Arrays.asList(response.getBody()));
        ResultSetWithCursor cursor = new ResultSetWithCursor();
        cursor.setResults(results);
        if (results.size() == 0) {
            return cursor;
        }
        String newestId = results.get(0).getId();
        Map<String, String> parametersSince = new HashMap<String, String>();
        parametersSince.put("since_id", newestId);
        String resultsUrlSince = UrlHelper.replaceQueryParameterInUrl(url, parametersSince);
        cursor.setSinceThisResultsUrl(resultsUrlSince);
        String oldestId = results.get(results.size() - 1).getId();
        Map<String, String> parametersUntil = new HashMap<String, String>();
        parametersUntil.put("until_id", oldestId);
        String resultsUrlUntil = UrlHelper.replaceQueryParameterInUrl(url, parametersUntil);
        cursor.setUntilThisResultsUrl(resultsUrlUntil);
        cursor.setThisResultsUrl(url);
        return cursor;
    }

    /**
     * Gets the most recent social content posted by the signed in user. The
     * timeline is always sorted by date.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param count
     *            [Optional] The amount of results to be returned, default and
     *            maximum: '200'
     * @param sinceID
     *            [Optional] Results with an ID greater than (that is, more
     *            recent than) the specified ID
     * @param untilID
     *            [Optional] Results with an ID less than (that is, older than)
     *            the specified ID
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @param includeFriends
     *            [Optional] Show also content created by friends, default:
     *            'false'
     * @return The timeline of the currently signed in user and URLs to load the
     *         timeline since or until the provided timeline
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ResultSetWithCursor getMyTimeline(PLYRestClient client, Integer count, String sinceID,
            String untilID, Boolean showOpines, Boolean showReviews, Boolean showPictures,
            Boolean showProducts, Boolean includeFriends) {
        String url = "/timeline/me";

        String resultsUrl = "/timeline/me";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        if (!StringUtils.isEmpty(sinceID)) {
            parameters.put("since_id", sinceID);
        }
        if (!StringUtils.isEmpty(untilID)) {
            parameters.put("until_id", untilID);
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
        if (!StringUtils.isEmpty(includeFriends)) {
            parameters.put("include_friends", includeFriends.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<BaseObject[]> response = client.exchange(url, HttpMethod.GET, BaseObject[].class,
                parameters);
        List<BaseObject> results = new ArrayList<BaseObject>(Arrays.asList(response.getBody()));
        ResultSetWithCursor cursor = new ResultSetWithCursor();
        cursor.setResults(results);
        if (results.size() == 0) {
            return cursor;
        }
        String newestId = results.get(0).getId();
        Map<String, String> parametersSince = new HashMap<String, String>(parameters);
        parametersSince.put("since_id", newestId);
        String resultsUrlSince = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersSince);
        cursor.setSinceThisResultsUrl(resultsUrlSince);
        String oldestId = results.get(results.size() - 1).getId();
        Map<String, String> parametersUntil = new HashMap<String, String>(parameters);
        parametersUntil.put("until_id", oldestId);
        String resultsUrlUntil = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersUntil);
        cursor.setUntilThisResultsUrl(resultsUrlUntil);
        resultsUrl = UrlHelper.addQueryParameterToUrl(resultsUrl, parameters);
        cursor.setThisResultsUrl(resultsUrl);
        return cursor;
    }

    /**
     * Gets the most recent social content for a product. The timeline is always
     * sorted by date.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param count
     *            [Optional] The amount of results to be returned, default and
     *            maximum: '200'
     * @param sinceID
     *            [Optional] Results with an ID greater than (that is, more
     *            recent than) the specified ID
     * @param untilID
     *            [Optional] Results with an ID less than (that is, older than)
     *            the specified ID
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @param showFriendsOnly
     *            [Optional] Show only content created by friends (followed
     *            users), default: 'false'
     * @return The timeline and URLs to load the timeline since or until the
     *         provided timeline.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ResultSetWithCursor getProductTimeline(PLYRestClient client, String gtin, Integer count,
            String sinceID, String untilID, Boolean showOpines, Boolean showReviews, Boolean showPictures,
            Boolean showProducts, Boolean showFriendsOnly) {
        String url = "/timeline/product/" + gtin;

        String resultsUrl = "/timeline/product/" + gtin;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        if (!StringUtils.isEmpty(sinceID)) {
            parameters.put("since_id", sinceID);
        }
        if (!StringUtils.isEmpty(untilID)) {
            parameters.put("until_id", untilID);
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
        if (!StringUtils.isEmpty(showFriendsOnly)) {
            parameters.put("show_friends_only", showFriendsOnly.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<BaseObject[]> response = client.exchange(url, HttpMethod.GET, BaseObject[].class,
                parameters);
        List<BaseObject> results = new ArrayList<BaseObject>(Arrays.asList(response.getBody()));
        ResultSetWithCursor cursor = new ResultSetWithCursor();
        cursor.setResults(results);
        if (results.size() == 0) {
            return cursor;
        }
        String newestId = results.get(0).getId();
        Map<String, String> parametersSince = new HashMap<String, String>(parameters);
        parametersSince.put("since_id", newestId);
        String resultsUrlSince = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersSince);
        cursor.setSinceThisResultsUrl(resultsUrlSince);
        String oldestId = results.get(results.size() - 1).getId();
        Map<String, String> parametersUntil = new HashMap<String, String>(parameters);
        parametersUntil.put("until_id", oldestId);
        String resultsUrlUntil = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersUntil);
        cursor.setUntilThisResultsUrl(resultsUrlUntil);
        resultsUrl = UrlHelper.addQueryParameterToUrl(resultsUrl, parameters);
        cursor.setThisResultsUrl(resultsUrl);
        return cursor;
    }

    /**
     * Gets the most recent social content posted. The timeline is always sorted
     * by date.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @param brand
     *            [Optional] The brand of the product
     * @param brandOwner
     *            [Optional] The brand owner of the product
     * @param count
     *            [Optional] The amount of results to be returned, default and
     *            maximum: '200'
     * @param sinceID
     *            [Optional] Results with an ID greater than (that is, more
     *            recent than) the specified ID
     * @param untilID
     *            [Optional] Results with an ID less than (that is, older than)
     *            the specified ID
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @param showFriendsOnly
     *            [Optional] Show only content created by friends (followed
     *            users), default: 'false'
     * @return The timeline and URLs to load the timeline since or until the
     *         provided timeline.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ResultSetWithCursor getTimeline(PLYRestClient client, String categoryKey, String brand,
            String brandOwner, Integer count, String sinceID, String untilID, Boolean showOpines,
            Boolean showReviews, Boolean showPictures, Boolean showProducts, Boolean showFriendsOnly) {
        String url = "/timeline";

        String resultsUrl = "/timeline";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        if (!StringUtils.isEmpty(brand)) {
            parameters.put("brand", brand);
        }
        if (!StringUtils.isEmpty(brandOwner)) {
            parameters.put("brand_owner", brandOwner);
        }
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        if (!StringUtils.isEmpty(sinceID)) {
            parameters.put("since_id", sinceID);
        }
        if (!StringUtils.isEmpty(untilID)) {
            parameters.put("until_id", untilID);
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
        if (!StringUtils.isEmpty(showFriendsOnly)) {
            parameters.put("show_friends_only", showFriendsOnly.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<BaseObject[]> response = client.exchange(url, HttpMethod.GET, BaseObject[].class,
                parameters);
        List<BaseObject> results = new ArrayList<BaseObject>(Arrays.asList(response.getBody()));
        ResultSetWithCursor cursor = new ResultSetWithCursor();
        cursor.setResults(results);
        if (results.size() == 0) {
            return cursor;
        }
        String newestId = results.get(0).getId();
        Map<String, String> parametersSince = new HashMap<String, String>(parameters);
        parametersSince.put("since_id", newestId);
        String resultsUrlSince = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersSince);
        cursor.setSinceThisResultsUrl(resultsUrlSince);
        String oldestId = results.get(results.size() - 1).getId();
        Map<String, String> parametersUntil = new HashMap<String, String>(parameters);
        parametersUntil.put("until_id", oldestId);
        String resultsUrlUntil = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersUntil);
        cursor.setUntilThisResultsUrl(resultsUrlUntil);
        resultsUrl = UrlHelper.addQueryParameterToUrl(resultsUrl, parameters);
        cursor.setThisResultsUrl(resultsUrl);
        return cursor;
    }

    /**
     * Gets the most recent social content posted by a user ID. The timeline is
     * always sorted by date.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param count
     *            [Optional] The amount of results to be returned, default and
     *            maximum: '200'
     * @param sinceID
     *            [Optional] Results with an ID greater than (that is, more
     *            recent than) the specified ID
     * @param untilID
     *            [Optional] Results with an ID less than (that is, older than)
     *            the specified ID
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @param includeFriends
     *            [Optional] Show also content created by friends, default:
     *            'false'
     * @return The timeline of a user and URLs to load the timeline since or
     *         until the provided timeline
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ResultSetWithCursor getUserTimeline(PLYRestClient client, String userID, Integer count,
            String sinceID, String untilID, Boolean showOpines, Boolean showReviews, Boolean showPictures,
            Boolean showProducts, Boolean includeFriends) {
        String url = "/timeline/user/" + userID;

        String resultsUrl = "/timeline/user/" + userID;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        if (!StringUtils.isEmpty(sinceID)) {
            parameters.put("since_id", sinceID);
        }
        if (!StringUtils.isEmpty(untilID)) {
            parameters.put("until_id", untilID);
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
        if (!StringUtils.isEmpty(includeFriends)) {
            parameters.put("include_friends", includeFriends.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<BaseObject[]> response = client.exchange(url, HttpMethod.GET, BaseObject[].class,
                parameters);
        List<BaseObject> results = new ArrayList<BaseObject>(Arrays.asList(response.getBody()));
        ResultSetWithCursor cursor = new ResultSetWithCursor();
        cursor.setResults(results);
        if (results.size() == 0) {
            return cursor;
        }
        String newestId = results.get(0).getId();
        Map<String, String> parametersSince = new HashMap<String, String>(parameters);
        parametersSince.put("since_id", newestId);
        String resultsUrlSince = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersSince);
        cursor.setSinceThisResultsUrl(resultsUrlSince);
        String oldestId = results.get(results.size() - 1).getId();
        Map<String, String> parametersUntil = new HashMap<String, String>(parameters);
        parametersUntil.put("until_id", oldestId);
        String resultsUrlUntil = UrlHelper.addQueryParameterToUrl(resultsUrl, parametersUntil);
        cursor.setUntilThisResultsUrl(resultsUrlUntil);
        resultsUrl = UrlHelper.addQueryParameterToUrl(resultsUrl, parameters);
        cursor.setThisResultsUrl(resultsUrl);
        return cursor;
    }

}
