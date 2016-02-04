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
 * Detailed statistics for the reviews. Count per star.
 */
@SuppressWarnings("serial")
public class ReviewStatisticsDetails implements Serializable {
    /**
     * The review count with 1 star.
     */
    @JsonProperty("pl-rev-rating-star-1")
    public Integer oneStarAllCount = 0;

    /**
     * The review count with 2 stars.
     */
    @JsonProperty("pl-rev-rating-star-2")
    public Integer twoStarAllCount = 0;

    /**
     * The review count with 3 stars.
     */
    @JsonProperty("pl-rev-rating-star-3")
    public Integer threeStarAllCount = 0;

    /**
     * The review count with 4 stars.
     */
    @JsonProperty("pl-rev-rating-star-4")
    public Integer fourStarAllCount = 0;

    /**
     * The review count with 5 stars.
     */
    @JsonProperty("pl-rev-rating-star-5")
    public Integer fiveStarAllCount = 0;

    public ReviewStatisticsDetails() {
        super();
    }

    /**
     * @return The review count with 1 star.
     */
    public Integer getOneStarAllCount() {
        return this.oneStarAllCount;
    }

    /**
     * @param oneStarAllCount
     *            The review count with 1 star.
     */
    public void setOneStarAllCount(Integer oneStarAllCount) {
        this.oneStarAllCount = oneStarAllCount;
    }

    /**
     * @return The review count with 2 stars.
     */
    public Integer getTwoStarAllCount() {
        return this.twoStarAllCount;
    }

    /**
     * @param twoStarAllCount
     *            The review count with 2 stars.
     */
    public void setTwoStarAllCount(Integer twoStarAllCount) {
        this.twoStarAllCount = twoStarAllCount;
    }

    /**
     * @return The review count with 3 stars.
     */
    public Integer getThreeStarAllCount() {
        return this.threeStarAllCount;
    }

    /**
     * @param threeStarAllCount
     *            The review count with 3 stars.
     */
    public void setThreeStarAllCount(Integer threeStarAllCount) {
        this.threeStarAllCount = threeStarAllCount;
    }

    /**
     * @return The review count with 4 stars.
     */
    public Integer getFourStarAllCount() {
        return this.fourStarAllCount;
    }

    /**
     * @param fourStarAllCount
     *            The review count with 4 stars.
     */
    public void setFourStarAllCount(Integer fourStarAllCount) {
        this.fourStarAllCount = fourStarAllCount;
    }

    /**
     * @return The review count with 5 stars.
     */
    public Integer getFiveStarAllCount() {
        return this.fiveStarAllCount;
    }

    /**
     * @param fiveStarAllCount
     *            The review count with 5 stars.
     */
    public void setFiveStarAllCount(Integer fiveStarAllCount) {
        this.fiveStarAllCount = fiveStarAllCount;
    }

}