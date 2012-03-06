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

import com.extjs.gxt.ui.client.util.Format;

/**
 * The Class StringUtils like org.apache.commons.lang.StringUtils.
 * Rewrite because can't reuse Apache Common Lang in GWT.
 *
 * @author dungvn3000
 * @since 5/4/11, 4:47 PM
 */
public final class StringUtils extends Format {

    /**
     * Path Character of url.
     */
    public static final Character PATH_SPLIT_CHAR = '/';

    /**
     * {@see org.apache.commons.lang.StringUtils.EMPTY}.
     */
    public static final String EMPTY = "";

    private StringUtils() {
        //hide it.
    }

    /**
     * @param s check string
     * @return true if this string is empty or null.
     */
    public static boolean isEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }

    /**
     * @param s check string
     * @return true if this string is not empty.
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Check String is blank or not.
     *
     * @param s
     * @return true if this string is null or blank
     */
    public static boolean isBlank(String s) {
        return s == null || isEmpty(s.trim());
    }


    /**
     * Check String is not blank or not.
     *
     * @param s string to check
     * @return false if this string is null or blank
     */
    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * Get last character of this string.
     *
     * @param s String to get last character.
     * @return null if string input is null.
     */
    public static Character getLastChar(String s) {
        return s != null ? s.charAt(s.length() - 1) : null;
    }

    /**
     * Concat urlPath with path.
     * <pre>
     *     StringUtils.concatUrlPath("http://example.com/", "abc") = "http://http://example.com/abc"
     *     StringUtils.concatUrlPath("http://example.com", "abc") = "http://http://example.com/abc"
     * </pre>
     *
     * @param urlPath
     * @param path
     * @return
     */
    public static String concatUrlPath(String urlPath, String path) {
        StringBuilder urlBuilder = new StringBuilder(urlPath);
        if (PATH_SPLIT_CHAR.equals(getLastChar(urlPath))) {
            urlBuilder.append(path);
        } else {
            urlBuilder.append(PATH_SPLIT_CHAR).append(path);
        }
        return urlBuilder.toString();
    }

    /**
     * Get default history name for a class.
     * <br/>Example: <br/>"TestPlace" will be "test".
     * <br/>"TestModule" will be "test".
     * <br/>"TestWordModule" will be "testWord".
     * <br/>"Word" will be null.
     *
     * @param word
     * @return
     */
    public static String getHistoryName(String word) {
        if (isNotEmpty(word)) {
            word = word.trim();
            for (int i = word.length(); i > 1; i--) {
                if (Character.isUpperCase(word.substring(i - 1, i).toCharArray()[0])) {
                    return word.substring(0, 1).toLowerCase() + word.substring(1, i - 1);
                }
            }
        }
        return null;
    }

    /**
     * Convert vietnamese unicode string to ascii string.
     *
     * @param st vietnamese unicode string.
     * @return ascii string.
     */
    public static String convertNonAscii(String st) {
        st = st.replaceAll("[eéèẽẻẹ]", "e");
        st = st.replaceAll("[êếềễểệ]", "e");

        st = st.replaceAll("[uúùũủụ]", "u");
        st = st.replaceAll("[ưứừữửự]", "u");

        st = st.replaceAll("[iíìĩỉị]", "i");

        st = st.replaceAll("[aáàãảạ]", "a");
        st = st.replaceAll("[ăắằẵẳặ]", "a");
        st = st.replaceAll("[âấầẫẩậ]", "a");

        st = st.replaceAll("[oóòõỏọ]", "o");
        st = st.replaceAll("[ơớờỡởợ]", "o");
        st = st.replaceAll("[ôốồỗổộ]", "o");

        st = st.replaceAll("[EÉÈẼẺẸ]", "E");
        st = st.replaceAll("[ÊẾỀỄỂỆ]", "E");

        st = st.replaceAll("[UÚÙŨỦỤ]", "U");
        st = st.replaceAll("[ƯỨỪỮỬỰ]", "U");

        st = st.replaceAll("[IÍÌĨỈỊ]", "I");

        st = st.replaceAll("[AÁÀÃẢẠ]", "A");
        st = st.replaceAll("[ĂẮẰẴẲẶ]", "A");
        st = st.replaceAll("[ÂẤẦẪẨẬ]", "A");

        st = st.replaceAll("[OÓÒÕỎỌ]", "O");
        st = st.replaceAll("[ÔỐỒỖỔỘ]", "O");
        st = st.replaceAll("[ƠỚỜỠỞỢ]", "O");

        st = st.replaceAll("[đ]", "d");
        st = st.replaceAll("[Đ]", "D");
        return st;
    }

    /**
     * Rewrite method indexOf(String) in java.lang.String.class
     *
     * @param st    String
     * @param subSt Sub string
     * @return -1 if not found
     */
    public static int indexOf(String st, String subSt) {
        if (st != null) {
            String[] results = st.split("");
            for (int i = 0; i <= st.length(); i++) {
                String s = results[i];
                if (s.equals(subSt)) {
                    if (i > 0) {
                        return i - 1;
                    }
                    return 0;
                }
            }
        }
        return -1;
    }
}
