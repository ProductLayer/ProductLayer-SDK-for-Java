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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sorien
 *
 * 
 *         The metadata of the user avatar images.
 */
@SuppressWarnings("serial")
public class UserAvatarImage extends BaseVotingObject {
    /**
     * The id of the user.
     */
    @JsonProperty("pl-usr-id")
    private String userID;

    /**
     * The nickname of the user.
     */
    @JsonProperty("pl-usr-nickname")
    private String userNickname;

    /**
     * The name of the image.
     */
    @JsonProperty("pl-img-name")
    private String name;

    /**
     * The width in pixel of the image.
     */
    @JsonProperty("pl-img-w-px")
    private Integer width;

    /**
     * The height in pixel of the image.
     */
    @JsonProperty("pl-img-h-px")
    private Integer height;

    /**
     * The image file id.
     */
    @JsonProperty("pl-img-file_id")
    private String imageFileId;

    /**
     * The parent image file id. This is only set if the image have been scaled
     * and this is not the original image.
     */
    @JsonProperty("pl-img-parent-file_id")
    private String parentImageFileId;

    /**
     * The url of the image.
     */
    @JsonProperty("pl-img-url")
    private String url;

    /**
     * The dominant color as RGB array.
     */
    @JsonProperty("pl-img-dominant_color")
    private int[] dominantColor = { 255, 255, 255 };

    /**
     * The dominant color as a hex value like #FFFFFF.
     */
    @JsonProperty("pl-img-dominant_color_hex")
    private String dominantColorHex = "#FFFFFF";

    public UserAvatarImage() {
    }

    /**
     * @return The id of the user.
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * @param userID
     *            The id of the user.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return The nickname of the user.
     */
    public String getUserNickname() {
        return this.userNickname;
    }

    /**
     * @param userNickname
     *            The nickname of the user.
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * @return The name of the image.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            The name of the image.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The width in pixel of the image.
     */
    public Integer getWidth() {
        return this.width;
    }

    /**
     * @param width
     *            The width in pixel of the image.
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return The height in pixel of the image.
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * @param height
     *            The height in pixel of the image.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The image file id.
     */
    public String getImageFileId() {
        return this.imageFileId;
    }

    /**
     * @param imageFileId
     *            The image file id.
     */
    public void setImageFileId(String imageFileId) {
        this.imageFileId = imageFileId;
    }

    /**
     * @return The parent image file id. This is only set if the image have been
     *         scaled and this is not the original image.
     */
    public String getParentImageFileId() {
        return this.parentImageFileId;
    }

    /**
     * @param parentImageFileId
     *            The parent image file id. This is only set if the image have
     *            been scaled and this is not the original image.
     */
    public void setParentImageFileId(String parentImageFileId) {
        this.parentImageFileId = parentImageFileId;
    }

    /**
     * @return The url of the image.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param url
     *            The url of the image.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The dominant color as RGB array.
     */
    public int[] getDominantColor() {
        return this.dominantColor;
    }

    /**
     * @param dominantColor
     *            The dominant color as RGB array.
     */
    public void setDominantColor(int[] dominantColor) {
        this.dominantColor = dominantColor;
    }

    /**
     * @return The dominant color as a hex value like #FFFFFF.
     */
    public String getDominantColorHex() {
        return this.dominantColorHex;
    }

    /**
     * @param dominantColorHex
     *            The dominant color as a hex value like #FFFFFF.
     */
    public void setDominantColorHex(String dominantColorHex) {
        this.dominantColorHex = dominantColorHex;
    }

}