package com.productlayer.rest.client.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.Count;
import com.productlayer.core.beans.Product;
import com.productlayer.core.beans.ResultMessageWithCount;
import com.productlayer.core.beans.User;
import com.productlayer.core.beans.UserChangePassword;
import com.productlayer.core.beans.UserEmail;
import com.productlayer.core.beans.ranking.RankingResults;
import com.productlayer.core.beans.reports.ProblemReport;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for managing users.
 */
public class UserService {

    /**
     * Logs in the user using basic authentication.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param username
     *            The username (will be set in {@code client} on success)
     * @param password
     *            The password (will be set in {@code client} on success)
     * @param rememberMe
     *            Whether to remember the login (a token will be received and
     *            set in {@code client})
     * @return The logged in user or null if the login failed
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User login(PLYRestClient client, String username, String password, Boolean rememberMe) {
        client.setUsername(username);
        client.setPassword(password);

        String url = "login";
        if (rememberMe) {
            url += "?remember_me=true";
        }

        try {
            ResponseEntity<User> response = client.exchange(url, HttpMethod.POST, User.class, null);
            if (response.getStatusCode() == HttpStatus.OK) {
                List<String> sessionCookies = response.getHeaders().get("Set-Cookie");
                if (sessionCookies != null && !sessionCookies.isEmpty()) {
                    for (String cookie : sessionCookies) {
                        // save only one cookie for authentication
                        if (cookie.startsWith("JSESSIONID") && !rememberMe) {
                            client.setSession(cookie);
                            client.setToken(null);
                        } else if (cookie.startsWith("X-ProductLayer-Auth-Token") && rememberMe) {
                            client.setToken(cookie);
                            client.setSession(null);
                        }
                    }

                }
                return response.getBody();
            }
        } catch (PLYHttpException e) {

        }

        client.setUsername(null);
        client.setPassword(null);
        return null;
    }

    /**
     * Logs in the user using an authorization token.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param token
     *            The authorization token (will be set in {@code client} as
     *            session or token on success)
     * @param rememberMe
     *            Whether to remember the login
     * @return The logged in user or null if the login failed
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User login(PLYRestClient client, String token, Boolean rememberMe) {
        client.setToken("X-ProductLayer-Auth-Token=" + token);

        String url = "login";

        try {
            ResponseEntity<User> response = client.exchange(url, HttpMethod.POST, User.class, null);
            if (response.getStatusCode() == HttpStatus.OK) {
                List<String> sessionCookies = response.getHeaders().get("Set-Cookie");
                if (sessionCookies != null && !sessionCookies.isEmpty()) {
                    for (String cookie : sessionCookies) {
                        // save only one cookie for authentication
                        if (cookie.startsWith("JSESSIONID") && !rememberMe) {
                            client.setSession(cookie);
                            client.setToken(null);
                        } else if (cookie.startsWith("X-ProductLayer-Auth-Token") && rememberMe) {
                            client.setToken(cookie);
                            client.setSession(null);
                        }
                    }

                }
                return response.getBody();
            }
        } catch (PLYHttpException e) {

        }

        client.setUsername(null);
        client.setPassword(null);
        client.setSession(null);
        client.setToken(null);
        return null;
    }

    /**
     * Logs out the current user.
     * 
     * Clears username/password/session/token in {@code client}.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static void logout(PLYRestClient client) {
        String url = "logout";

        client.exchange(url, HttpMethod.POST, String.class, null);

        client.setToken(null);
        client.setSession(null);
        client.setUsername(null);
        client.setPassword(null);
    }

    /**
     * Checks if the active session is valid and if the user is signed in.
     * 
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return True if signed in, false else
     */
    public static boolean isSignedIn(PLYRestClient client) {
        String url = "signedin";

        try {
            ResponseEntity<String> status = client.exchange(url, HttpMethod.GET, String.class, null);
            if (status.getBody().equalsIgnoreCase("false")) {
                client.setSession(null);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Changes the password of the current user. An email will be sent to the
     * user, so a compromised account can be detected. The user will be
     * automatically logged out and needs to login with the new password. <br>
     * There are 2 ways of updating the password:
     * <ul>
     * <li>the user must be logged in</li>
     * <li>a reset token must be present</li>
     * </ul>
     * The reset token will be sent via email if the user doesn't know the
     * password.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param resetToken
     *            [Optional] The user must be logged in or the reset token must
     *            be set to reset the password.
     * @param oldPassword
     *            The current password
     * @param newPassword
     *            The new password
     * @return The user if found
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User changePassword(PLYRestClient client, String resetToken, String oldPassword,
            String newPassword) {
        UserChangePassword changePassword = new UserChangePassword();
        changePassword.setOldPassword(oldPassword);
        changePassword.setNewPassword(newPassword);
        return changePassword(client, resetToken, changePassword);
    }

    /**
     * Changes the password of the current user. An email will be sent to the
     * user, so a compromised account can be detected. The user will be
     * automatically logged out and needs to login with the new password. <br>
     * There are 2 ways of updating the password:
     * <ul>
     * <li>the user must be logged in</li>
     * <li>a reset token must be present</li>
     * </ul>
     * The reset token will be sent via email if the user doesn't know the
     * password.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param resetToken
     *            [Optional] The user must be logged in or the reset token must
     *            be set to reset the password.
     * @param changePassword
     *            The changePassword
     * @return The user if found
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User changePassword(PLYRestClient client, String resetToken,
            UserChangePassword changePassword) {
        String url = "/user/change_password";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(resetToken)) {
            parameters.put("reset_token", resetToken);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User> response = client.exchangeWithObject(url, HttpMethod.POST, changePassword,
                User.class, parameters);
        return response.getBody();
    }

    /**
     * Registers a new user. Minimum information which must be provided is
     * (nickname, email).
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param user
     *            The user
     * @return The newly created user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User createUser(PLYRestClient client, User user) {
        String url = "/users";

        ResponseEntity<User> response = client.exchangeWithObject(url, HttpMethod.POST, user, User.class,
                null);
        return response.getBody();
    }

    /**
     * Disable email newsletter with the token from a newsletter email.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param token
     *            The one time token from the email can only be used to disable
     *            the email newsletter.
     * @return OK if success.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String disableEmailNewsletterViaToken(PLYRestClient client, String token) {
        String url = "/user/setting/disable_email_newsletter";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(token)) {
            parameters.put("token", token);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, String.class, parameters);
        return response.getBody();
    }

    /**
     * Disable email notifications with the token from a notification email.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param token
     *            The one time token from the email can only be used to disable
     *            the email notifications.
     * @return OK if success.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String disableEmailNotificationViaToken(PLYRestClient client, String token) {
        String url = "/user/setting/disable_email_notification";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(token)) {
            parameters.put("token", token);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, String.class, parameters);
        return response.getBody();
    }

    /**
     * Enable or disable email newsletter for the user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param enable
     *            Boolean for enabling or disabling the email newsletter
     * @return The user if found.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User enableOrDisableEmailNewsletter(PLYRestClient client, Boolean enable) {
        String url = "/user/setting/email_newsletter";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(enable)) {
            parameters.put("enable", enable.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User> response = client.exchange(url, HttpMethod.PUT, User.class, parameters);
        return response.getBody();
    }

    /**
     * Enable or disable email notification for the user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param enable
     *            Boolean for enabling or disabling the email notification
     * @return The user if found.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User enableOrDisableEmailNotification(PLYRestClient client, Boolean enable) {
        String url = "/user/setting/email_notification";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(enable)) {
            parameters.put("enable", enable.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User> response = client.exchange(url, HttpMethod.PUT, User.class, parameters);
        return response.getBody();
    }

    /**
     * Find friends from other connected social providers which have already a
     * productlayer account .
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param facebook
     *            [Optional] Find facebook friends which are already here.
     *            Default: true
     * @param twitter
     *            [Optional] Find twitter friends which are already here.
     *            Default: true
     * @return The found friends from other social providers.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User[] findFriendsFromOtherSocialNetworks(PLYRestClient client, Boolean facebook,
            Boolean twitter) {
        String url = "/user/me/find_friends";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(facebook)) {
            parameters.put("facebook", facebook.toString());
        }
        if (!StringUtils.isEmpty(twitter)) {
            parameters.put("twitter", twitter.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User[]> response = client.exchange(url, HttpMethod.GET, User[].class, parameters);
        return response.getBody();
    }

    /**
     * Follows a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param followUser
     *            The nickname of the user
     * @return The updated user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User followUser(PLYRestClient client, String followUser) {
        String url = "/user/follow";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(followUser)) {
            parameters.put("nickname", followUser);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User> response = client.exchange(url, HttpMethod.POST, User.class, parameters);
        return response.getBody();
    }

    /**
     * Gets GTINs of all products that have been downvoted by a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @return GTINs of any found products
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getDownVotedGTINsFromUser(PLYRestClient client, String userID, String categoryKey) {
        String url = "/user/" + userID + "/down_voted/products/ids";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets the count of all products that have been downvoted by a user. If a
     * category key is present, only the count for the specified category and
     * subcategories is returned.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @return The product count
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Count getDownVotedProductCountFromUser(PLYRestClient client, String userID,
            String categoryKey) {
        String url = "/user/" + userID + "/down_voted/products/count";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ResultMessageWithCount> response = client.exchange(url, HttpMethod.GET,
                ResultMessageWithCount.class, parameters);
        return (Count) response.getBody().getResult();
    }

    /**
     * Get all products which have been down-voted by the user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @param fetchOnly
     *            [Optional] Fetch only specific keys
     * @return Array of products
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] getDownVotedProductsFromUser(PLYRestClient client, String userID,
            String categoryKey, String language, String fetchOnly) {
        String url = "/user/" + userID + "/down_voted/products";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(fetchOnly)) {
            parameters.put("fetch_only", fetchOnly);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Product[]> response = client
                .exchange(url, HttpMethod.GET, Product[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets the followed users IDs of the specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param nicknameOrId
     *            The nickname or ID of the user
     * @return Any followed user IDs
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getFollowedUserIDs(PLYRestClient client, String nicknameOrId) {
        String url = "/user/" + nicknameOrId + "/following_ids";

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, null);
        return response.getBody();
    }

    /**
     * Gets the followed users of a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, paging is disabled
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page
     * @param nicknameOrId
     *            The nickname or ID of the user
     * @param orderBy
     *            [Optional] Used to sort the result-set by one or more columns.
     *            The order by parameters are <strong>seperated by a
     *            semicolon</strong>. Also you need to provide a prefix
     *            <strong>asc for ascending</strong> or <strong>desc for
     *            descending order</strong><br>
     * <br>
     *            <strong>Default:</strong> pl-usr-points_desc (User Score
     *            descending)
     * @return Any followed users
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User[] getFollowedUsers(PLYRestClient client, Integer page, Integer recordsPerPage,
            String nicknameOrId, String orderBy) {
        String url = "/user/" + nicknameOrId + "/following";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(orderBy)) {
            parameters.put("order_by", orderBy);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User[]> response = client.exchange(url, HttpMethod.GET, User[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets the follower IDs of a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param nicknameOrId
     *            The nickname or ID of the user
     * @return Any user IDs following the specified user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getFollowingUserIDs(PLYRestClient client, String nicknameOrId) {
        String url = "/user/" + nicknameOrId + "/follower_ids";

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, null);
        return response.getBody();
    }

    /**
     * Gets the followers of a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param page
     *            [Optional] The page to be displayed starting with 0 - if no
     *            page has been provided, paging is disabled
     * @param recordsPerPage
     *            [Optional] The amount of items to be displayed per page
     * @param nicknameOrId
     *            The nickname or ID of the user
     * @param orderBy
     *            [Optional] Used to sort the result-set by one or more columns.
     *            The order by parameters are <strong>seperated by a
     *            semicolon</strong>. Also you need to provide a prefix
     *            <strong>asc for ascending</strong> or <strong>desc for
     *            descending order</strong><br>
     * <br>
     *            <strong>Default:</strong> pl-usr-points_desc (User Score
     *            descending)
     * @return Any users following the specified user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User[] getFollowingUsers(PLYRestClient client, Integer page, Integer recordsPerPage,
            String nicknameOrId, String orderBy) {
        String url = "/user/" + nicknameOrId + "/follower";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(page)) {
            parameters.put("page", page.toString());
        }
        if (!StringUtils.isEmpty(recordsPerPage)) {
            parameters.put("records_per_page", recordsPerPage.toString());
        }
        if (!StringUtils.isEmpty(orderBy)) {
            parameters.put("order_by", orderBy);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User[]> response = client.exchange(url, HttpMethod.GET, User[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets the top scoring users within a time range.
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
     * @param showOpines
     *            [Optional] Display opines, default: 'true'
     * @param showReviews
     *            [Optional] Display reviews, default: 'true'
     * @param showPictures
     *            [Optional] Display uploaded images, default: 'true'
     * @param showProducts
     *            [Optional] Display created/updated products, default: 'true'
     * @return The top users contained within a ranking results object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static RankingResults<User> getTopScorers(PLYRestClient client, Date from_date, Date to_date,
            int count, Boolean showOpines, Boolean showReviews, Boolean showPictures, Boolean showProducts) {
        String url = "/users/top_scorer";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(from_date)) {
            parameters.put("from_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from_date));
        }
        if (!StringUtils.isEmpty(to_date)) {
            parameters.put("to_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to_date));
        }
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", String.valueOf(count));
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

        ResponseEntity<RankingResults<User>> response = client.exchange(url, HttpMethod.GET,
                new ParameterizedTypeReference<RankingResults<User>>() {
                }, parameters);
        return response.getBody();
    }

    /**
     * Gets GTINs of all products that have been upvoted by a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @return GTINs of any found products
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static String[] getUpVotedGTINsFromUser(PLYRestClient client, String userID, String categoryKey) {
        String url = "/user/" + userID + "/up_voted/products/ids";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<String[]> response = client.exchange(url, HttpMethod.GET, String[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets the count of all products that have been upvoted by a user. If a
     * category key is present, only the count for the specified category and
     * subcategories is returned.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @return The product count
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Count getUpVotedProductCountFromUser(PLYRestClient client, String userID, String categoryKey) {
        String url = "/user/" + userID + "/up_voted/products/count";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ResultMessageWithCount> response = client.exchange(url, HttpMethod.GET,
                ResultMessageWithCount.class, parameters);
        return (Count) response.getBody().getResult();
    }

    /**
     * Get all products which have been up-voted by the user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userID
     *            The identifier of the user
     * @param categoryKey
     *            [Optional] The category key starting with 'pl-prod-cat-',
     *            e.g.: pl-prod-cat-books
     * @param language
     *            [Optional] The preferred language (e.g.: 'en' or 'de')
     * @param fetchOnly
     *            [Optional] Fetch only specific keys
     * @return Array of products
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Product[] getUpVotedProductsFromUser(PLYRestClient client, String userID,
            String categoryKey, String language, String fetchOnly) {
        String url = "/user/" + userID + "/up_voted/products";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(categoryKey)) {
            parameters.put("category_key", categoryKey);
        }
        if (!StringUtils.isEmpty(language)) {
            parameters.put("language", language);
        }
        if (!StringUtils.isEmpty(fetchOnly)) {
            parameters.put("fetch_only", fetchOnly);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Product[]> response = client
                .exchange(url, HttpMethod.GET, Product[].class, parameters);
        return response.getBody();
    }

    /**
     * Gets a specific user by nickname or ID. The nickname can change so the ID
     * should be used to request user data.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param nicknameOrID
     *            The nickname or ID of the user
     * @return The identified user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User getUserByNicknameOrID(PLYRestClient client, String nicknameOrID) {
        String url = "/user/" + nicknameOrID;

        ResponseEntity<User> response = client.exchange(url, HttpMethod.GET, User.class, null);
        return response.getBody();
    }

    /**
     * Gets the overall user count or the count for a specific timeframe.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param from_date
     *            [Optional] Start date, format: yyyy-MM-dd HH:mm:ss
     * @param to_date
     *            [Optional] End date, format: yyyy-MM-dd HH:mm:ss
     * @return The user count
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Count getUserCount(PLYRestClient client, Date from_date, Date to_date) {
        String url = "/users/count";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(from_date)) {
            parameters.put("from_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from_date));
        }
        if (!StringUtils.isEmpty(to_date)) {
            parameters.put("to_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to_date));
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ResultMessageWithCount> response = client.exchange(url, HttpMethod.GET,
                ResultMessageWithCount.class, parameters);
        return (Count) response.getBody().getResult();
    }

    /**
     * Gets the points of a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userId
     *            The identifier of the user
     * @return The user's points
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Long getUserPoints(PLYRestClient client, String userId) {
        String url = "/user/" + userId + "/points";

        ResponseEntity<Map<String, Long>> response = client.exchange(url, HttpMethod.GET,
                new ParameterizedTypeReference<Map<String, Long>>() {
                }, null);
        return Long.parseLong(response.getBody().get("points").toString());
    }

    /**
     * Get a user's scoring history within a time range.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userId
     *            The identifier of the user
     * @param from_date
     *            [Optional] Start date, format: yyyy-MM-dd
     * @param to_date
     *            [Optional] End date, format: yyyy-MM-dd
     * @return The user's scoring history
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static Long getUserPointsHistory(PLYRestClient client, String userId, Date from_date, Date to_date) {
        String url = "/user/" + userId + "/points_history";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(from_date)) {
            parameters.put("from_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(from_date));
        }
        if (!StringUtils.isEmpty(to_date)) {
            parameters.put("to_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(to_date));
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<Map<String, Long>> response = client.exchange(url, HttpMethod.GET,
                new ParameterizedTypeReference<Map<String, Long>>() {
                }, parameters);
        return Long.parseLong(response.getBody().get("points").toString());
    }

    /**
     * Sends a report about a user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param userId
     *            The identifier of the user
     * @param report
     *            The report
     * @return The problem report object
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ProblemReport reportUser(PLYRestClient client, String userId, ProblemReport report) {
        String url = "/users/report_problem";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(userId)) {
            parameters.put("user_id", userId);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ProblemReport> response = client.exchangeWithObject(url, HttpMethod.POST, report,
                ProblemReport.class, parameters);
        return response.getBody();
    }

    /**
     * Generates a new password for the user. The password will be sent to the
     * user's email address.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param user
     *            The user
     * @return The user if found
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User resetPasswordViaEmail(PLYRestClient client, UserEmail user) {
        String url = "/user/lost_password";

        ResponseEntity<User> response = client.exchangeWithObject(url, HttpMethod.POST, user, User.class,
                null);
        return response.getBody();
    }

    /**
     * Searches for a user with a simple text search.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param query
     *            The query may contain the email, nickname, first name and last
     *            name of the user.
     * @return Any users matching the text search
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User[] searchUsers(PLYRestClient client, String query) {
        String url = "/users";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(query)) {
            parameters.put("query", query);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User[]> response = client.exchange(url, HttpMethod.GET, User[].class, parameters);
        return response.getBody();
    }

    /**
     * Unfollows a specific user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param followUser
     *            The nickname of the user
     * @return The updated user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User unfollowUser(PLYRestClient client, String followUser) {
        String url = "/user/unfollow";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(followUser)) {
            parameters.put("nickname", followUser);
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<User> response = client.exchange(url, HttpMethod.POST, User.class, parameters);
        return response.getBody();
    }

    /**
     * Updates a specific user. Users can only update their own account.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param user
     *            The user
     * @return The updated user
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static User updateUser(PLYRestClient client, User user) {
        String url = "/user";

        ResponseEntity<User> response = client
                .exchangeWithObject(url, HttpMethod.PUT, user, User.class, null);
        return response.getBody();
    }

}
