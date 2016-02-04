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
package com.productlayer.core.beans.gamification;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sorien
 *
 * 
 * 
 */
@SuppressWarnings("serial")
public class Achievement implements Serializable {
    /**
     * The object id.
     */
    @JsonProperty(value="pl-id")
    private Long id;

    /**
     * The unique achievement key.
     */
    @JsonProperty(value="pl-achv-key")
    private String key;

    /**
     * The url for the image.
     */
    @JsonProperty(value="pl-achv-img-url")
    private String imageUrl;

    /**
     * The default english name. All other localizations must be queried from
     * the localization table.
     * 
     * The name of the achievement.
     */
    @JsonProperty(value="pl-achv-name")
    private String name;

    /**
     * The description of the achievement.
     */
    @JsonProperty(value="pl-achv-desc")
    private String description;

    /**
     * The type of the achievement. e.g.: Legendary, Checkpoint, Daily, Weekly,
     * Monthly
     */
    @JsonProperty(value="pl-achv-type")
    private AchievementType type;

    public Achievement() {
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
     * @return The unique achievement key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key
     *            The unique achievement key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The url for the image.
     */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /**
     * @param imageUrl
     *            The url for the image.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The name of the achievement.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the achievement.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description of the achievement.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            The description of the achievement.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The type of the achievement. e.g.: Legendary, Checkpoint, Daily,
     *         Weekly, Monthly
     */
    public AchievementType getType() {
        return this.type;
    }

    /**
     * @param type
     *            The type of the achievement. e.g.: Legendary, Checkpoint,
     *            Daily, Weekly, Monthly
     */
    public void setType(AchievementType type) {
        this.type = type;
    }

}