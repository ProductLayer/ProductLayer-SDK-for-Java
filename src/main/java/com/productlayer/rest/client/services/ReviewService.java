package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.Review;
import com.productlayer.core.beans.reports.ProblemReport;
import com.productlayer.core.beans.summary.FullReviewStatistics;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing reviews of products.
 */
public class ReviewService {

    /**
     * Creates a new review for a product. If the user earns points for this
     * operation 'X-ProductLayer-User-Points' and
     * 'X-ProductLayer-User-Points-Changed' will be present in the response
     * header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param review
     *            The review
     * @return The newly created review
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review createReview(PLYRestClient client, Review review) {
        return createReview(client, review.getProductGtin(), review);
    }

    /**
     * Updates an existing review of a product. Only the title, body, rating and
     * language can be changed. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param review
     *            The review
     * @return The updated review
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review updateReview(PLYRestClient client, Review review) {
        return updateReview(client, review.getId(), review);
    }

    /**
     * Creates a new review for a product. If the user earns points for this
     * operation 'X-ProductLayer-User-Points' and
     * 'X-ProductLayer-User-Points-Changed' will be present in the response
     * header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param review
     *            The review
     * @return The newly created review
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review createReview(PLYRestClient client, String gtin, Review review) {
        String url = "/product/" + gtin + "/review";

        ResponseEntity<Review> response = client.exchangeWithObject(url, HttpMethod.POST, review,
                Review.class, null);
        return response.getBody();
    }

    /**
     * Downvotes a specific review.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param reviewID
     *            The identifier of the review
     * @return The review with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review downVoteReview(PLYRestClient client, String reviewID) {
        String url = "/review/" + reviewID + "/down_vote";

        ResponseEntity<Review> response = client.exchange(url, HttpMethod.POST, Review.class, null);
        return response.getBody();
    }

    /**
     * Gets a review by ID.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param reviewID
     *            The identifier of the review
     * @return The identified review
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review getReview(PLYRestClient client, String reviewID) {
        String url = "/review/" + reviewID;

        ResponseEntity<Review> response = client.exchange(url, HttpMethod.GET, Review.class, null);
        return response.getBody();
    }

    /**
     * Gets the review statistics for the specified GTIN and language.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param gtin
     *            The GTIN (barcode) of the product
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return The review statistics
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static FullReviewStatistics getReviewStatistics(PLYRestClient client, String gtin, String language) {
        String url = "/reviews/statistics";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(gtin)) {
            parameters.put("gtin", gtin);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<FullReviewStatistics> response = client.exchange(url, HttpMethod.GET,
                FullReviewStatistics.class, parameters);
        return response.getBody();
    }

    /**
     * Sends a report about copyright infringements or any other problems with
     * the review.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param reviewID
     *            The identifier of the review
     * @param report
     *            The report
     * @return The problem report object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProblemReport reportReview(PLYRestClient client, String reviewID, ProblemReport report) {
        String url = "/reviews/report_problem";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(reviewID)) {
            parameters.put("review_id", reviewID);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProblemReport> response = client.exchangeWithObject(url, HttpMethod.POST, report,
                ProblemReport.class, parameters);
        return response.getBody();
    }

    /**
     * Searches for reviews.
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
     *            [Optional] The preferred language (e.g.: 'en' or 'de'),
     *            default: 'en'
     * @param nickname
     *            [Optional] The nickname of the user
     * @param userID
     *            [Optional] The identifier of the user
     * @param rating
     *            [Optional] The rating between 0 and 5 stars
     * @param order_by
     *            [Optional] Used to sort the result-set by one or more columns.
     *            The order by parameters are <strong>seperated by a
     *            semicolon</strong>. Also you need to provide a prefix
     *            <strong>asc for ascending</strong> or <strong>desc for
     *            descending order</strong><br>
     * <br>
     *            <strong>Default:</strong> pl-upd-time_desc,
     *            pl-created-time_desc (Newly inserted and updated reviews
     *            first.)
     * @return Any reviews matching the specified criteria
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review[] searchReviews(PLYRestClient client, Integer page, Integer recordsPerPage,
            String gtin, String language, String nickname, String userID, Integer rating, String order_by) {
        String url = "/reviews";

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
        if (!StringUtils.isEmpty(nickname)) {
            parameters.put("nickname", nickname);
        }
        if (!StringUtils.isEmpty(userID)) {
            parameters.put("user_id", userID);
        }
        if (!StringUtils.isEmpty(rating)) {
            parameters.put("rating", rating.toString());
        }
        if (!StringUtils.isEmpty(order_by)) {
            parameters.put("order_by", order_by);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Review[]> response = client.exchange(url, HttpMethod.GET, Review[].class, parameters);
        return response.getBody();
    }

    /**
     * Upvotes a specific review.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param reviewID
     *            The identifier of the review
     * @return The review with the new vote score
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review upVoteReview(PLYRestClient client, String reviewID) {
        String url = "/review/" + reviewID + "/up_vote";

        ResponseEntity<Review> response = client.exchange(url, HttpMethod.POST, Review.class, null);
        return response.getBody();
    }

    /**
     * Updates an existing review of a product. Only the title, body, rating and
     * language can be changed. If the user earns points for this operation
     * 'X-ProductLayer-User-Points' and 'X-ProductLayer-User-Points-Changed'
     * will be present in the response header.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param reviewID
     *            The identifier of the review
     * @param review
     *            The review
     * @return The updated review
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Review updateReview(PLYRestClient client, String reviewID, Review review) {
        String url = "/review/" + reviewID;

        ResponseEntity<Review> response = client.exchangeWithObject(url, HttpMethod.PUT, review,
                Review.class, null);
        return response.getBody();
    }

}
