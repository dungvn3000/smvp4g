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

package net.smvp.mvp.client.core.view.templateview;

import com.google.gwt.user.client.ui.HTML;
import net.smvp.mvp.client.core.view.AbstractView;

/**
 * The Class AccessDenyView.
 *
 * @author Nguyen Duc Dung
 * @since Aug 15, 2010, 7:07:15 PM
 */
public class AccessDenyView extends AbstractView {

    public AccessDenyView() {
        initializeView();
    }

    /**
     * Inits the panel.
     */
    @Override
    public void initializeView() {
        HTML textWarning = new HTML(
                "<b><h2>Sorry you can't access this page</h2></b>");
        setWidget(textWarning);
    }
}
