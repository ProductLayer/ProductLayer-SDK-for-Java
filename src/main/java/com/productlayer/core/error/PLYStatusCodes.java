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
 * ProductLayer status codes and their explanations.
 */
public enum PLYStatusCodes {

    /**
     * {@code 1001 Update not performed, no changes found}.
     */
    OBJECT_NOT_UPDATED_NO_CHANGES(PLYStatusCodes.OBJECT_NOT_UPDATED_NO_CHANGES_CODE,
            PLYStatusCodes.OBJECT_NOT_UPDATED_NO_CHANGES_MSG),

    /**
     * {@code 1002 Database exception occurred and the rollback couldn'T be performed.}
     * .
     */
    ROLLBACK_EXCEPTION(PLYStatusCodes.ROLLBACK_EXCEPTION_CODE, PLYStatusCodes.ROLLBACK_EXCEPTION_MSG),

    /**
     * {@code 1003 A required parameter has not been found.}.
     */
    REQUIRED_PARAMETER_NOT_FOUND(PLYStatusCodes.REQUIRED_PARAMETER_NOT_FOUND_CODE,
            PLYStatusCodes.REQUIRED_PARAMETER_NOT_FOUND_MSG),

    /**
     * {@code 1006 Sending email failed, please try again later or contact support@productlayer.com}
     * .
     */
    SENDING_EMAIL_FAILED(PLYStatusCodes.SENDING_EMAIL_FAILED_CODE, PLYStatusCodes.SENDING_EMAIL_FAILED_MSG),

    /**
     * {@code 1007 You have mixed up the dates! From date can't be after to date.}
     * .
     */
    FROM_DATE_AFTER_TO_DATE(PLYStatusCodes.FROM_DATE_AFTER_TO_DATE_CODE,
            PLYStatusCodes.FROM_DATE_AFTER_TO_DATE_MSG),

    COULD_NOT_PARSE_JSON(PLYStatusCodes.COULD_NOT_PARSE_JSON_CODE, PLYStatusCodes.COULD_NOT_PARSE_JSON_MSG),

    JSON_PROPERTY_NOT_FOUND(PLYStatusCodes.JSON_PROPERTY_NOT_FOUND_CODE,
            PLYStatusCodes.JSON_PROPERTY_NOT_FOUND_MSG),

    INVALID_LANGUAGE(PLYStatusCodes.INVALID_LANGUAGE_CODE, PLYStatusCodes.INVALID_LANGUAGE_MSG),

    VOTING_FAILED(PLYStatusCodes.VOTING_FAILED_CODE, PLYStatusCodes.VOTING_FAILED_MSG),

    NOT_INSERTED_DUPLICATE_FOUND(PLYStatusCodes.NOT_INSERTED_DUPLICATE_FOUND_CODE,
            PLYStatusCodes.NOT_INSERTED_DUPLICATE_FOUND_MSG),

    /***********************************************************************
     *********************** 2000 - USER ERROR CODES ***********************
     ***********************************************************************/

    /**
     * {@code 2001 A user with this email is already registered.}
     */
    REGISTRATION_FAILED_EMAIL_DUPLICATE(PLYStatusCodes.REGISTRATION_FAILED_EMAIL_DUPLICATE_CODE,
            PLYStatusCodes.REGISTRATION_FAILED_EMAIL_DUPLICATE_MSG),

    /**
     * {@code 2002 A user with this nickname is already registered.}
     */
    REGISTRATION_FAILED_NICKNAME_DUPLICATE(PLYStatusCodes.REGISTRATION_FAILED_NICKNAME_DUPLICATE_CODE,
            PLYStatusCodes.REGISTRATION_FAILED_NICKNAME_DUPLICATE_MSG),

    /**
     * {@code 2003 You have entered the wrong password!}
     */
    WRONG_PASSWORD_PROVIDED(PLYStatusCodes.WRONG_PASSWORD_PROVIDED_CODE,
            PLYStatusCodes.WRONG_PASSWORD_PROVIDED_MSG),

    /**
     * {@code 2004 You cannot update the password with the same one!}
     */
    PASSWORDS_ARE_EQUALS(PLYStatusCodes.PASSWORDS_ARE_EQUALS_CODE, PLYStatusCodes.PASSWORDS_ARE_EQUALS_MSG),

    /**
     * {@code 2005 The relation to this user already exists!}
     */
    USER_RELATION_ALREADY_EXISTS(PLYStatusCodes.USER_RELATION_ALREADY_EXISTS_CODE,
            PLYStatusCodes.USER_RELATION_ALREADY_EXISTS_MSG),

    /***********************************************************************
     ********************** 3000 - PRODUCT ERROR CODES *********************
     ***********************************************************************/

    /**
     * {@code 3001 Couldn't create the product because a duplicate has been found.}
     * .
     */
    PRODUCT_NOT_INSERTED_DUPLICATE_FOUND(PLYStatusCodes.PRODUCT_NOT_INSERTED_DUPLICATE_FOUND_CODE,
            PLYStatusCodes.PRODUCT_NOT_INSERTED_DUPLICATE_FOUND_MSG),

    PRODUCT_GTIN_NOT_VALID(PLYStatusCodes.PRODUCT_GTIN_NOT_VALID_CODE,
            PLYStatusCodes.PRODUCT_GTIN_NOT_VALID_MSG),

    PRODUCT_CATEGORY_NOT_VALID(PLYStatusCodes.PRODUCT_CATEGORY_NOT_VALID_CODE,
            PLYStatusCodes.PRODUCT_CATEGORY_NOT_VALID_MSG),

    PRODUCT_NUTRITION_KEY_INVALID(PLYStatusCodes.PRODUCT_NUTRITION_KEY_INVALID_CODE,
            PLYStatusCodes.PRODUCT_NUTRITION_KEY_INVALID_MSG),

    PRODUCT_CHARACTERISTICS_KEY_INVALID(PLYStatusCodes.PRODUCT_CHARACTERISTICS_KEY_INVALID_CODE,
            PLYStatusCodes.PRODUCT_CHARACTERISTICS_KEY_INVALID_MSG),

    /***********************************************************************
     *********************** 4001 - REVIEW ERROR CODES *********************
     ***********************************************************************/

    /**
     * {@code 4001 Couldn't create the review because a duplicate has been found.}
     * .
     */
    REVIEW_NOT_INSERTED_DUPLICATE_FOUND(PLYStatusCodes.REVIEW_NOT_INSERTED_DUPLICATE_FOUND_CODE,
            PLYStatusCodes.REVIEW_NOT_INSERTED_DUPLICATE_FOUND_MSG),

    /***********************************************************************
     ******************** 5000 - APPLICATION ERROR CODES *******************
     ***********************************************************************/

    /**
     * {@code 5001 You have reached the application limit. No more applications allowed!}
     * .
     */
    APPLICATION_LIMIT_REACHED(PLYStatusCodes.APPLICATION_LIMIT_REACHED_CODE,
            PLYStatusCodes.APPLICATION_LIMIT_REACHED_MSG),

    /**
     * {@code 5002 Missing or wrong application type!}
     */
    APPLICATION_MISSING_OR_INVALID_TYPE(PLYStatusCodes.APPLICATION_MISSING_OR_INVALID_TYPE_CODE,
            PLYStatusCodes.APPLICATION_MISSING_OR_INVALID_TYPE_MSG),

    /**
     * {@code 5003 Found an application with the same name or url!}
     */
    APPLICATION_ALREADY_EXISTS(PLYStatusCodes.APPLICATION_ALREADY_EXISTS_CODE,
            PLYStatusCodes.APPLICATION_ALREADY_EXISTS_MSG),

    /***********************************************************************
     ******************* 6000 - PRODUCT LIST ERROR CODES *******************
     ***********************************************************************/

    /**
     * {@code 6001 The product list type is invalid! }.
     */
    PRODUCT_LIST_TYPE_INVALID(PLYStatusCodes.PRODUCT_LIST_TYPE_INVALID_CODE,
            PLYStatusCodes.PRODUCT_LIST_TYPE_INVALID_MSG),

    /**
     * {@code 6002 The product list sharing type is invalid! }.
     */
    PRODUCT_LIST_SHARING_TYPE_INVALID(PLYStatusCodes.PRODUCT_LIST_SHARING_TYPE_INVALID_CODE,
            PLYStatusCodes.PRODUCT_LIST_SHARING_TYPE_INVALID_MSG),

    /***********************************************************************
     ******************* 9000 - AUTHENTICATION ERROR CODES *****************
     ***********************************************************************/

    /**
     * {@code 9001 API-KEY not found in header!}
     */
    API_KEY_NOT_FOUND(PLYStatusCodes.API_KEY_NOT_FOUND_CODE, PLYStatusCodes.API_KEY_NOT_FOUND_MSG),

    /**
     * {@code 9002 API-KEY invalid, no Application with this key!}
     */
    API_KEY_INVALID(PLYStatusCodes.API_KEY_INVALID_CODE, PLYStatusCodes.API_KEY_INVALID_MSG),

    /**
     * {@code 9003 API-KEY invalid, this IP is not allowed!}
     */
    API_KEY_INVALID_IP(PLYStatusCodes.API_KEY_INVALID_IP_CODE, PLYStatusCodes.API_KEY_INVALID_IP_MSG),

    /***********************************************************************
     ***************************** HTTP CODES ******************************
     ***********************************************************************/

    /**
     * {@code 400 Bad Request.}
     */
    HTTP_STATUS_BAD_REQUEST(PLYStatusCodes.HTTP_STATUS_BAD_REQUEST_CODE,
            PLYStatusCodes.HTTP_STATUS_BAD_REQUEST_MSG),

    /**
     * {@code 403 Forbidden.}
     */
    HTTP_STATUS_FORBIDDEN(PLYStatusCodes.HTTP_STATUS_FORBIDDEN_CODE, PLYStatusCodes.HTTP_STATUS_FORBIDDEN_MSG),

    /**
     * {@code 404 Not found.}
     */
    HTTP_STATUS_NOT_FOUND(PLYStatusCodes.HTTP_STATUS_NOT_FOUND_CODE, PLYStatusCodes.HTTP_STATUS_NOT_FOUND_MSG),

    /**
     * {@code 409 Conflict.}
     */
    HTTP_STATUS_CONFLICT(PLYStatusCodes.HTTP_STATUS_CONFLICT_CODE, PLYStatusCodes.HTTP_STATUS_CONFLICT_MSG);

    public final static int OBJECT_NOT_UPDATED_NO_CHANGES_CODE = 1001;
    public final static String OBJECT_NOT_UPDATED_NO_CHANGES_MSG = "The object was not updated because no changes have been found.";

    public final static int ROLLBACK_EXCEPTION_CODE = 1002;
    public final static String ROLLBACK_EXCEPTION_MSG = "Database exception occurred and the rollback couldn'T be performed.";

    public final static int REQUIRED_PARAMETER_NOT_FOUND_CODE = 1003;
    public final static String REQUIRED_PARAMETER_NOT_FOUND_MSG = "A required parameter has not been found.";

    public final static int SENDING_EMAIL_FAILED_CODE = 1006;
    public final static String SENDING_EMAIL_FAILED_MSG = "Sending email failed, please try again later or contact support@productlayer.com";

    public final static int FROM_DATE_AFTER_TO_DATE_CODE = 1007;
    public final static String FROM_DATE_AFTER_TO_DATE_MSG = "You have mixed up the dates! From date can't be after to date.";

    public final static int COULD_NOT_PARSE_JSON_CODE = 1008;
    public final static String COULD_NOT_PARSE_JSON_MSG = "The JSON object you entered couldn't be parsed!";

    public final static int JSON_PROPERTY_NOT_FOUND_CODE = 1009;
    public final static String JSON_PROPERTY_NOT_FOUND_MSG = "A JSON property couldn't be found! Have you mistyped a key?";

    public final static int INVALID_LANGUAGE_CODE = 1010;
    public final static String INVALID_LANGUAGE_MSG = "The language string was in the wrong format and couldn't be parsed! Format: en or de";

    public final static int VOTING_FAILED_CODE = 1011;
    public final static String VOTING_FAILED_MSG = "Voting failed!";

    public final static int NOT_INSERTED_DUPLICATE_FOUND_CODE = 1012;
    public final static String NOT_INSERTED_DUPLICATE_FOUND_MSG = "Insert into database failed, duplicate has been found! Perhaps you should try update instead of insert.";

    /***********************************************************************
     *********************** 2000 - USER ERROR CODES ***********************
     ***********************************************************************/
    public final static int REGISTRATION_FAILED_EMAIL_DUPLICATE_CODE = 2001;
    public final static String REGISTRATION_FAILED_EMAIL_DUPLICATE_MSG = "A user with this email is already registered.";

    public final static int REGISTRATION_FAILED_NICKNAME_DUPLICATE_CODE = 2002;
    public final static String REGISTRATION_FAILED_NICKNAME_DUPLICATE_MSG = "A user with this nickname is already registered.";

    public final static int WRONG_PASSWORD_PROVIDED_CODE = 2003;
    public final static String WRONG_PASSWORD_PROVIDED_MSG = "You have entered the wrong password!";

    public final static int PASSWORDS_ARE_EQUALS_CODE = 2004;
    public final static String PASSWORDS_ARE_EQUALS_MSG = "You cannot update the password with the same one!";

    public final static int USER_RELATION_ALREADY_EXISTS_CODE = 2005;
    public final static String USER_RELATION_ALREADY_EXISTS_MSG = "The relation to this user already exists!";

    /***********************************************************************
     ********************** 3000 - PRODUCT ERROR CODES *********************
     ***********************************************************************/
    public final static int PRODUCT_NOT_INSERTED_DUPLICATE_FOUND_CODE = 3001;
    public final static String PRODUCT_NOT_INSERTED_DUPLICATE_FOUND_MSG = "Couldn't create the product because a duplicate has been found! Same gtin and language. Perhaps you wanted to update the existing product.";

    public final static int PRODUCT_GTIN_NOT_VALID_CODE = 3002;
    public final static String PRODUCT_GTIN_NOT_VALID_MSG = "The gtin you entered was not valid. The gtin can only contain numbers and the maximum length is 14 digits.";

    public final static int PRODUCT_CATEGORY_NOT_VALID_CODE = 3003;
    public final static String PRODUCT_CATEGORY_NOT_VALID_MSG = "The category is not valid.";

    public final static int PRODUCT_NUTRITION_KEY_INVALID_CODE = 3004;
    public final static String PRODUCT_NUTRITION_KEY_INVALID_MSG = "Invalid nutrition key. All nutrition keys must start with pl-prod-nutr-";

    public final static int PRODUCT_CHARACTERISTICS_KEY_INVALID_CODE = 3005;
    public final static String PRODUCT_CHARACTERISTICS_KEY_INVALID_MSG = "Invalid characteristic key. All characteristic keys must start with pl-prod-char-";

    /***********************************************************************
     *********************** 4001 - REVIEW ERROR CODES *********************
     ***********************************************************************/
    public final static int REVIEW_NOT_INSERTED_DUPLICATE_FOUND_CODE = 4001;
    public final static String REVIEW_NOT_INSERTED_DUPLICATE_FOUND_MSG = "Couldn't create the review because a duplicate has been found! A user can only create one review per product. Perhaps you wanted to update the existing review.";

    /***********************************************************************
     ******************** 5000 - APPLICATION ERROR CODES *******************
     ***********************************************************************/
    public final static int APPLICATION_LIMIT_REACHED_CODE = 5001;
    public final static String APPLICATION_LIMIT_REACHED_MSG = "You have reached the application limit. No more applications allowed!";

    public final static int APPLICATION_MISSING_OR_INVALID_TYPE_CODE = 5002;
    public final static String APPLICATION_MISSING_OR_INVALID_TYPE_MSG = "Missing or wrong application type!";

    public final static int APPLICATION_ALREADY_EXISTS_CODE = 5003;
    public final static String APPLICATION_ALREADY_EXISTS_MSG = "Found an application with the same name or url!";

    /***********************************************************************
     ******************** 6000 - PRODUCT LIST ERROR CODES ******************
     ***********************************************************************/

    public final static int PRODUCT_LIST_TYPE_INVALID_CODE = 6001;
    public final static String PRODUCT_LIST_TYPE_INVALID_MSG = "The product list type is invalid!";

    public final static int PRODUCT_LIST_SHARING_TYPE_INVALID_CODE = 6002;
    public final static String PRODUCT_LIST_SHARING_TYPE_INVALID_MSG = "The product list sharing type is invalid!";

    /***********************************************************************
     ******************* 9000 - AUTHENTICATION ERROR CODES *****************
     ***********************************************************************/

    public final static int API_KEY_NOT_FOUND_CODE = 9001;
    public final static String API_KEY_NOT_FOUND_MSG = "API-KEY not found in header!";

    public final static int API_KEY_INVALID_CODE = 9002;
    public final static String API_KEY_INVALID_MSG = "API-KEY invalid, no Application with this key!";

    public final static int API_KEY_INVALID_IP_CODE = 9003;
    public final static String API_KEY_INVALID_IP_MSG = "API-KEY invalid, this IP is not allowed!";

    /***********************************************************************
     ***************************** HTTP CODES ******************************
     ***********************************************************************/
    public final static int HTTP_STATUS_BAD_REQUEST_CODE = 400;
    public final static String HTTP_STATUS_BAD_REQUEST_MSG = "Bad Request";

    public final static int HTTP_STATUS_FORBIDDEN_CODE = 403;
    public final static String HTTP_STATUS_FORBIDDEN_MSG = "Forbidden";

    public final static int HTTP_STATUS_NOT_FOUND_CODE = 404;
    public final static String HTTP_STATUS_NOT_FOUND_MSG = "Not Found";

    public final static int HTTP_STATUS_CONFLICT_CODE = 409;
    public final static String HTTP_STATUS_CONFLICT_MSG = "Unknown conflict occurred. Exception Stacktrace provided. Please contact support@productlayer.com";

    private final int value;

    private final String reasonPhrase;

    private PLYStatusCodes(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * @return the integer value of this status code
     */
    public int value() {
        return this.value;
    }

    /**
     * @return the reason phrase of this status code
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * @return a string representation of this status code
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Returns the enum constant of this type with the specified numeric value.
     * 
     * @param statusCode
     *            the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException
     *             if this enum has no constant for the specified numeric value
     */
    public static PLYStatusCodes valueOf(int statusCode) {
        for (PLYStatusCodes status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

}
