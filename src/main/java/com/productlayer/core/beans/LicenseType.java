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
 * The license defines how many requests can be performed per month.
 */
@SuppressWarnings("serial")
public class LicenseType extends BaseObject {
    /**
     * The name of the license.
     */
    @JsonProperty("pl-lic-name")
    private String name;

    /**
     * The description of the license.
     */
    @JsonProperty("pl-lic-desc")
    private String description;

    /**
     * The hard limit for monthly accesses to the API. If the hard limit is
     * reached no more accesses are possible till the end of the month.
     */
    @JsonProperty("pl-lic-lim")
    private Long hardLimitPerMonth;

    /**
     * a license with a higher access limit. The soft limit for monthly accesses
     * to the API. If the soft is reached an email will be send to the
     * developer.
     */
    @JsonProperty("pl-lic-lim_warn")
    private Long softLimitPerMonth;

    /**
     * The price of the license per month in EUR.
     */
    @JsonProperty("pl-lic-price")
    private float pricePerMonth;

    public LicenseType() {
    }

    /**
     * @return The name of the license.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the license.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description of the license.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            The description of the license.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The hard limit for monthly accesses to the API. If the hard limit
     *         is reached no more accesses are possible till the end of the
     *         month.
     */
    public Long getHardLimitPerMonth() {
        return this.hardLimitPerMonth;
    }

    /**
     * @param hardLimitPerMonth
     *            The hard limit for monthly accesses to the API. If the hard
     *            limit is reached no more accesses are possible till the end of
     *            the month.
     */
    public void setHardLimitPerMonth(Long hardLimitPerMonth) {
        this.hardLimitPerMonth = hardLimitPerMonth;
    }

    /**
     * @return The soft limit for monthly accesses to the API. If the soft is
     *         reached an email will be send to the developer.
     */
    public Long getSoftLimitPerMonth() {
        return this.softLimitPerMonth;
    }

    /**
     * @param softLimitPerMonth
     *            The soft limit for monthly accesses to the API. If the soft is
     *            reached an email will be send to the developer.
     */
    public void setSoftLimitPerMonth(Long softLimitPerMonth) {
        this.softLimitPerMonth = softLimitPerMonth;
    }

    /**
     * @return The price of the license per month in EUR.
     */
    public float getPricePerMonth() {
        return this.pricePerMonth;
    }

    /**
     * @param pricePerMonth
     *            The price of the license per month in EUR.
     */
    public void setPricePerMonth(float pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

}