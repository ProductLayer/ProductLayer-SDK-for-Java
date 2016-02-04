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
 */
@SuppressWarnings("serial")
public class Opine extends BaseSharingObject {
    /**
     * The parent of the opine.
     */
    @JsonProperty("pl-parent")
    private ObjectReference parent;

    /**
     * The gtin (barcode) of the product.
     */
    @JsonProperty("pl-prod-gtin")
    private String gtin;

    /**
     * The opine text (max 140 chars).
     */
    @JsonProperty("pl-opine-text")
    private String text;

    /**
     * The language of the opine.
     */
    @JsonProperty("pl-lng")
    private String language;

    /**
     * The location information if provided.
     */
    @JsonProperty("pl-opine-location")
    private Location location;

    /**
     * The images for the opine.
     */
    @JsonProperty("pl-opine-img")
    private List<ProductImage> images;

    public Opine() {
        super();
    }

    /**
     * @return The parent of the opine.
     */
    public ObjectReference getParent() {
        return this.parent;
    }

    /**
     * @param parent
     *            The parent of the opine.
     */
    public void setParent(ObjectReference parent) {
        this.parent = parent;
    }

    /**
     * @return The gtin (barcode) of the product.
     */
    public String getGtin() {
        return this.gtin;
    }

    /**
     * @param gtin
     *            The gtin (barcode) of the product.
     */
    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    /**
     * @return The opine text (max 140 chars).
     */
    public String getText() {
        return this.text;
    }

    /**
     * @param text
     *            The opine text (max 140 chars).
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The language of the opine.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            The language of the opine.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The location information if provided.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * @param location
     *            The location information if provided.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return The images for the opine.
     */
    public List<ProductImage> getImages() {
        return this.images;
    }

    /**
     * @param images
     *            The images for the opine.
     */
    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

}