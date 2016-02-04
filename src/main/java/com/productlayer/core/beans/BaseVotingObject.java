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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@SuppressWarnings("serial")
public class BaseVotingObject extends BaseObject {
    /**
     * The voting score of the object. (+1 for a up vote, -1 for a down vote)
     */
    @JsonProperty("pl-vote-score")
    private Integer votingScore = 0;

    /**
     * The users who up voted image.
     */
    @JsonProperty("pl-vote-usr_upvotes")
    private List<SimpleUserInfo> upVoters;

    /**
     * The users who down voted the image.
     */
    @JsonProperty("pl-vote-usr_downvotes")
    private List<SimpleUserInfo> downVoters;

    public BaseVotingObject() {
        super();
    }

    /**
     * @return The voting score of the object. (+1 for a up vote, -1 for a down
     *         vote)
     */
    public Integer getVotingScore() {
        return this.votingScore;
    }

    /**
     * @return The users who up voted image.
     */
    public List<SimpleUserInfo> getUpVoters() {
        return this.upVoters;
    }

    /**
     * @return The users who down voted the image.
     */
    public List<SimpleUserInfo> getDownVoters() {
        return this.downVoters;
    }

}