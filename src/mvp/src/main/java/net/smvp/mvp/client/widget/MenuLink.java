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

package net.smvp.mvp.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Hyperlink;
import net.smvp.mvp.client.core.history.HistoryPlaceGinInjector;
import net.smvp.mvp.client.core.utils.StringUtils;

/**
 * This widget using like @Hyperlink. But it useful for Menu.
 *
 * @author Nguyen Duc Dung
 * @since 8/17/11, 8:00 PM
 */
public class MenuLink extends Hyperlink {

    /**
     * We will add this style name when a menu link was  active.
     */
    public static final String ACTIVE_MENU_CSS_STYLE = "active";

    private HistoryPlaceGinInjector historyPlaceGinInjector = GWT.create(HistoryPlaceGinInjector.class);

    private boolean isActive;

    /**
     * Creates a hyperlink with its text and target history token specified.
     *
     * @param targetHistoryToken the history token to which it will link.
     */
    public MenuLink(String targetHistoryToken) {
        super(StringUtils.EMPTY, targetHistoryToken);
        historyPlaceGinInjector.getHistoryPlace().addMenuLink(this);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
