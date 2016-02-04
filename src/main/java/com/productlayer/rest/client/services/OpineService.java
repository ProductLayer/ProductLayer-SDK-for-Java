package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.Opine;
import com.productlayer.core.beans.reports.ProblemReport;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing opines.
 */
public class OpineService {

    /**
     * Posts an opine. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opine
     *            The opine
     * @return The newly created opine
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine createOpine(PLYRestClient client, Opine opine) {
        String url = "/opines";

        ResponseEntity<Opine> response = client.exchangeWithObject(url, HttpMethod.POST, opine, Opine.class,
                null);
        return response.getBody();
    }

    /**
     * Deletes an opine. Only the owner of the opine can delete it. If the user
     * earns points for this operation 'X-ProductLayer-User-Points' and
     * 'X-ProductLayer-User-Points-Changed' will be present in the response
     * header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @return The deleted opine
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine deleteOpine(PLYRestClient client, String opineID) {
        String url = "/opine/" + opineID;

        ResponseEntity<Opine> response = client.exchange(url, HttpMethod.DELETE, Opine.class, null);
        return response.getBody();
    }

    /**
     * Downvotes a specific opine.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @return The opine with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine downVoteOpine(PLYRestClient client, String opineID) {
        String url = "/opine/" + opineID + "/down_vote";

        ResponseEntity<Opine> response = client.exchange(url, HttpMethod.POST, Opine.class, null);
        return response.getBody();
    }

    /**
     * Gets a specific opine.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @return The identified opine
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine getOpine(PLYRestClient client, String opineID) {
        String url = "/opine/" + opineID;

        ResponseEntity<Opine> response = client.exchange(url, HttpMethod.GET, Opine.class, null);
        return response.getBody();
    }

    /**
     * Sends a report about copyright infringements or any other problems with
     * the opine.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @param report
     *            The report
     * @return The problem report object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProblemReport reportOpine(PLYRestClient client, String opineID, ProblemReport report) {
        String url = "/opine/report_problem";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(opineID)) {
            parameters.put("opine_id", opineID);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProblemReport> response = client.exchangeWithObject(url, HttpMethod.POST, report,
                ProblemReport.class, parameters);
        return response.getBody();
    }

    /**
     * Searches for an opine.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, the first page will be shown
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page,
     *            default: '200'
     * @param gtin
     *            [Optional] The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @param showFriendsOnly
     *            [Optional] Show only content created by friends (followed
     *            users), default: 'false'
     * @param nickname
     *            [Optional] The nickname of the user
     * @param userID
     *            [Optional] The identifier of the user
     * @param order_by
     *            [Optional] Used to sort the result-set by one or more columns.
     *            The order by parameters are <strong>seperated by a
     *            semicolon</strong>. Also you need to provide a prefix
     *            <strong>asc for ascending</strong> or <strong>desc for
     *            descending order</strong><br>
     * <br>
     *            <strong>Default:</strong> pl-created-time_asc (The date the
     *            opine was created ascending)
     * @return Any opines matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine[] searchOpines(PLYRestClient client, Integer page, Integer recordsPerPage,
            String gtin, String language, Boolean showFriendsOnly, String nickname, String userID,
            String order_by) {
        String url = "/opines";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(gtin)) {
            parameters.put("gtin", gtin);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(showFriendsOnly)) {
            parameters.put("show_friends_only", showFriendsOnly.toString());
        }
        if (!StringUtils.isEmpty(nickname)) {
            parameters.put("nickname", nickname);
        }
        if (!StringUtils.isEmpty(userID)) {
            parameters.put("user_id", userID);
        }
        if (!StringUtils.isEmpty(order_by)) {
            parameters.put("order_by", order_by);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Opine[]> response = client.exchange(url, HttpMethod.GET, Opine[].class, parameters);
        return response.getBody();
    }

    /**
     * Upvotes a specific opine.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param opineID
     *            The identifier of the opine
     * @return The opine with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Opine upVoteOpine(PLYRestClient client, String opineID) {
        String url = "/opine/" + opineID + "/up_vote";

        ResponseEntity<Opine> response = client.exchange(url, HttpMethod.POST, Opine.class, null);
        return response.getBody();
    }

}
