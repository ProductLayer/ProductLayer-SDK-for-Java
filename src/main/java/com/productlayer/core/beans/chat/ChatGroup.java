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
package com.productlayer.core.beans.chat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.User;

/**
 * @author sorien
 *
 */
public class ChatGroup {
    /**
     * The object id.
     */
    @JsonProperty("pl-id")
    private Long id;

    /**
     * The object identifier.
     */
    @JsonProperty("pl-class")
    private String _class = getDomainObjectIdentifier();

    /**
     * The title of the chat group.
     */
    @JsonProperty("pl-chat-title")
    private String title;

    /**
     * The members of the chat group.
     */
    @JsonProperty("pl-chat-members")
    public List<User> members;

    /**
     * The user who created the chat group.
     */
    @JsonProperty("pl-created-by")
    public User createdBy;

    /**
     * The timestamp when object was created.
     */
    @JsonProperty("pl-created-time")
    private Long created;

    /**
     * The timestamp when object was updated the last time.
     */
    @JsonProperty("pl-upd-time")
    private Long updated;

    public ChatGroup() {
        super();
    }

    /**
     * @return single character identifier of this class
     */
    public String getSingleCharObjectIdentifier() {
        return "G";
    }

    /**
     * @return string identifier of this class
     */
    public String getSimpleObjectIdentifier() {
        return "ChatGroup";
    }

    /**
     * @return full identifier of this class
     */
    public String getDomainObjectIdentifier() {
        return "com.productlayer." + getSimpleObjectIdentifier();
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
    public String get_class() {
        return this._class;
    }

    /**
     * @param _class
     *            The object identifier.
     */
    public void set_class(String _class) {
        this._class = _class;
    }

    /**
     * @return The title of the chat group.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     *            The title of the chat group.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The members of the chat group.
     */
    public List<User> getMembers() {
        return this.members;
    }

    /**
     * @param members
     *            The members of the chat group.
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * @return The user who created the chat group.
     */
    public User getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            The user who created the chat group.
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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
    public Long getUpdated() {
        return this.updated;
    }

    /**
     * @param updated
     *            The timestamp when object was updated the last time.
     */
    public void setUpdated(Long updated) {
        this.updated = updated;
    }

}