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
 * @author Rene Swoboda
 *
 * 
 * 
 */
@SuppressWarnings("serial")
public class ValuesForKey extends BaseObject {
    /**
     * The key.
     */
    @JsonProperty("pl-key")
    private String key;

    /**
     * The values which have already been added to the key.
     */
    @JsonProperty("pl-values")
    private List<String> values;

    /**
     * The locale for the values.
     */
    @JsonProperty("pl-lng")
    private String language;

    public ValuesForKey() {
    }

    public ValuesForKey(String key, List<String> values, String language) {
        this.key = key;
        this.values = values;
        this.language = language;
    }

    /**
     * @return The key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key
     *            The key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The values which have already been added to the key.
     */
    public List<String> getValues() {
        return this.values;
    }

    /**
     * @param values
     *            The values which have already been added to the key.
     */
    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * @return The locale for the values.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            The locale for the values.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}