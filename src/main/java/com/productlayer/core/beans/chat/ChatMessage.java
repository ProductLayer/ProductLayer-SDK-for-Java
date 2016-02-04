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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.productlayer.core.beans.User;

/**
 * @author sorien
 *
 * 
 *         A single message from a chat group.
 */
public class ChatMessage {
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
     * The user who wrote the text message.
     */
    @JsonProperty("pl-created-by")
    public User createdBy;

    /**
     * The text of the message.
     */
    @JsonProperty("pl-chat-message")
    private String message;

    /**
     * The timestamp when object was created.
     */
    @JsonProperty("pl-created-time")
    private long created;

    public ChatMessage() {
        super();
    }

    /**
     * @return single character identifier of this class
     */
    public String getSingleCharObjectIdentifier() {
        return "M";
    }

    /**
     * @return string identifier of this class
     */
    public String getSimpleObjectIdentifier() {
        return "ChatMessage";
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
     * @return The user who wrote the text message.
     */
    public User getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            The user who wrote the text message.
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return The text of the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     *            The text of the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The timestamp when object was created.
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * @param created
     *            The timestamp when object was created.
     */
    public void setCreated(long created) {
        this.created = created;
    }

}