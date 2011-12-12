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

import com.allen_sauer.gwt.log.client.Log;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.UIObject;

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
        if (widget instanceof Text) {
            //for ext gwt widget.
            ((Text) widget).setText(text);
        } else if (widget instanceof Button) {
            ((Button) widget).setText(text);
        } else if (widget instanceof Field) {
            ((Field) widget).setFieldLabel(text);
        } else if (widget instanceof ContentPanel) {
            ((ContentPanel) widget).setHeading(text);
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
        if (widget instanceof Field) {
            ((Field) widget).setEmptyText(emptyText);
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

}
