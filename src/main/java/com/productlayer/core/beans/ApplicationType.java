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

import java.util.ArrayList;
import java.util.List;

/**
 * The application type consists of the target platform.
 */
public enum ApplicationType {

    IOS("iOS"), ANDROID("Android"), WINDOWS_PHONE("WindowsPhone"), CROSS_PLATFORM("CrossPlatform"), WINDOWS(
            "Windows"), MAC_OSX("MacOS"), LINUX("Linux"), WEB("Web");

    private ApplicationType(final String text) {
        this.text = text;
    }

    private final String text;

    /**
     * @param text
     *            the application type as a string
     * @return the ApplicationType enum for the given string
     */
    public static ApplicationType fromString(String text) {
        if (text != null) {
            for (ApplicationType app : ApplicationType.values()) {
                if (text.equalsIgnoreCase(app.text)) {
                    return app;
                }
            }
        }
        return null;
    }

    /**
     * @return all available application types as strings
     */
    public static List<String> getOptions() {
        List<String> options = new ArrayList<String>();
        for (ApplicationType app : ApplicationType.values()) {
            options.add(app.text);
        }
        return options;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}