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
@SuppressWarnings("serial")
public class Category extends BaseObject {
    /**
     * The category key for translations. This key is also set as
     * product.category.
     */
    @JsonProperty("pl-cat-key")
    private String key;

    /**
     * The default name of the category in english. If you need another language
     * ask the localization service for a translation of the 'pl-cat-key'.
     */
    @JsonProperty("pl-cat-def_name")
    private String name;

    /**
     * The default description of the category in english. If you need another
     * language ask the localization service for a translation of the
     * 'pl-cat-key'+'-description'.
     */
    @JsonProperty("pl-cat-def_desc")
    private String description;

    /**
     * The count of all products within this category.
     */
    @JsonProperty("pl-cat-prod_cnt")
    private Long productCount;

    /**
     * The subcategories of the category.
     */
    @JsonProperty("pl-cat-subcat")
    private List<Category> subCategories;

    /**
     * The default nutrition keys which should be provided for the product.
     * e.g.: pl-prod-nutr-calories_kj and pl-prod-nutr-calories_kcal for the
     * category food
     */
    @JsonProperty("pl-cat-def_nutr")
    private List<String> defaultNutritionKeys;

    /**
     * The default characteristic keys which should be provided for the product.
     * e.g.: pl-prod-char-cpu and pl-prod-char-ram for the category computer
     */
    @JsonProperty("pl-cat-def_char")
    private List<String> defaultCharacteristicKeys;

    public Category() {
        super();
    }

    public Category(String key, String name, String description) {
        super();
        this.key = key;
        this.name = name;
        this.description = description;
    }

    /**
     * @return The category key for translations. This key is also set as
     *         product.category.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return The default name of the category in english. If you need another
     *         language ask the localization service for a translation of the
     *         'pl-cat-key'.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The default description of the category in english. If you need
     *         another language ask the localization service for a translation
     *         of the 'pl-cat-key'+'-description'.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The count of all products within this category.
     */
    public Long getProductCount() {
        return this.productCount;
    }

    /**
     * @param productCount
     *            The count of all products within this category.
     */
    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    /**
     * @return The subcategories of the category.
     */
    public List<Category> getSubCategories() {
        return this.subCategories;
    }

    /**
     * @return The default nutrition keys which should be provided for the
     *         product. e.g.: pl-prod-nutr-calories_kj and
     *         pl-prod-nutr-calories_kcal for the category food
     */
    public List<String> getDefaultNutritionKeys() {
        return this.defaultNutritionKeys;
    }

    /**
     * @return The default characteristic keys which should be provided for the
     *         product. e.g.: pl-prod-char-cpu and pl-prod-char-ram for the
     *         category computer
     */
    public List<String> getDefaultCharacteristicKeys() {
        return this.defaultCharacteristicKeys;
    }

}