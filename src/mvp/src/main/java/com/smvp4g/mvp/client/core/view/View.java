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

package com.smvp4g.mvp.client.core.view;

import com.google.gwt.user.client.ui.Widget;
import com.smvp4g.mvp.client.core.i18n.Constants;
import com.smvp4g.mvp.client.core.security.HasRole;

import java.util.List;

/**
 * The Class View.
 *
 * @author dungvn3000
 * @since 3/13/11, 6:54 PM
 */
public interface View<C extends Constants> {

    /**
     * Bind with a presenter.
     */
    void bind();

    /**
     * Show a View.
     *
     * @return false if the view didn't shown.
     */
    boolean show();


    /**
     * Show a view
     *
     * @param replace exits view with new view.
     * @return
     */
    boolean show(boolean replace);

    /**
     * Create a View.
     */
    void createView();

    /**
     * Get dom id of div element where the view will render to.
     *
     * @return parent dom id of view.
     */
    String getParentDomId();

    /**
     * Set dom id of div element where the view will render to.
     *
     * @param parentDomId
     */
    void setParentDomId(String parentDomId);

    /**
     * Get html id in of View.
     * <p/>
     * <b>Note:</b> That id must be unique.
     *
     * @return html dom id of view.
     */
    String getDomId();

    /**
     * @return all role of this View..
     */
    List<HasRole> getRoles();

    /**
     * Set Widget for View.
     *
     * @param w Widget.
     */
    void setWidget(Widget w);

    /**
     * Get the i18n constant of the view.
     *
     * @return
     */
    C getConstant();

    /**
     * Check this view is a security view or not.
     *
     * @return true if it is a security view.
     */
    boolean isSecurityView();
}
