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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Rene Swoboda on 29/12/13.
 */
@SuppressWarnings("serial")
public class Product extends BaseVotingObject {
    /**
     * The gtin (barcode) of the product.
     */
    @JsonProperty("pl-prod-gtin")
    private String gtin;

    /**
     * The name of the product.
     */
    @JsonProperty("pl-prod-name")
    private String name;

    /**
     * The short description of the product.
     */
    @JsonProperty("pl-prod-desc-short")
    private String shortDescription;

    /**
     * The detailed description of the product.
     */
    @JsonProperty("pl-prod-desc-long")
    private String longDescription;

    /**
     * The language of the product.
     */
    @JsonProperty("pl-lng")
    private String language;

    /**
     * The product category (food, clothes, ...).
     */
    @JsonProperty("pl-prod-cat")
    private String category;

    /**
     * The name of the brand information.
     */
    @JsonProperty("pl-brand-name")
    private String brand;

    /**
     * The name of the brand owner information.
     */
    @JsonProperty("pl-brand-own-name")
    private String brandOwner;

    /**
     * The product avg review rating.
     */
    @JsonProperty("pl-prod-review-rating")
    private Float reviewRating;

    /**
     * The product review count.
     */
    @JsonProperty("pl-prod-review-count")
    private Integer reviewCount;

    /**
     * The nutrition information.
     */
    @JsonProperty("pl-prod-nutr")
    private Map<String, String> nutrition;

    /**
     * The characteristics information. e.g.: color, size, weight,
     * screen-resolution, ...
     */
    @JsonProperty("pl-prod-char")
    private Map<String, String> characteristics;

    /**
     * The packaging information.
     */
    @JsonProperty("pl-prod-pkg")
    private Packaging packaging;

    /**
     * The source from the information.
     */
    @JsonProperty("pl-prod-src")
    private String source;

    /**
     * The homepage or landingpage of the product.
     */
    @JsonProperty("pl-prod-homepage")
    private String homepage;

    /**
     * Additional links for the product. e.g.: Support Forum, FAQ's, ...
     */
    @JsonProperty("pl-prod-lnks")
    private List<String> links;

    /**
     * The default product image metadata.
     */
    @JsonProperty("pl-prod-img")
    private ProductImage defaultImage;

    /**
     * The product image count.
     */
    @JsonProperty("pl-prod-img-count")
    private Long imageCount;

    /**
     * The product opine count.
     */
    @JsonProperty("pl-prod-opine-count")
    private Long opineCount;

    /**
     * Additional languages for the product.
     */
    @JsonProperty("pl-additional-lng")
    private List<String> additionalLanguages;

    /**
     * Links where you can buy the product.
     */
    @JsonProperty("pl-prod-lnks-buy")
    private HashMap<String, String> buyLinks;

    public Product() {
        super();
    }

    public Product(String gtin) {
        super();
        setGtin(gtin);
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
     * @return The name of the product.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The short description of the product.
     */
    public String getShortDescription() {
        return this.shortDescription;
    }

    /**
     * @param shortDescription
     *            The short description of the product.
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return The detailed description of the product.
     */
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     * @param longDescription
     *            The detailed description of the product.
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * @return The language of the product.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            The language of the product.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The product category (food, clothes, ...).
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * @param category
     *            The product category (food, clothes, ...).
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The name of the brand information.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * @param brand
     *            The name of the brand information.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return The name of the brand owner information.
     */
    public String getBrandOwner() {
        return this.brandOwner;
    }

    /**
     * @param brandOwner
     *            The name of the brand owner information.
     */
    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    /**
     * @return The product avg review rating.
     */
    public Float getReviewRating() {
        return this.reviewRating;
    }

    /**
     * @param reviewRating
     *            The product avg review rating.
     */
    public void setReviewRating(Float reviewRating) {
        this.reviewRating = reviewRating;
    }

    /**
     * @return The product review count.
     */
    public Integer getReviewCount() {
        return this.reviewCount;
    }

    /**
     * @param reviewCount
     *            The product review count.
     */
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    /**
     * @return The nutrition information.
     */
    public Map<String, String> getNutrition() {
        return this.nutrition;
    }

    /**
     * @param nutrition
     *            The nutrition information.
     */
    public void setNutrition(Map<String, String> nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * @return The characteristics information. e.g.: color, size, weight,
     *         screen-resolution, ...
     */
    public Map<String, String> getCharacteristics() {
        return this.characteristics;
    }

    /**
     * @param characteristics
     *            The characteristics information. e.g.: color, size, weight,
     *            screen-resolution, ...
     */
    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    /**
     * @return The packaging information.
     */
    public Packaging getPackaging() {
        return this.packaging;
    }

    /**
     * @param packaging
     *            The packaging information.
     */
    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    /**
     * @return The source from the information.
     */
    public String getSource() {
        return this.source;
    }

    /**
     * @param source
     *            The source from the information.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return The homepage or landingpage of the product.
     */
    public String getHomepage() {
        return this.homepage;
    }

    /**
     * @param homepage
     *            The homepage or landingpage of the product.
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * @return Additional links for the product. e.g.: Support Forum, FAQ's, ...
     */
    public List<String> getLinks() {
        return this.links;
    }

    /**
     * @param links
     *            Additional links for the product. e.g.: Support Forum, FAQ's,
     *            ...
     */
    public void setLinks(List<String> links) {
        this.links = links;
    }

    /**
     * @return The default product image metadata.
     */
    public ProductImage getDefaultImage() {
        return this.defaultImage;
    }

    /**
     * @param defaultImage
     *            The default product image metadata.
     */
    public void setDefaultImage(ProductImage defaultImage) {
        this.defaultImage = defaultImage;
    }

    /**
     * @return The product image count.
     */
    public Long getImageCount() {
        return this.imageCount;
    }

    /**
     * @param imageCount
     *            The product image count.
     */
    public void setImageCount(Long imageCount) {
        this.imageCount = imageCount;
    }

    /**
     * @return The product opine count.
     */
    public Long getOpineCount() {
        return this.opineCount;
    }

    /**
     * @param opineCount
     *            The product opine count.
     */
    public void setOpineCount(Long opineCount) {
        this.opineCount = opineCount;
    }

    /**
     * @return Additional languages for the product.
     */
    public List<String> getAdditionalLanguages() {
        return this.additionalLanguages;
    }

    /**
     * @param additionalLanguages
     *            Additional languages for the product.
     */
    public void setAdditionalLanguages(List<String> additionalLanguages) {
        this.additionalLanguages = additionalLanguages;
    }

    /**
     * @return Links where you can buy the product.
     */
    public HashMap<String, String> getBuyLinks() {
        return this.buyLinks;
    }

    /**
     * @param buyLinks
     *            Links where you can buy the product.
     */
    public void setBuyLinks(HashMap<String, String> buyLinks) {
        this.buyLinks = buyLinks;
    }

}