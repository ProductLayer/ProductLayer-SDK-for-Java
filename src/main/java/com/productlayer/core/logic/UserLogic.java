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

import com.productlayer.core.beans.User;
import com.productlayer.core.beans.UserRole;

/**
 * Does checks on User beans and their roles.
 */
public class UserLogic {

    /**
     * Checks whether the user is an administrator.
     * 
     * @param user
     *            The user to check
     * @return true if the user is an administrator, false else
     */
    public static boolean isAdmin(User user) {
        return hasRole(user, UserRole.ADMIN);
    }

    /**
     * Checks whether the user is a developer.
     * 
     * @param user
     *            The user to check
     * @return true if the user is a developer, false else
     */
    public static boolean isDeveloper(User user) {
        return hasRole(user, UserRole.DEVELOPER);
    }

    /**
     * Checks whether the user is a user.
     * 
     * @param user
     *            The user to check
     * @return true if the user is a user, false else
     */
    public static boolean isUser(User user) {
        return hasRole(user, UserRole.USER);
    }

    /**
     * Checks whether the user has the specified role.
     * 
     * @param user
     *            The user to check
     * @param userRole
     *            The role to check for
     * @return true if the user has role {@code userRole}, false else
     */
    public static boolean hasRole(User user, UserRole userRole) {
        if (user == null)
            return false;

        for (String role : user.getRoles()) {
            if (role.equals(userRole.toString())) {
                return true;
            }
        }

        return false;
    }

}
