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
 * @author Rene Swoboda
 * 
 * 
 *         The brand of a product.
 */
public class Brand {
    /**
     * The object identifier.
     */
    @JsonProperty("pl-class")
    protected String beautifiedClass;

    /**
     * The bsin - brand identifier .
     */
    @JsonProperty("pl-brand-bsin")
    private String bsin;

    /**
     * The name of the brand.
     */
    @JsonProperty("pl-brand-name")
    private String name;

    /**
     * The homepage of the brand.
     */
    @JsonProperty("pl-brand-homepage")
    private String homepage;

    /**
     * The type of the brand.
     */
    @JsonProperty("pl-brand-type")
    private String type;

    public Brand() {
        super();
    }

    public static final String getSimpleObjectIdentifier() {
        return "Brand";
    }

    public static final String getDomainObjectIdentifier() {
        return "com.productlayer." + getSimpleObjectIdentifier();
    }

    /**
     * @return The object identifier.
     */
    public String getBeautifiedClass() {
        return this.beautifiedClass;
    }

    /**
     * @param beautifiedClass
     *            The object identifier.
     */
    public void setBeautifiedClass(String beautifiedClass) {
        this.beautifiedClass = beautifiedClass;
    }

    /**
     * @return The bsin - brand identifier .
     */
    public String getBsin() {
        return this.bsin;
    }

    /**
     * @param bsin
     *            The bsin - brand identifier .
     */
    public void setBsin(String bsin) {
        this.bsin = bsin;
    }

    /**
     * @return The name of the brand.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the brand.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The homepage of the brand.
     */
    public String getHomepage() {
        return this.homepage;
    }

    /**
     * @param homepage
     *            The homepage of the brand.
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * @return The type of the brand.
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            The type of the brand.
     */
    public void setType(String type) {
        this.type = type;
    }

}