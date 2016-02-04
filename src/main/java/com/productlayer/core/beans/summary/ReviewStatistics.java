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
	* 
*/
@SuppressWarnings("serial")
public class ReviewStatistics implements Serializable {
    /**
     * The global average rating.
     */
    @JsonProperty("pl-rev-rating-avg")
    public Float avgRating = 0F;

    /**
     * The global review count.
     */
    @JsonProperty("pl-rev-rating-count")
    public Integer count = 0;

    /**
     * The review details. Count per star.
     */
    @JsonProperty("pl-rev-rating-details")
    public ReviewStatisticsDetails details;

    public ReviewStatistics() {
        super();
    }

    /**
     * @return The global average rating.
     */
    public Float getAvgRating() {
        return this.avgRating;
    }

    /**
     * @param avgRating
     *            The global average rating.
     */
    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    /**
     * @return The global review count.
     */
    public Integer getCount() {
        return this.count;
    }

    /**
     * @param count
     *            The global review count.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The review details. Count per star.
     */
    public ReviewStatisticsDetails getDetails() {
        return this.details;
    }

    /**
     * @param details
     *            The review details. Count per star.
     */
    public void setDetails(ReviewStatisticsDetails details) {
        this.details = details;
    }

}