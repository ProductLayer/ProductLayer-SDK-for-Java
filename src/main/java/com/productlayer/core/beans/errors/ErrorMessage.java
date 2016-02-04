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
package com.productlayer.core.beans.errors;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import com.productlayer.core.error.PLYStatusCodes;

/**
 * Created by Rene Swoboda on 29/12/13.
 * 
 * The error message with all needed information.
 */
@SuppressWarnings("serial")
public class ErrorMessage implements Serializable {
    /**
     * The error message.
     */
    private String message;

    /**
     * The productlayer error code.
     */
    private int code;

    /**
     * The stacktrace will only be available for alpha and beta api's.
     */
    private String throwable;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ErrorMessage(Exception ex, int code) {
        this.message = ex.getLocalizedMessage();
        this.code = code;
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        this.throwable = sw.getBuffer().toString();
    }

    public ErrorMessage(String message, PLYStatusCodes code) {
        this(message, code.value());
    }

    public ErrorMessage(PLYStatusCodes code) {
        this(code.getReasonPhrase(), code.value());
    }

    public ErrorMessage(PLYStatusCodes code, Exception ex) {
        this(code.getReasonPhrase() + " - Detailed Message: " + ex.getLocalizedMessage(), code.value());
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        this.throwable = sw.getBuffer().toString();
    }

    /**
     * @return The error message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     *            The error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The productlayer error code.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * @param code
     *            The productlayer error code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return The stacktrace will only be available for alpha and beta api's.
     */
    public String getThrowable() {
        return this.throwable;
    }

    /**
     * @param throwable
     *            The stacktrace will only be available for alpha and beta
     *            api's.
     */
    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

}