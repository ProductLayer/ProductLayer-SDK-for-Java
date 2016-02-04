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
package com.productlayer.core.beans.localization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.BaseObject;

/**
 * @author Rene Swoboda
 *
 * 
 *         The localization key collection contains all localized strings from
 *         productlayer.
 */
@SuppressWarnings("serial")
public class LocalizedKey extends BaseObject {
    /**
     * Language for the dictionary.
     */
    @JsonProperty("pl-lng")
    private String language;

    /**
     * The key for the localized string.
     */
    @JsonProperty("pl-locale-key")
    private String key;

    /**
     * The localized string.
     */
    @JsonProperty("pl-locale-value")
    private String value;

    public LocalizedKey() {
        super();
    }

    public LocalizedKey(String key, String value, String language) {
        super();
        this.key = key;
        this.value = value;
        this.language = language;
    }

    /**
     * @return single character identifier of this class
     */
    public String getSingleCharObjectIdentifier() {
        return "K";
    }

    /**
     * @return string identifier of this class
     */
    public String getSimpleObjectIdentifier() {
        return "LocalizedKey";
    }

    /**
     * @return full identifier of this class
     */
    public String getDomainObjectIdentifier() {
        return "com.productlayer." + getSimpleObjectIdentifier();
    }

    public String getDisplayText() {
        if (value == null || value.length() == 0 || value.equalsIgnoreCase("TBL")) {
            return getKey();
        } else {
            return getValue();
        }
    }

    /**
     * @return Language for the dictionary.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            Language for the dictionary.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The key for the localized string.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key
     *            The key for the localized string.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The localized string.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value
     *            The localized string.
     */
    public void setValue(String value) {
        this.value = value;
    }

}