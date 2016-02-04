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
package com.productlayer.core.error;

import java.util.ArrayList;
import java.util.List;

import com.productlayer.core.beans.errors.ErrorMessage;

/**
 * A runtime exception thrown on HTTP status codes received from the API
 * indicating an error. Stores the HTTP status code as well as one or several
 * error messages.
 */
public class PLYHttpException extends RuntimeException {

    private static final long serialVersionUID = -5488048609821741983L;

    private static final int HTTP_STATUS = 500;

    private final List<ErrorMessage> errors;
    private final int httpStatus;

    /**
     * Constructs the exception with an error message and a default HTTP status
     * code of 500.
     * 
     * @param error
     *            the error message to contain
     */
    public PLYHttpException(ErrorMessage error) {
        this(error, HTTP_STATUS);
    }

    /**
     * Constructs the exception with several error messages and a default HTTP
     * status code of 500.
     * 
     * @param errors
     *            the error messages to contain
     */
    public PLYHttpException(List<ErrorMessage> errors) {
        this(errors, HTTP_STATUS);
    }

    /**
     * Constructs the exception with an error message and a HTTP status code.
     * 
     * @param error
     *            the error message to contain
     * @param httpStatus
     *            the status code to store
     */
    public PLYHttpException(ErrorMessage error, int httpStatus) {
        super();

        this.errors = new ArrayList<ErrorMessage>();
        this.errors.add(error);

        this.httpStatus = httpStatus;
    }

    /**
     * Constructs the exception with several error messages and a HTTP status
     * code.
     * 
     * @param errors
     *            the error messages to contain
     * @param httpStatus
     *            the status code to store
     */
    public PLYHttpException(List<ErrorMessage> errors, int httpStatus) {
        super();

        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    /**
     * @return the error messages
     */
    public List<ErrorMessage> getErrors() {
        return errors;
    }

    /**
     * @return the HTTP status code
     */
    public int getHttpStatus() {
        return httpStatus;
    }

}
