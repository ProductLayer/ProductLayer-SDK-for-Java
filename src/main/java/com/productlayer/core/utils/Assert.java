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
package com.productlayer.core.utils;

/**
 * Provides static methods to validate arguments, assisting in identifying
 * programmer errors.
 * 
 * If an argument is found invalid, an {@code IllegalArgumentException} is
 * thrown.
 */
public class Assert {

    /**
     * Validates an argument is not null, throwing an
     * {@code IllegalArgumentException} else.
     * 
     * @param obj
     *            the object to test
     */
    public static void notNull(Object obj) {
        notNull(obj, null);
    }

    /**
     * Validates an argument is not null, throwing an
     * {@code IllegalArgumentException} else.
     * 
     * @param obj
     *            the object to test
     * @param msg
     *            the exception message to use, null for default
     */
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw msg == null ? new IllegalArgumentException(
                    "Validation fail: this argument must not be null") : new IllegalArgumentException(msg);
        }
    }

    /**
     * Validates an expression is true, throwing an
     * {@code IllegalArgumentException} else.
     * 
     * @param expr
     *            the boolean expression to test
     */
    public static void isTrue(boolean expr) {
        isTrue(expr, null);
    }

    /**
     * Validates an expression is true, throwing an
     * {@code IllegalArgumentException} else.
     * 
     * @param expr
     *            the boolean expression to test
     * @param msg
     *            the exception message to use, null for default
     */
    public static void isTrue(boolean expr, String msg) {
        if (!expr) {
            throw msg == null ? new IllegalArgumentException("Validation fail: this argument must be true")
                    : new IllegalArgumentException(msg);
        }
    }

    private Assert() {

    }

}
