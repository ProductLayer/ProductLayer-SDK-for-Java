package com.productlayer.rest.client.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.activities.RichActivity;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.rest.client.PLYRestClient;

/**
 * Methods for retrieving activities for the logged in user.
 */
public class ActivityService {

    /**
     * Get the last 20 activities for the logged in user sorted by update
     * timestamp.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return A list of activities.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static RichActivity[] getActivities(PLYRestClient client) {
        String url = "/activities";

        ResponseEntity<RichActivity[]> response = client.exchange(url, HttpMethod.GET, RichActivity[].class,
                null);
        return response.getBody();
    }

    /**
     * Set a specific activity to read.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param activityId
     *            The identifier of the activity.
     * @return A the updated activity.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static RichActivity setActivityToRead(PLYRestClient client, String activityId) {
        String url = "/activities/" + activityId + "/read";

        ResponseEntity<RichActivity> response = client.exchange(url, HttpMethod.POST, RichActivity.class,
                null);
        return response.getBody();
    }

}
