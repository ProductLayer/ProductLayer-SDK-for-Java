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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base class for document classes.
 * 
 * created:
 * 
 * @author Oliver Gierke
 * 
 *         adapted:
 * @author Rene Swoboda
 * 
 *         All database objects are extending this base class.
 */
@SuppressWarnings("serial")
public class BaseObject implements Serializable {
    /**
     * The object id.
     */
    @JsonProperty("pl-id")
    protected String id;

    /**
     * The object identifier.
     */
    @JsonProperty("pl-class")
    protected String beautifiedClass;

    /**
     * The version.
     */
    @JsonProperty("pl-version")
    protected Long versionId;

    /**
     * The timestamp when object was created.
     */
    @JsonProperty("pl-created-time")
    private Long created;

    /**
     * The user who created the object.
     */
    @JsonProperty("pl-created-by")
    private SimpleUserInfo createdBy;

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

    public BaseObject() {
        super();
    }

    /**
     * @return single character identifier of this class
     */
    @JsonIgnore
    public String getSingleCharObjectIdentifier() {
        return "B";
    }

    /**
     * @return string identifier of this class
     */
    @JsonIgnore
    public String getSimpleObjectIdentifier() {
        return "BaseObject";
    }

    /**
     * @return full identifier of this class
     */
    @JsonIgnore
    public String getDomainObjectIdentifier() {
        return "com.productlayer." + getSimpleObjectIdentifier();
    }

    /**
     * @return The object id.
     */
    public String getId() {
        return this.id;
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
     * @return The version.
     */
    public Long getVersionId() {
        return this.versionId;
    }

    /**
     * @return The timestamp when object was created.
     */
    public Long getCreated() {
        return this.created;
    }

    /**
     * @return The user who created the object.
     */
    public SimpleUserInfo getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return The timestamp when object was updated the last time.
     */
    public Long getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * @return The user who updated the object the last time.
     */
    public SimpleUserInfo getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

}