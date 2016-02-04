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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rene Swoboda
 * 
 */
@SuppressWarnings("serial")
public class Packaging implements Serializable {
    /**
     * The units per package.
     */
    @JsonProperty("pl-prod-pkg-units")
    private Integer units;

    /**
     * The name of the package.
     */
    @JsonProperty("pl-prod-pkg-name")
    private String name;

    /**
     * The package description.
     */
    @JsonProperty("pl-prod-pkg-desc")
    private String description;

    /**
     * All what's packed into.
     */
    @JsonProperty("pl-prod-pkg-cont")
    private String contains;

    public Packaging() {
        super();
    }

    /**
     * @return The units per package.
     */
    public Integer getUnits() {
        return this.units;
    }

    /**
     * @param units
     *            The units per package.
     */
    public void setUnits(Integer units) {
        this.units = units;
    }

    /**
     * @return The name of the package.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the package.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The package description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            The package description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return All what's packed into.
     */
    public String getContains() {
        return this.contains;
    }

    /**
     * @param contains
     *            All what's packed into.
     */
    public void setContains(String contains) {
        this.contains = contains;
    }

}