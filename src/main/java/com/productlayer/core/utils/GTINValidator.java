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

import java.util.Arrays;

import com.productlayer.core.logic.ProductLogic;

/**
 * Validates product GTINs (barcodes).
 */
public final class GTINValidator {

    /**
     * The restricted distribution GTIN prefixes. These GTINs are currently not
     * supported.
     */
    private static String[] restrictedDistributionPrefix = new String[] { "020", "021", "022", "023", "024",
            "025", "026", "027", "028", "029", "040", "041", "042", "043", "044", "045", "046", "047", "048",
            "049", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212",
            "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226",
            "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240",
            "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254",
            "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268",
            "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282",
            "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296",
            "297", "298", "299" };

    /**
     * Checks if the characters are all numeric.
     * 
     * @param aChars
     *            The character array
     * @return True if all characters are numeric, otherwise false
     */
    private static boolean _isNumeric(final char[] aChars) {
        for (final char c : aChars)
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    /**
     * @param c
     *            the character to parse
     * @return the numeric value of character {@code c} in the decimal system
     */
    private static int _getDigit(final char c) {
        return Character.digit(c, 10);
    }

    /**
     * Calculates the checksum of a character array (GTIN).
     * 
     * @param aChars
     *            The gtin as character array.
     * @param nLen
     *            The length of the array
     * @return The checksum
     */
    public static int calcChecksum(final char[] aChars, final int nLen) {
        int nChecksum = 0;
        int nFactor = (nLen % 2) == 0 ? 1 : 3;
        for (int i = 0; i < nLen; i++) {
            nChecksum += _getDigit(aChars[i]) * nFactor;
            nFactor = 4 - nFactor;
        }
        nChecksum = (10 - (nChecksum % 10)) % 10;
        return nChecksum;
    }

    /**
     * Checks if the checksum is valid.
     * 
     * @param aChars
     *            The character array
     * @return True if it's a valid checksum, otherwise false
     */
    private static boolean _isValidChecksum(final char[] aChars) {
        return calcChecksum(aChars, aChars.length - 1) == _getDigit(aChars[aChars.length - 1]);
    }

    /**
     * Checks if a string is a valid GTIN 8.
     * 
     * @param sGTIN8
     *            The string to check
     * @return True if it's a valid GTIN 8, otherwise false
     */
    public static boolean isValidGTIN8(final String sGTIN8) {
        if (sGTIN8.length() != 8)
            return false;
        final char[] aChars = sGTIN8.toCharArray();
        if (!_isNumeric(aChars))
            return false;

        if (!isValidPrefix(getPrefixGTIN8(sGTIN8))) {
            return false;
        }

        return _isValidChecksum(aChars);
    }

    /**
     * Checks if a string is a valid GTIN 12.
     * 
     * @param sGTIN12
     *            The string to check
     * @return True if it's a valid GTIN 12, otherwise false
     */
    public static boolean isValidGTIN12(final String sGTIN12) {
        if (sGTIN12.length() != 12)
            return false;
        final char[] aChars = sGTIN12.toCharArray();
        if (!_isNumeric(aChars))
            return false;
        if (!isValidPrefix(getPrefix(sGTIN12))) {
            return false;
        }
        return _isValidChecksum(aChars);
    }

    /**
     * Checks if a string is a valid GTIN 13.
     * 
     * @param sGTIN13
     *            The string to check
     * @return True if it's a valid GTIN 13, otherwise false
     */
    public static boolean isValidGTIN13(final String sGTIN13) {

        if (sGTIN13.length() != 13)
            return false;
        final char[] aChars = sGTIN13.toCharArray();
        if (!_isNumeric(aChars))
            return false;
        if (!isValidPrefix(getPrefix(sGTIN13))) {
            return false;
        }
        return _isValidChecksum(aChars);
    }

    /**
     * Checks if a string is a valid GTIN 14.
     * 
     * @param sGTIN14
     *            The string to check
     * @return True if it's a valid GTIN 14, otherwise false
     */
    public static boolean isValidGTIN14(final String sGTIN14) {
        if (sGTIN14.length() != 14)
            return false;
        final char[] aChars = sGTIN14.toCharArray();
        if (!_isNumeric(aChars))
            return false;
        if (!isValidPrefix(getPrefixGTIN14(sGTIN14))) {
            return false;
        }
        return _isValidChecksum(aChars);
    }

    /**
     * Checks if a string is a valid GTIN.
     * 
     * @param sGTIN
     *            The string to check
     * @return True if the GTIN is valid, otherwise false
     */
    public static boolean isValidGTIN(final String sGTIN) {

        if (StringUtils.isEmpty(sGTIN)) {
            return false;
        }

        String replacedLeadingZeros = ProductLogic.trimleadingZeros(sGTIN);
        int length = replacedLeadingZeros.length();

        // Can only be GTIN-8
        if (length <= 8 && length >= 7) {
            // fill with leading zeros to match GTIN-8
            String sGTIN8 = ("00000000" + replacedLeadingZeros).substring(replacedLeadingZeros.length());

            return isValidGTIN8(sGTIN8);
        } else if (length <= 14 && length >= 11) {
            // fill with leading zeros to match GTIN-14
            String sGTIN14 = ("00000000000000" + replacedLeadingZeros).substring(replacedLeadingZeros
                    .length());

            return isValidGTIN14(sGTIN14);
        } else {
            return false;
        }
    }

    /**
     * Extracts the prefix from a GTIN.
     * 
     * @param sGTIN
     *            The GTIN to extract the prefix from
     * @return The prefix of the GTIN or null if unable to extract
     */
    public static String getPrefix(final String sGTIN) {
        if (StringUtils.isEmpty(sGTIN)) {
            return null;
        }

        String replacedLeadingZeros = ProductLogic.trimleadingZeros(sGTIN);

        // Can only be GTIN-8
        if (replacedLeadingZeros.length() <= 8) {
            // fill with leading zeros to match GTIN-8
            String sGTIN8 = ("00000000" + replacedLeadingZeros).substring(replacedLeadingZeros.length());

            return getPrefixGTIN8(sGTIN8);
        } else {
            // fill with leading zeros to match GTIN-14
            String sGTIN14 = ("00000000000000" + replacedLeadingZeros).substring(replacedLeadingZeros
                    .length());

            return getPrefixGTIN14(sGTIN14);
        }
    }

    /**
     * Extracts the prefix from a GTIN with 8 digits.
     * 
     * @param sGTIN8
     *            The GTIN with 8 digits
     * @return The prefix or null if unable to extract
     */
    public static String getPrefixGTIN8(final String sGTIN8) {
        if (StringUtils.isEmpty(sGTIN8) || sGTIN8.length() != 8) {
            return null;
        }

        return sGTIN8.substring(0, 3);
    }

    /**
     * Extracts the prefix from a GTIN with 14 digits.
     * 
     * @param sGTIN14
     *            The GTIN with 14 digits
     * @return The prefix or null if unable to extract
     */
    public static String getPrefixGTIN14(final String sGTIN14) {
        if (StringUtils.isEmpty(sGTIN14) || sGTIN14.length() != 14) {
            return null;
        }

        return sGTIN14.substring(1, 4);
    }

    /**
     * Checks if a prefix is valid.
     * 
     * @param prefix
     *            The prefix to check
     * @return True if it's a valid prefix, otherwise false
     */
    public static boolean isValidPrefix(String prefix) {
        if (StringUtils.isEmpty(prefix) || prefix.length() != 3) {
            return false;
        }

        if (Arrays.asList(restrictedDistributionPrefix).contains(prefix)) {
            return false;
        }

        return true;
    }

    private GTINValidator() {

    }

}
