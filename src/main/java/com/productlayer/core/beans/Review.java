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
 * Created by Rene Swoboda on 29/12/13.
 * 
 * 
 */
@SuppressWarnings("serial")
public class Review extends BaseTimelineObject {
    /**
     * The gtin (barcode) of the product.
     */
    @JsonProperty("pl-prod-gtin")
    private String productGtin;

    /**
     * The subject of the review.
     */
    @JsonProperty("pl-rev-subj")
    private String subject;

    /**
     * The detailed review text.
     */
    @JsonProperty("pl-rev-body")
    private String body;

    /**
     * The rating for the product.
     */
    @JsonProperty("pl-rev-rating")
    private Integer rating;

    /**
     * The language of the review.
     */
    @JsonProperty("pl-lng")
    private String language;

    /**
     * The constructor for the review.
     * 
     * @param productId
     *            The id of the product.
     * @param subject
     *            The subject of the review.
     * @param body
     *            The body of the review.
     * @param rating
     *            The rating for the product. (0 = low, 5 = high)
     */
    public Review(String productId, String subject, String body, int rating) {
        setProductGtin(productId);
        this.subject = subject;
        this.body = body;
        this.rating = rating;
    }

    public Review() {
    }

    /**
     * @return The gtin (barcode) of the product.
     */
    public String getProductGtin() {
        return this.productGtin;
    }

    /**
     * @param productGtin
     *            The gtin (barcode) of the product.
     */
    public void setProductGtin(String productGtin) {
        this.productGtin = productGtin;
    }

    /**
     * @return The subject of the review.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * @param subject
     *            The subject of the review.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return The detailed review text.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * @param body
     *            The detailed review text.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return The rating for the product.
     */
    public Integer getRating() {
        return this.rating;
    }

    /**
     * @param rating
     *            The rating for the product.
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * @return The language of the review.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            The language of the review.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}