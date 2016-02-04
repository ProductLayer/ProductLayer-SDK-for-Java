/**
 * Copyright (c) 2015, ProductLayer GmbH All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer. 
 * 
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.productlayer.core.beans.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.Opine;

/**
 * @author sorien
 *
 * 
 *         Opines imported from other social networks like facebook or twitter.
 */
@SuppressWarnings("serial")
public class SocialOpine extends Opine {
    /**
     * The provider id like facebook or twitter.
     */
    @JsonProperty("pl-opine-provider-user_id")
    private String userId;

    /**
     * The provider id like facebook or twitter.
     */
    @JsonProperty("pl-opine-provider-user_nickname")
    private String userNickname;

    /**
     * The provider id like facebook or twitter.
     */
    @JsonProperty("pl-opine-provider-id")
    private String providerId;

    /**
     * The count for the comments.
     */
    @JsonProperty("pl-opine-comment_count")
    private Integer commentCount;

    /**
     * Likes on facebook or favorites on twitter.
     */
    @JsonProperty("pl-opine-like_count")
    private Integer likeCount;

    /**
     * Shares on facebook or retweets on twitter.
     */
    @JsonProperty("pl-opine-shared_count")
    private Integer sharedCount;

    public SocialOpine() {
        super();
    }

    /**
     * @return The provider id like facebook or twitter.
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * @param userId
     *            The provider id like facebook or twitter.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return The provider id like facebook or twitter.
     */
    public String getUserNickname() {
        return this.userNickname;
    }

    /**
     * @param userNickname
     *            The provider id like facebook or twitter.
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * @return The provider id like facebook or twitter.
     */
    public String getProviderId() {
        return this.providerId;
    }

    /**
     * @param providerId
     *            The provider id like facebook or twitter.
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return The count for the comments.
     */
    public Integer getCommentCount() {
        return this.commentCount;
    }

    /**
     * @param commentCount
     *            The count for the comments.
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * @return Likes on facebook or favorites on twitter.
     */
    public Integer getLikeCount() {
        return this.likeCount;
    }

    /**
     * @param likeCount
     *            Likes on facebook or favorites on twitter.
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return Shares on facebook or retweets on twitter.
     */
    public Integer getSharedCount() {
        return this.sharedCount;
    }

    /**
     * @param sharedCount
     *            Shares on facebook or retweets on twitter.
     */
    public void setSharedCount(Integer sharedCount) {
        this.sharedCount = sharedCount;
    }

}