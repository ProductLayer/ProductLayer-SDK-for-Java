package com.productlayer.rest.client.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.productlayer.core.beans.chat.ChatGroup;
import com.productlayer.core.beans.chat.ChatMessage;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.utils.StringUtils;
import com.productlayer.rest.client.PLYRestClient;
import com.productlayer.rest.client.helper.UrlHelper;

/**
 * Methods for sending/receiving messages to/from other users.
 */
public class ChatService {

    /**
     * Add users to the chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @param userIds
     *            The userIds
     * @return The updated chat group.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup addUserToChatGroup(PLYRestClient client, String chatGroupId, List<String> userIds) {
        String url = "/chat_groups/" + chatGroupId + "/users";

        ResponseEntity<ChatGroup> response = client.exchangeWithObject(url, HttpMethod.POST, userIds,
                ChatGroup.class, null);
        return response.getBody();
    }

    /**
     * Get a specific chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @param body
     *            The body
     * @return The updated chat group.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup changeChatGroupTitle(PLYRestClient client, String chatGroupId,
            HashMap<String, String> body) {
        String url = "/chat_groups/" + chatGroupId + "/title";

        ResponseEntity<ChatGroup> response = client.exchangeWithObject(url, HttpMethod.PUT, body,
                ChatGroup.class, null);
        return response.getBody();
    }

    /**
     * Create a new chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param group
     *            The group
     * @return The created chat group.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup createChatGroups(PLYRestClient client, ChatGroup group) {
        String url = "/chat_groups";

        ResponseEntity<ChatGroup> response = client.exchangeWithObject(url, HttpMethod.POST, group,
                ChatGroup.class, null);
        return response.getBody();
    }

    /**
     * Get the chat groups for the logged in user.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @return A list of chat groups for the logged in user.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup[] getChatGroups(PLYRestClient client) {
        String url = "/chat_groups";

        ResponseEntity<ChatGroup[]> response = client.exchange(url, HttpMethod.GET, ChatGroup[].class, null);
        return response.getBody();
    }

    /**
     * Get chat messages from the chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @param sinceTimestamp
     *            [Optional] Request entries since this timestamp in ms since
     *            01.01.1970.
     * @param untilTimestamp
     *            [Optional] Request entries until this timestamp in ms since
     *            01.01.1970.
     * @param count
     *            [Optional] The amount of results to be returned, default and
     *            maximum: '200'
     * @return The chat messages.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatMessage[] getChatMessagesFromGroup(PLYRestClient client, String chatGroupId,
            Long sinceTimestamp, Long untilTimestamp, Integer count) {
        String url = "/chat_groups/" + chatGroupId + "/messages";

        Map<String, String> parameters = new HashMap<String, String>();
        if (!StringUtils.isEmpty(sinceTimestamp)) {
            parameters.put("since_timestamp", sinceTimestamp.toString());
        }
        if (!StringUtils.isEmpty(untilTimestamp)) {
            parameters.put("until_timestamp", untilTimestamp.toString());
        }
        if (!StringUtils.isEmpty(count)) {
            parameters.put("count", count.toString());
        }
        url = UrlHelper.addQueryParameterPlaceholderToUrl(url, parameters);

        ResponseEntity<ChatMessage[]> response = client.exchange(url, HttpMethod.GET, ChatMessage[].class,
                parameters);
        return response.getBody();
    }

    /**
     * Get a specific chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @return The chat group for the specified id.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup getSpecificChatGroup(PLYRestClient client, String chatGroupId) {
        String url = "/chat_groups/" + chatGroupId;

        ResponseEntity<ChatGroup> response = client.exchange(url, HttpMethod.GET, ChatGroup.class, null);
        return response.getBody();
    }

    /**
     * Send a new message.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @param message
     *            The message
     * @return The chat message.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatMessage postChatMessage(PLYRestClient client, String chatGroupId, ChatMessage message) {
        String url = "/chat_groups/" + chatGroupId + "/messages";

        ResponseEntity<ChatMessage> response = client.exchangeWithObject(url, HttpMethod.POST, message,
                ChatMessage.class, null);
        return response.getBody();
    }

    /**
     * Remove user from the chat group.
     *
     * @param client
     *            The REST client configured to handle communications with the
     *            ProductLayer API server
     * @param chatGroupId
     *            The identifier of the chat group.
     * @param userId
     *            The identifier of the user
     * @return The updated chat group.
     * @throws PLYHttpException
     *             On any HTTP status code indicating failure
     * @throws RestClientException
     *             On any client-side HTTP error
     */
    public static ChatGroup removeUserFromChatGroup(PLYRestClient client, String chatGroupId, String userId) {
        String url = "/chat_groups/" + chatGroupId + "/users/" + userId;

        ResponseEntity<ChatGroup> response = client.exchange(url, HttpMethod.DELETE, ChatGroup.class, null);
        return response.getBody();
    }

}
