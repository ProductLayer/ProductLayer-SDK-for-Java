package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.gamification.Achievement;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for achievements.
 */
public class GamificationService {

    /**
     * Gets localized achievement for key.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param key
     *            The key of the achievement. e.g.: pl-achv-first_photo
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return The localized achievement
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Achievement getAchievementForKey(PLYRestClient client, String key, String language) {
        String url = "/achievements/" + key;

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Achievement> response = client.exchange(url, HttpMethod.GET, Achievement.class,
                parameters);
        return response.getBody();
    }

    /**
     * Gets all achievements unlocked by the given user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userId
     *            The identifier of the user
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @return Any achievements unlocked by the user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Achievement[] getAchievementForUser(PLYRestClient client, String userId, String language) {
        String url = "/users/" + userId + "/achievements";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Achievement[]> response = client.exchange(url, HttpMethod.GET, Achievement[].class,
                parameters);
        return response.getBody();
    }

}
