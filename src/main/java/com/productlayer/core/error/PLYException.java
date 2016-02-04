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

/**
 * A runtime exception thrown on general errors within the ProductLayer
 * framework. Stores a code and message from {@link PLYStatusCodes}.
 */
@SuppressWarnings("serial")
public class PLYException extends RuntimeException {

    private PLYStatusCodes code;

    /**
     * Constructs a <code>PLYException</code> without any details.
     */
    public PLYException() {
        super();
    }

    /**
     * Constructs a <code>PLYException</code> with the specified status code and
     * message retrieved from {@code code}.
     * 
     * @param code
     *            the ProductLayer status code
     */
    public PLYException(PLYStatusCodes code) {
        super(code.getReasonPhrase());

        this.code = code;
    }

    /**
     * Constructs a <code>PLYException</code> with the specified status code and
     * detail message.
     * 
     * @param code
     *            the ProductLayer status code
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link Throwable#getMessage()} method).
     */
    public PLYException(PLYStatusCodes code, String message) {
        super(message);

        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * <p>
     * Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail message.
     * 
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link Throwable#getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *            is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * @since 1.5
     */
    public PLYException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
     * contains the class and detail message of <tt>cause</tt>). This
     * constructor is useful for exceptions that are little more than wrappers
     * for other throwables (for example,
     * {@link java.security.PrivilegedActionException}).
     * 
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *            is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * @since 1.5
     */
    public PLYException(Throwable cause) {
        super(cause);
    }

    /**
     * @return the ProductLayer status code
     */
    public PLYStatusCodes getCode() {
        return code;
    }
}
