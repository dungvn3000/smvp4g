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

package com.smvp4g.mvp.client.widget;

/**
 * The Extension of Ext TextField.
 *
 * @author Nguyen Duc Dung
 * @since 8/21/11, 2:20 AM
 */
public class TextField extends com.sencha.gxt.widget.core.client.form.TextField {

    private static final String EMPTY_TEXT_ATTR = "placeholder";

    private String emptyText;
    
    @Override
    public void setEmptyText(String emptyText) {
        //Fix using placeholder attr for empty text.
        if (isRendered() && getEmptyText() != null) {
            this.emptyText = emptyText;
            getInputEl().setAttribute(EMPTY_TEXT_ATTR, getEmptyText());
        }
    }

    @Override
    public String getEmptyText() {
        return this.emptyText;
    }
}
