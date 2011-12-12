/*
 * Copyright (C) 2009 - 2012 SMVP.NET
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

package net.smvp.mvp.client.core.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;

/**
 * GWTUtils is a set of helper classes to make it easier to work with GWT
 *
 * @author Nazmul Idris
 * @version 1.0
 * @since Jan 9, 2008, 1:04:17 PM
 */
@SuppressWarnings({"ALL"})
public final class GWTUtils {

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// native js helpers
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public static native String getParamString() /*-{
        return $wnd.location.search;
    }-*/;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static HashMap parseParamString(String string) {
        String[] ray = string.substring(1, string.length()).split("&");
        HashMap map = new HashMap();

        for (int i = 0; i < ray.length; i++) {
            GWT.log("ray[" + i + "]=" + ray[i], null);
            String[] substrRay = ray[i].split("=");
            map.put(substrRay[0], substrRay[1]);
        }

        return map;
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// DOM and RootPanel helpers
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    /**
     * This method removes the element identified by s from the browser's DOM
     */
    public static void removeElementFromDOM(String s) {
        notempty(s);
        com.google.gwt.user.client.Element loading = DOM.getElementById(s);
        DOM.removeChild(RootPanel.getBodyElement(), loading);
    }

    public static void addWidgetToDOM(String s, Widget widget) {
        notempty(s);
        notnull(widget);
        RootPanel.get(s).add(widget);
    }

    public static void removeAllWidgetFromDOM(String s) {
        notempty(s);
        RootPanel.get(s).clear();
    }

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// assertions, validations
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    /**
     * asserts that the given string is not null or empty
     */
    private static void notempty(String s) throws IllegalArgumentException {
        if (s == null) throw new IllegalArgumentException("String is null");

        if (s.trim().equals("")) throw new IllegalArgumentException("String is empty");
    }

    /**
     * asserts that the given widget is not null
     */
    private static void notnull(Widget widget) throws IllegalArgumentException {
        if (widget == null) throw new IllegalArgumentException("Widget can not be null");
    }

}//end class GWTUtils
