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
package com.productlayer.core.logic;

import com.productlayer.core.beans.errors.ErrorMessage;
import com.productlayer.core.error.PLYHttpException;
import com.productlayer.core.error.PLYStatusCodes;
import com.productlayer.core.utils.StringUtils;

/**
 * Utility methods for products.
 */
public class ProductLogic {

    /**
     * Creates a full 14 digits GTIN.
     * 
     * @param gtin
     *            The GTIN to work on
     * @return the GTIN padded with leading zeros to reach 14 digits
     * @throws PLYHttpException
     *             if a basic validity check on {@code gtin} fails
     */
    public static String createFull14DigitsGTIN(String gtin) {
        if (StringUtils.isEmpty(gtin)) {
            return null;
        }

        if (gtin.matches("^[0-9]{1,14}$")) {
            for (int i = gtin.length(); i < 14; i++) {
                gtin = "0" + gtin;
            }

            return gtin;
        } else {
            throw new PLYHttpException(new ErrorMessage(PLYStatusCodes.PRODUCT_GTIN_NOT_VALID),
                    PLYStatusCodes.HTTP_STATUS_BAD_REQUEST_CODE);
        }
    }

    /**
     * Trims all leading zeros of a GTIN.
     * 
     * @param sGTIN
     *            The GTIN to trim
     * @return The GTIN without any leading zeros
     */
    public static String trimleadingZeros(String sGTIN) {
        return sGTIN.replaceFirst("^0+(?!$)", "");
    }

}
