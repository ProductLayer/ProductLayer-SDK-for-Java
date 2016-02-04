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
package com.productlayer.core.beans.lists;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.Product;

/**
 * This object identifies a product in a product list.
 */
@SuppressWarnings("serial")
public class ProductListItem implements Serializable {
    /**
     * The object id.
     */
    @JsonProperty("pl-id")
    private Long id;

    /**
     * No need to load the full product information if a user only want's to
     * scan the products of the list. The gtin (barcode) of the product.
     */
    @JsonProperty("pl-prod-gtin")
    private String productGtin;

    /**
     * A simple note for people you share the list. A simple note for people you
     * share the list.
     */
    @JsonProperty("pl-list-prod-note")
    private String note;

    /**
     * The amount for the list. The amount for the list.
     */
    @JsonProperty("pl-list-prod-cnt")
    private Integer count = 1;

    /**
     * The priority to sort the list. e.g.: Which present i prefer for my
     * birthday. The priority to sort the list. e.g.: Which present i prefer for
     * my birthday.
     */
    @JsonProperty("pl-list-prod-prio")
    private Integer priority = 0;

    /**
     * The simple product information.
     */
    @JsonProperty("pl-prod")
    private Product product;

    public ProductListItem() {
        super();
    }

    /**
     * @return The object id.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            The object id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The gtin (barcode) of the product.
     */
    public String getProductGtin() {
        return this.productGtin;
    }

    /**
     * @param productGtin
     *            The gtin (barcode) of the product.
     */
    public void setProductGtin(String productGtin) {
        this.productGtin = productGtin;
    }

    /**
     * @return A simple note for people you share the list.
     */
    public String getNote() {
        return this.note;
    }

    /**
     * @param note
     *            A simple note for people you share the list.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return The amount for the list.
     */
    public Integer getCount() {
        return this.count;
    }

    /**
     * @param count
     *            The amount for the list.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The priority to sort the list. e.g.: Which present i prefer for
     *         my birthday.
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * @param priority
     *            The priority to sort the list. e.g.: Which present i prefer
     *            for my birthday.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return The simple product information.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * @param product
     *            The simple product information.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

}