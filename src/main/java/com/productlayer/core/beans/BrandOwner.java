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
 *         The brand owner of a product.
 */
public class BrandOwner {
    /**
     * The object identifier.
     */
    @JsonProperty("pl-class")
    protected String beautifiedClass;

    /**
     * The name of the brand owner.
     */
    @JsonProperty("pl-brand-own-name")
    private String name;

    /**
     * The homepage of the brand owner.
     */
    @JsonProperty("pl-brand-own-homepage")
    private String homepage;

    /**
     * Additional links of the brand owner.
     */
    @JsonProperty("pl-brand-own-add_lnks")
    private List<String> additionalLinks;

    /**
     * The brands of the brand owner.
     */
    @JsonProperty("pl-brands")
    private List<Brand> brands;

    public BrandOwner() {
        super();
    }

    public static final String getSimpleObjectIdentifier() {
        return "BrandOwner";
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
     * @return The name of the brand owner.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the brand owner.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The homepage of the brand owner.
     */
    public String getHomepage() {
        return this.homepage;
    }

    /**
     * @param homepage
     *            The homepage of the brand owner.
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * @return Additional links of the brand owner.
     */
    public List<String> getAdditionalLinks() {
        return this.additionalLinks;
    }

    /**
     * @param additionalLinks
     *            Additional links of the brand owner.
     */
    public void setAdditionalLinks(List<String> additionalLinks) {
        this.additionalLinks = additionalLinks;
    }

    /**
     * @return The brands of the brand owner.
     */
    public List<Brand> getBrands() {
        return this.brands;
    }

    /**
     * @param brands
     *            The brands of the brand owner.
     */
    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

}