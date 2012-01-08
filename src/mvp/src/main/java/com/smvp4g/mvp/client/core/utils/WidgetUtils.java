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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.WidgetComponent;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

/**
 * The Class WidgetUtils.
 *
 * @author dungvn3000
 * @since 6/26/11, 11:21 AM
 */
public final class WidgetUtils {

    private WidgetUtils() {
        //Hide it.
    }

    /**
     * Using for set text for GWT and GXT Widget.
     *
     * @param widget
     * @param text
     */
    public static void setText(Object widget, String text) {
        if (widget instanceof ContentPanel) {
            ((ContentPanel) widget).setHeadingText(text);
        } else if (widget instanceof Image) {
            ((Image) widget).setTitle(text);
        } else if (widget instanceof HasText) {
            //for gwt widget.
            ((HasText) widget).setText(text);
        } else if (widget != null) {
            Log.error("Can't set text for class " + widget.getClass());
        }
    }

    /**
     * Set empty text for GWT and GXT Widget.
     *
     * @param widget
     * @param emptyText
     */
    public static void setEmptyText(Object widget, String emptyText) {
        if (widget instanceof ValueBaseField) {
            ((ValueBaseField) widget).setEmptyText(emptyText);
        } else if (widget != null) {
            Log.error("Can't set empty text for class " + widget.getClass());
        }
    }

    /**
     * Set visible for GWT & GXT Widget.
     *
     * @param widget
     * @param visible
     */
    public static void setVisible(Object widget, boolean visible) {
        if (widget instanceof UIObject) {
            ((UIObject) widget).setVisible(visible);
        } else if (widget != null) {
            Log.error("Can't set visible for cass " + widget.getClass());
        }
    }

    /**
     * Remove widget form parent.
     *
     * @param widget
     */
    public static void removeFormParent(Object widget) {
        if (widget instanceof Widget) {
            Widget parent = ((Widget) widget).getParent();
            if (parent != null) {
                if (parent instanceof WidgetComponent) {
                    //It was wrapped by WidgetComponent.
                    parent = parent.getParent();
                }
                if (parent instanceof Container) {
                    ((Container) parent).remove((Widget)widget);
                } else {
                    ((Widget) widget).removeFromParent();
                }
            }
        } else if (widget != null) {
            Log.error("Can't remove cass " + widget.getClass());
        }
    }
}
