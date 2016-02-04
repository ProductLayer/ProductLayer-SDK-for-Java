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
package com.productlayer.core.beans.summary;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sorien
 *
 * 
 * 
 */
@SuppressWarnings("serial")
public class FullReviewStatistics implements Serializable {
    /**
     * The global review statistics.
     */
    @JsonProperty("pl-rev-rating-global")
    public ReviewStatistics global;

    /**
     * The review statistics from friends.
     */
    @JsonProperty("pl-rev-rating-friends")
    public ReviewStatistics friends;

    public FullReviewStatistics() {
        super();
    }

    /**
     * @return The global review statistics.
     */
    public ReviewStatistics getGlobal() {
        return this.global;
    }

    /**
     * @param global
     *            The global review statistics.
     */
    public void setGlobal(ReviewStatistics global) {
        this.global = global;
    }

    /**
     * @return The review statistics from friends.
     */
    public ReviewStatistics getFriends() {
        return this.friends;
    }

    /**
     * @param friends
     *            The review statistics from friends.
     */
    public void setFriends(ReviewStatistics friends) {
        this.friends = friends;
    }

}