/*
 * Copyright (C) 2009 - 2012 SMVP4G.COM
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.smvp4g.mvp.client.core.utils;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.Cookies;
import com.smvp4g.mvp.client.core.security.HasRole;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * The Class LoginUtils.
 *
 * @author Nguyen Duc Dung
 * @since Aug 15, 2010, 6:54:12 PM
 */

public final class LoginUtils {

    public static final String SESSION_ID = "sessionId";

    public static final String USER_NAME = "userName";

    public static final String ROLE = "Role";

    public static final long DURATION = 1000 * 60 * 60 * 24 * 14; //duration remembering login. 2 weeks in this example.

    private LoginUtils() {
        //Hide it.
    }

    /**
     * Save user login information to cookies.
     *
     * @param userName must be not null
     * @param userRole must be not null
     */
    public static void login(String userName, String userRole) {
        assert userName != null;
        assert userRole != null;
        Date expires = new Date(System.currentTimeMillis() + DURATION);
        Cookies.setCookie(SESSION_ID, userName);
        Cookies.setCookie(USER_NAME, userName);
        Cookies.setCookie(ROLE, userRole);
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public static boolean getLogin() {
        try {
            String sessionId = Cookies.getCookie("sessionId");
            if (sessionId == null || sessionId.equals("")
                    || sessionId.equals("undefined")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public static String getUserName() {
        if (!getLogin()) {
            return "";
        }
        String userName = Cookies.getCookie(USER_NAME);
        if (userName == null) {
            userName = "";
        }
        return userName;
    }

    /**
     * Gets the role.
     *
     * @return null if user not login.
     */
    public static String getRole() {
        if (getLogin()) {
            return Cookies.getCookie(ROLE);
        }
        return null;
    }

    /**
     * Check permission of user on panel.
     *
     * @param roles    the list role of Panel
     * @param userRole the user role
     * @return true, if it's successful
     */
    public static boolean checkPermission(List<HasRole> roles, String userRole) {
        return checkPermission(roles.toArray(new HasRole[roles.size()]), userRole);
    }

    /**
     * Check permission of user on panel.
     *
     * @param roles    the list role of Panel
     * @param userRole the user role
     * @return true, if it's successful
     */
    public static boolean checkPermission(HasRole[] roles, String userRole) {
        if (roles == null || roles.length == 0) {
            //if list of roles is empty or null, that mean this panel don't need login to show.
            return true;
        }
        boolean hasRole = false;
        for (HasRole role : roles) {
            if (role.getRole().equals(userRole)) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

    /**
     * Log out.
     */
    public static void logOut() {
        Cookies.removeCookie(SESSION_ID);
        Cookies.removeCookie(USER_NAME);
        Cookies.removeCookie(ROLE);
    }

    /**
     * Use for hash password of user.
     *
     * @param input
     * @return md5 string
     */
    public static String md5hash(String input) {
        byte[] inputBytes = input.getBytes();
        String output = null;

        try {
            MessageDigest algorithm =
                    MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(inputBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                hexString.append(Integer.toHexString(
                        (aMessageDigest & 0xFF) | 0x100)
                        .toLowerCase().substring(1, 3));
            }
            output = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            //ignore
            Log.debug(e.getMessage(), e);
        }
        return output;
    }
}
