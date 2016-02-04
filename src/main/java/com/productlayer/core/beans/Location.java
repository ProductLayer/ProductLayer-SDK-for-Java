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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rene Swoboda
 *
 * 
 *         The location data (latitude, longitude).
 */
@SuppressWarnings("serial")
public class Location implements Serializable {
    /**
     * The latitude e.g.: 48.263321.
     */
    @JsonProperty("latitude")
    private double latitude;

    /**
     * The longitude e.g.: 14.253965.
     */
    @JsonProperty("longitude")
    private double longitude;

    public Location() {
        super();
    }

    /**
     * Constructor with longitude and latitude parameter.
     * 
     * @param longitude The longitude of the location.
     * @param latitude The latitude of the location.
     */
    public Location(Double longitude, Double latitude) {
        super();
        if (longitude != null) {
            this.longitude = longitude;
        }
        if (latitude != null) {
            this.latitude = latitude;
        }
    }

    /**
     * @return The latitude e.g.: 48.263321.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @param latitude
     *            The latitude e.g.: 48.263321.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude e.g.: 14.253965.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @param longitude
     *            The longitude e.g.: 14.253965.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}