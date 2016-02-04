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
package com.productlayer.core.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sorien
 *
 */
@SuppressWarnings("serial")
public class BaseSharingObject extends BaseTimelineObject {
    /**
     * If this parameter is true, the opine will be shared via facebook if the
     * user is connected with a facebook account.
     */
    @JsonProperty("pl-share-facebook")
    private Boolean shareViaFacebook = false;

    /**
     * The id of the post from facebook. If empty and pl-share-facebook was
     * true, the object couldn't be shared.
     */
    @JsonProperty("pl-share-facebook-post_id")
    private String facebookPostId;

    /**
     * If this parameter is true, the opine will be shared via twitter if the
     * user is connected with a twitter account.
     */
    @JsonProperty("pl-share-twitter")
    private Boolean shareViaTwitter = false;

    /**
     * The id of the tweet from twitter. If empty and pl-share-twitter was true,
     * the object couldn't be shared.
     */
    @JsonProperty("pl-share-twitter-post_id")
    private String twitterPostId;

    public BaseSharingObject() {
        super();
    }

    /**
     * @return If this parameter is true, the opine will be shared via facebook
     *         if the user is connected with a facebook account.
     */
    public Boolean getShareViaFacebook() {
        return this.shareViaFacebook;
    }

    /**
     * @param shareViaFacebook
     *            If this parameter is true, the opine will be shared via
     *            facebook if the user is connected with a facebook account.
     */
    public void setShareViaFacebook(Boolean shareViaFacebook) {
        this.shareViaFacebook = shareViaFacebook;
    }

    /**
     * @return The id of the post from facebook. If empty and pl-share-facebook
     *         was true, the object couldn't be shared.
     */
    public String getFacebookPostId() {
        return this.facebookPostId;
    }

    /**
     * @param facebookPostId
     *            The id of the post from facebook. If empty and
     *            pl-share-facebook was true, the object couldn't be shared.
     */
    public void setFacebookPostId(String facebookPostId) {
        this.facebookPostId = facebookPostId;
    }

    /**
     * @return If this parameter is true, the opine will be shared via twitter
     *         if the user is connected with a twitter account.
     */
    public Boolean getShareViaTwitter() {
        return this.shareViaTwitter;
    }

    /**
     * @param shareViaTwitter
     *            If this parameter is true, the opine will be shared via
     *            twitter if the user is connected with a twitter account.
     */
    public void setShareViaTwitter(Boolean shareViaTwitter) {
        this.shareViaTwitter = shareViaTwitter;
    }

    /**
     * @return The id of the tweet from twitter. If empty and pl-share-twitter
     *         was true, the object couldn't be shared.
     */
    public String getTwitterPostId() {
        return this.twitterPostId;
    }

    /**
     * @param twitterPostId
     *            The id of the tweet from twitter. If empty and
     *            pl-share-twitter was true, the object couldn't be shared.
     */
    public void setTwitterPostId(String twitterPostId) {
        this.twitterPostId = twitterPostId;
    }

}