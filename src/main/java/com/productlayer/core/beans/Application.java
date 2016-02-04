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
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Any developer can create a new application.<br>
 * Using the API-KEY of the application the developer can access the
 * ProductLayer API.
 */
@SuppressWarnings("serial")
public class Application extends BaseObject {
    /**
     * The name of the application.
     */
    @JsonProperty("pl-app-name")
    private String name;

    /**
     * The description of the application.
     */
    @JsonProperty("pl-app-desc")
    private String description;

    /**
     * The type of the application.
     */
    @JsonProperty("pl-app-type")
    private String type;

    /**
     * The URL the app or the homepage can be found at.
     */
    @JsonProperty("pl-app-url")
    private String url;

    /**
     * The generated API_KEY for the application. All requests must use the
     * API_KEY.
     */
    @JsonProperty("pl-app-app_key")
    private String apiKey;

    /**
     * The access counter for this application. Every request will increase the
     * counter. This counter will be reset by the system at the end of each
     * month.
     */
    @JsonProperty("pl-app-access_count")
    private Long accessCounter;

    /**
     * The access counter history seperated by months.
     */
    @JsonProperty("pl-app-access_hist")
    private Map<String, Long> accessHistory;

    /**
     * The license of the application.
     */
    @JsonProperty("pl-lic")
    private LicenseType license;

    /**
     * The IPs that are allowed to access the API using the API-KEY. If no IPs
     * are defined any IP may connect using this API-KEY.
     */
    @JsonProperty("pl-app-valid-ips")
    private List<String> allowedIPs;

    /**
     * Constructs an empty application object with a random API key.
     */
    public Application() {
        if (apiKey == null) {
            apiKey = UUID.randomUUID().toString();
        }
    }

    /**
     * @return The name of the application.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The description of the application.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The type of the application.
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return The URL the app or the homepage can be found at.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @return The generated API_KEY for the application. All requests must use
     *         the API_KEY.
     */
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * @return The access counter for this application. Every request will
     *         increase the counter. This counter will be reset by the system at
     *         the end of each month.
     */
    public Long getAccessCounter() {
        return this.accessCounter;
    }

    /**
     * @return The access counter history seperated by months.
     */
    public Map<String, Long> getAccessHistory() {
        return this.accessHistory;
    }

    /**
     * @return The license of the application.
     */
    public LicenseType getLicense() {
        return this.license;
    }

    /**
     * @return The IPs that are allowed to access the API using the API-KEY. If
     *         no IPs are defined any IP may connect using this API-KEY.
     */
    public List<String> getAllowedIPs() {
        return this.allowedIPs;
    }

}