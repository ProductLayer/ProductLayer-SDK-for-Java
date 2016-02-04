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
 * @author sorien
 *
 */
public class ResultSetWithCursor {
    /**
     * The list of results.
     */
    @JsonProperty("results")
    private List<BaseObject> results;

    /**
     * The url for requesting more recent (next) results.
     */
    @JsonProperty("since-this-url")
    private String sinceThisResultsUrl;

    /**
     * The url for requesting older (previous) results.
     */
    @JsonProperty("until-this-url")
    private String untilThisResultsUrl;

    /**
     * The url for the current request.
     */
    @JsonProperty("this-url")
    private String thisResultsUrl;

    public ResultSetWithCursor() {
        super();
    }

    /**
     * @return The list of results.
     */
    public List<BaseObject> getResults() {
        return this.results;
    }

    /**
     * @param results
     *            The list of results.
     */
    public void setResults(List<BaseObject> results) {
        this.results = results;
    }

    /**
     * @return The url for requesting more recent (next) results.
     */
    public String getSinceThisResultsUrl() {
        return this.sinceThisResultsUrl;
    }

    /**
     * @param sinceThisResultsUrl
     *            The url for requesting more recent (next) results.
     */
    public void setSinceThisResultsUrl(String sinceThisResultsUrl) {
        this.sinceThisResultsUrl = sinceThisResultsUrl;
    }

    /**
     * @return The url for requesting older (previous) results.
     */
    public String getUntilThisResultsUrl() {
        return this.untilThisResultsUrl;
    }

    /**
     * @param untilThisResultsUrl
     *            The url for requesting older (previous) results.
     */
    public void setUntilThisResultsUrl(String untilThisResultsUrl) {
        this.untilThisResultsUrl = untilThisResultsUrl;
    }

    /**
     * @return The url for the current request.
     */
    public String getThisResultsUrl() {
        return this.thisResultsUrl;
    }

    /**
     * @param thisResultsUrl
     *            The url for the current request.
     */
    public void setThisResultsUrl(String thisResultsUrl) {
        this.thisResultsUrl = thisResultsUrl;
    }

}