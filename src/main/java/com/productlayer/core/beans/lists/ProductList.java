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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.SimpleUserInfo;

/**
 * @author Rene Swoboda
 *
 * 
 *         With the product list you can group products which are important to
 *         you. Like a wishlist for your birthday.
 */
@SuppressWarnings("serial")
public class ProductList implements Serializable {
    /**
     * The object id.
     */
    @JsonProperty("pl-id")
    private Long id;

    /**
     * The object identifier.
     */
    @JsonProperty("pl-class")
    protected String beautifiedClass;

    /**
     *
     */
    @JsonIgnore
    public static final String charObjectIdentifier = "L";

    /**
     *
     */
    @JsonIgnore
    public static final String shortObjectIdentifier = "List";

    /**
     *
     */
    @JsonIgnore
    public static final String longObjectIdentifier = "com.productlayer." + ProductList.shortObjectIdentifier;

    /**
     * The id of the owner of the list. The user who created the object.
     */
    @JsonProperty("pl-created-by")
    private SimpleUserInfo createdBy;

    /**
     * The title of the list.
     */
    @JsonProperty("pl-list-title")
    private String title;

    /**
     * The description for the list.
     */
    @JsonProperty("pl-list-desc")
    private String description;

    /**
     * The list of products.
     */
    @JsonProperty("pl-list-products")
    private List<ProductListItem> products;

    /**
     * The list type for the list.
     */
    @JsonProperty("pl-list-type")
    private ProductListType listType;

    /**
     * The sharing type for the list.
     */
    @JsonProperty("pl-list-share")
    private SharingType sharingType = SharingType.notShared;

    /**
     * A list of user ids the product list is shared.
     */
    @JsonProperty("pl-list-shared-users")
    private List<String> sharedUserIDs;

    /**
     * The timestamp when object was created.
     */
    @JsonProperty("pl-created-time")
    private Long created;

    /**
     * The timestamp when object was updated the last time.
     */
    @JsonProperty("pl-upd-time")
    private Long lastUpdated;

    /**
     * The user who updated the object the last time.
     */
    @JsonProperty("pl-upd-by")
    private SimpleUserInfo lastUpdatedBy;

    public ProductList() {
        super();
    }

    /**
     * @return The single character object identifier.
     */
    @JsonIgnore
    public String getSingleCharObjectIdentifier() {
        return ProductList.charObjectIdentifier;
    }

    /**
     * @return The simple object identifier.
     */
    @JsonIgnore
    public String getSimpleObjectIdentifier() {
        return ProductList.shortObjectIdentifier;
    }

    /**
     * @return The full object identifier with domain prefix.
     */
    @JsonIgnore
    public String getDomainObjectIdentifier() {
        return ProductList.longObjectIdentifier;
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
     * @return The user who created the object.
     */
    public SimpleUserInfo getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            The user who created the object.
     */
    public void setCreatedBy(SimpleUserInfo createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return The title of the list.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     *            The title of the list.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description for the list.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            The description for the list.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The list of products.
     */
    public List<ProductListItem> getProducts() {
        return this.products;
    }

    /**
     * @param products
     *            The list of products.
     */
    public void setProducts(List<ProductListItem> products) {
        this.products = products;
    }

    /**
     * @return The list type for the list.
     */
    public ProductListType getListType() {
        return this.listType;
    }

    /**
     * @param listType
     *            The list type for the list.
     */
    public void setListType(ProductListType listType) {
        this.listType = listType;
    }

    /**
     * @return The sharing type for the list.
     */
    public SharingType getSharingType() {
        return this.sharingType;
    }

    /**
     * @param sharingType
     *            The sharing type for the list.
     */
    public void setSharingType(SharingType sharingType) {
        this.sharingType = sharingType;
    }

    /**
     * @return A list of user ids the product list is shared.
     */
    public List<String> getSharedUserIDs() {
        return this.sharedUserIDs;
    }

    /**
     * @param sharedUserIDs
     *            A list of user ids the product list is shared.
     */
    public void setSharedUserIDs(List<String> sharedUserIDs) {
        this.sharedUserIDs = sharedUserIDs;
    }

    /**
     * @return The timestamp when object was created.
     */
    public Long getCreated() {
        return this.created;
    }

    /**
     * @param created
     *            The timestamp when object was created.
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     * @return The timestamp when object was updated the last time.
     */
    public Long getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * @param lastUpdated
     *            The timestamp when object was updated the last time.
     */
    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return The user who updated the object the last time.
     */
    public SimpleUserInfo getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     *            The user who updated the object the last time.
     */
    public void setLastUpdatedBy(SimpleUserInfo lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}